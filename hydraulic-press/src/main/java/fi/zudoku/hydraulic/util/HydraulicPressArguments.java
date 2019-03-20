/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
