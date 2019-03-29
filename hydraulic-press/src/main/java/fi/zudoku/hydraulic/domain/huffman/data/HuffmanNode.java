package fi.zudoku.hydraulic.domain.huffman.data;

/**
 * This represents a node of a Huffman Tree.
 * @see HuffmanLeafNode
 * @see HuffmanInternalNode
 */
public abstract class HuffmanNode implements Comparable<HuffmanNode> {
    
    /**
     * For leaf nodes, it is the frequency of the chunk in the uncompressed data.
     * For internal nodes, it is the combined frequency of all the child leafs.
     * @return The frequency of the node
     */
    public abstract int getAmount();

    @Override
    public int compareTo(HuffmanNode t) {
        return this.getAmount() - t.getAmount();
    }
    
    
}
