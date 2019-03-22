package fi.zudoku.hydraulic.domain.huffman.data;

import java.util.Objects;

public class HuffmanInternalNode extends HuffmanNode {
    
    private final HuffmanNode left;
    private final HuffmanNode right;

    public HuffmanInternalNode(HuffmanNode left, HuffmanNode right) {
        this.left = left;
        this.right = right;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    @Override
    public int getAmount() {
        return left.getAmount() + right.getAmount();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof HuffmanInternalNode) {
            HuffmanInternalNode otherNode = (HuffmanInternalNode) other;
            boolean sameLeftLeaf = this.left.equals(otherNode.left);
            boolean sameRightLeaf = this.right.equals(otherNode.right);
            return sameLeftLeaf && sameRightLeaf;
        } else {
            return false;
        }
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.left);
        hash = 89 * hash + Objects.hashCode(this.right);
        return hash;
    }

    
   

}
