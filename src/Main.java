import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        HobbyManager hobbyManager = new HobbyManager();
        String answer;
        String addAnother;

        do {
            System.out.println("Digite um hobby para adicionar: ");
            String newHobby = scanner.nextLine().trim(); //newHobby recebe a entrada do usuario / trim remove espacos no comeco e fim do texto

            if (hobbyManager.addHobby(newHobby)) {
                System.out.println("Hobby adicionado: " + newHobby);
            } else {
                System.out.println("O hobby nao pode estar vazio");
            }
            System.out.println("Deseja cadastrar outro hobby? (s/n)");
            addAnother = scanner.nextLine();
        } while (addAnother.equalsIgnoreCase("s"));

        if (hobbyManager.listHobbies().isEmpty()) {
            System.out.println("Nenhum hobby cadastrado. Cadastre um hobby para sortear uma atividade.");
            scanner.close();
            return;
        }

        System.out.println("Hobbies cadastrados:");


        for (String hobby : hobbyManager.listHobbies()) {
            // Para cada texto da lista hobbies, guarde o elemento atual na variável hobby e execute o bloco
            System.out.println("- " + hobby);
        }

        System.out.println("Bem vindo ao Sisifo!");

        do{
            System.out.println("Pressione enter para sortear uma atividade.");
            scanner.nextLine(); //aguarda a entrada do usuario para continuar a execucao

            System.out.println("Atividade sorteada: " + hobbyManager.drawHobby());

            System.out.println("Deseja sortear novamente? (s/n)");
            answer= scanner.nextLine();

        }while (answer.equalsIgnoreCase("s")); //ignora a diferenca entre maiuscula e minuscula

        System.out.println("Total de sorteios: " + hobbyManager.getDrawCount());
        System.out.println("Aproveite seu tempo livre!");

        scanner.close();

    }
}
