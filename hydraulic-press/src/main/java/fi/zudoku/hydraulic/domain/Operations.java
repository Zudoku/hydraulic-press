package fi.zudoku.hydraulic.domain;

public enum Operations {
    // Huffman Coding
    COMPRESS_HUFFMAN_CODING,
    DECOMPRESS_HUFFMAN_CODING,
    
    // Lempel Ziv 77
    COMPRESS_LZ77,
    DECOMPRESS_LZ77,
    
    // Deflate
    COMPRESS_DEFLATE,
    DECOMPRESS_DEFLATE
}
