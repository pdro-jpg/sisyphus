import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Gerencia os hobbies e sua persistência, sem ler entradas nem imprimir mensagens.
public class HobbyManager {
    // Os campos privados impedem que outras classes alterem a lista diretamente.
    private final List<String> hobbies = new ArrayList<>();
    private final Random random = new Random();
    private int drawCount; // Conta apenas os sorteios da execucao atual; nao e salvo no arquivo.
    // Resolve o arquivo a partir do diretorio de trabalho da aplicacao.
    private final Path file = Path.of("hobbies.txt").toAbsolutePath();

    // Carrega um hobby por linha em UTF-8 e ignora linhas vazias.
    // Falhas de leitura sao repassadas para Main, que informa o usuario.
    public HobbyManager() throws IOException {
        try {
            for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
                String hobby = line.trim();
                if (!hobby.isEmpty()) {
                    hobbies.add(hobby);
                }
            }
        } catch (java.nio.file.NoSuchFileException exception) {
            // A ausência do arquivo e esperada na primeira execucao: a lista começa vazia.
        }
    }

    // Retorna false para nomes vazios; trim remove espacos nas extremidades.
    public boolean addHobby(String name) throws IOException {
        String hobby = name.trim();
        if (hobby.isEmpty()) {
            return false;
        }
        // Salva uma copia com a alteracao antes de modificar a lista em memória.
        // Se a gravação falhar, a lista original permanece intacta.
        List<String> updatedHobbies = new ArrayList<>(hobbies);
        updatedHobbies.add(hobby);
        saveHobbies(updatedHobbies);
        hobbies.add(hobby);
        return true;
    }

    // Devolve uma copia para que o chamador nao consiga alterar a lista interna.
    public List<String> listHobbies() {
        return new ArrayList<>(hobbies);
    }

    // Recebe um indice começando em zero e rejeita posições inexistentes.
    public boolean removeHobby(int index) throws IOException {
        if (index < 0 || index >= hobbies.size()) {
            return false;
        }
        // Confirma a remocao em memória somente depois de salvar o arquivo atualizado.
        List<String> updatedHobbies = new ArrayList<>(hobbies);
        updatedHobbies.remove(index);
        saveHobbies(updatedHobbies);
        hobbies.remove(index);
        return true;
    }

    // Grava primeiro em um arquivo temporario para nao sobrescrever o original durante a escrita.
    private void saveHobbies(List<String> updatedHobbies) throws IOException {
        Path temporaryFile = Files.createTempFile(file.getParent(), "hobbies-", ".tmp");
        try {
            Files.write(temporaryFile, updatedHobbies, StandardCharsets.UTF_8);
            try {
                // A substituicao atomica troca o arquivo inteiro em uma unica operacao.
                Files.move(temporaryFile, file, StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException exception) {
                // Se o sistema nao permitir a troca atomica, usa a substituição convencional.
                Files.move(temporaryFile, file, StandardCopyOption.REPLACE_EXISTING);
            }
        } finally {
            // Remove o temporario caso ele ainda exista, inclusive apos uma falha.
            Files.deleteIfExists(temporaryFile);
        }
    }

    // Main verifica se a lista esta preenchida antes de chamar este metodo.
    public String drawHobby() {
        // nextInt gera um indice entre zero (inclusive) e o tamanho da lista (exclusive).
        int selectedIndex = random.nextInt(hobbies.size());
        drawCount++;
        return hobbies.get(selectedIndex);
    }

    // Permite consultar a contagem sem expor o campo para alteracao externa.
    public int getDrawCount() {
        return drawCount;
    }
}
