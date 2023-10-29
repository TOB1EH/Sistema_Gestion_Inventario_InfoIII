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
     * Inserts a new node to the avl tree.
     * @param root of avl tree.
     * @param element to be inserted.
     * @return root of the new avl tree
     */
    TreeNode<T> insert(TreeNode<T> root, T element) {
        if (root == null) {
            return new TreeNode<>(element);
        }

        if (element.compareTo(root.element) < 0) {
            root.left = insert(root.left, element);
        } else if (element.compareTo(root.element) > 0) {
            root.right = insert(root.right, element);
        } else {
            return root;
        }

        // Updates heigth of actual node
        updateHeigth(root);

        // Get the balance factor of the node to check the balance
        int balance = getBalance(root);

        // Cases of imbalance and rotations
        // Left left case
        if (balance > 1 && element.compareTo(root.left.element) < 0) {
            return rightRotate(root);
        }

        // Right right case
        if (balance < -1 && element.compareTo(root.right.element) > 0) {
            return leftRotate(root);
        }

        // Left right case (double rotation)
        if (balance > 1 && element.compareTo(root.left.element) > 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right left case (double rotation)
        if (balance < -1 && element.compareTo(root.right.element) < 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

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

    //Codigo para Tobi
    /**
    * Finds the smallest item in a subtree
    * @param root of the tree
    * @return minimun node of the tree
    */
    public TreeNode<U> findMin(TreeNode<U> root) {
        if(root != null) {
            while(root.leftNode != null) {
                root = root.leftNode;
            }
        }
        return root;
    }

    /**
     * Removes minimum item from a subtree
     * @param root the node that roots the tree
     * @return the new root
     * @throws ItemNotFoundException if root is empty
     */
    public TreeNode<U> removeMin(TreeNode<U> root) throws ItemNotFoundException {
        if(root == null) {
            throw new ItemNotFoundException();
        } else if(root.leftNode != null) {
            root.leftNode = removeMin(root.leftNode);
            return root;
        } else {
            return root.rightNode;
        }
    }
}