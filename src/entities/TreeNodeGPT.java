package entities;

/**
 * La clase TreeNodeGPT representa un nodo en un árbol.
 */
class TreeNodeGPT {
    // La clave del nodo
    String key;
    
    // La altura del nodo en el árbol
    int height;
    
    // Referencia al nodo hijo izquierdo
    TreeNodeGPT left;
    
    // Referencia al nodo hijo derecho
    TreeNodeGPT right;
    
    /**
     * Constructor de la clase TreeNodeGPT.
     * @param d La clave del nodo.
     */
    TreeNodeGPT(String d) {
        // Inicializar la clave del nodo con el valor proporcionado
        key = d;
        
        // Establecer la altura del nodo en 1
        height = 1;
    }
}
