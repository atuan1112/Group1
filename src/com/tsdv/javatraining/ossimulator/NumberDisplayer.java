/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator;

/**
 * NumberDisplayer peripheral
 * 
 */
public class NumberDisplayer implements Peripheral {
    private int type = NUMBER_DISPLAYER;
    
    /**
     * Constructor for NumberDisplayer
     * @param type type of peripheral
     */
    public NumberDisplayer() {
    }

    /**
     * Get peripheral type
     * @return peripheral type
     */
    @Override
    public int getType() {
        return this.type;
    }

    /**
     * Print data as character
     * @param data data in Integer type
     */
    @Override
    public void outData(int data) {
        System.out.print(data);
    }
    
}
