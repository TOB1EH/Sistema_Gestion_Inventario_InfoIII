package exceptions;

public class ProductNotFoundException extends AVLTreeException {
    public ProductNotFoundException(String x) {
        super(x + " was not found in the inventory.");
    }
}
