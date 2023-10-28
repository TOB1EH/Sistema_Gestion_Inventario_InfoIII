package entities;

/**
 * Represents a generic linked list data structure.
 * 
 * @param <T> the type of elements in the list
 */
public class List<T> {
    private ListNode<T> front;

    /**
     * Constructs an empty list.
     */
    public List() {
        this.front = null;
    }

    /**
     * Inserts a new node with the given element at the end of the list.
     * If the list is empty, the new node becomes the front of the list.
     * 
     * @param element the element to be inserted
     * @throws NullPointerException if the element is null
     */
    public void insertNode(T element) {
        if (element == null) {
            throw new NullPointerException("The element is null");
        }

        // Insert the node
        ListNode<T> newNode = new ListNode<T>(element);

        if (front == null) {
            front = newNode;
        } else {
            ListNode<T> lastNode = front;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
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
}
