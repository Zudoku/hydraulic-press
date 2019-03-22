package fi.zudoku.hydraulic.domain.huffman.data;

public class HuffmanLeafNode extends HuffmanNode {
    
    private final byte dataToCompress;
    private int amount;

    public HuffmanLeafNode(byte dataToCompress, int amount) {
        this.dataToCompress = dataToCompress;
        this.amount = amount;
    }

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
