/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator;

/**
 * TextDisplayer peripheral
 * 
 */
public class TextDisplayer implements Peripheral {
    private int type = TEXT_DISPLAYER;
    
    /**
     * Constructor for TextDisplayer
     * @param type type of peripheral
     */
    public TextDisplayer() {
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
        System.out.print(((char)data));
    }
    
}
