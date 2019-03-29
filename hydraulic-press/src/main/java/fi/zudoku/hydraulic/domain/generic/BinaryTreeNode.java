package fi.zudoku.hydraulic.domain.generic;

public class BinaryTreeNode {
    private final byte value;
    private final Object result;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
 
    public BinaryTreeNode(byte value, Object result) {
        this.value = value;
        this.result = result;
        right = null;
        left = null;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public BinaryTreeNode getRight() {
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

    public Object getResult() {
        return result;
    }
}
