package entities;

/**
 * Represents a node in a binary tree.
 * Extends the Node class and contains information about the node's element, height, left child, and right child.
 *
 * @param <T> the type of element stored in the node
 */
public class TreeNode<T> extends Node<T> {
    T element;
    Integer height;
    Node<T> left;
    Node<T> right;

    /**
     * Constructs a TreeNode object with the specified element.
     * Initializes the height
     * Initializes the left child, and right child to null.
     *
     * @param element the element to be stored in the node
     */
    public TreeNode(T element) {
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
    public TreeNode(T element, TreeNode<T> left, TreeNode<T> right) {
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
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    /**
     * Returns the left child of the TreeNode object.
     *
     * @return the left child of the node
     */
    public Node<T> getLeft() {
        return left;
    }

    /**
     * Sets the right child of the TreeNode object.
     *
     * @param right the right child to be set
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }

    /**
     * Returns the right child of the TreeNode object.
     *
     * @return the right child of the node
     */
    public Node<T> getRight() {
        return right;
    }

    public void printElementNode() {
        System.out.println(this.element + " ");
    }
}
