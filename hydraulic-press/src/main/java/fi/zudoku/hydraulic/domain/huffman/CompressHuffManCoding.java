package fi.zudoku.hydraulic.domain.huffman;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanLeafNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;
import static fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree.DYNAMIC_CHUNK_SIZE;
import static fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree.HEADER_BYTES;
import fi.zudoku.hydraulic.util.BitBlob;
import static fi.zudoku.hydraulic.util.ByteUtils.putIntegerIntoByteArray;
import java.util.PriorityQueue;

public class CompressHuffManCoding implements Operation {

    @Override
    public byte[] execute(byte[] input) {
        // construct huffman tree
        HuffmanTree tree = buildHuffmanTreeFromInput(input);
        
        // go through input, and replace bytes from input with the huffman tree bits
        BitBlob compressedData = new BitBlob();
        for (byte b: input) {
            BitBlob compressed = tree.getCompressedBitsForByte(b);
            compressedData = BitBlob.append(compressedData, compressed);
        }
        
        // encode huffman tree (and other needed info) to the beginning of the result
        byte[] header = serializeHuffmanTree(tree, compressedData);
        
        // Combine header and compressed part
        byte[] result = new byte[header.length + compressedData.getData().length];
        System.arraycopy(header, 0, result, 0, header.length);
        System.arraycopy(compressedData.getData(), 0, result, header.length, compressedData.getData().length);
        
        return result;
    }

    /**
     * Traverse through the input one byte at a time, and construct a huffman tree with it.
     * @param input data to build huffman tree from.
     * @return a complete HuffmanTree.
     */
    public static HuffmanTree buildHuffmanTreeFromInput(byte[] input) {
        PriorityQueue<HuffmanLeafNode> nodes = buildMinHeapFromInput(input);
        
        HuffmanLeafNode[] result = new HuffmanLeafNode[nodes.size()];
        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            result[i] = nodes.poll();
        }
        
        return new HuffmanTree(result);
    }
    
    private static PriorityQueue<HuffmanLeafNode> buildMinHeapFromInput(byte[] input) {
        PriorityQueue<HuffmanLeafNode> nodes = new PriorityQueue<>();
        for (byte data: input) {
            HuffmanLeafNode nodeToAdd = new HuffmanLeafNode(data, 1);
            
            if (nodes.contains(nodeToAdd)) {
                for (HuffmanLeafNode node : nodes) {
                    if (node.equals(nodeToAdd)) {
                        node.addOne();
                    }
                }
            } else {
                nodes.add(nodeToAdd);
            }
        }
        return new PriorityQueue<>(nodes);
    }
    
    /**
     * Serializes the huffman tree to a format that can be read back when decompressing.
     * This can be saved to a file and read back.
     * The serialization in sequenctial order: 
     * 
     * serialized pattern:
     * HEADER:
     * 4 bytes = how many nodes there are (integer)
     * 1 bytes = what is the byte cutoff
     * DATA:
     * n amount of dynamic chunks
     * 
     * dynamic chunk pattern:
     * 1 bytes for uncompressed data, 
     * 4 bytes for the amount it occurs in the original data (integer)
     * 
     * @param tree huffman tree to serialize.
     * @param compressedData the compressed data. Used for byte cutoff.
     * @return serialized byte array for this tree. 
     */
    public static byte[] serializeHuffmanTree(HuffmanTree tree, BitBlob compressedData) {
        int dynamicBytes = tree.getLeafNodeAmount() * DYNAMIC_CHUNK_SIZE;
        byte[] result = new byte[HEADER_BYTES + dynamicBytes];
        // Construct header
        result = putIntegerIntoByteArray(result, tree.getLeafNodeAmount(), 0);
        result[4] = getByteCutOff(compressedData);

        // Construct data
        for (int index = 0; index < tree.getInputNodes().length; index++) {
            int resultIndex = HEADER_BYTES + (index * 5);
            HuffmanLeafNode currentLeafNode = tree.getInputNodes()[index];
            
            result[resultIndex] = currentLeafNode.getDataToCompress();
            result = putIntegerIntoByteArray(result, currentLeafNode.getAmount(), resultIndex + 1);
        }
        return result;
    }
    
    /**
     * This is the bytecutoff header field.
     * This is needed so that the uncompressing algorigthm 
     * knows when to stop parsing the last byte. Otherwise it might uncompress 
     * extra data from the remaining 0 filled bits.
     * @param compressedData final compressed data.
     * @return bytecutoff header field value.
     */
    private static byte getByteCutOff(BitBlob compressedData) {
        int byteCutOff = compressedData.getNumOfBits() % 8;
        return  byteCutOff == 0 ? 8 : (byte) byteCutOff;
    }
}
