package fi.zudoku.hydraulic;

import fi.zudoku.hydraulic.domain.huffman.CompressHuffManCoding;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanInternalNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanLeafNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CompressHuffManCodingTest {
    
    // 4 = 5 times
    // 1 = 4 times
    // 2 = 3 times
    // 0 = 2 times
    // 3 = 1 times
    byte[] input = new byte[] {
        1,1,0,1,1,0,2,2,2,3,4,4,4,4,4
    };
    
    @Test
    public void huffmanTreeStructureIsValid() {
        HuffmanTree tree = CompressHuffManCoding.buildHuffmanTreeFromInput(input);
        tree.initialize();
        
        HuffmanInternalNode rootNode = (HuffmanInternalNode) tree.getRootNode();
        
        assertCorrectHuffManNode(rootNode, Integer.MAX_VALUE);
        
    }
    
    private void assertCorrectHuffManNode(HuffmanNode node, int previousFrequency) {
        if (node instanceof HuffmanLeafNode) {
            HuffmanLeafNode leafNode = (HuffmanLeafNode) node;
            assertTrue(previousFrequency > leafNode.getAmount());
        } else if (node instanceof HuffmanInternalNode) {
            HuffmanInternalNode internalNode = (HuffmanInternalNode) node;
            assertTrue(previousFrequency > internalNode.getAmount());
            assertCorrectHuffManNode(internalNode.getLeft(), internalNode.getAmount());
            assertCorrectHuffManNode(internalNode.getRight(), internalNode.getAmount());
        } else {
            assertTrue(false);
        }
    }
}