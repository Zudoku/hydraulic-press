package fi.zudoku.hydraulic.domain.huffman;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.generic.MinHeap;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanLeafNode;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;
import static fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree.DYNAMIC_CHUNK_SIZE;
import static fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree.HEADER_BYTES;
import fi.zudoku.hydraulic.util.BitBlob;
import fi.zudoku.hydraulic.util.ByteUtils;
import static fi.zudoku.hydraulic.util.ByteUtils.putIntegerIntoByteArray;

public class CompressHuffManCoding implements Operation {
    
    public static final int COMPRESSED_DATA_BUFFER_SIZE = 10000;

    @Override
    public byte[] execute(byte[] input) {
        // construct huffman tree
        HuffmanTree tree = buildHuffmanTreeFromInput(input);
        
        // go through input, and replace bytes from input with the huffman tree bits
        BitBlob compressedData = new BitBlob();
        // To improve performance, we append smaller bitblobs together first
        // before appending the larger blobs together
        BitBlob compressedDataBuffer = new BitBlob();
        for (int i = 0; i < input.length; i++) {
            if (i % COMPRESSED_DATA_BUFFER_SIZE == 0) {
                // Instead of appending every bitblob into each other
                // append smaller ones first to reduce computation time
                compressedData = BitBlob.append(compressedData, compressedDataBuffer);
                compressedDataBuffer = new BitBlob();
            }
            BitBlob compressed = tree.getCompressedBitsForByte(input[i]);
            compressedDataBuffer = BitBlob.append(compressedDataBuffer, compressed);
        }
        compressedData = BitBlob.append(compressedData, compressedDataBuffer);
        
        // encode huffman tree (and other needed info) to the beginning of the result
        byte[] header = serializeHuffmanTree(tree, compressedData);
        
        // Combine header and compressed part
        byte[] result = new byte[header.length + compressedData.getData().length];
        ByteUtils.arrayCopy(header, 0, result, 0, header.length);
        ByteUtils.arrayCopy(compressedData.getData(), 0, result, header.length, compressedData.getData().length);
        
        return result;
    }

    /**
     * Traverse through the input one byte at a time, and construct a huffman tree with it.
     * @param input data to build huffman tree from.
     * @return a complete HuffmanTree.
     */
    public static HuffmanTree buildHuffmanTreeFromInput(byte[] input) {
        MinHeap nodes = buildMinHeapFromInput(input);
        
        HuffmanLeafNode[] result = new HuffmanLeafNode[nodes.size()];
        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            result[i] = (HuffmanLeafNode) nodes.poll();
        }
        
        return new HuffmanTree(result);
    }
    
    private static MinHeap buildMinHeapFromInput(byte[] input) {
        MinHeap nodes = new MinHeap();
        HuffmanLeafNode[] index = new HuffmanLeafNode[256];
        for (byte data: input) {
            HuffmanLeafNode nodeToAdd = new HuffmanLeafNode(data, 1);
            // data & 0xFF => turn signed value into unsigned
            HuffmanLeafNode foundNode = index[data & 0xFF];
            if (foundNode != null) {
                foundNode.addOne();
            } else {
                nodes.add(nodeToAdd);
                index[data & 0xFF] = nodeToAdd;
            }
        }
        nodes.reOrder();
        return nodes;
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
     * This is needed so that the uncompressing algorithm 
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
