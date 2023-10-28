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
    public int getHeight(TreeNode<T> root) {
        return (root != null) ? root.getHeight() : 0;
    }

    /**
     * Returns balance factor of a TreeNode in an avl tree.
     * @param root of the avl tree.
     * @return factor balance of avl tree, 0 if there's no root.
     */
    public int getBalance(TreeNode<T> root) {
        return (root != null) ? (getHeight(root.getLeft()) - getHeight(root.getRight())) : 0;
    }

    /**
     * Updates the heigth of the avl tree.
     * @param root of the avl tree to update the heigth.
     */
    public void updateHeigth(TreeNode<T> root) {
        root.setHeight(1 + Math.max(getHeight(root.getLeft()), getHeight(root.getRight())));
    }

    /**
     * Checks if tree is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Creates a new tree, using three parameters
     * @param leftBranch leftTreeNode
     * @param element of the root-TreeNode
     * @param rightBranch rightTreeNode
     * @return tree TreeNode created
     */
    public TreeNode<T> newTree(TreeNode<T> leftBranch, T element, TreeNode<T> rightBranch) {
        return new TreeNode<>(element, leftBranch, rightBranch);
    }

    /** (IND)
     * Recorre el arbol de manera inOrder, primero recorre el subarbol izquierdo en orden (I) , luego
     * visita el nodo raiz (N), en tercer y ultimo lugar recorre el subarbol derecho en orden (D).
     * @param root of the binary tree
     */
    public void inOrder(TreeNode<T> root) {
        //Caso base: que el subarbol este vacio (root == null)
        if(root != null) {
            inOrder(root.getLeft());
            root.printelementTreeNode();
            inOrder(root.getRight());
        }
    }

    /**
     * Calculates and returns the total number of TreeNodes of a binary tree
     * @param root of the binary tree
     * @return total number of TreeNodes
     */
    public int getNumTreeNodes(TreeNode<T> root) {
        if(root == null) {
            return 0;
        } else {
            return 1 + getNumTreeNodes(root.getLeft()) + getNumTreeNodes(root.getRight());
        }
    }

    /**Calculates the depth (profundidad o cantidad de niveles) of the tree.
     * This is the rustic method (coded by me). The following similar methods were extracted from the internet.
     * @param root of the tree
     * @return amount of levels of the tree (depth)
     */
    public int depth(TreeNode<T> root) {
        int leftMax = 0; 
        int rightMax = 0;
        
        if(root == null) {
            return 0;
        } 
        if(root.left != null) {
            leftMax = depth(root.left);
        }
        if(root.right != null) {
            rightMax = depth(root.right);
        } 

        return Math.max(leftMax, rightMax) + 1;
    }

    /* Metodo optimizado de depth. Ver el depth primero antes que este. 
    Luego, una vez entendido depth, dejar este nuevo depth escrito y borrar el de arriba.
    public int depth(TreeTreeNode<U> root) {
        if(root == null) {
            return 0;
        } 
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    } 
    */

    /** Prints all leaves TreeNodes (nodos hoja) of the tree.
     * @param root of the tree who have the leaves TreeNodes to be printed
     */
    public void printLeaves(TreeNode<T> root) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            System.out.print(root.element + ", ");
            return;
        }
        if(root.left != null) {
            printLeaves(root.left);
        } 
        if(root.right != null) {
            printLeaves(root.right);
        }
    }

    /** 
     * Rotates to the rigth one time the avl tree.
     * @param root of avl tree.
     * @return new root of avl tree.
     */
    TreeNode<T> rightRotate(TreeNode<T> root) {
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
    TreeNode<T> leftRotate(TreeNode<T> root) {
        TreeNode<T> y = root.right;
        TreeNode<T> t2 = y.left;

        y.setLeft(root);
        root.setRight(t2);

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    /* //Lo estoy completando yo, no tocar
    TreeNode<T> insert(TreeNode<T> TreeNode, int data) {
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
    }

    public TreeNode<T> leftRigthRotate(TreeNode<T> z) {
        //To do rotacion doble izquierda
    }

    public TreeNode<T> rightLeftRotate(TreeNode<T> z) {
        //To do rotacion doble derecha
    }
}