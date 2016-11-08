/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.model;

import java.util.List;

/**
 * Class to represent instruction opt code and its operands
 * Test commit
 * @author ToanTV
 */
public class Instruction {

    private InstructionInfo info;
    private List<Integer> operandList;

    public Instruction() {
        throw new UnsupportedOperationException();
    }

    public int getOptCode() {
        return info.optCode;
    }

    public InstructionInfo getInfo() {
        return info;
    }

    public void setInfo(InstructionInfo info) {
        this.info = info;
    }

    public List<Integer> getOperands() {
        return operandList;
    }

    public void setOperands(List<Integer> operands) {
        this.operandList = operands;
    }

    public void addOperands(int operand) {
        throw new UnsupportedOperationException();
    }
}
