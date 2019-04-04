/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.zudoku.hydraulic;


import fi.zudoku.hydraulic.util.BitBlob;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created Apr 4, 2019
 * @author arska
 */
public class BitBlobTest {

    @Test
    public void BitBlobSimpleAppendOneTest() {
        BitBlob first = new BitBlob();
        
        first.appendOne();
        byte result = -128; // 1000 0000
        Assert.assertTrue(first.getData()[0] == result);
    }
    
    @Test
    public void BitBlobSimpleAppendTest() {
        BitBlob first = new BitBlob();
        BitBlob second = new BitBlob();
        
        first.appendOne();
        first.appendOne();
        second.appendZero();
        second.appendOne();
        
        BitBlob bitBlob = BitBlob.append(first, second);
        byte result = -48; // 1101 0000
        Assert.assertTrue(bitBlob.getData()[0] == result);
    }
    
    @Test
    public void BitBlobSimpleAppendOneTaest() {
        BitBlob first = new BitBlob(30, new byte[] {0, -86, 85, -4});
        BitBlob second = new BitBlob(3, new byte[] {-32});
        
        BitBlob.append(first, second);
    }
    
    
    
}
