package entities;
/**
 * Clase que describe Product
 */
public class Product{

    private String nombre;
    private int stock;

    /**
     * Inicializa Product
     * @param nombre del Product
     * @param stock del Product
     */
    public Product(String nombre,int stock)
    {
        this.nombre = nombre;
        this.stock = stock;
    }

    /**
     * Obtiene nombre de producto
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Fija nombre del producto
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el valor de Stock del producto
     * @return Stock del producto
     */
    public int getStock() {
        return stock;
    }

    /**
     * Fija el stock del producto
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
}
