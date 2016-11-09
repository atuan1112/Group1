/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represent a segment of data in the memory
 *
 * @author TrinhNX
 */
public class DataSegment {

    private final int address;
    private final int data[];

    private DataSegment(int address, int[] data) {
        this.address = address;
        this.data = data;
    }

    public int getAddress() {
        return address;
    }

    public int[] getData() {
        return data;
    }

    public int getDataFromIndex(int index) {
        // TODO: Index out of bound exception checking
        return data[index];
    }

    public int size() {
        return data.length;
    }

    /**
     * Helper class to build the datasegment
     */
    public static class Builder {

        private int address;
        private final List<Integer> data;

        public Builder() {
            address = 0;
            data = new ArrayList<>();
        }

        public Builder setAddress(int address) {
            this.address = address;
            return this;
        }

        public Builder addValue(int value) {
            data.add(value);
            return this;
        }

        public boolean isEmpty() {
            return data.isEmpty();
        }

        public DataSegment build() {
            return new DataSegment(address, data.stream().mapToInt(i -> i).toArray());
        }
    }
}
