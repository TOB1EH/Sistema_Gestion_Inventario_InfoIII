package entities;

/**
 * Represents a generic node
 *
 * @param <T> the type of data stored in the node
 */
public class Node<T> {
    private T element;

    /**
     * Constructs a new node with the specified element.
     *
     * @param element the element to be stored in the node
     */
    public Node(T element) {
        this.element = element;
    }
}
