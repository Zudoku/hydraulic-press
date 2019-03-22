package fi.zudoku.hydraulic.domain.huffman.data;

public abstract class HuffmanNode implements Comparable<HuffmanNode> {
    
    public abstract int getAmount();

    @Override
    public int compareTo(HuffmanNode t) {
        return this.getAmount() - t.getAmount();
    }
    
    
}
