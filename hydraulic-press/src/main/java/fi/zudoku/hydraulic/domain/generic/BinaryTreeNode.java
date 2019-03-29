package fi.zudoku.hydraulic.domain.generic;

public class BinaryTreeNode<T> {
    private final byte value;
    private final T result;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
 
    /**
     * Instance of a binary tree node. (which itself is a binary tree.)
     * @param value of the node.
     * @param result a value that should be contained in the node.
     */
    public BinaryTreeNode(byte value, T result) {
        this.value = value;
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
