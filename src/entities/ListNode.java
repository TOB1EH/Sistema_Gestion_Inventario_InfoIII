package entities;

/**
 * Represents a node in a linked list.
 * The ListNode class is a subclass of the Node class. It contains a reference to the next node in the list.
 *
 * @param <T> the type of element stored in the node
 */
public class ListNode<T> extends Node<T> {
    private Integer next;
    private long dateid;//date in unix timestamp

    /**
     * Constructs a new ListNode object with the specified element.
     *
     * @param element the element to be stored in the node
     */
    public ListNode(T element) {
        super(element);
        this.next = null;
        this.dateid = System.currentTimeMillis()/1000;
    }
}
