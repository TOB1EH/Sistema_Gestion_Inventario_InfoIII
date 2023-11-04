package entities;

/**
 * Represents a node in a linked list.
 * The ListNode class is a subclass of the Node class. It contains a reference to the next node in the list.
 *
 */
public class ListNode extends Node{
    public ListNode next;

    /**
     * Constructs a new ListNode object with the specified element.
     *
     * @param element the element to be stored in the node
     */
    public ListNode(String element) {
        super(element);
        this.next = null;
    }
}
