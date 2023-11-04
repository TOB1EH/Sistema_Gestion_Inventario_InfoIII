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
        tree.root = tree.insert(tree.root, manzana);
        
        Product platano = new Product("Platano", 15);
        tree.root = tree.insert(tree.root, platano);
        
        Product naranja = new Product("Naranja", 10);
        tree.root = tree.insert(tree.root, naranja);
        
        Product mango = new Product("Mango", 10);
        tree.root = tree.insert(tree.root, mango);

        Product pera = new Product("Pera", 10);
        tree.root = tree.insert(tree.root, pera);

        Product uva = new Product("Uva", 10);
        tree.root = tree.insert(tree.root, uva);

        System.out.println("Recorrido en inorden del árbol construido: "+tree.inOrder(tree.root));
        String keyToDelete = "Naranja";
        tree.root = tree.deleteNode(tree.root, keyToDelete);
        System.out.println("\n\nÁrbol después de eliminar " + keyToDelete + ": "+tree.inOrder(tree.root));
    }
}