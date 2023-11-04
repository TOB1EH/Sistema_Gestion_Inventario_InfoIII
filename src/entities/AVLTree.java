package entities;

import exceptions.ProductNotFoundException;

/**
 * The `AVLTree` class is an implementation of an AVL tree, which is a self-balancing binary search tree.
 * It provides methods for inserting and deleting nodes, as well as traversing the tree in pre-order.
 */
public class AVLTree {

    public TreeNode root;
    //private TreeNode root;

    /**
     * Returns the root node of the AVL tree.
     * @return The root node of the AVL tree.
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Sets the root node of the AVL tree.
     * @param root The root node to be set.
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    /**
     * Returns the height of a given node.
     * @param node The node to calculate the height for.
     * @return The height of the node.
     */
    private int height(TreeNode node) {
        return node == null ? 0 : node.height;
    }

    /**
     * Returns the maximum of two integers.
     * @param a The first integer.
     * @param b The second integer.
     * @return The maximum of the two integers.
     */
    private int max(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * Performs a right rotation on a given node.
     * @param y The node to perform the right rotation on.
     * @return The new root node after the rotation.
     */
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    /**
     * Performs a left rotation on a given node.
     * @param x The node to perform the left rotation on.
     * @return The new root node after the rotation.
     */
    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    /**
     * Returns the balance factor of a given node.
     * @param N The node to calculate the balance factor for.
     * @return The balance factor of the node.
     */
    private int getBalance(TreeNode N) {
        return N == null ? 0 : height(N.left) - height(N.right);
    }

    /**
     * Inserts a new node with the given /ment into the AVL tree.
     * @param node The root node of the AVL tree.
     * @param element The element of the new node to be inserted.
     * @return The new root node of the AVL tree.
     */
    public TreeNode insert(TreeNode node, String element) {
        if (node == null) {
            return new TreeNode(element);
        }

        if (element.compareTo(node.element) < 0) {
            node.left = insert(node.left, element);
        } else if (element.compareTo(node.element) > 0){
            node.right = insert(node.right, element);
        } else {
            return node;
        }

        node.height = max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        if (balance > 1 && element.compareTo(node.left.element) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && element.compareTo(node.right.element) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && element.compareTo(node.left.element) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && element.compareTo(node.right.element) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * Returns the node with the minimum element value in a given subtree.
     * @param node The root node of the subtree.
     * @return The node with the minimum element value.
     */
    private TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Deletes a node with the given element from the AVL tree.
     * @param root The root node of the AVL tree.
     * @param element The element of the node to be deleted.
     * @return The new root node of the AVL tree.
     */
    public TreeNode deleteNode(TreeNode root, String element) {
        if (root == null) {
            return root;
        }

        int cmp = element.compareTo(root.element);
        if (cmp < 0) {
            root.left = deleteNode(root.left, element);
        } else if (cmp > 0) {
            root.right = deleteNode(root.right, element);
        } else {
            if (root.left == null || root.right == null) {
                TreeNode temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                TreeNode temp = minValueNode(root.right);
                root.element = temp.element;
                root.right = deleteNode(root.right, temp.element);
            }
        }

        if(root == null) {
            return root;
        }

        root.height = max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

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
     * Searches a product in the inventory and returns the information about it as a string.
     * If the product is not found, it throws a ProductNotFoundException.
     * @param root The root node of the AVL tree.
     * @param name The name of the product to be found.
     * @return The information about the product as a string.
     * @throws ProductNotFoundException If the product is not found.
     */
    public String search(TreeNode root, String name) throws ProductNotFoundException {
        if (root == null) {
            throw new ProductNotFoundException(name);
        }

        int cmp = name.compareTo(root.element);
        if (cmp == 0) {
            return "Product's name: " + root.element + ", Stock: "; // + stock
        } else if (cmp < 0) {
            return search(root.left, name);
        } else {
            return search(root.right, name);
        }
    }

    /**
     * Traverses the AVL tree in pre-order and returns a string representation of the elements of the nodes.
     * @param node The root node of the AVL tree.
     * @return A string representation of the traversal.
     */
    public String preOrder(TreeNode node) {
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            sb.append(node.element).append(" ");
            sb.append(preOrder(node.left));
            sb.append(preOrder(node.right));
        }
        return sb.toString();
    }

    /**
     * Traverses the AVL tree in in-order and returns a string representation of the elements of the nodes.
     * @param node The root node of the AVL tree.
     * @return A string representation of the traversal.
     */
    public String inOrder(TreeNode node) {
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            sb.append(inOrder(node.left));
            sb.append(node.element).append(" ");
            sb.append(inOrder(node.right));
        }
        return sb.toString();
    }

    /*     Devolver una representación de cadena del recorrido en lugar de usar `System.out.print` hace que el código sea más modular porque separa la lógica transversal de la lógica de impresión. También hace que el código sea más fácil de probar porque la salida se puede comparar con los valores esperados. */
}
