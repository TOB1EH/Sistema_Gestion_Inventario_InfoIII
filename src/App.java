import java.util.Scanner;
import entities.AVLTree;
import entities.List;
import entities.ListNode;
import entities.Product;
import exceptions.ProductNotFoundException;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        AVLTree productTree = new AVLTree();
        List productHistory = new List();
        int option;
        String optionStr;
        menuIntro();

        do {
            option = menu();

            switch(option) {
                case 1:
                    System.out.println("\n\nEnter the name of the product to add:\n");
                    addProduct(inputProduct(), productTree, productHistory);
                    break;
                case 2:
                    do {
                        option = 0;
                        System.out.println("\n\nDo you want to ...");
                        System.out.println("        1) Eliminate a product.");
                        System.out.println("        2) Decrease your stock.");
                        System.out.println("        3) Go back to menu.");
                        System.out.print("\nEnter an option: ");

                        try {
                            option = Integer.parseInt(scanner.nextLine());

                            switch(option) {
                                case 1:
                                    System.out.println("\nEnter the name of the product you want to delete:\n");
                                    removeProduct(inputProduct(), productTree, productHistory);
                                    break;
                                case 2:
                                    System.out.println("\nEnter the name of the product you want to reduce stock:\n");
                                    decreaseStock(inputProduct(), productTree);
                                    break;
                                case 3:
                                    break;
                                default:
                                     System.err.println("\n                "+"\033[41m"+"Invalid"+"\033[0m");
                                    break;
                            }
                        } catch(NumberFormatException e) {
                            System.err.println("\n                "+"\033[41m"+"You must enter a numerical value."+"\033[0m");
                        }

                    } while(option != 3);
                    break;
                case 3:
                    do{
                        System.out.println("\n\nEnter the name of the product you want to search for:\n");
                        findingProduct(inputProduct(), productTree, productHistory);
                        do{
                            System.out.print("\nDo you want to do another search? (y / n) ");
                            optionStr = scanner.nextLine().toLowerCase().replaceAll("\\s+", "");
                            if(!optionStr.equals("n") && !optionStr.equals("y"))
                                System.err.println("\n                "+"\033[41m"+"Invalid"+"\033[0m");   
                        }while(!optionStr.equals("n") && !optionStr.equals("y"));
                    
                    }while(!optionStr.equals("n"));
                    break;
                case 4:
                    System.out.println("\n\n                "+"\033[45m"+"Complete product inventory:"+"\033[0m");
                    showOrderProductList(productHistory);
                    System.out.println("                  (Press enter to continue)");
                    scanner.nextLine();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\n                "+"\033[41m"+"Invalid value entered, you must enter a numerical value."+"\033[0m");
                    break;
            }
        } while(option != 5);

        System.out.println("\n----------Thank you for using the inventory management system----------\n\n");
        scanner.close();
    }

    /**
     * Shows by screen a logotype of the inventory management system and the introduction of the menu.
     */
    private static void menuIntro() {
       System.out.println("\n"+ "\u001B[33m" + //
                "▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄\n" + //
                "░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░"+"\u001B[0m");
        System.out.println("\n" + //
                "\t██╗███╗░░██╗██╗░░░██╗███████╗███╗░░██╗████████╗░█████╗░██████╗░██╗░░░██╗\n" + //
                "\t██║████╗░██║██║░░░██║██╔════╝████╗░██║╚══██╔══╝██╔══██╗██╔══██╗╚██╗░██╔╝\n" + //
                "\t██║██╔██╗██║╚██╗░██╔╝█████╗░░██╔██╗██║░░░██║░░░██║░░██║██████╔╝░╚████╔╝░\n" + //
                "\t██║██║╚████║░╚████╔╝░██╔══╝░░██║╚████║░░░██║░░░██║░░██║██╔══██╗░░╚██╔╝░░\n" + //
                "\t██║██║░╚███║░░╚██╔╝░░███████╗██║░╚███║░░░██║░░░╚█████╔╝██║░░██║░░░██║░░░\n" + //
                "\t╚═╝╚═╝░░╚══╝░░░╚═╝░░░╚══════╝╚═╝░░╚══╝░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝░░░╚═╝░░░\n" + //
                "\n" + //
                "███╗░░░███╗░█████╗░███╗░░██╗░█████╗░░██████╗░███████╗███╗░░░███╗███████╗███╗░░██╗████████╗\n" + //
                "████╗░████║██╔══██╗████╗░██║██╔══██╗██╔════╝░██╔════╝████╗░████║██╔════╝████╗░██║╚══██╔══╝\n" + //
                "██╔████╔██║███████║██╔██╗██║███████║██║░░██╗░█████╗░░██╔████╔██║█████╗░░██╔██╗██║░░░██║░░░\n" + //
                "██║╚██╔╝██║██╔══██║██║╚████║██╔══██║██║░░╚██╗██╔══╝░░██║╚██╔╝██║██╔══╝░░██║╚████║░░░██║░░░\n" + //
                "██║░╚═╝░██║██║░░██║██║░╚███║██║░░██║╚██████╔╝███████╗██║░╚═╝░██║███████╗██║░╚███║░░░██║░░░\n" + //
                "╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝░╚═════╝░╚══════╝╚═╝░░░░░╚═╝╚══════╝╚═╝░░╚══╝░░░╚═╝░░░");

        System.out.println("\n"+ "\u001B[33m" + //
                "▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄ ▄▄\n" + //
                "░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░"+"\u001B[0m");
        System.out.println("\n                Welcome to the inventory management system.");
        System.out.println("                            (Press enter to start)");
        scanner.nextLine();
    }

    /**
     * Shows by screen the menu of the inventory management system.
     * @return option to use of the menu.
     */
    private static int menu() {
        System.out.println("===========================================================================");
        System.out.println("\nEnter an option:\n");
        System.out.println("        1) Add a product");
        System.out.println("        2) Remove a product");
        System.out.println("        3) Search product");
        System.out.println("        4) Show Inventory");
        System.out.println("        5) Exit\n");

        try {
            int option = Integer.parseInt(scanner.nextLine());
            return option;
        } catch(NumberFormatException e) {
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
            System.out.println("\n                "+"\033[42m"+" Found: "+"\033[0m" +"\n" + product);
            return product;
       } catch(ProductNotFoundException e) {
            try {
                product = productList.searchProduct(productToFind);
                System.out.println("\n                "+"\033[42m"+" Found: \n" +"\033[0m"+ product);

                return product;
            } catch(ProductNotFoundException d) {
                System.out.println("\n                "+"\033[41m"+"Product \"" + productToFind + "\" not found in the system."+"\033[0m"+"\n");

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
            if(product.stock == 0)
                productTree.insertProduct(product);
            incrementStock(product);
            System.out.println("\n                "+"\033[42m"+"Product added successfully!"+"\033[0m");
            System.out.println("                  (Press enter to continue)");
            scanner.nextLine();
        } else {
            String option = "";

            do {
                System.out.println("The product: " + productToBeAdded +
                " is not registered in the system.");
                System.out.print("\nDo you want to add it? (y / n) ");
                option = scanner.nextLine().toLowerCase().replaceAll("\\s+", "");

                switch(option) {
                    case "y":
                        product = new Product(productToBeAdded.toLowerCase().replaceAll("\\s+", "_"), 0);
                        incrementStock(product);
                        //Insert to the avltree and list
                        productTree.insertProduct(product);
                        productList.insertNode(product);
                        System.out.println("\n                "+"\033[42m"+"Product added successfully!"+"\033[0m");
                        System.out.println("                  (Press enter to continue)");
                        scanner.nextLine();
                        break;
                    case "n":
                        break;
                    default:
                        System.err.println("\n                "+"\033[41m"+"Invalid"+"\033[0m");
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
            System.out.println("\nEnter the stock to add:\n");

            try {
                stock = Integer.parseInt(scanner.nextLine());
                if(stock <= 0)
                    System.err.println("\n                "+"\033[41m"+"You must enter a value greater than 0."+"\033[0m");
            } catch(NumberFormatException e) {
                System.err.println("\n                "+"\033[41m"+"You must enter a numerical value."+"\033[0m");
            }
        } while(stock <= 0);

        product.stock += stock;
    }

    /**
     * Decreases stock of the product inserted by input. If stock is equal 0, then it eliminates the product from the avltree.
     * @param productTree to be eliminated from the tree or reduced stock.
     */
    private static void decreaseStock(String productToDelete, AVLTree productTree) {

        try {
            Product product = productTree.searchProduct(productToDelete);
            System.out.println("\n           "+"\033[42m"+"Product: " + productToDelete + " was found registered in the system!"+"\033[0m"+" Current stock: " + product.stock);

            int stock = 0;

            do {
                System.out.println("\nEnter how many inventory items you want to delete: ");

                try {
                    stock = Integer.parseInt(scanner.nextLine());
                } catch(NumberFormatException e) {
                    System.err.println("\n                "+"\033[41m"+"You must enter a numerical value."+"\033[0m");
                }
                if(stock <= 0) {
                    System.err.println("\n                "+"\033[41m"+"You must enter a value greater than 0."+"\033[0m");
                }
            } while(stock <= 0);

            if (product.stock >= stock) {
                product.stock -= stock;
                System.out.println("\n           "+"\033[42m"+"Successfully deleted items!"+"\033[0m");
                if (product.stock == 0) {
                    productTree.deleteProduct(productToDelete);
                }
            } else {
                System.err.println("\n                         "+"\u001B[48;2;255;165;0m"+" WARNING! "+"\033[0m"+"\n");
                System.err.println("You have entered a greater stock than the current product has.");
                System.out.println("Current stock: " + product.stock);

                String option = "";

                do {
                    System.out.print("\nDo you want to enter another stock? (y / n) ");
                    option = scanner.nextLine().toLowerCase().replaceAll("\\s+", "");

                    switch(option) {
                        case "y":
                            decreaseStock(productToDelete, productTree);
                            break;
                        case "n":
                            break;
                        default:
                            System.err.println("\n                "+"\033[41m"+"Invalid"+"\033[0m");
                            break;
                    }
                } while(!option.equals("n") && !option.equals("y"));
            }
        } catch(ProductNotFoundException e) {
            System.out.println("\n                "+"\033[41m"+"Product \"" + productToDelete + "\" not found in the system."+"\033[0m"+"\n");

        }
    }

    /**
     * Sorts the historical list alphabetically and displays it by console
     *
     * @param productList The historical list of products to be sorted and displayed.
     */
    private static void showOrderProductList(List produList) {
        AVLTree aux = new AVLTree();
        ListNode temp = produList.getFront();
        while (temp != null) {
            aux.insertProduct(temp.product);
            temp = temp.next;
        }
        System.out.println(aux.userInOrder());

    }

    /**
     * Removes a product from the inventory system.
     * It checks if the product exists in the system, prompts the user for confirmation,
     * and then deletes the product from both the AVL tree and the product list.
     *
     * @param productToDelete The name of the product to be deleted.
     * @param productTree     The AVL tree data structure that stores the products.
     * @param productList     The linked list data structure that stores the products.
     */
    private static void removeProduct(String productToDelete, AVLTree productTree, List productList) {
        Product product = findingProduct(productToDelete, productTree, productList);
        if (product == null)
            return;

        String option;
        do {
             System.err.println("\n                         "+"\u001B[48;2;255;165;0m"+" WARNING! "+"\033[0m"+"\n");
            System.out.print("Product " + productToDelete + " will be deleted. Do you want to confirm? (y / n) ");
            option = scanner.nextLine().toLowerCase().replaceAll("\\s+", "");
            switch (option) {
                case "y":
                    productTree.deleteProduct(productToDelete);
                    productList.removeNode(productToDelete);
                    System.out.println("\n                "+"\033[42m"+"Product disposed correctly!"+"\033[0m");
                    break;
                case "n":
                    break;
                default:
                    System.err.println("\n                "+"\033[41m"+"Invalid"+"\033[0m");
                    break;
            }
        } while (!option.equals("n") && !option.equals("y"));
    }
}
