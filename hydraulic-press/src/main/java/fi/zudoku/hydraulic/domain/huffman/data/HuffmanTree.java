package fi.zudoku.hydraulic.domain.huffman.data;

import fi.zudoku.hydraulic.domain.generic.BinaryTree;
import fi.zudoku.hydraulic.util.BitBlob;
import java.util.PriorityQueue;

/**
 * This represents a complete Huffman tree. 
 * The tree must be initialized before usage, with the *initialize* method
 * 
 * This class contains a reference to the root node of the tree.
 * After calling the initialize method, 
 * it also has a binary tree to quickly find leafs that we need for compression
 */
public class HuffmanTree {
    
    public static final int HEADER_BYTES = 5;
    public static final int DYNAMIC_CHUNK_SIZE =  1 + 4;
    
    private final HuffmanNode rootNode;
    private final BinaryTree<HuffmanLeafNode> searchTree;
    private final HuffmanLeafNode[] inputNodes;

    /**
     * Creates an instance of this class with the given nodes.
     * @param inputNodes min heap of leaf nodes in the huffman tree
     */
    public HuffmanTree(HuffmanLeafNode[] inputNodes) {
        PriorityQueue<HuffmanNode> nodes = new PriorityQueue<>();
        
        for (int i = 0; i < inputNodes.length; i++) {
            HuffmanLeafNode currentNode = inputNodes[i];
            nodes.add(currentNode);
        }
        
        while (nodes.size() > 1) {
            HuffmanNode lower = nodes.poll();
            HuffmanNode higher = nodes.poll();
            HuffmanNode combined = new HuffmanInternalNode(lower, higher);
            nodes.add(combined);
        }
        this.rootNode = nodes.poll();
        this.inputNodes = inputNodes;
        this.searchTree = new BinaryTree<>();
        calculatePathForLeafNodesAndSetUpSearchTree();
    }
    
    /**
     * This returns the compressed bits for the given chunk of data.
     * @param input the uncompressed chunk of data that we want to compress
     * (the size of the chunk probably changes from byte to something else)
     * @return compressed bits 
     */
    public BitBlob getCompressedBitsForByte(byte input) {
        HuffmanLeafNode foundNode = searchTree.find(input);
        return foundNode.getCompressed().copy();
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
            if (internalNode.getRight() != null) {
                BitBlob rightBitBlob = bitBlob.copy();
                rightBitBlob.appendOne();
                travelDownNode(internalNode.getRight(), rightBitBlob);
            }
        }
    }

    public HuffmanNode getRootNode() {
        return rootNode;
    }

    public BinaryTree<HuffmanLeafNode> getSearchTree() {
        return searchTree;
    }
    
    public int getLeafNodeAmount() {
        return this.inputNodes.length;
    }

    public HuffmanLeafNode[] getInputNodes() {
        return inputNodes;
    }
}
