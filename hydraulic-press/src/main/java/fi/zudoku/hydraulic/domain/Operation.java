package fi.zudoku.hydraulic.domain;

/**
 * This represents one operation that this program can do.
 * The operation is either a way to compress or decompress data.
 * @see Operations
 */
public interface Operation {
    
    /**
     * This method manipulates the given data using some algorithm. 
     * It does not modify the given byte array but returns a new byte array of the result of the operation.
     * @param input data that should be manipulated
     * @return manipulated data
     */
    public byte[] execute(byte[] input);
}
