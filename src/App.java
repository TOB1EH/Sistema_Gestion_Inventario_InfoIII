import java.util.InputMismatchException;
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
        int option;

        menuIntro();

        do {
            option = menu();

            switch(option) {
                case 1:
                    System.out.println("Enter the name of the product to add:");
                    addProduct(inputProduct(), productTree, productHistory);
                    break;
                case 2:
                    System.out.println("Enter the name of the product you want to delete.");
                    removeProduct(inputProduct(), productTree, productHistory);
                    break;
                case 3:
                    System.out.println("Enter the name of the product you want to search for.");
                    findingProduct(inputProduct(), productTree, productHistory);
                    break;
                case 4:
                    System.out.println("Complete product inventory:");
                    System.out.println(productHistory);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\nInvalid value entered, you must enter a numerical value.");
                    break;
            }
        } while(option != 5);

        System.out.println("Gracias por utilizar el sistema de gestion de inventario.");
        scanner.close();
    }

    /**
     * Shows by screen a logotype of the inventory management system and the introduction of the menu.
     */
    private static void menuIntro() {
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

        System.out.println("Welcome to the inventory management system.");
    }

    /**
     * Shows by screen the menu of the inventory management system.
     * @return option to use of the menu.
     */
    private static int menu() {
        System.out.println("\nPlease enter an option as desired:");
        System.out.println("If you want to add a product, enter the number 1.");
        System.out.println("If you want to remove a product from inventory, enter the number 2.");
        System.out.println("If you want to search for a product, enter the number 3.");
        System.out.println("If you want to show the complete inventory of products, enter the number 4.");
        System.out.println("If you want to leave, enter the number 5.");

        try {
            int option = scanner.nextInt();
            return option;
        } catch(InputMismatchException e) {
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
        try {
            product = productTree.searchProduct(productToFind); 
            System.out.println("Found:\n" + product);

            return product;
       } catch(ProductNotFoundException e) {
            try {
                product = productList.searchProduct(productToFind);
                System.out.println("Found:\n" + product);

                return product;
            } catch(ProductNotFoundException d) {
                System.err.println("Product not found in the system.");

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
            productToFind = scanner.nextLine().toLowerCase().replaceAll("\\s+", "_");
        }

        return productToFind;
    }

    /**
     * Add stock to an existing product or add the product if it is not in the system.
     * @param productTree
     * @param productList
     */
    public static void addProduct(String productToBeAdded, AVLTree productTree, List productList) {
        Product product = findingProduct(productToBeAdded, productTree, productList);
        
        if(product != null) {
            System.out.println("Found:\n" + product);
            incrementStock(product);
        } else {
            String option = "";

            do {
                System.out.println("The product: " + productToBeAdded + 
                " is not registered in the system.\nDo you want to add it? (y / n)");
                option = scanner.nextLine().toLowerCase().replaceAll("\\s+", "");

                switch(option) {
                    case "y":
                        product = new Product(productToBeAdded.toLowerCase().replaceAll("\\s+", "_"), 0);
                        incrementStock(product);
                        //Insert to the avltree and list
                        productTree.insertProduct(product);
                        productList.insertNode(product);
                        System.out.println("Product added successfully!");
                        break;
                    case "n":
                        break;
                    default:
                        System.err.println("Invalid");
                        break;
                }
            } while(!option.equals("n") && !option.equals("y"));
        }
    }

    /**
     * Increments stock.
     * @param product to be incremented.
     */
    public static void incrementStock(Product product) {
        int stock = 0;

        do {
            System.out.println("Enter the stock to add: ");

            try {
                stock = scanner.nextInt();
            } catch(InputMismatchException e) {
                System.err.println("You must enter a numerical value.");
            }
            if(stock == 0) {
                System.err.println("You must enter a value greater than 0.");
            }
        } while(stock <= 0);

        product.stock += stock;
    }

    /**
     * Decreases stock of the product inserted by input. If stock is equal 0, then it eliminates the product from the avltree.
     * @param productTree to be eliminated from the tree or reduced stock.
     * @param productList to be eliminated from the list or reduced stock.
     */
    private static void removeProduct(String productToDelete, AVLTree productTree, List productList) {
        int stock = 0;

        do {
            System.out.print("Enter how many inventory items you want to delete: ");

            try {
                stock = scanner.nextInt();
            } catch(InputMismatchException e) {
                System.err.println("You must enter an integer numerical value.");
            }
            if(stock <= 0) {
                System.err.println("You must enter a value greater than 0");
            }
        } while(stock <= 0);
      
        try {
            Product product = productTree.searchProduct(productToDelete);
            if (product.stock >= stock) {
                product.stock -= stock;
                System.out.println("Successfully deleted items!");
                if (product.stock == 0) {
                    productTree.deleteProduct(productToDelete);
                }
            } else {
                System.err.println("You have entered a greater stock than the current product has.");
            }
        } catch(ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
