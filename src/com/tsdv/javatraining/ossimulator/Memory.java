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
    
    /**
     * Constructor for Memory class
     * @param capacity 
     */
    public Memory(int capacity) {
        if (capacity <= 0 ) {
            throw new UnsupportedOperationException(String.format("Memory size [%d]. Must be greater than 0.", capacity));
        }
        data = new int[capacity];
        this.capacity = capacity;
    }
    
    /**
     * Load user program
     * @param data User program data
     */
    public void load(ProgramData data[]) {
        for (int i = 0; i < data.length; i ++) {
            if (data[i].address > 0 && data[i].data.length > 0) {
                int programAddress = data[i].address;
                int programSize = data[i].data.length;
                for (int j = 0; j < programSize; j ++) {
                    this.data[programAddress + j] = data[i].data[j];
                }
            }
        }
    }
    
    /**
     * Clear loaded user-program
     */
    public void clear() {
        data = new int[capacity];
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

    public int getCapacity() {
        return capacity;
    }
}
