package entities;

/**
 * La clase TreeNodeGPT representa un nodo en un árbol.
 */
public class TreeNode extends Node{
    
    // La altura del nodo en el árbol
    public int height;
    
    // Referencia al nodo hijo izquierdo
    public TreeNode left;
    
    // Referencia al nodo hijo derecho
    public TreeNode right;
    
    /**
     * Constructor de la clase TreeNodeGPT.
     * @param d La clave del nodo.
     */
    public TreeNode(String d) {
        // Inicializar la clave del nodo con el valor proporcionado
        super(d);
        
        // Establecer la altura del nodo en 1
        height = 1;
    }
}
