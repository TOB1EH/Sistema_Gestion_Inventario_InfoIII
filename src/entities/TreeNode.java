package entities; 
/** 
 * The TreeNode class represents a node in a tree. 
 */ 
public class TreeNode extends Node { 
    // The height of the node in the tree 
    public int height; 
    // Reference to the left child node 
    public TreeNode left; 
    // Reference to the right child node 
    public TreeNode right; 
    /** 
     * Constructor of the TreeNode class. 
     * @param d The key of the node. 
     */ 
    public TreeNode(String d) { 
        // Initialize the key of the node with the provided value 
        super(d); 
        // Set the height of the node to 1 
        height = 1; 
    } 
} 
 