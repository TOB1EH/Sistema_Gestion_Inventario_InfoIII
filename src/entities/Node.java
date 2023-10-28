package entities;

/**
 * Represents a generic node
 *
 * @param <AnyType> the type of data stored in the node
 */
public class Node<AnyType> {
    private AnyType element;

    /**
     * Constructs a new node with the specified element.
     *
     * @param element the element to be stored in the node
     */
    public Node(AnyType element) {
        this.element = element;
    }
}
