import java.util.Scanner;
import entities.AVLTree;
import entities.List;
import entities.Product;
import exceptions.ProductNotFoundException;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        AVLTree productTree = new AVLTree();
        List productHistory = new List();
        menuIntro();
        int op;
        do{
            op=menu();
            switch(op) {
                case 1:
                    addProduct(productTree, productHistory);
                    break;
                case 2:
                    removeProduct(productTree, productHistory);
                    break;
                case 3:
                    System.out.println("Ingrese el nombre del producto que desea buscar.");
                    findingProduct(inputProduct(), productTree, productHistory);
                    break;
                case 4:
                    System.out.println("Inventario de productos completo:");
                    System.out.println(productHistory);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\nValor ingresado invalido,debes ingresar un valor numerico");
                    break;
            }
            
            
        }while(op != 5);
        System.out.println("Gracias por utilizar el sistema de gestion de inventario.");
        scanner.close();
}

    private static void menuIntro()
    {
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

        System.out.println("Bienvenido al sistema de gestion de inventario.\n");
        
    }
    private static int menu() {
        System.out.println("\nPor favor, ingrese una opcion segun desee:");
        System.out.println("Si desea agregar un producto, ingrese el numero 1.");
        System.out.println("Si desea eliminar un producto del inventario, ingrese el numero 2");
        System.out.println("Si desea buscar un producto, ingrese el numero 3");
        System.out.println("Si desea mostrar el inventario completo de productos, ingrese el numero 4");
        System.out.println("Si desea salir, ingrese el numero 5");
        try{

            int op =Integer.parseInt(scanner.nextLine());
            return op;
        }
        catch(NumberFormatException e)
        {
            return 6;
        }
    }

    /**
     * First looks for the product in the avl tree, if not's in the avl tree looks for it in the list, otherwise there's not the product in the inventory. 
     * @param productToFind looks for that product.
     * @param productTree avl tree to look for.
     * @param productList list to look for.
     * @return product found, otherwise null.
     */
    private static Product findingProduct(String productToFind, AVLTree productTree, List productList) {
        Product product;
        try{
            product = productTree.searchProduct(productToFind); 
            System.out.println("Encontrado: \n" + product);
            return product;

       }catch(ProductNotFoundException e)
       {
            try{
                product = productList.searchProduct(productToFind);
                System.out.println("Encontrado: \n" + product);
                return product;
            } catch(ProductNotFoundException d)
            {
                System.out.println("Producto no encontrado en el sistema\n");
                return null;
            }
       }
    }

    /** Reads input on the keyboard the product that will be used for.
     * 
     * @return product inserted by keyboard.
     */
    private static String inputProduct() {
        String productToFind = scanner.nextLine().toLowerCase().replaceAll("\\s+", "_");

        while(productToFind.isEmpty()) {
            System.out.println("Debes ingresar un nombre de producto!");
            productToFind = scanner.nextLine().toLowerCase().replaceAll("\\s+", "_");
        }
        return productToFind;
    }

/**
 * Add stock to an existing product or add the product if it is not in the system.
 * @param productTree
 * @param productList
 */
    public static void addProduct( AVLTree productTree, List productList)
    {
        System.out.println("\nIngresa el nombre del producto a añadir:");
        String productName=inputProduct();
        Product product = findingProduct(productName, productTree, productList);
        
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

    /**
     * Increments stock
     * @param product
     */
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
