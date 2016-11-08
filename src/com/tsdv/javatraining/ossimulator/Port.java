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
    public Port(int id) {
        this.id = id;
        this.value = 0;
        connect(null);
    }
    
    /**
     * Constructor which initialize both id, value and peripheral device
     * @param id id of port
     * @param value value of port
     * @param peripheral peripheral connect to this port
     */
    public Port(int id, int value, Peripheral peripheral) {
        this.id = id;
        connect(peripheral);
        outData(value);
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
        this.value = value;
        if (this.peripheral != null) {
            this.peripheral.outData(value);
        }
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

}
