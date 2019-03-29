package fi.zudoku.hydraulic.util;

import fi.zudoku.hydraulic.domain.Operations;

public class HydraulicPressArguments {
    
    private final Operations operation;
    private final byte[] data;

    /**
     * Command line arguments in a defined structure.
     * @param operation what operation should we do for the data.
     * @param data bytes to manipulate.
     */

    public HydraulicPressArguments(Operations operation, byte[] data) {
        this.operation = operation;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public Operations getOperation() {
        return operation;
    }
}
