package fi.zudoku.hydraulic.utils;


import fi.zudoku.hydraulic.util.BitBlob;
import org.junit.Assert;
import org.junit.Test;

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
}
