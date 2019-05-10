package fi.zudoku.hydraulic;

import fi.zudoku.hydraulic.domain.huffman.CompressHuffManCoding;
import fi.zudoku.hydraulic.domain.huffman.DecompressHuffManCoding;
import fi.zudoku.hydraulic.domain.lzss.CompressLZSS;
import fi.zudoku.hydraulic.domain.lzss.DecompressLZSS;
import fi.zudoku.hydraulic.util.HydraulicPressArguments;

/**
 * This class (de)compresses the given data with given arguments.
 */
public class HydraulicPressInstance {
    
    /**
     * This performs one round of (de)compression.
     * 
     * @param arguments contains the input data that should be (de)compressed and the (de)compression style to be used
     * @return the result of the (de)compression
     */
    public byte[] run(HydraulicPressArguments arguments) {
        switch (arguments.getOperation()) {
            case COMPRESS_HUFFMAN_CODING:
                return compressHuffman(arguments);
                
            case DECOMPRESS_HUFFMAN_CODING:
                return decompressHuffman(arguments);
                
            case COMPRESS_LZ77:
                return compressLZ77(arguments);
                
            case DECOMPRESS_LZ77:
                return decompressLZ77(arguments);
            default:
                System.out.println("Unknown operation!");
                return new byte[0];
        }
    }
    /**
     * This performs Huffman Coding compression algorithm to the given input data and outputs the result as a byte array.
     * This result also includes the Huffman Tree that is needed to decompress the data.
     * @param arguments this contains the input data and any additional parameters
     * @return Huffman Coding compressed data of original data given in the arguments
     */
    private byte[] compressHuffman(HydraulicPressArguments arguments) {
        CompressHuffManCoding compressHuffmanCoding = new CompressHuffManCoding();
        
        return compressHuffmanCoding.execute(arguments.getData());
    }
    /**
     * This performs Huffman Coding decompression algorithm to the given input data and outputs the result as a byte array.
     * @see *compressHuffman*
     * @param arguments this contains the input data and any additional parameters
     * @return original data
     */
    private byte[] decompressHuffman(HydraulicPressArguments arguments) {
        DecompressHuffManCoding decompressHuffmanCoding = new DecompressHuffManCoding();
        
        return decompressHuffmanCoding.execute(arguments.getData());
    }
    /**
     * This performs Lempel Ziv 77 compression algorithm to the given input data and outputs the result as a byte array.
     * @param arguments this contains the input data and any additional parameters
     * @return Lempel Ziv 77 compressed data of original data given in the arguments
     */
    private byte[] compressLZ77(HydraulicPressArguments arguments) {
        CompressLZSS compressLZSS = new CompressLZSS();
        
        return compressLZSS.execute(arguments.getData());
    }
    /**
     * This performs Lempel Ziv 77 decompression algorithm to the given input data and outputs the result as a byte array.
     * @see *compressLZ77*
     * @param arguments this contains the input data and any additional parameters
     * @return original data
     */
    private byte[] decompressLZ77(HydraulicPressArguments arguments) {
        DecompressLZSS decompressLZSS = new DecompressLZSS();
        return decompressLZSS.execute(arguments.getData());
    }
    
}
