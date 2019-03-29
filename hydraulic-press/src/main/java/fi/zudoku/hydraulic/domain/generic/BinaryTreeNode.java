package fi.zudoku.hydraulic.domain.generic;

public class BinaryTreeNode<T> {
    private final byte value;
    private final T result;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
 
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
