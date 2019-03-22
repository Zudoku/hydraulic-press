package fi.zudoku.hydraulic.domain.huffman.data;

public abstract class HuffmanNode implements Comparable<HuffmanNode> {
    protected final byte dataToCompress;
    protected int amount;
    
    public HuffmanNode(byte dataToCompress, int amount) {
        this.dataToCompress = dataToCompress;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public byte getDataToCompress() {
        return dataToCompress;
    }

    public void addOne() {
        this.amount++;
    }
    
    @Override
    public boolean equals(Object other) {
        return (other instanceof HuffmanNode 
                && ((HuffmanNode)other).dataToCompress == dataToCompress);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.dataToCompress;
        return hash;
    }

    @Override
    public int compareTo(HuffmanNode t) {
        return this.amount - t.amount;
    }
    
    
}
