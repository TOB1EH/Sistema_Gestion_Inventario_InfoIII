package entities;

import exceptions.ProductNotFoundException;

/**
 * Represents a generic linked list data structure.
 *
 * The List class provides methods to insert and remove nodes from the list, as well as check if the list is empty.
 */
public class List {
    private ListNode front;

    /**
     * Constructs an empty list.
     */
    public List() {
        this.front = null;
    }

    /**
     * Inserts a new node with the given product at the end of the list.
     * If the list is empty, the new node becomes the front of the list.
     *
     * @param product the product to be inserted
     * @throws NullPointerException if the product is null
     */
    public void insertNode(Product product) {
        if (product == null) {
            throw new NullPointerException("The product is null");
        }

        // Insert the node
        ListNode newNode = new ListNode(product);

        if (front == null) {
            front = newNode;
        } else {
            ListNode lastNode = front;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
        }
    }

    /**
     * Removes the node with the given product from the list.
     * If the product is not found, the list remains unchanged.
     *
     * @param key the product to be removed
     */
    public void removeNode(String key) {
        if (front == null) {
            return;
        }

        if (front.product.element.equals(key)) {
            front = front.next;
            return;
        }

        ListNode currentNode = front;
        while (currentNode.next != null) {
            if (currentNode.next.product.element.equals(key)) {
                currentNode.next = currentNode.next.next;
                return;
            }
            currentNode = currentNode.next;
        }
    }

    /**
    * Finds a product in the list, if the product was found, it prints the information about it, otherwise prints a message error.
    * @param key to find in the list.
    * @return product found.
    * @throws ProductNotFoundException if product wasn't found
    */
    public String findProduct(String key) throws ProductNotFoundException {
        ListNode currentNode = front;

        while(currentNode != null) {
            if(currentNode.product.element.equals(key)) {
                return currentNode.product.toString();
            }
            currentNode = currentNode.next;
        }

        throw new ProductNotFoundException(key);
    }
    
    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.front == null;
    }
}