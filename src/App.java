import java.util.Scanner;
import entities.AVLTree;
import entities.List;
import entities.Product;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        AVLTree productTree = new AVLTree();
        List productHistory = new List();
        switch(menu()) {
            case 1:
                addProduct(productTree, productHistory);
                break;
            case 2:
                removeProduct(productTree, productHistory);
                break;
            case 3:
                System.out.println("Ingrese el nombre del producto que desea buscar.");
                break;
            case 4:
                System.out.println("Inventario de productos completo:");
                ListNode currentNode = productHistory.getFront();
                while (currentNode != null) {
                    System.out.println(currentNode.product);
                    currentNode = currentNode.next;
                }
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
    Product product = findingProduct(productName.toLowerCase().replaceAll("\\s+", "_"), productTree, productList);
    
    if(product != null)
    {
        System.out.println("Encontrado: \n" + product);
        incrementStock(product);
    }
    else
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

            if(stock == 0)
                System.out.println("Debes ingresar un valor superior a 0");
        } while(stock <= 0);
        product.stock += stock;
    }

    private static void removeProduct(AVLTree productTree, List productList) {
        System.out.println("Ingrese el nombre del producto que desea eliminar.");
        String string = scanner.nextLine().toLowerCase().replace(" ", "_");

        int stock = 0;
        do {
            System.out.print("Ingrese cuantos elementos del inventario desea eliminar: ");
            try {
                stock = scanner.nextInt();
            }
            catch(NumberFormatException e) {
                System.out.println("Debes ingresar un valor numerico entero.");
            }
            if(stock == 0)
                System.out.println("Debes ingresar un valor superior a 0");
        } while(stock <= 0);
      
              try {
            Product product = productTree.searchProduct(string);
            if (product.stock >= stock) {
                product.stock -= stock;
                System.out.println("Elementos eliminados correctamente!");
                if (product.stock == 0) {
                    productTree.deleteProduct(string);
                }
            }
        } catch (ProductNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    
    private static Product findingProduct(String productToFind, AVLTree productTree, List productList)
    {
        return null;
    }
}
