package entities;

/**
 * Represents a node in a linked list.
 * The ListNode class is a subclass of the Node class. It contains a reference to the next node in the list.
 *
 */
public class ListNode extends Node{
    public ListNode next;

    /**
     * Constructs a new ListNode object with the specified product.
     *
     * @param product the product to be stored in the node
     */
    public ListNode(Product product) {
        super(product);
        this.next = null;
    }
}
