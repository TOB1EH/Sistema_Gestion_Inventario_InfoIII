package entities;

/**
 * Represents a node in a linked list.
 * The ListNode class is a subclass of the Node class. It contains a reference to the next node in the list.
 *
 * @param <AnyType> the type of element stored in the node
 */
public class ListNode<AnyType> extends Node<AnyType> {
    private Integer next;

    /**
     * Constructs a new ListNode object with the specified element.
     *
     * @param element the element to be stored in the node
     */
    public ListNode(AnyType element) {
        super(element);
        this.next = null;
    }
}
