package fi.zudoku.hydraulic.domain.huffman;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanInternalNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanLeafNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;
import static fi.zudoku.hydraulic.util.ByteUtils.growByteArrayWithOne;
import static fi.zudoku.hydraulic.util.ByteUtils.intFromByteArray;

public class DecompressHuffManCoding implements Operation {

    @Override
    public byte[] execute(byte[] input) {
        // read huffman tree
        HuffmanTree tree = readHuffmanTreeFromInput(input);
        tree.initialize();
        
        // go through input, and transform the compressed data to uncompressed using huffman tree
        int firstByte = (intFromByteArray(input, 0) * 5) + 4;
        HuffmanNode currentNode = tree.getRootNode();
        byte[] result = new byte[0];
        for (int index = firstByte; index < input.length; index++) {
            byte byteToHandle = input[index];
            
            for (byte bit = 0; bit < 8;bit++) {
                byte path = (byte) (byteToHandle << bit);
                HuffmanNode nextNode = travelDownNode(path, currentNode);
                if (nextNode instanceof HuffmanLeafNode) {
                    HuffmanLeafNode foundLeafNode = (HuffmanLeafNode) nextNode;
                    result = growByteArrayWithOne(result);
                    result[result.length - 1] = foundLeafNode.getDataToCompress();
                    currentNode = tree.getRootNode();
                }
            }
        }
        return result;
    }
    
    private HuffmanNode travelDownNode(byte path, HuffmanNode currentNode){
        //check if current node is leafnode (it is the only node of the tree)
        if (currentNode instanceof HuffmanLeafNode) {
            return currentNode;
        }
        HuffmanInternalNode currentInternalNode = (HuffmanInternalNode) currentNode;
        if (path == 0) {
            return currentInternalNode.getLeft();
        } else {
            return currentInternalNode.getRight();
        }
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

