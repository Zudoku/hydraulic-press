package fi.zudoku.hydraulic.domain.generic;


/**
 * Binary search tree.
 */
public class BinaryTree<T> {
    
    private BinaryTreeNode<T> rootNode = null;

    /**
     * Adds a new node into the binary tree, if it is not already in the tree.
     * @param key of the node.
     * @param result a value that should be contained in the node.
     */
    public void add(byte key, T result) {
        rootNode = add(rootNode, key, result);
    }
    
    private BinaryTreeNode add(BinaryTreeNode current, byte key, T result) {
        if (current == null) {
            return new BinaryTreeNode(key, result);
        }
 
        if (key < current.getValue()) {
            BinaryTreeNode newLeft = add(current.getLeft(), key, result);
            current.setLeft(newLeft);
        } else if (key > current.getValue()) {
            BinaryTreeNode newRight = add(current.getRight(), key, result);
            current.setRight(newRight);
        } else {
            return current;
        }
 
        return current;
    }
    
    /**
     * Finds the result with the given value.
     * @param key some value that matches the one given in the add method.
     * @return found result, or null if not found.
     */
    public T find(byte key) {
        return find(rootNode, key);
    }
    
    private T find(BinaryTreeNode<T> node, byte key) {
        if (node == null) {
            return null;
        }
        if (node.getValue() == key) {
            return node.getResult();
        }
        
        if (node.getValue() > key) {
            return find(node.getLeft(), key);
        } else {
            return find(node.getRight(), key);
        }
    }

    public BinaryTreeNode<T> getRootNode() {
        return rootNode;
    }
}
