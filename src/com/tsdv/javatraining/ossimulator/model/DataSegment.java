/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Require Java8
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

    /**
     * Helper class to build the datasegment
     */
    static class Builder {

        private int address;
        private List<Integer> data;

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

        public DataSegment build() {
            return new DataSegment(address, data.stream().mapToInt(i -> i).toArray());
        }
    }
}