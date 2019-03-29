/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.zudoku.hydraulic;

import fi.zudoku.hydraulic.domain.huffman.CompressHuffManCoding;
import fi.zudoku.hydraulic.domain.huffman.DecompressHuffManCoding;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created Mar 29, 2019
 * @author arska
 */
public class HuffManCodingDataPercistencyTest {
    // 4 = 5 times
    // 1 = 4 times
    // 2 = 3 times
    // 0 = 2 times
    // 3 = 1 times
    byte[] input = new byte[] {
        1,1,0,1,1,0,2,2,2,3,4,4,4,4,4
    };
    
    
    @Test
    public void huffmanTreeSerilizationWorks() {
        HuffmanTree originalTree = CompressHuffManCoding.buildHuffmanTreeFromInput(input);
        originalTree.initialize();
        
        byte[] result = originalTree.toCompressedData();
        
        HuffmanTree serializedTree = DecompressHuffManCoding.readHuffmanTreeFromInput(result);
        serializedTree.initialize();

        Assert.assertArrayEquals(
                originalTree.getCompressedBitsForByte((byte)0).getData(),
                serializedTree.getCompressedBitsForByte((byte)0).getData()
        );
        
        Assert.assertArrayEquals(
                originalTree.getCompressedBitsForByte((byte)1).getData(),
                serializedTree.getCompressedBitsForByte((byte)1).getData()
        );
        
        Assert.assertArrayEquals(
                originalTree.getCompressedBitsForByte((byte)2).getData(),
                serializedTree.getCompressedBitsForByte((byte)2).getData()
        );
        
        Assert.assertArrayEquals(
                originalTree.getCompressedBitsForByte((byte)3).getData(),
                serializedTree.getCompressedBitsForByte((byte)3).getData()
        );
        
        Assert.assertArrayEquals(
                originalTree.getCompressedBitsForByte((byte)4).getData(),
                serializedTree.getCompressedBitsForByte((byte)4).getData()
        );
        
        Assert.assertArrayEquals(
                originalTree.getCompressedBitsForByte((byte)5).getData(),
                serializedTree.getCompressedBitsForByte((byte)5).getData()
        );
        
        Assert.assertArrayEquals(originalTree.toCompressedData(), serializedTree.toCompressedData());

    }
}
