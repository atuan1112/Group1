/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.component;

import com.tsdv.javatraining.ossimulator.api.Peripheral;

/**
 * NumberDisplayer peripheral
 *
 */
public class NumberDisplayer implements Peripheral {

    /**
     * Get peripheral type
     *
     * @return peripheral type
     */
    @Override
    public int getType() {
        return Peripheral.TEXT_DISPLAYER;
    }

    /**
     * Print data as character
     *
     * @param data data in Integer type
     */
    @Override
    public void outData(int data) {
        System.out.print(data);
    }
}
