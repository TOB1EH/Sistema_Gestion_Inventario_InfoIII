package entities;

/**
 * The TreeNode class represents a node in a tree.
 * It extends the Node class and adds additional fields and methods specific to tree nodes.
 */
public class TreeNode extends Node {

    /**
     * The height of the node in the tree.
     */
    public int height;

    /**
     * Reference to the left child node.
     */
    public TreeNode left;

    /**
     * Reference to the right child node.
     */
    public TreeNode right;

    /**
     * Constructor of the TreeNode class.
     * Initializes the key of the node with the provided value and sets the height of the node to 1.
     * @param product The key of the node.
     */
    public TreeNode(Product product) {
        super(product);
        this.height = 1;
    }
}