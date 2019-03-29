package fi.zudoku.hydraulic.domain.huffman.data;

import fi.zudoku.hydraulic.domain.generic.BinaryTree;
import fi.zudoku.hydraulic.util.BitBlob;

/**
 * This represents a complete Huffman tree. 
 * The tree must be initialized before usage, with the *initialize* method
 * 
 * This class contains a reference to the root node of the tree.
 * After calling the initialize method, 
 * it also has a binary tree to quickly find leafs that we need for compression
 */
public class HuffmanTree {
    
    private final HuffmanNode rootNode;
    private BinaryTree<HuffmanLeafNode> searchTree;

    public HuffmanTree(HuffmanNode rootNode) {
        this.rootNode = rootNode;
    }
    
    public void initialize() {
        searchTree = new BinaryTree<>();
        calculatePathForLeafNodesAndSetUpSearchTree();
    }
    /**
     * This returns the compressed bits for the given chunk of data
     * @param input, the uncompressed chunk of data that we want to compress
     * (the size of the chunk probably changes from byte to something else)
     * @return compressed bits 
     */
    public BitBlob getCompressedBitsForByte(byte input) {
        HuffmanLeafNode foundNode = searchTree.find(input);
        return foundNode.getCompressed();
    }
    
    /**
     * Serializes the huffman tree to a format that can be read back when decompressing
     */
    public byte[] toCompressedData() {
        // TODO
        return null;
    }
   

    /**
     * This travels down from rootNode and calculates the path 
     * (meaning the code / the compressed value) for all the leaf nodes it encounters.
     * This needs to be done, because we store that information in the leaf nodes themselves, 
     * so we dont have to calculate it every time.
     * When we find a leaf node, we add it to the binary tree.
     */
    private void calculatePathForLeafNodesAndSetUpSearchTree() {
        travelDownNode(rootNode, new BitBlob());
    }
    
    private void travelDownNode(HuffmanNode node, BitBlob bitBlob) {
        if (node instanceof HuffmanLeafNode) {
            HuffmanLeafNode leafNode = (HuffmanLeafNode) node;
            leafNode.setCompressed(bitBlob.copy());
            searchTree.add(leafNode.getDataToCompress(), leafNode);
        } else {
            HuffmanInternalNode internalNode = (HuffmanInternalNode) node;
            if (internalNode.getLeft() != null) {
                BitBlob leftBitBlob = bitBlob.copy();
                leftBitBlob.appendZero();
                travelDownNode(internalNode.getLeft(), leftBitBlob);
            }
            if (internalNode.getLeft() != null) {
                BitBlob rightBitBlob = bitBlob.copy();
                rightBitBlob.appendOne();
                travelDownNode(internalNode.getRight(), rightBitBlob);
            }
        }
    }
}
