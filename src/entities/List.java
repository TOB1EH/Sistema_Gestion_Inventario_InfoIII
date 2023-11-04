package entities;

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
     * Inserts a new node with the given element at the end of the list.
     * If the list is empty, the new node becomes the front of the list.
     *
     * @param element the element to be inserted
     * @throws NullPointerException if the element is null
     */
    public void insertNode(String element) {
        if (element == null) {
            throw new NullPointerException("The element is null");
        }

        // Insert the node
        ListNode newNode = new ListNode(element);

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
     * Removes the node with the given element from the list.
     * If the element is not found, the list remains unchanged.
     *
     * @param element the element to be removed
     */
    public void removeNode(String element) {
        if (front == null) {
            return;
        }

        if (front.element.equals(element)) {
            front = front.next;
            return;
        }

        ListNode currentNode = front;
        while (currentNode.next != null) {
            if (currentNode.next.element.equals(element)) {
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
}