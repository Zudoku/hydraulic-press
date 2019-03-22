package fi.zudoku.hydraulic.domain.huffman.data;

import fi.zudoku.hydraulic.domain.generic.BinaryTree;
import fi.zudoku.hydraulic.util.BitBlob;

public class HuffmanTree {
    
    private final HuffmanNode rootNode;
    private BinaryTree searchTree;

    public HuffmanTree(HuffmanNode rootNode) {
        this.rootNode = rootNode;
    }
    
    public void initialize() {
        calculatePathForLeafNodesAndSetUpSearchTree();
    }
    public BitBlob getCompressedBitsForByte(byte input) {
        HuffmanLeafNode foundNode = (HuffmanLeafNode) searchTree.find(input);
        return foundNode.getCompressed();
    }
    
    
    // encoding the huffman tree for the compressed file
    
    public byte[] toCompressedData() {
        // encode the huffman tree to a format that can be read back when decompressing
        return null;
    }
   

    private void calculatePathForLeafNodesAndSetUpSearchTree() {
        
    }
}
