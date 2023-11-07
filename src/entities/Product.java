package entities;

/**
 * Class that represents a product with an element and stock quantity.
 */
public class Product {
    public String element;
    public int stock;

    /**
     * Initializes a new Product object with the given element and stock quantity.
     * @param element the element of the product
     * @param stock the stock quantity of the product
     */
    public Product(String element, int stock) {
        this.element = element;
        this.stock = stock;
    }

    /**
     * Returns a string representation of the Product object, including the element and stock quantity.
     * @return the string representation of the Product object
     */
    @Override
    public String toString() {
        return "Product: " + element + "\nStock: " + stock + "\n";
    }
}