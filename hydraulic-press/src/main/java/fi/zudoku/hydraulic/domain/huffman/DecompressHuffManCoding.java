package fi.zudoku.hydraulic.domain.huffman;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;

public class DecompressHuffManCoding implements Operation {

    @Override
    public byte[] execute(byte[] input) {
        // read huffman tree
        HuffmanTree tree = readHuffmanTreeFromInput(input);
        tree.initialize();
        
        // go through input, and transform the compressed data to uncompressed using huffman tree
        return null;
    }

    /**
     * Reads the serialized huffman tree and deserializes it into a HuffmanTree class.
     * @param input data that contains the serialized huffman tree in the beginning.
     * @return a complete huffman tree.
     */
    public static HuffmanTree readHuffmanTreeFromInput(byte[] input) {
        return HuffmanTree.fromSerializedData(input);
    }
   
}

