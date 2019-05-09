package fi.zudoku.hydraulic.domain.generic;

public class BinaryTreeNode<T> {
    private final int value;
    private final T result;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
 
    /**
     * Instance of a binary tree node. (which itself is a binary tree.)
     * @param key of the node.
     * @param result a value that should be contained in the node.
     */
    public BinaryTreeNode(int key, T result) {
        this.value = key;
        this.result = result;
        right = null;
        left = null;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public int getValue() {
        return value;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public T getResult() {
        return result;
    }
}
