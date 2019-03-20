/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.zudoku.hydraulic.domain;

/**
 *
 * @author arska
 */
public interface Operation {
    
    public byte[] excecute(byte[] input);
}
