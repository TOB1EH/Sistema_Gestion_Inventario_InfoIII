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

    public ListNode getFront() {
        return front;
    }

    /**
     * Inserts a new node with the given product at the end of the list if it'snt on the list .
     * If the list is empty, the new node becomes the front of the list.
     *
     * @param product the product to be inserted
     * @throws NullPointerException if the product is null
     */
    public void insertNode(Product product) {
        if (product == null)
            throw new NullPointerException("The product is null");
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
     * Searches for a product with the given name in the linked list.
     * If the product is found, it is returned. If the product is not found, a ProductNotFoundException is thrown.
     *
     * @param name The name of the product to search for.
     * @return The product with the given name if found.
     * @throws ProductNotFoundException If the product with the given name is not found.
     */
    public Product searchProduct(String name) throws ProductNotFoundException {
        ListNode currentNode = front;

        while (currentNode != null) {
            if (currentNode.product.element.equals(name)) {
                return currentNode.product;
            }
            currentNode = currentNode.next;
        }

        throw new ProductNotFoundException(name);
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
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.front == null;
    }

    /**
     * Returns a string representation of the linked list.
     * Each product in the list is separated by a newline character.
     *
     * @return The string representation of the linked list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode temp = front;
        while (temp != null) {
            sb.append(temp.product.toString()).append("\n");
            temp = temp.next;
        }
        return sb.toString();
    }
}