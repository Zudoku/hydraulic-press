package fi.zudoku.hydraulic.util;

import fi.zudoku.hydraulic.domain.Operations;

public class HydraulicPressArguments {
    
    private final Operations operation;
    private final byte[] data;

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
