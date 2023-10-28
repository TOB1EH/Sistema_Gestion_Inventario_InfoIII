import javax.swing.tree.TreeNode;

public class AVLTree<T> {
    Node<T> root;

    public AVLTree() {
        this.root = null;
    }

    public AVLTree(Node<T> root) {
        this.root = root;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    /**
     * Returns the height of the avl tree.
     * @param root of the avl tree.
     * @return height of avl tree, 0 if there's no root.
     */
    public int getHeight(Node<T> root) {
        return (root != null) ? root.getHeight() : 0;
    }

    /**
     * Returns balance factor of a node in an avl tree.
     * @param root of the avl tree.
     * @return factor balance of avl tree, 0 if there's no root.
     */
    public int getBalance(Node<T> root) {
        return (root != null) ? (getHeight(root.getleft()) - getHeight(root.getright())) : 0;
    }

    /**
     * Updates the heigth of the avl tree.
     * @param root of the avl tree to update the heigth.
     */
    public void updateHeigth(Node<T> root) {
        root.setHeight(1 + Math.max(getHeight(root.getleft()), getHeight(root.getright())));
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
     * @param leftBranch leftNode
     * @param value of the root-node
     * @param rightBranch rightNode
     * @return tree node created
     */
    public Node<T> newTree(Node<T> leftBranch, T value, Node<T> rightBranch) {
        return new Node<>(value, leftBranch, rightBranch);
    }

    /** (IND)
     * Recorre el arbol de manera inOrder, primero recorre el subarbol izquierdo en orden (I) , luego
     * visita el nodo raiz (N), en tercer y ultimo lugar recorre el subarbol derecho en orden (D).
     * @param root of the binary tree
     */
    public void inOrder(Node<T> root) {
        //Caso base: que el subarbol este vacio (root == null)
        if(root != null) {
            inOrder(root.getleft());
            root.printValueNode();
            inOrder(root.getright());
        }
    }

    /**
     * Calculates and returns the total number of nodes of a binary tree
     * @param root of the binary tree
     * @return total number of nodes
     */
    public int getNumNodes(Node<T> root) {
        if(root == null) {
            return 0;
        } else {
            return 1 + getNumNodes(root.getleft()) + getNumNodes(root.getright());
        }
    }

    /**Calculates the depth (profundidad o cantidad de niveles) of the tree.
     * This is the rustic method (coded by me). The following similar methods were extracted from the internet.
     * @param root of the tree
     * @return amount of levels of the tree (depth)
     */
    public int depth(Node<T> root) {
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
    public int depth(TreeNode<U> root) {
        if(root == null) {
            return 0;
        } 
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    } 
    */

    /** Prints all leaves nodes (nodos hoja) of the tree.
     * @param root of the tree who have the leaves nodes to be printed
     */
    public void printLeaves(Node<T> root) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            System.out.print(root.value + ", ");
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
    Node<T> rightRotate(Node<T> root) {
        Node<T> x = root.left;
        Node<T> t2 = x.right;

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
    Node<T> leftRotate(Node<T> root) {
        Node<T> y = root.right;
        Node<T> t2 = y.left;

        y.setLeft(root);
        root.setRight(t2);

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    /* //Lo estoy completando yo, no tocar
    Node<T> insert(Node<T> node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            // No permitir duplicados
            return node;
        }

        // Actualizar la altura del nodo actual
        node.height = 1 + max(height(node.left), height(node.right));

        // Obtener el factor de equilibrio del nodo para verificar el equilibrio
        int balance = getBalance(node);

        // Casos de desequilibrio y rotaciones
        // Caso izquierda izquierda
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }

        // Caso derecha derecha
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }

        // Caso izquierda derecha
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Caso derecha izquierda
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Lo estoy completando yo, no tocar
    public void insert(int data) {
        root = insert(root, data);
    } */

    public Node<T> delete(Node<T> root, int data) {
        //To Do
    }

    public Node<T> leftRigthRotate(Node<T> z) {
        //To do rotacion doble izquierda
    }

    public Node<T> rightLeftRotate(Node<T> z) {
        //To do rotacion doble derecha
    }
}