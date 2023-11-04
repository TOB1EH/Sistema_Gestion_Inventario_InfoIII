import entities.AVLTree;
import entities.Product;
import exceptions.ProductNotFoundException;
/**
 * TestAVLGPT
 */
public class TestAVLGPT {
    public static void main(String[] args) throws ProductNotFoundException {
        AVLTree tree = new AVLTree();

        Product manzana = new Product("Manzana", 10);
        tree.insertProduct(manzana);

        Product platano = new Product("Platano", 15);
        tree.insertProduct(platano);

        Product naranja = new Product("Naranja", 10);
        tree.insertProduct(naranja);

        Product mango = new Product("Mango", 10);
        tree.insertProduct(mango);

        Product pera = new Product("Pera", 10);
        tree.insertProduct(pera);

        Product uva = new Product("Uva", 10);
        tree.insertProduct(uva);

        System.out.println("Recorrido en inorden del árbol construido: "+tree.inOrder(tree.getRoot()));
        String keyToDelete = "Naranja";
        tree.deleteProduct(keyToDelete);
        System.out.println("\n\nÁrbol después de eliminar " + keyToDelete + ": "+tree.inOrder(tree.getRoot()));
    }
}