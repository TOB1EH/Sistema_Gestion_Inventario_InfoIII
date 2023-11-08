package entities;

import exceptions.ProductNotFoundException;

/**
 * The `AVLTree` class is an implementation of an AVL tree, which is a self-balancing binary search tree.
 * It provides methods for inserting and deleting nodes, as well as traversing the tree in pre-order.
 */
public class AVLTree {

    private TreeNode root;

    /**
     * Returns the root node of the AVL tree.
     * @return The root node of the AVL tree.
     */
    public TreeNode getRoot() {
        return root;
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
        TreeNode t2 = x.right;
        x.right = y;
        y.left = t2;
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
        TreeNode t2 = y.left;
        y.left = x;
        x.right = t2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    /**
     * Returns the balance factor of a given node.
     * @param N The node to calculate the balance factor for.
     * @return The balance factor of the node.
     */
    private int getBalance(TreeNode n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    public void insertProduct(Product product) {
        this.root = insert(root, product);
    }

    /**
     * Inserts a new node with the given /ment into the AVL tree.
     * @param node The root node of the AVL tree.
     * @param product The product of the new node to be inserted.
     * @return The new root node of the AVL tree.
     */
    private TreeNode insert(TreeNode node, Product product) {
        if (node == null) {
            return new TreeNode(product);
        }

        if (product.element.compareTo(node.product.element) < 0) {
            node.left = insert(node.left, product);
        } else if (product.element.compareTo(node.product.element) > 0){
            node.right = insert(node.right, product);
        } else {
            return node;
        }

        node.height = max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        if (balance > 1 && product.element.compareTo(node.left.product.element) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && product.element.compareTo(node.right.product.element) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && product.element.compareTo(node.left.product.element) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && product.element.compareTo(node.right.product.element) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * Returns the node with the minimum product value in a given subtree.
     * @param node The root node of the subtree.
     * @return The node with the minimum product value.
     */
    private TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public void deleteProduct(String key) {
        this.root = deleteNode(root, key);
    }

    /**
     * Deletes a node with the given product from the AVL tree.
     * @param root The root node of the AVL tree.
     * @param product The product of the node to be deleted.
     * @return The new root node of the AVL tree.
     */
    private TreeNode deleteNode(TreeNode root, String key) {
        if (root == null) {
            return root;
        }

        int cmp = key.compareTo(root.product.element);
        if (cmp < 0) {
            root.left = deleteNode(root.left, key);
        } else if (cmp > 0) {
            root.right = deleteNode(root.right, key);
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
                root.product = temp.product;
                root.right = deleteNode(root.right, temp.product.element);
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

    public Product searchProduct(String name) throws ProductNotFoundException {
        return search(root, name);
    }


    /**
     * Searches a product in the inventory and returns the information about it as a string.
     * If the product is not found, it throws a ProductNotFoundException.
     * @param root The root node of the AVL tree.
     * @param name The name of the product to be found.
     * @return The information about the product as a string.
     * @throws ProductNotFoundException If the product is not found.
     */
    private Product search(TreeNode root, String name) throws ProductNotFoundException {
        if (root == null) {
            throw new ProductNotFoundException(name);
        }

        int cmp = name.compareTo(root.product.element);
        if (cmp == 0) {
            return root.product;
        } else if (cmp < 0) {
            return search(root.left, name);
        } else {
            return search(root.right, name);
        }
    }

    /**
     * Traverses the AVL tree in in-order and returns a string representation of the products of the nodes.
     * @param node The root node of the AVL tree.
     * @return A string representation of the traversal.
     */
    public String inOrder(TreeNode node) {
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            sb.append(inOrder(node.left));
            sb.append(node.product.toString()).append("\n");
            sb.append(inOrder(node.right));
        }
        return sb.toString();
    }

    public String userInOrder() {
        return inOrder(this.root);
    }

}
