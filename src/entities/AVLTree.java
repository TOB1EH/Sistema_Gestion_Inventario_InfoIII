package entities;
 
public class AVLTree<T> {
    TreeNode<T> root;

    public AVLTree() {
        this.root = null;
    }

    public AVLTree(TreeNode<T> root) {
        this.root = root;
    }

    public TreeNode<T> getRoot() {
        return this.root;
    }

    /**
     * Returns the height of the avl tree.
     * @param root of the avl tree.
     * @return height of avl tree, 0 if there's no root.
     */
    private int getHeight(TreeNode<T> root) {
        return (root != null) ? root.getHeight() : 0;
    }

    /**
     * Returns balance factor of a TreeNode in an avl tree.
     * @param root of the avl tree.
     * @return factor balance of avl tree, 0 if there's no root.
     */
    private int getBalance(TreeNode<T> root) {
        return (root != null) ? (getHeight(root.getLeft()) - getHeight(root.getRight())) : 0;
    }

    /**
     * Updates the heigth of the avl tree.
     * @param root of the avl tree to update the heigth.
     */
    private void updateHeigth(TreeNode<T> root) {
        root.setHeight(1 + Math.max(getHeight(root.getLeft()), getHeight(root.getRight())));
    }

    /**
     * Creates a new tree, using three parameters
     * @param leftBranch leftTreeNode
     * @param element of the root-TreeNode
     * @param rightBranch rightTreeNode
     * @return tree TreeNode created
     */
    private TreeNode<T> newTree(TreeNode<T> leftBranch, T element, TreeNode<T> rightBranch) {
        return new TreeNode<>(element, leftBranch, rightBranch);
    }

    /** (IND)
     * Recorre el arbol de manera inOrder, primero recorre el subarbol izquierdo en orden (I) , luego
     * visita el nodo raiz (N), en tercer y ultimo lugar recorre el subarbol derecho en orden (D).
     * @param root of the binary tree
     */
    private void inOrder(TreeNode<T> root) {
        //Caso base: que el subarbol este vacio (root == null)
        if(root != null) {
            inOrder(root.getLeft());
            root.printElementNode();
            inOrder(root.getRight());
        }
    }

    /** 
     * Rotates to the rigth one time the avl tree.
     * @param root of avl tree.
     * @return new root of avl tree.
     */
    private TreeNode<T> rightRotate(TreeNode<T> root) {
        TreeNode<T> x = root.left;
        TreeNode<T> t2 = x.right;

        x.setRight(root);
        root.setLeft(t2);

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * Rotates to the left one time the avl tree.
     * @param root of avl tree.
     * @return new root of avl tree.
     */
    private TreeNode<T> leftRotate(TreeNode<T> root) {
        TreeNode<T> y = root.right;
        TreeNode<T> t2 = y.left;

        y.setLeft(root);
        root.setRight(t2);

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    /**
     * TO-DO insercion de elemento al 
     * @param TreeNode
     * @return
     */
    public TreeNode<T> insert(TreeNode<T> TreeNode) {
        return null;
    }
    /* //Lo estoy completando yo, no tocar
        if (TreeNode == null) {
            return new TreeNode(data);
        }

        if (data < TreeNode.data) {
            TreeNode.left = insert(TreeNode.left, data);
        } else if (data > TreeNode.data) {
            TreeNode.right = insert(TreeNode.right, data);
        } else {
            // No permitir duplicados
            return TreeNode;
        }

        // Actualizar la altura del nodo actual
        TreeNode.height = 1 + max(height(TreeNode.left), height(TreeNode.right));

        // Obtener el factor de equilibrio del nodo para verificar el equilibrio
        int balance = getBalance(TreeNode);

        // Casos de desequilibrio y rotaciones
        // Caso izquierda izquierda
        if (balance > 1 && data < TreeNode.left.data) {
            return rightRotate(TreeNode);
        }

        // Caso derecha derecha
        if (balance < -1 && data > TreeNode.right.data) {
            return leftRotate(TreeNode);
        }

        // Caso izquierda derecha
        if (balance > 1 && data > TreeNode.left.data) {
            TreeNode.left = leftRotate(TreeNode.left);
            return rightRotate(TreeNode);
        }

        // Caso derecha izquierda
        if (balance < -1 && data < TreeNode.right.data) {
            TreeNode.right = rightRotate(TreeNode.right);
            return leftRotate(TreeNode);
        }

        return TreeNode;
    }

    // Lo estoy completando yo, no tocar
    public void insert(int data) {
        root = insert(root, data);
    } */
    public TreeNode<T> delete(TreeNode<T> root, int data) {
        //To Do
        return null;
    }

    private TreeNode<T> leftRigthRotate(TreeNode<T> z) {
        //To do rotacion doble izquierda
        return null;
    }

    private TreeNode<T> rightLeftRotate(TreeNode<T> z) {
        //To do rotacion doble derecha
        return null;
    }
}