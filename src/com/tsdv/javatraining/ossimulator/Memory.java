/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator;

/**
 * Class represents physical memory of computer
 * 
 */
public class Memory {
    private int[] data;
    private int capacity;
    private int programAddress = -1;
    private int programSize = -1;
    
    /**
     * Constructor for Memory class
     * @param capacity 
     */
    public Memory(int capacity) {
        if (capacity <= 0 ) {
            throw new UnsupportedOperationException(String.format("Memory size [%d]. Must be greater than 0.", capacity));
        }
        data = new int[capacity];
    }
    
    /**
     * Load user program
     * @param data User program data
     */
    public void load(ProgramData data) {
        if (data.address > 0 && data.data.length > 0) {
            programAddress = data.address;
            programSize = data.data.length;
            for (int i = 0; i < programSize; i ++) {
                this.data[programAddress + i] = data.data[i];
            }
        }
    }
    
    /**
     * Clear loaded user-program
     */
    public void clear() {
        if (programAddress >= 0) {
            for (int i = 0; i < programSize; i ++) {
                this.data[programAddress + i] = 0;
            }
            programAddress = -1;
            programSize = -1;
        }
    }
    
    /**
     * Get value at position "address" of memory
     * @param address
     * @return Integer value at position "address"
     */
    public int read(int address) {
        return data[address];
    }
    
    /**
     * Write "data" to memory at position "address"
     * @param address position where "data" is written
     * @param data value of memory at position "address"
     */
    public void write(int address, int data) {
        this.data[address] = data;
    }
}
