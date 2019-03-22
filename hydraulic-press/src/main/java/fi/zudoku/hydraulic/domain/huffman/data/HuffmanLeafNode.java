package fi.zudoku.hydraulic.domain.huffman.data;

public class HuffmanLeafNode extends HuffmanNode {

    public HuffmanLeafNode(byte dataToCompress, int amount) {
        super(dataToCompress, amount);
    }
}
