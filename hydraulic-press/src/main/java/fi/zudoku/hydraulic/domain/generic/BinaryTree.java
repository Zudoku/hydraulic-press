package fi.zudoku.hydraulic.domain.generic;

import java.lang.reflect.Array;

/**
 * Binary search tree.
 */
public class BinaryTree<T> {
    
    private BinaryTreeNode<T> rootNode = null;

    /**
     * Adds a new node into the binary tree, if it is not already in the tree.
     * @param value of the node.
     * @param result a value that should be contained in the node.
     */
    public void add(byte value, T result) {
        rootNode = add(rootNode, value, result);
    }
    
    private BinaryTreeNode add(BinaryTreeNode current, byte value, T result) {
        if (current == null) {
            return new BinaryTreeNode(value, result);
        }
 
        if (value < current.getValue()) {
            BinaryTreeNode newLeft = add(current.getLeft(), value, result);
            current.setLeft(newLeft);
        } else if (value > current.getValue()) {
            BinaryTreeNode newRight = add(current.getRight(), value, result);
            current.setRight(newRight);
        } else {
            return current;
        }
 
        return current;
    }
    
    /**
     * Finds the result with the given value.
     * @param value some value that matches the one given in the add method.
     * @return found result, or null if not found.
     */
    public T find(byte value) {
        return find(rootNode, value);
    }
    
    private T find(BinaryTreeNode<T> node, byte value) {
        if (node == null) {
            return null;
        }
        if (node.getValue() == value) {
            return node.getResult();
        }
        
        if (node.getValue() > value) {
            return find(node.getLeft(), value);
        } else {
            return find(node.getRight(), value);
        }
    }
    
    public T[] getAll(Class<T[]> type) {
        return getChildNodes(rootNode, type);
    }
    
    private T[] getChildNodes(BinaryTreeNode<T> node, Class<T[]> type) {
        T[] leftChildren = node.getLeft() == null ? newArray(type, 0) : getChildNodes(node.getLeft(), type);
        T[] rightChildren = node.getRight()== null ? newArray(type, 0) : getChildNodes(node.getRight(), type);
        
        T[] result = newArray(type, 1 + leftChildren.length + rightChildren.length);
        
        result[0] = node.getResult();
        
        System.arraycopy(leftChildren, 0, result, 1, leftChildren.length);
        System.arraycopy(rightChildren, 0, result, 1 + leftChildren.length, leftChildren.length);
        
        return result;
    }
    
    public static <T> T[] newArray(Class<T[]> type, int size) {
        return type.cast(Array.newInstance(type.getComponentType(), size));
    }
    
}
