package fi.zudoku.hydraulic.domain.generic;


/**
 * Binary search tree.
 * NOTE: this binary tree is not balanced.
 */
public class BinaryTree<T> {
    
    private BinaryTreeNode<T> rootNode = null;

    /**
     * Adds a new node into the binary tree, if it is not already in the tree.
     * @param key of the node.
     * @param result a value that should be contained in the node.
     */
    public void add(int key, T result) {
        rootNode = add(rootNode, key, result);
    }
    
    private BinaryTreeNode add(BinaryTreeNode current, int key, T result) {
        if (current == null) {
            return new BinaryTreeNode(key, result);
        }
 
        if (key < current.getKey()) {
            BinaryTreeNode newLeft = add(current.getLeft(), key, result);
            current.setLeft(newLeft);
        } else {
            BinaryTreeNode newRight = add(current.getRight(), key, result);
            current.setRight(newRight);
        }
 
        return current;
    }
    
    /**
     * Finds the result with the given key.
     * @param key some value that matches the one given in the add method.
     * @return found result, or null if not found.
     */
    public T find(int key) {
        return find(rootNode, key);
    }
    
    private T find(BinaryTreeNode<T> node, int key) {
        if (node == null) {
            return null;
        }
        if (node.getKey() == key) {
            return node.getResult();
        }
        
        if (node.getKey() > key) {
            return find(node.getLeft(), key);
        } else {
            return find(node.getRight(), key);
        }
    }
    
    /**
     * Removes the result with the given key.
     * If nothing is found, does not do anything.
     * @param key some value that matches the one given in the add method.
     */
    public void remove(int key) {
        rootNode = remove(rootNode, key);
    }
    
    private BinaryTreeNode remove(BinaryTreeNode node, int key) {
        if (node == null) {
            return null;
        }
 
        if (key == node.getKey()) {
            return removeNode(node);
        } 
        if (key < node.getKey()) {
            node.setLeft(remove(node.getLeft(), key));
            return node;
        }
        node.setRight(remove(node.getRight(), key));
        return node;
    }
    
    private BinaryTreeNode removeNode(BinaryTreeNode nodeToBeRemoved) {
        if (nodeToBeRemoved.getLeft() == null && nodeToBeRemoved.getRight() == null) {
            return null;
        }
        if (nodeToBeRemoved.getRight() == null) {
            return nodeToBeRemoved.getLeft();
        }
 
        if (nodeToBeRemoved.getLeft() == null) {
            return nodeToBeRemoved.getRight();
        }
       
        BinaryTreeNode minNode = findMinNode(nodeToBeRemoved.getRight());
       
        BinaryTreeNode replacement = new BinaryTreeNode(minNode.getKey(), minNode.getResult());
        replacement.setLeft(nodeToBeRemoved.getLeft());
        replacement.setRight(remove(nodeToBeRemoved.getRight(), minNode.getKey()));
        return replacement;
    }
    
    private BinaryTreeNode findMinNode(BinaryTreeNode node) {
        return node.getLeft() == null ? node : findMinNode(node.getLeft());
    }
    
    /**
     * Finds the node with the smallest value in the binary tree.
     * @return BinaryTreeNode<T> with the smallest value or *null*
     */
    public BinaryTreeNode<T> findMinNode() {
        return findMinNode(rootNode);
    }

    /**
     * Returns the topmost node of this binary tree.
     * @return rootNode or null
     */
    public BinaryTreeNode<T> getRootNode() {
        return rootNode;
    }
    
    /**
     * Calculates how many nodes are in this Binary Tree.
     * @return how many nodes there are
     */
    public int size() {
        return size(rootNode);
    }
    
    private int size(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftSize = size(node.getLeft());
        int rightSize = size(node.getRight());
        
        return 1 + leftSize + rightSize;
    }
}
