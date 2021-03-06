/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.api;

/**
 * Interface of classes which represent peripheral in computer architecture
 * 
 */
public interface Peripheral {
    static final int TEXT_DISPLAYER = 1;
    static final int NUMBER_DISPLAYER = 2;
    
    /**
     * Get peripheral type
     * @return type
     */
    public int getType();
    
    /**
     * Processing data from OS
     * @param data data input to peripheral
     */
    public void outData(int data);
}
