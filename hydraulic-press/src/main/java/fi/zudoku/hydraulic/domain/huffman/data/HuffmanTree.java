package fi.zudoku.hydraulic.domain.huffman.data;

import fi.zudoku.hydraulic.domain.generic.BinaryTree;
import static fi.zudoku.hydraulic.domain.huffman.CompressHuffManCoding.buildHuffmanTreeFromMinHeap;
import fi.zudoku.hydraulic.util.BitBlob;
import static fi.zudoku.hydraulic.util.ByteUtils.intFromByteArray;
import java.nio.ByteBuffer;
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
    
    private final HuffmanNode rootNode;
    private BinaryTree<HuffmanLeafNode> searchTree;
    private PriorityQueue<HuffmanLeafNode> inputNodes;
    private int leafNodes = 0;

    /**
     * Creates an instance of this class with the given nodes.
     * @param inputNodes min heap of leaf nodes in the huffman tree
     */
    public HuffmanTree(PriorityQueue<HuffmanLeafNode> inputNodes) {
        PriorityQueue<HuffmanNode> nodes = new PriorityQueue<>(inputNodes);
        
        // DEBUG!
        PriorityQueue<HuffmanLeafNode> debugNodes = new PriorityQueue<>(inputNodes);
        while (debugNodes.size() > 0) {
            HuffmanLeafNode debugNode = debugNodes.poll();
            System.out.println(debugNode.getDataToCompress() + " == " + debugNode.getActualAmount() + " == " + debugNode.getAmount());
        }
        System.out.println("---");
        // END DEBUG!
        
        while (nodes.size() > 1) {
            HuffmanNode lower = nodes.poll();
            HuffmanNode higher = nodes.poll();
            HuffmanNode combined = new HuffmanInternalNode(lower, higher);
            nodes.add(combined);
        }
        this.rootNode = nodes.poll();
        this.inputNodes = inputNodes;
    }
    
    /**
     * This initializes the tree. It updates the binary search tree
     * and calculates the compressed data for each leafnode.
     * The tree must be initialized before usage, with this method.
     */
    public void initialize() {
        searchTree = new BinaryTree<>();
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
        return foundNode.getCompressed();
    }
    
    /**
     * Serializes the huffman tree to a format that can be read back when decompressing.
     * This can be saved to a file and read back.
     * 4 first bytes = how many nodes there are
     * dynamic bytes bytes = 1 byte for the uncompressed data, 
     * 4 for the amount it occurs in the original data
     * @return serialized byte array for this tree. 
     */
    public byte[] toCompressedData() {
        int headerBytes = 4;
        int dynamicBytes = leafNodes * (1 + 4);
        byte[] result = new byte[headerBytes + dynamicBytes];
        
        result[0] = (byte) (leafNodes >> 24);
        result[1] = (byte) (leafNodes >> 16);
        result[2] = (byte) (leafNodes >> 8);
        result[3] = (byte) (leafNodes );
        
        HuffmanLeafNode[] leafs = searchTree.getAll(HuffmanLeafNode[].class);
        
        for(int index = 0; index < leafs.length; index++) {
            int resultIndex = 4 + (index * 5);
            HuffmanLeafNode currentLeafNode = leafs[index];
            result[resultIndex] = currentLeafNode.getDataToCompress();
            result[resultIndex + 1] = (byte) (currentLeafNode.getActualAmount() >> 24);
            result[resultIndex + 2] = (byte) (currentLeafNode.getActualAmount() >> 16);
            result[resultIndex + 3] = (byte) (currentLeafNode.getActualAmount() >> 8);
            result[resultIndex + 4] = (byte) (currentLeafNode.getActualAmount() );
        }
        
        return result;
    }
    
    public static HuffmanTree fromSerializedData(byte[] input) {
        int nodeAmount = intFromByteArray(input, 0);
        PriorityQueue<HuffmanNode> nodes = new PriorityQueue<>();
        
        for(int index = 0; index < nodeAmount; index++) {
            int inputIndex = 4 + (index * 5);
            byte dataToCompress = input[inputIndex];
            int nodeFrequency = intFromByteArray(input, inputIndex + 1);
            HuffmanLeafNode newNode = new HuffmanLeafNode(dataToCompress, nodeFrequency);
            nodes.add(newNode);
        }
        
        return buildHuffmanTreeFromMinHeap(nodes);
    }
   

    /**
     * This travels down from rootNode and calculates the path 
     * (meaning the code / the compressed value) for all the leaf nodes it encounters.
     * This needs to be done, because we store that information in the leaf nodes themselves, 
     * so we dont have to calculate it every time.
     * When we find a leaf node, we add it to the binary tree.
     */
    private void calculatePathForLeafNodesAndSetUpSearchTree() {
        leafNodes = 0;
        travelDownNode(rootNode, new BitBlob());
    }
    
    private void travelDownNode(HuffmanNode node, BitBlob bitBlob) {
        if (node instanceof HuffmanLeafNode) {
            HuffmanLeafNode leafNode = (HuffmanLeafNode) node;
            leafNode.setCompressed(bitBlob.copy());
            searchTree.add(leafNode.getDataToCompress(), leafNode);
            leafNodes++;
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

    public HuffmanNode getRootNode() {
        return rootNode;
    }

    public BinaryTree<HuffmanLeafNode> getSearchTree() {
        return searchTree;
    }
    
}
