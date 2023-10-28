package entities;

public class Node<AnyType> {
    private Integer heigth;
    private AnyType element;
    private Node<AnyType> left;
    private Node<AnyType> right;


    public Node(AnyType element) {
        this.heigth = 1;
        this.element = element;
        this.left = null;
        this.right = null;
    }

    public Node(AnyType element, Node<AnyType> left, Node<AnyType> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
}
