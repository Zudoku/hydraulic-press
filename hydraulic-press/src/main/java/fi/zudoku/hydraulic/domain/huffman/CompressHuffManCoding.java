package fi.zudoku.hydraulic.domain.huffman;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanLeafNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;
import fi.zudoku.hydraulic.util.BitBlob;
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
        BitBlob compressedData = new BitBlob();
        for(byte b: input) {
            BitBlob compressed = tree.getCompressedBitsForByte(b);
            compressedData = BitBlob.append(compressedData, compressed);
        }
        // Combine header and compressed part
        byte[] result = new byte[header.length + compressedData.getData().length];
        System.arraycopy(header, 0, result, 0, header.length);
        System.arraycopy(compressedData.getData(), 0, result, header.length, compressedData.getData().length);
        
        return result;
    }

    /**
     * Traverse through the input one byte at a time, and construct a huffman tree with it.
     * @param input data to build huffman tree from.
     * @return a complete HuffmanTree.
     */
    public static HuffmanTree buildHuffmanTreeFromInput(byte[] input) {
        PriorityQueue<HuffmanLeafNode> nodes = buildMinHeapFromInput(input);
        
        HuffmanLeafNode[] result = new HuffmanLeafNode[nodes.size()];
        int size = nodes.size();
        for(int i = 0; i < size; i++) {
            result[i] = nodes.poll();
        }
        
        return new HuffmanTree(result);
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
        return new PriorityQueue<>(nodes);
    }
}
