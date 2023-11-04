import java.util.Scanner;

import entities.AVLTree;
import entities.List;

public class App {
    public static void main(String[] args) throws Exception {
        AVLTree productTree = new AVLTree();
        List productHistory = new List();
        switch(menu()) {
            case 1:
                System.out.println("Ingrese el nombre del producto que desea agregar.");        
                break;
            case 2:
                System.out.println("Ingrese el nombre del producto que desea eliminar.");
                break;
            case 3:
                System.out.println("Ingrese el nombre del producto que desea buscar.");
                break;
            case 4: 
                System.out.println("Inventario de productos completo:");
                break;
            default:
                System.out.println("Valor ingresado invalido");
                break;
        }
        System.out.println("Gracias por utilizar el sistema de gestion de inventario.");
}

    private static int menu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + //
                "──────────────────────────────────────────\n" + //
                "─██████████████─██████████████─██████████─\n" + //
                "─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░██─\n" + //
                "─██░░██████████─██░░██████████─████░░████─\n" + //
                "─██░░██─────────██░░██───────────██░░██───\n" + //
                "─██░░██████████─██░░██───────────██░░██───\n" + //
                "─██░░░░░░░░░░██─██░░██──██████───██░░██───\n" + //
                "─██████████░░██─██░░██──██░░██───██░░██───\n" + //
                "─────────██░░██─██░░██──██░░██───██░░██───\n" + //
                "─██████████░░██─██░░██████░░██─████░░████─\n" + //
                "─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░██─\n" + //
                "─██████████████─██████████████─██████████─\n" + //
                "──────────────────────────────────────────");

        System.out.println("Bienvenido al sistema de gestion de inventario.\n Por favor, ingrese una opcion segun desee.");
        System.out.println("Si desea agregar un producto, ingrese el numero 1.");
        System.out.println("Si desea eliminar un producto del inventario, ingrese el numero 2");
        System.out.println("Si desea buscar un producto, ingrese el numero 3");
        System.out.println("Si desea mostrar el inventario completo de productos, ingrese el numero 4");
        System.out.println("Si desea salir, ingrese el numero 5");
        int op =scanner.nextInt();
        scanner.close();
        return op;
    }
    
}