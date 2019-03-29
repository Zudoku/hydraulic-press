package fi.zudoku.hydraulic.domain.huffman.data;

import fi.zudoku.hydraulic.util.BitBlob;

/**
 * Represents a leaf node in a huffman tree.
 */
public class HuffmanLeafNode extends HuffmanNode {
    
    private final byte dataToCompress;
    private int amount;
    private BitBlob compressed;

    /**
     * This represents a leaf node of a huffman tree.
     * @param dataToCompress the uncompressed data that this node represents
     * @param amount the times it was found in the original data.
     */
    public HuffmanLeafNode(byte dataToCompress, int amount) {
        this.dataToCompress = dataToCompress;
        this.amount = amount;
    }

    /**
     * This is the frequency of the huffman tree.
     * @return The frequency of the chunk in the uncompressed data.
     */
    @Override
    public int getAmount() {
        return amount;
    }
    
    public byte getDataToCompress() {
        return dataToCompress;
    }
    
    /**
     * Increments the amount of times this chunk appears in the uncompressed data.
     */
    public void addOne() {
        amount++;
    }

    public BitBlob getCompressed() {
        return compressed;
    }

    public void setCompressed(BitBlob compressed) {
        this.compressed = compressed;
    }
    
    @Override
    public boolean equals(Object other) {
        return (other instanceof HuffmanLeafNode && ((HuffmanLeafNode) other).dataToCompress == dataToCompress);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.dataToCompress;
        return hash;
    }
}
