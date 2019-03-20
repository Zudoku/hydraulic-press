package fi.zudoku.hydraulic;

import fi.zudoku.hydraulic.domain.huffman.CompressHuffManCoding;
import fi.zudoku.hydraulic.util.HydraulicPressArguments;

public class HydraulicPressInstance {
    
    private final HydraulicPressArguments arguments;
    
    public HydraulicPressInstance(HydraulicPressArguments arguments) {
        this.arguments = arguments;
    }
    
    
    public void run() {
        switch(arguments.getOperation()) {
            case COMPRESS_HUFFMAN_CODING:
                compressHuffman();
                break;
                
            default:
                System.out.println("Unknown operation!");
                break;
        }
    }
    
    private void compressHuffman() {
        CompressHuffManCoding compressHuffmanCoding = new CompressHuffManCoding();
        
        byte[] result = compressHuffmanCoding.excecute(arguments.getData());
        
        saveOperationResult(result);
    }
    
    private void saveOperationResult(byte[] result) {
        // TODO
    }
}
