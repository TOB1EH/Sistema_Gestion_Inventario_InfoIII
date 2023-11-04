package entities;

/**
 * Represents a generic node
 */
public class Node{
    public Product product;

    /**
     * Constructs a new node with the specified product.
     *
     * @param product the product to be stored in the node
     */
    public Node(Product product) {
        this.product = product;
    }
}
