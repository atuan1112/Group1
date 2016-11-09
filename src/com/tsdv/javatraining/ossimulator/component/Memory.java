/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.component;

import com.tsdv.javatraining.ossimulator.exception.IllegalAccessMemoryException;
import com.tsdv.javatraining.ossimulator.data.DataSegment;
import com.tsdv.javatraining.ossimulator.util.ErrMessage;
import java.util.Arrays;
import java.util.List;

/**
 * Class represents physical memory of computer
 *
 */
public class Memory {

    private int[] data;
    private int capacity;

    /**
     * Constructor for Memory class
     *
     * @param capacity
     */
    public Memory(int capacity) {
        if (capacity <= 0) {
            throw new UnsupportedOperationException(String.format("Memory size [%d]. Must be greater than 0.", capacity));
        }
        data = new int[capacity];
        Arrays.fill(data, 0);
        this.capacity = capacity;
    }

    /**
     * Load user program
     *
     * @param data User program data
     */
    public void load(List<DataSegment> data) {
        // loop all segment
        for (int i = 0; i < data.size(); i++) {
            // load data from each segement
            DataSegment segment = data.get(i);
            int address = segment.getAddress();
            int size = segment.size();
            if (address >= 0 && size > 0) {
                for (int j = 0; j < size; j++) {
                    this.data[address + j] = segment.getDataFromIndex(j);
                }
            }
        }
    }

    /**
     * Clear loaded user-program
     */
    public void clear() {
        Arrays.fill(data, 0);
    }

    /**
     * Get value at position "address" of memory
     *
     * @param address
     * @return Integer value at position "address"
     * @throws com.tsdv.javatraining.ossimulator.exception.IllegalAccessMemoryException
     */
    public int read(int address) throws IllegalAccessMemoryException {
        if (address < 0 || address >= this.data.length) {
            throw new IllegalAccessMemoryException(ErrMessage.OUR_OF_RANGE_MEMORY);
        }
        return data[address];
    }

    /**
     * Write "data" to memory at position "address"
     *
     * @param address position where "data" is written
     * @param data value of memory at position "address"
     * @throws com.tsdv.javatraining.ossimulator.exception.IllegalAccessMemoryException
     */
    public void write(int address, int data) throws IllegalAccessMemoryException {
        if (address < 0 || address >= this.data.length) {
            throw new IllegalAccessMemoryException(ErrMessage.OUR_OF_RANGE_MEMORY);
        }
        this.data[address] = data;
    }

    public int getCapacity() {
        return capacity;
    }
}
