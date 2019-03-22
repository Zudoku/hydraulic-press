package fi.zudoku.hydraulic.domain.huffman;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanLeafNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;
import java.util.PriorityQueue;

public class CompressHuffManCoding implements Operation{

    @Override
    public byte[] excecute(byte[] input) {
        // construct huffman tree
        HuffmanTree tree = buildHuffmanTreeFromInput(input);
        
        // encode huffman tree to the beginning of the result
        
        // go through input, and replace bytes from input with the huffman tree bits
        
        return null;
    }

    
    public static HuffmanTree buildHuffmanTreeFromInput(byte[] input) {
        PriorityQueue<HuffmanNode> nodes = new PriorityQueue<>();
        for(byte data: input) {
            HuffmanLeafNode nodeToAdd = new HuffmanLeafNode(data, 1);
            
            if (nodes.contains(nodeToAdd)) {
                for (HuffmanNode node : nodes) {
                    if (node.equals(nodeToAdd)) {
                        node.addOne();
                    }
                }
            } else {
                nodes.add(nodeToAdd);
            }
        }
        
       
        
        nodes = new PriorityQueue<>(nodes);
        int size = nodes.size();
        
        for (int i = 0; i < size; i++) {
            HuffmanNode n = nodes.remove();
            
            System.out.println(n.getDataToCompress() + " " + n.getAmount());
        }
        
        
       

        return null;
    }
   
}
