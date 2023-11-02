package entities;

/**
 * Represents a generic node
 */
public class Node{
    public String element;
    public int stock;
    /**
     * Constructs a new node with the specified element.
     *
     * @param element the element to be stored in the node
     */
    public Node(String element) {
        this.element = element;
        this.stock = 0;
    }

    /**
     * Returns the element of the product
     * @return elment's node
     */
    public String getElement() {
        return element;
    }

    /**
     * Sets the element of the product.
     * @param element to be setted.
     */
    public void setElement(String element) {
        this.element = element;
    }

    /**
     * Returns stock of the product.
     * @return stock's node.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets stock of the product.
     * @param stock to be setted.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    
}
