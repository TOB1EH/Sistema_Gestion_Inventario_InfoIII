import entities.AVLTreeGPT; 
/** 
 * TestAVLGPT 
 */ 
public class TestAVLGPT { 
    public static void main(String[] args) { 
        AVLTreeGPT tree = new AVLTreeGPT(); 
        tree.root = tree.insert(tree.root, "manzana"); 
        tree.root = tree.insert(tree.root, "plátano"); 
        tree.root = tree.insert(tree.root, "naranja"); 
        tree.root = tree.insert(tree.root, "mango"); 
        tree.root = tree.insert(tree.root, "pera"); 
        tree.root = tree.insert(tree.root, "uva"); 
        System.out.println("Recorrido en inorden del árbol construido: "); 
        tree.inOrder(tree.root); 
        String keyToDelete = "naranja"; 
        tree.root = tree.deleteNode(tree.root, keyToDelete); 
        System.out.println("\n\nÁrbol después de eliminar " + keyToDelete + ":"); 
        tree.inOrder(tree.root); 
    } 
}