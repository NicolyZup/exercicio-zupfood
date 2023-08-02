package view;

import service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuView {
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();

    public static void menuInicial(){
        System.out.println("-------------------------------------------");
        System.out.println("Selecione uma opção:");
        System.out.println("(1) Comprar Lanche");
        System.out.println("(2) Comprar Bebida");
        System.out.println("-------------------------------------------");

        int opcao = opcaoEscolhida();
        switch (opcao){
            case 1:
                mostrarLanches();
                break;
            case 2:
                mostrarBebidas();
                break;
            default:
                System.out.println("Opção inválida, tente novamente.");
                menuInicial();
                break;
        }
    }


    public static int opcaoEscolhida() {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        } catch (NumberFormatException e) {
            System.out.println("Formato inválido, para escolher o item, você deve informar o número dele.");
            scanner.nextLine();
            menuInicial();
        } catch (InputMismatchException e) {
            System.out.println("Formato inválido, para escolher o item, você deve informar o número dele.");
            scanner.nextLine();
            menuInicial();
        }
        return 1;
    }

    private static void mostrarLanches(){
        System.out.println("-----------Lanches-----------");
        userService.consultarLanches();
        System.out.println("Informe qual lanche deseja adicionar ao carrinho:");
        int opcao = opcaoEscolhida();
        switch (opcao){
            case 1:
                System.out.println("Qual será a quantidade?");
                int quantidadeLanche1 = scanner.nextInt();
                scanner.nextLine();
                userService.adicionarLanche(1,quantidadeLanche1);
                mostrarCarrinho();
                break;
            case 2:
                System.out.println("Qual será a quantidade?");
                int quantidadeLanche2 = scanner.nextInt();
                scanner.nextLine();
                userService.adicionarLanche(2,quantidadeLanche2);
                mostrarCarrinho();
                break;
            default:
                System.out.println("Opção inválida, tente novamente.");
                mostrarLanches();
                break;
        }
    }

    private static void mostrarBebidas(){
        System.out.println("-----------Bebidas-----------");
        userService.consultarBebidas();
        System.out.println("Informe qual lanche deseja adicionar ao carrinho:");
        int opcao = opcaoEscolhida();
        switch (opcao){
            case 1:
                System.out.println("Qual será a quantidade?");
                int quantidadeBebida1 = scanner.nextInt();
                scanner.nextLine();
                userService.adicionarBebida(1,quantidadeBebida1);
                mostrarCarrinho();
                break;
            case 2:
                System.out.println("Qual será a quantidade?");
                int quantidadeBebida2 = scanner.nextInt();
                scanner.nextLine();
                userService.adicionarBebida(2,quantidadeBebida2);
                mostrarCarrinho();
                break;
            default:
                System.out.println("Opção inválida, tente novamente.");
                mostrarBebidas();
                break;
        }
    }
    private static void mostrarCarrinho(){
        System.out.println("==================CARRINHO==================");
        userService.consultarCarrinho();
        System.out.println("TOTAL: R$"+userService.valorTotal());
        System.out.println("============================================");
        System.out.println("Deseja realizar mais pedidos ou pagar a conta?");
        System.out.println("1) Realizar mais pedidos");
        System.out.println("2) Pagar a conta");
        int opcao = opcaoEscolhida();
        switch (opcao){
            case 1:
                menuInicial();
                break;
            case 2:
                System.out.println("Volte sempre!");
                break;
            default:
                System.out.println("Opção inválida, tente novamente.");
                mostrarCarrinho();
                break;
        }
    }

}

