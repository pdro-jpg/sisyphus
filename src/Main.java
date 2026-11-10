import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Main{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String answer;
        String addAnother;

        ArrayList<String> hobbies = new ArrayList<>(); //criacao do ArrayList
        hobbies.add("Ler"); //adiciona string no array
        hobbies.add("Jogar");
        hobbies.add("Ouvir musica");
        hobbies.add("Caminhar");

        do {
            System.out.println("Digite um hobby para adicionar: ");
            String newHobby = scanner.nextLine().trim(); //newHobby recebe a entrada do usuario / trim remove espacos no comeco e fim do texto

            if (newHobby.isEmpty()) { //verifica se a entrada esta vazia
                System.out.println("O hobby nao pode estar vazio");
            } else { //se nao estiver vazia, aceita o cadastro do hobby
                hobbies.add(newHobby);
                System.out.println("Hobby adicionado: " + newHobby);
            }
            System.out.println("Deseja cadastrar outro hobby? (s/n)");
            addAnother = scanner.nextLine();
        } while (addAnother.equalsIgnoreCase("s"));

        System.out.println("Hobbies cadastrados:");


        for (String hobby : hobbies) { // laco que percorre todos os elementos da lista
            // Para cada texto da lista hobbies, guarde o elemento atual na variável hobby e execute o bloco
            System.out.println("- " + hobby);
        }

        System.out.println("Bem vindo ao Sisifo!");

        int drawCount = 0; //conta quantos sorteios foram feitos durante a execucao

        do{
            System.out.println("Pressione enter para sortear uma atividade.");
            scanner.nextLine(); //aguarda a entrada do usuario para continuar a execucao

            int selectedIndex = random.nextInt(hobbies.size()); //sorteia os hobbies dentro do ArrayList hobbies
            drawCount++; //adiciona +1 na contagem de sorteios

            System.out.println("Indice sorteado: "+ selectedIndex);
            System.out.println("Atividade sorteada: "+ hobbies.get(selectedIndex));

            System.out.println("Deseja sortear novamente? (s/n)");
            answer= scanner.nextLine();

        }while (answer.equalsIgnoreCase("s")); //ignora a diferenca entre maiuscula e minuscula

        System.out.println("Total de sorteios: "+ drawCount);
        System.out.println("Aproveite seu tempo livre!");

        scanner.close();

    }
}