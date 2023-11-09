# Sistema de Gestion de Inventario - Info III

## Authors
- Brambilla Agustin
- Funes Tobias
- Vargas Benjamin

## Summary
The `App` class is the main class of an inventory management system. It provides a menu-driven interface for users to add, remove, search, and display products in the inventory. The class uses an AVL tree data structure to store the products and a linked list to maintain a historical list of products.

## Example Usage
```java
// Create instances of AVLTree and List
AVLTree productTree = new AVLTree();
List productHistory = new List();

// Display menu and handle user input
int option;
do {
    option = menu();

    switch(option) {
        case 1:
            // Add a product to the inventory
            addProduct(inputProduct(), productTree, productHistory);
            break;
        case 2:
            // Remove a product or decrease stock
            do {
                option = 0;
                // Display sub-menu and handle user input
                switch(option) {
                    case 1:
                        // Remove a product from the inventory
                        removeProduct(inputProduct(), productTree, productHistory);
                        break;
                    case 2:
                        // Decrease stock of a product
                        decreaseStock(inputProduct(), productTree);
                        break;
                    default:
                        // Invalid option
                        break;
                }
            } while(option != 1 && option != 2);
            break;
        case 3:
            // Search for a product in the inventory
            findingProduct(inputProduct(), productTree, productHistory);
            break;
        case 4:
            // Display the complete product inventory
            showOrderProductList(productHistory);
            break;
        case 5:
            // Exit the program
            break;
        default:
            // Invalid option
            break;
    }
} while(option != 5);
```

## Code Analysis
### Main functionalities
- Add a product to the inventory
- Remove a product from the inventory
- Decrease stock of a product
- Search for a product in the inventory
- Display the complete product inventory
___
### Methods
- `menuIntro()`: Displays the inventory management system logo and menu introduction.
- `menu()`: Displays the menu options and returns the user's choice.
- `findingProduct(String productToFind, AVLTree productTree, List productList)`: Searches for a product in the AVL tree and the linked list.
- `inputProduct()`: Reads user input for a product name.
- `addProduct(String productToBeAdded, AVLTree productTree, List productList)`: Adds a product to the inventory.
- `incrementStock(Product product)`: Increments the stock of a product.
- `decreaseStock(String productToDelete, AVLTree productTree)`: Decreases the stock of a product.
- `showOrderProductList(List productList)`: Sorts and displays the product list in alphabetical order.
- `removeProduct(String productToDelete, AVLTree productTree, List productList)`: Removes a product from the inventory.
___
### Fields
- `private static Scanner scanner`: Scanner object for user input.
___



<h4 align="center">
:construction: Proyecto en construcción :construction:
</h4>