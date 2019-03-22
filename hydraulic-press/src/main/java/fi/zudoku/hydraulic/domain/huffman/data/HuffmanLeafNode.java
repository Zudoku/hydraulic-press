package fi.zudoku.hydraulic.domain.huffman.data;

import fi.zudoku.hydraulic.util.BitBlob;

/**
 * Represents a leaf node in a huffman tree.
 */
public class HuffmanLeafNode extends HuffmanNode {
    
    private final byte dataToCompress;
    private int amount;
    private BitBlob compressed;

    public HuffmanLeafNode(byte dataToCompress, int amount) {
        this.dataToCompress = dataToCompress;
        this.amount = amount;
    }

    /**
     * @return The frequency of the chunk in the uncompressed data.
     */
    @Override
    public int getAmount() {
        return amount;
    }
    
    public byte getDataToCompress() {
        return dataToCompress;
    }
    
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
