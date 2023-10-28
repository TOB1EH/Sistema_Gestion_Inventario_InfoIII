package entities;
/**
 * Class that describes Product
 */
public class Product{
    private String name;
    private int stock;
    /**
     * Initializes Product
     * @param name of the Product
     * @param stock of the Product
     */
    public Product(String name,int stock)
    {
        this.name = name;
        this.stock = stock;
    }
    /**
     * Gets the product name
     * @return name of the product
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the product name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the stock value of the product
     * @return stock of the product
     */
    public int getStock() {
        return stock;
    }
    /**
     * Sets the stock of the product
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}