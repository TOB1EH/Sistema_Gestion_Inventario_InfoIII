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

## Methods

### `menuIntro()`: Displays the inventory management system logo and menu introduction.

    Inputs:
    There are no inputs for this method.

    Flow:
    1. The method prints a logotype of the inventory management system.
    2. The method prints an introduction message for the menu.
    3. The method waits for the user to press enter to continue.

    Outputs:
    There are no outputs for this method.


### `menu()`: Displays the menu options and returns the user's choice.

    Inputs:
    None

    Flow:
    1. Print the menu options.
    2. Read the user's input.
    3. Convert the input to an integer.
    4. Return the integer value.

    Outputs:
    The selected option as an integer.


### `findingProduct(String productToFind, AVLTree productTree, List productList)`: Searches for a product in the AVL tree and the linked list.

    Inputs:
    - `productToFind` (String): The name of the product to search for.
    - `productTree` (AVLTree): The AVL tree data structure containing the products.
    - `productList` (List): The linked list data structure containing the products.

    Flow:
    1. Try to search for the product in the AVL tree using the `searchProduct` method of the `productTree`.
    2. If the product is found in the AVL tree, print a success message and return the product.
    3. If the product is not found in the AVL tree, try to search for it in the linked list using the `searchProduct` method of the `productList`.
    4. If the product is found in the linked list, print a success message and return the product.
    5. If the product is not found in either the AVL tree or the linked list, print an error message and return null.

    Outputs:
    - `product` (Product): The product found in either the AVL tree or the linked list, or null if the product is not found.


### `inputProduct()`: Reads user input for a product name.

    Inputs:
    None

    Flow:
    1. Read the input from the keyboard and convert it to lowercase.
    2. Replace any whitespace in the input with underscores.
    3. Check if the input is empty. If it is, repeat step 1 and 2 until a non-empty input is entered.
    4. Return the processed input as the product.

    Outputs:
    The product entered by the user.

### `addProduct(String productToBeAdded, AVLTree productTree, List productList)`: Adds a product to the inventory.

    Inputs:
    - `productToBeAdded` (String): The name of the product to be added.
    - `productTree` (AVLTree): An AVLTree object representing the product tree.
    - `productList` (List): A List object representing the product history.

    Flow:
    1. The method first calls the `findingProduct` method to check if the product already exists in the system.
    2. If the product exists, it checks if the stock is zero. If so, it inserts the product into the product tree.
    3. It then calls the `incrementStock` method to increment the stock of the product.
    4. It prints a success message and waits for the user to press enter.
    5. If the product does not exist, it prompts the user to add it.
    6. If the user chooses to add the product, it creates a new Product object with the given name and zero stock.
    7. It calls the `incrementStock` method to increment the stock of the new product.
    8. It inserts the new product into the product tree and the product list.
    9. It prints a success message and waits for the user to press enter.

    Outputs:
    None


### `incrementStock(Product product)`: Increments the stock of a product.

    Inputs:
    - `product` (Product): The product object for which the stock needs to be incremented.

    Flow:
    1. Prompt the user to enter the stock to add.
    2. Read the user input and parse it as an integer.
    3. If the parsed value is less than or equal to 0, display an error message and repeat step 1.
    4. Add the parsed value to the current stock of the product.

    Outputs:
    None. The stock of the given product is updated directly.


### `decreaseStock(String productToDelete, AVLTree productTree)`: Decreases the stock of a product.

    Inputs:
    - `productToDelete` (String): The name of the product to be deleted or have its stock reduced.
    - `productTree` (AVLTree): The AVL tree containing the products.

    Flow:
    1. Search for the product in the AVL tree using the `searchProduct` method.
    2. If the product is found, prompt the user to enter the number of inventory items to be deleted.
    3. If the input is not a numerical value, display an error message.
    4. If the input is less than or equal to zero, display an error message.
    5. If the input is valid, check if the stock of the product is greater than or equal to the input.
    6. If the stock is greater than or equal to the input, subtract the input from the stock and display a success message.
    7. If the stock becomes zero, delete the product from the AVL tree using the `deleteProduct` method.
    8. If the stock is less than the input, display an error message and prompt the user to enter another stock.
    9. If the user chooses to enter another stock, recursively call the `decreaseStock` method.
    10. If the user chooses not to enter another stock, exit the method.

    Outputs:
    - Success message if the stock is successfully reduced.
    - Error message if the input is not a numerical value.
    - Error message if the input is less than or equal to zero.
    - Error message if the stock is less than the input.
    - Error message if the product is not found in the AVL tree.


### `showOrderProductList(List productList)`: Sorts and displays the product list in alphabetical order.

    Inputs:
    - `productList` (List): The historical list of products to be sorted and displayed.

    Flow:
    1. Create an `AVLTree` object called `aux`.
    2. Get the front node of the `productList` using the `getFront` method and assign it to a `ListNode` variable called `temp`.
    3. Iterate over the `temp` node and its next nodes until `temp` becomes null.
    4. Inside the loop, insert the product of the current `temp` node into the `aux` AVL tree using the `insertProduct` method.
    5. After the loop ends, call the `userInOrder` method on the `aux` AVL tree to get the sorted products in the tree.
    6. Print the sorted products on the console.

    Outputs:
    None. The method only displays the sorted products on the console.


### `removeProduct(String productToDelete, AVLTree productTree, List productList)`: Removes a product from the inventory.

    Inputs:
    - `productToDelete` (String): The name of the product to be deleted.
    - `productTree` (AVLTree): The AVL tree data structure that stores the products.
    - `productList` (List): The linked list data structure that stores the products.

    Flow:
    1. Find the product in the system by calling the `findingProduct` method with the `productToDelete`, `productTree`, and `productList` as arguments.
    2. If the product is found, print the product name and current stock.
    3. If the product is not found, print a message and return.
    4. Prompt the user for confirmation to delete the product.
    5. If the user confirms (by entering 'y'), delete the product from both the AVL tree and the product list.
    6. If the user does not confirm (by entering 'n'), do nothing.
    7. Repeat steps 4-6 until the user confirms or cancels.

    Outputs:
    -If the product is found, print the product name and current stock.
    - If the product is not found, print a message indicating that the product is not registered in the system.
    - If the user confirms the deletion, print a message indicating that the product was deleted.
    - If the user cancels the deletion, do nothing.

___



<h4 align="center">
:construction: Proyecto en construcción :construction:
</h4>