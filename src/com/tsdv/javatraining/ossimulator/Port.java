/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator;

/**
 * Class represents port interface of computer
 * 
 */
public class Port {
    private int id;
    private int value;
    private Peripheral peripheral;
    
    /**
     * Constructor for Port class
     * @param id id of port
     * @param value value of port
     */
    public Port(int id, int value) {
        this.id = id;
        this.value = value;
        connect(null);
    }
    
    /**
     * Constructor which initialize both id, value and peripheral device
     * @param id id of port
     * @param value value of port
     * @param peripheral peripheral connect to this port
     */
    public Port(int id, int value, Peripheral peripheral) {
        connect(peripheral);
    }
    
    /**
     * Connect to peripheral device from current port
     * @param peripheral Peripheral device
     */
    public final void connect(Peripheral peripheral) {
        this.peripheral = peripheral;
    }
    
    /**
     * Put data to peripheral device
     * @param value output data
     */
    public void outData(int value) {
        if (this.peripheral != null) {
            this.peripheral.outData(value);
        }
    }
}
