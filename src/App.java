import java.util.Scanner;

import entities.AVLTree;
import entities.List;
import entities.Product;
import exceptions.ProductNotFoundException;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        AVLTree productTree = new AVLTree();
        List productHistory = new List();
        switch(menu()) {
            case 1:
                addProduct(productTree, productHistory);
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
        int op =Integer.parseInt(scanner.nextLine());
        return op;
    }

public static void addProduct( AVLTree productTree, List productList)
{
    String productName="";
    do{
        System.out.println("Ingrese el nombre del producto que desea agregar:");
        productName = scanner.nextLine();
        if(productName.equals(""))
            System.out.println("Debes ingresar un nombre de producto!");
    }while(productName.equals(""));
    Product product;

    //busqueda
    try//buscar en el arbol 
    {
        product = productTree.searchProduct(productName.toLowerCase().replaceAll("\\s+", ""));
        System.out.println("Encontrado: \n" + product);
        incrementStock(product);

    }
    catch(ProductNotFoundException e)//el produto no se encontro en el arbol
    {
        //busqueda en la lista
        System.out.println("->CONTROL FLOW SOUT: Producto no encontrado en arbol");//sout auxiliar
        try
        {
            product = productList.searchProduct(productName.toLowerCase().replaceAll("\\s+", "_"));
            System.out.println("Encontrado: \n" + product);
            incrementStock(product);

        }
        catch(ProductNotFoundException d)//producto no se encontro en la lista
        {
            String op="";
            do
            {
                System.out.println("El producto: " + productName + 
                " no se encuentra registrado en el sistema\n Deseea añadirlo?(s/n)\n");
                op = scanner.nextLine().toLowerCase().replaceAll("\\s+", "");
                switch (op)
                {
                    case "s":
                        product = new Product(productName.toLowerCase().replaceAll("\\s+", "_"), 0);
                        incrementStock(product);
                        //insertar en arbol y lista
                        productTree.insertProduct(product);
                        productList.insertNode(product);
                        System.out.println("Producto añadido con exito!");
                        break;
                    case "n":
                        break;
                    default:
                        System.out.println("Invalido");
                        break;
                }
            }while(!op.equals("n") && !op.equals("s"));
        }
    }
}

    public static void incrementStock(Product product)
    {
        int stock =0;
        do
        {
            System.out.println("Ingresa el stock a añadir: ");
            try
            {
                stock = Integer.parseInt(scanner.nextLine());
            }
            catch(NumberFormatException nfe)
            {
                System.out.println("Debes ingresar un valor numerico");
            }
            if(stock == 0)
                System.out.println("Debes ingresar un valor superior a 0");
        } while(stock <= 0);
        
        product.stock += stock;
    }
}
