package fi.zudoku.hydraulic;

import fi.zudoku.hydraulic.domain.huffman.CompressHuffManCoding;
import fi.zudoku.hydraulic.domain.huffman.DecompressHuffManCoding;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;
import org.junit.Assert;
import org.junit.Test;

public class HuffManCodingDataPercistencyTest {
    // 4 = 5 times
    // 1 = 4 times
    // 2 = 3 times
    // 0 = 2 times
    // 3 = 1 times
    byte[] input1 = new byte[] {
        1, 1, 0, 1, 1, 0, 2, 2, 2, 3, 4, 4, 4, 4, 4
    };
    byte[] input2 = ("Contrary to popular belief, Lorem Ipsum is not simply random text. "
            + "It has roots in a piece of classical Latin literature from 45 BC, making it"
            + " over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney "
            + "College in Virginia, looked up one of the more obscure Latin words, consectetur,"
            + " from a Lorem Ipsum passage, and going through the cites of the word in classical"
            + " literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1."
            + "10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil)"
            + " by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular "
            + "during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes"
            + " from a line in section 1.10.32.").getBytes();
    
    /**
     * This makes test makes sure that the (de)serialization of the tree does not lose data.
     */
    @Test
    public void huffmanTreeSerilizationWorks() {
        assertTreeIsSerializedRight(input1);
        assertTreeIsSerializedRight(input2);
    }
    
    private void assertTreeIsSerializedRight(byte[] data) {
        HuffmanTree originalTree = CompressHuffManCoding.buildHuffmanTreeFromInput(data);
        originalTree.initialize();
        
        byte[] result = originalTree.toCompressedData();
        
        HuffmanTree serializedTree = DecompressHuffManCoding.readHuffmanTreeFromInput(result);
        serializedTree.initialize();
        
        for (byte i : data) {
            Assert.assertArrayEquals(
                originalTree.getCompressedBitsForByte(i).getData(),
                serializedTree.getCompressedBitsForByte(i).getData()
            );
        }

        Assert.assertArrayEquals(originalTree.toCompressedData(), serializedTree.toCompressedData());
    }
}
