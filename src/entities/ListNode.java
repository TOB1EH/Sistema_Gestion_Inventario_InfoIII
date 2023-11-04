package entities;

/**
 * Represents a node in a linked list.
 * The ListNode class is a subclass of the Node class. It contains a reference to the next node in the list.
 *
 */
<<<<<<< HEAD
public class ListNode<T> extends Node<T> {
    public ListNode<T> next;
=======
public class ListNode extends Node{
    public ListNode next;
>>>>>>> 5c0ea065503cead2a52ae41b95168cd87f8c3e53

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
