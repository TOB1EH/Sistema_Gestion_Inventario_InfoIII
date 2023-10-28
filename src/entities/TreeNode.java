package entities;

/**
 * Represents a node in a binary tree.
 * Extends the Node class and contains information about the node's element, height, left child, and right child.
 *
 * @param <AnyType> the type of element stored in the node
 */
public class TreeNode<AnyType> extends Node<AnyType> {
    private Integer height;
    private Node<AnyType> left;
    private Node<AnyType> right;

    /**
     * Constructs a TreeNode object with the specified element.
     * Initializes the height
     * Initializes the left child, and right child to null.
     *
     * @param element the element to be stored in the node
     */
    public TreeNode(AnyType element) {
        super(element);
        this.height = 1;
        this.left = null;
        this.right = null;
    }

    /**
     * Constructs a TreeNode object with the specified element, left child, and right child.
     *
     * @param element the element to be stored in the node
     * @param left    the left child of the node
     * @param right   the right child of the node
     */
    public TreeNode(AnyType element, TreeNode<AnyType> left, TreeNode<AnyType> right) {
        super(element);
        this.left = left;
        this.right = right;
    }

    /**
     * Sets the height of the TreeNode object.
     *
     * @param height the height to be set
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Returns the height of the TreeNode object.
     *
     * @return the height of the node
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Sets the left child of the TreeNode object.
     *
     * @param left the left child to be set
     */
    public void setLeft(Node<AnyType> left) {
        this.left = left;
    }

    /**
     * Returns the left child of the TreeNode object.
     *
     * @return the left child of the node
     */
    public Node<AnyType> getLeft() {
        return left;
    }

    /**
     * Sets the right child of the TreeNode object.
     *
     * @param right the right child to be set
     */
    public void setRight(Node<AnyType> right) {
        this.right = right;
    }

    /**
     * Returns the right child of the TreeNode object.
     *
     * @return the right child of the node
     */
    public Node<AnyType> getRight() {
        return right;
    }
}
