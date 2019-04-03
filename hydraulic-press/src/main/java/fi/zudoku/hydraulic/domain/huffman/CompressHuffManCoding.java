package fi.zudoku.hydraulic.domain.huffman;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanLeafNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;
import java.util.PriorityQueue;

public class CompressHuffManCoding implements Operation {

    @Override
    public byte[] execute(byte[] input) {
        // construct huffman tree
        HuffmanTree tree = buildHuffmanTreeFromInput(input);
        tree.initialize();
        
        // encode huffman tree to the beginning of the result
        byte[] header = tree.toCompressedData();
        
        // go through input, and replace bytes from input with the huffman tree bits
        return header;
    }

    /**
     * Traverse through the input one byte at a time, and construct a huffman tree with it.
     * @param input data to build huffman tree from.
     * @return a complete HuffmanTree.
     */
    public static HuffmanTree buildHuffmanTreeFromInput(byte[] input) {
        PriorityQueue<HuffmanLeafNode> nodes = buildMinHeapFromInput(input);
        
        return new HuffmanTree(nodes);
    }
    
    private static PriorityQueue<HuffmanLeafNode> buildMinHeapFromInput(byte[] input) {
        PriorityQueue<HuffmanLeafNode> nodes = new PriorityQueue<>();
        for (byte data: input) {
            HuffmanLeafNode nodeToAdd = new HuffmanLeafNode(data, 1);
            
            if (nodes.contains(nodeToAdd)) {
                for (HuffmanLeafNode node : nodes) {
                    if (node.equals(nodeToAdd)) {
                        node.addOne();
                    }
                }
            } else {
                nodes.add(nodeToAdd);
            }
        }
        return nodes;
    }
}
