package fi.zudoku.hydraulic.util;

import fi.zudoku.hydraulic.domain.Operations;

public class HydraulicPressArguments {
    
    private final Operations operation;
    private final String inputFilename;
    private final byte[] data;

    /**
     * Command line arguments in a defined structure.
     * @param operation what operation should we do for the data.
     * @param data bytes to manipulate.
     * @param inputFilename
     */

    public HydraulicPressArguments(Operations operation, byte[] data, String inputFilename) {
        this.operation = operation;
        this.data = data;
        this.inputFilename = inputFilename;
    }

    public byte[] getData() {
        return data;
    }

    public Operations getOperation() {
        return operation;
    }

    public String getInputFilename() {
        return inputFilename;
    }
}
