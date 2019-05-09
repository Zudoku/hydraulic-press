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
    public void add(int key, T result) {
        rootNode = add(rootNode, key, result);
    }
    
    private BinaryTreeNode add(BinaryTreeNode current, int key, T result) {
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
    public T find(int key) {
        return find(rootNode, key);
    }
    
    private T find(BinaryTreeNode<T> node, int key) {
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
    
    public void remove(int key) {
        rootNode = remove(rootNode, key);
    }
    
    private BinaryTreeNode remove(BinaryTreeNode node, int key) {
        if (node == null) {
            return null;
        }
 
        if (key == node.getValue()) {
            return removeNode(node);
        } 
        if (key < node.getValue()) {
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
       
        BinaryTreeNode replacement = new BinaryTreeNode(minNode.getValue(), minNode.getResult());
        replacement.setLeft(nodeToBeRemoved.getLeft());
        replacement.setRight(remove(nodeToBeRemoved.getRight(), minNode.getValue()));
        return replacement;
    }
    
    private BinaryTreeNode findMinNode(BinaryTreeNode node) {
        return node.getLeft() == null ? node : findMinNode(node.getLeft());
    }

    public BinaryTreeNode<T> getRootNode() {
        return rootNode;
    }
}
