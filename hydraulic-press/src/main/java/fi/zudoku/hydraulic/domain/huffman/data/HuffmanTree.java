package fi.zudoku.hydraulic.domain.huffman.data;

public class HuffmanTree {
    
    // creating of the tree from fresh data
    
    public void addToTree(char foundUnit) {
        //the type of argument might change, 
        // because we might want to treat a bigger area as one huffman unit (2/4/8 bytes for example?)
    }
    
    public static HuffmanTree buildHuffmanTreeFromInput(byte[] data) {
        return null;
    }
    
    
    // using the tree to compress the actual input
    
    public void getCompressedDataForUnit(char foundUnit) {
        
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
