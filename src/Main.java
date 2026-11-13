import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//As regras e os dados ficam em HobbyManager.
public class Main {
    public static void main(String[] args) {
        HobbyManager hobbyManager;
        try {
            // Ao criar o gerenciador, recupera os hobbies salvos na execucao anterior.
            hobbyManager = new HobbyManager();
        } catch (IOException exception) {
            // Encerra se a leitura falhar, evitando trabalhar com uma lista incompleta.
            System.out.println("Nao foi possivel carregar hobbies.txt: " + exception.getMessage());
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bem vindo ao Sisifo!");
            boolean running = true;

            // Repete o menu ate o usuario escolher sair ou a entrada ser encerrada.
            while (running) {
                System.out.println("\n1 - Cadastrar hobby");
                System.out.println("2 - Listar hobbies");
                System.out.println("3 - Remover hobby");
                System.out.println("4 - Sortear atividade");
                System.out.println("0 - Sair");
                System.out.println("Escolha uma opcao:");

                // Evita tentar ler uma linha quando nao ha mais entrada disponivel.
                if (!scanner.hasNextLine()) {
                    break;
                }

                try {
                    // Le a opcao como texto para aceitar entradas invalidas sem interromper o programa.
                    switch (scanner.nextLine().trim()) {
                        case "1":
                            // O gerenciador valida e salva o hobby antes de confirmar o cadastro.
                            System.out.println("Digite um hobby (Enter para voltar ao menu):");
                            if (!scanner.hasNextLine()) {
                                running = false;
                                break;
                            }
                            String name = scanner.nextLine().trim();
                            if (hobbyManager.addHobby(name)) {
                                System.out.println("Hobby adicionado: " + name);
                            } else {
                                System.out.println("O hobby nao pode estar vazio. Voltando ao menu.");
                            }
                            break;
                        case "2":
                            // Formatacao da lista -> responsabilidade da interface de console.
                            printHobbies(hobbyManager.listHobbies());
                            break;
                        case "3":
                            // Guarda a lista exibida para identificar o hobby removido na mensagem.
                            List<String> hobbies = hobbyManager.listHobbies();
                            printHobbies(hobbies);
                            if (hobbies.isEmpty()) {
                                break;
                            }
                            System.out.println("Digite o numero do hobby para remover (Enter para voltar):");
                            if (!scanner.hasNextLine()) {
                                running = false;
                                break;
                            }
                            String number = scanner.nextLine().trim();
                            // Uma entrada vazia cancela a remoção e retorna ao menu.
                            if (number.isEmpty()) {
                                break;
                            }
                            try {
                                // A lista exibida começa em 1, mas os indices do Java começam em 0.
                                int index = Integer.parseInt(number) - 1;
                                if (hobbyManager.removeHobby(index)) {
                                    System.out.println("Hobby removido: " + hobbies.get(index));
                                } else {
                                    System.out.println("Numero de hobby invalido.");
                                }
                            } catch (NumberFormatException exception) {
                                // Trata textos e numeros fora do intervalo aceito por int.
                                System.out.println("Digite um numero valido.");
                            }
                            break;
                        case "4":
                            // So permite o sorteio quando existe pelo menos uma atividade.
                            if (hobbyManager.listHobbies().isEmpty()) {
                                System.out.println("Cadastre um hobby antes de sortear.");
                            } else {
                                System.out.println("Atividade sorteada: " + hobbyManager.drawHobby());
                            }
                            break;
                        case "0":
                            // Interrompe o laco e permite exibir o resumo da execucao.
                            running = false;
                            break;
                        default:
                            System.out.println("Opcao invalida. Tente novamente.");
                    }
                } catch (IOException exception) {
                    // Uma falha ao salvar mantem os dados anteriores e permite continuar no menu.
                    System.out.println("Nao foi possivel salvar os hobbies. A alteracao nao foi aplicada: "
                            + exception.getMessage());
                }
            }
        }

        System.out.println("Total de sorteios: " + hobbyManager.getDrawCount());
        System.out.println("Aproveite seu tempo livre!");
    }

    // Exibe os hobbies numerados para que o usuario possa escolher qual remover.
    private static void printHobbies(List<String> hobbies) {
        if (hobbies.isEmpty()) {
            System.out.println("Nenhum hobby cadastrado.");
            return;
        }
        System.out.println("Hobbies cadastrados:");
        for (int index = 0; index < hobbies.size(); index++) {
            System.out.println((index + 1) + " - " + hobbies.get(index));
        }
    }
}
