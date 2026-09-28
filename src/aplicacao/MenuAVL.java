package aplicacao;

import arvores.AvlInt;

import java.util.Scanner;

public class MenuAVL {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AvlInt avl = new AvlInt();

        int opcao;
        do{
            System.out.println( "0 - Sair do programa\n" +
                                "1 - Insere 1 valor na AVL\n" +
                                "2 - Apresenta pós ordem os nós da AVL apresentando também o FB do nó\n");
            opcao = sc.nextInt();
            switch (opcao){
                case 0 -> System.out.println("Encerrando o programa...");

                case 1 -> {
                    System.out.println("Digite o valor a ser inserido na AVL: ");
                    int valor = sc.nextInt();
                    avl.root = avl.inserirH(avl.root, valor);
                }

                case 2 -> {
                    System.out.println("Apresentando AVL");
                    avl.mostraFB(avl.root);
                }

                default -> System.out.println("Opção inválida!");
            }

        } while (opcao!=0);


    }
}
