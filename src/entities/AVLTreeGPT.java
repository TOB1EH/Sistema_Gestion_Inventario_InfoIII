package entities;

/**
 * La clase AVLTreeGPT es una implementación de un árbol AVL en Java.
 */
public class AVLTreeGPT {
    // La raíz del árbol
    public TreeNodeGPT root;
    
    /**
     * Devuelve la altura de un nodo.
     * @param N El nodo del que se quiere obtener la altura.
     * @return La altura del nodo.
     */
    int height(TreeNodeGPT N) {
        if (N == null)
            return 0;
        return N.height;
    }
    
    /**
     * Devuelve el máximo de dos números enteros.
     * @param a El primer número.
     * @param b El segundo número.
     * @return El máximo de los dos números.
     */
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
    
    /**
     * Realiza una rotación hacia la derecha en un nodo.
     * @param y El nodo en el que se realizará la rotación.
     * @return El nuevo nodo que quedará en la posición del nodo original.
     */
    TreeNodeGPT rightRotate(TreeNodeGPT y) {
        TreeNodeGPT x = y.left;
        TreeNodeGPT T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }
    
    /**
     * Realiza una rotación hacia la izquierda en un nodo.
     * @param x El nodo en el que se realizará la rotación.
     * @return El nuevo nodo que quedará en la posición del nodo original.
     */
    TreeNodeGPT leftRotate(TreeNodeGPT x) {
        TreeNodeGPT y = x.right;
        TreeNodeGPT T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }
    
    /**
     * Devuelve el factor de balance de un nodo.
     * @param N El nodo del que se quiere obtener el factor de balance.
     * @return El factor de balance del nodo.
     */
    int getBalance(TreeNodeGPT N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }
    
    /**
     * Inserta un nuevo nodo con la clave proporcionada en el árbol.
     * @param node El nodo en el que se quiere insertar el nuevo nodo.
     * @param key La clave del nuevo nodo.
     * @return El nuevo nodo insertado.
     */
    public TreeNodeGPT insert(TreeNodeGPT node, String key) {
        if (node == null)
            return (new TreeNodeGPT(key));
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = insert(node.left, key);
        else if (cmp > 0)
            node.right = insert(node.right, key);
        else
            return node;
        node.height = 1 + max(height(node.left), height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && key.compareTo(node.left.key) < 0)
            return rightRotate(node);
        if (balance < -1 && key.compareTo(node.right.key) > 0)
            return leftRotate(node);
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    
    /**
     * Devuelve el nodo con la clave mínima en un subárbol.
     * @param node El nodo raíz del subárbol.
     * @return El nodo con la clave mínima en el subárbol.
     */
    TreeNodeGPT minValueNode(TreeNodeGPT node) {
        TreeNodeGPT current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }
    
    /**
     * Elimina un nodo con la clave proporcionada del árbol.
     * @param root La raíz del árbol.
     * @param key La clave del nodo que se quiere eliminar.
     * @return La raíz del árbol después de eliminar el nodo.
     */
    public TreeNodeGPT deleteNode(TreeNodeGPT root, String key) {
        if (root == null)
            return root;
        int cmp = key.compareTo(root.key);
        if (cmp < 0)
            root.left = deleteNode(root.left, key);
        else if (cmp > 0)
            root.right = deleteNode(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                TreeNodeGPT temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                TreeNodeGPT temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }
        if (root == null)
            return root;
        root.height = max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    
    /**
     * Recorre el árbol en preorden e imprime las claves de los nodos.
     * @param node El nodo raíz del subárbol que se quiere recorrer.
     */
    public void preOrder(TreeNodeGPT node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * Recorre el árbol en orden e imprime las claves de los nodos.
     * @param node El nodo raíz del subárbol que se quiere recorrer.
     */
    public void inOrder(TreeNodeGPT node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }
}