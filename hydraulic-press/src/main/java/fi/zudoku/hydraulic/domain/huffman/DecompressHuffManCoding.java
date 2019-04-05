package fi.zudoku.hydraulic.domain.huffman;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanInternalNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanLeafNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;
import static fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree.DYNAMIC_CHUNK_SIZE;
import static fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree.HEADER_BYTES;
import static fi.zudoku.hydraulic.util.ByteUtils.growByteArrayWithOne;
import static fi.zudoku.hydraulic.util.ByteUtils.intFromByteArray;

public class DecompressHuffManCoding implements Operation {

    @Override
    public byte[] execute(byte[] input) {
        // read huffman tree
        HuffmanTree tree = deserializeHuffmanTree(input);
        
        // go through input, and transform the compressed data to uncompressed using huffman tree
        int chunks = intFromByteArray(input, 0);
        byte byteCutOff = input[4];
        int firstByte = (chunks * DYNAMIC_CHUNK_SIZE) + HEADER_BYTES;
        HuffmanNode currentNode = tree.getRootNode();
        byte[] result = new byte[0];
        for (int index = firstByte; index < input.length; index++) {
            byte byteToHandle = input[index];
            
            for (byte bit = 0; bit < 8;bit++) {
                
                if (index == input.length - 1 && bit >= byteCutOff) {
                    continue;
                }
                
                int path = byteToHandle & 0xFF;
                path >>= 7 - bit;
                path <<= 7;
                byte r = (byte) path;
                path = r & 0xFF;
                path >>= 7;
                HuffmanNode nextNode = travelDownNode((byte) path, currentNode);
                if (nextNode instanceof HuffmanLeafNode) {
                    HuffmanLeafNode foundLeafNode = (HuffmanLeafNode) nextNode;
                    result = growByteArrayWithOne(result);
                    result[result.length - 1] = foundLeafNode.getDataToCompress();
                    currentNode = tree.getRootNode();
                } else {
                    currentNode = nextNode;
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
     * Reads the serialized input and deserializes it into a HuffmanTree class.
     * For more information on what the serialization pattern is:
     * @see CompressHuffmanCoding#serializeHuffmanTree
     * @param input data that contains the serialized huffman tree in the beginning.
     * @return a complete huffman tree.
     */
    public static HuffmanTree deserializeHuffmanTree(byte[] input) {
        int chunks = intFromByteArray(input, 0);
        HuffmanLeafNode[] nodes = new HuffmanLeafNode[chunks];
        
        for(int index = 0; index < chunks; index++) {
            int inputIndex = HEADER_BYTES + (index * DYNAMIC_CHUNK_SIZE);
            byte dataToCompress = input[inputIndex];
            int nodeFrequency = intFromByteArray(input, inputIndex + 1);
            HuffmanLeafNode newNode = new HuffmanLeafNode(dataToCompress, nodeFrequency);
            nodes[index] = newNode;
        }
        return new HuffmanTree(nodes);
    }
   
}

