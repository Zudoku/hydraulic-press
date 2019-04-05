package fi.zudoku.hydraulic.domain.huffman;

import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;
import static fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree.DYNAMIC_CHUNK_SIZE;
import static fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree.HEADER_BYTES;
import static fi.zudoku.hydraulic.util.ByteUtils.intFromByteArray;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

public class CompressHuffManCodingTest {
    
    // 4 = 5 times
    // 1 = 4 times
    // 2 = 3 times
    // 0 = 2 times
    // 3 = 1 times
    byte[] input = new byte[] {
        1, 1, 0, 1, 1, 0, 2, 2, 2, 3, 4, 4, 4, 4, 4
    };
    
    @Test
    public void testTreeIsSerializedTheRightWay() {
        byte[] testInput = input;
        HuffmanTree tree = CompressHuffManCoding.buildHuffmanTreeFromInput(testInput);
        
        CompressHuffManCoding compressHuffmanCoding = new CompressHuffManCoding();
        
        byte[] result = compressHuffmanCoding.execute(testInput);
        
        int chunks = intFromByteArray(result, 0);
        byte byteCutOff = result[4];
        // Check headers
        Assert.assertEquals(chunks, tree.getLeafNodeAmount());
        Assert.assertEquals(1, byteCutOff); // calculated by hand
        
        HashMap<Byte, Integer> results = new HashMap<>();
        // check dynamic headers
        for (int index = 0; index < tree.getLeafNodeAmount(); index++) {
            int resultIndex = HEADER_BYTES + (index * DYNAMIC_CHUNK_SIZE);
            byte dataToCompress = result[resultIndex];
            int nodeFrequency = intFromByteArray(result, resultIndex + 1);
            // Check that it is unique
            Assert.assertFalse(results.containsKey(dataToCompress));
            results.put(byteCutOff, nodeFrequency);
            // Check that it is the same order as in tree
            Assert.assertEquals(dataToCompress, tree.getInputNodes()[index].getDataToCompress());

        }
        
    }
    
}
