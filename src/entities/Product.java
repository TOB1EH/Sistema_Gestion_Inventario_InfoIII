package entities;
/**
 * Class that describes Product
 */
public class Product{
    public String element;
    public int stock;
    /**
     * Initializes Product
     * @param element of the Product
     * @param stock of the Product
     */
    public Product(String element,int stock)
    {
        this.element = element;
        this.stock = stock;
    }
    
}