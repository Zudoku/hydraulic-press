package fi.zudoku.hydraulic.domain.huffman.data;

public class HuffmanTree {
    
    private final HuffmanNode rootNode;

    public HuffmanTree(HuffmanNode rootNode) {
        this.rootNode = rootNode;
    }
    
    // using the tree to compress the actual input
    
    public void getCompressedDataForUnit(byte foundUnit) {
        
    }
    
    // encoding the huffman tree for the compressed file
    
    public byte[] toCompressedData() {
        // encode the huffman tree to a format that can be read back with 
        // readHuffmanTreeFromCompressedData(...)
        return null;
    }
   
    // decoding the huffman tree from the compressed file
    
    public static HuffmanTree readHuffmanTreeFromCompressedData(byte[] data) {
        return null;
    }
}
