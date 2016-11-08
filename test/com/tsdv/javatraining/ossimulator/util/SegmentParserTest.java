/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.util;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author TrinhNX
 */
public class SegmentParserTest {

    @Test
    public void testIsValidInstruction_FailInstruction() {
        final String line = "a100";
        boolean execute = SegmentParser.isValidInstruction(line);
        Assert.assertEquals("Not valid instruction", execute, false);
    }

    @Test
    public void testIsValidInstruction_Fail() {
        final String line = "failse";
        boolean execute = SegmentParser.isValidInstruction(line);
        Assert.assertEquals("Not valid instruction", execute, false);
    }

    @Test
    public void testIsValidInstruction_AddressSuccess() {
        final String line = ".1000";
        boolean execute = SegmentParser.isValidInstruction(line);
        Assert.assertEquals("Not valid instruction", execute, true);
    }

    @Test
    public void testIsValidInstruction_OptCodeSuccess() {
        final String line = "100";
        boolean execute = SegmentParser.isValidInstruction(line);
        Assert.assertEquals("Valid instruction", execute, true);
    }

    @Test
    public void testIsValidInstruction_OperandSuccess() {
        final String line = "-1";
        boolean execute = SegmentParser.isValidInstruction(line);
        Assert.assertEquals("Valid instruction", execute, true);
    }

    @Test
    public void testGetInstructionData_Fail() {
        final String line = "a100";
        int result = SegmentParser.getInstructionData(line);
        Assert.assertEquals("Not valid instruction", -1, result);
    }

    @Test
    public void testGetInstructionData_SuccessAddress() {
        final String line = ".100";
        int result = SegmentParser.getInstructionData(line);
        Assert.assertEquals("valid instruction", 100, result);
    }

    @Test
    public void testGetInstructionData_SuccessOptCode() {
        final String line = "100";
        int result = SegmentParser.getInstructionData(line);
        Assert.assertEquals("valid instruction", 100, result);
    }

    @Test
    public void testGetInstructionData_SuccessOperand() {
        final String line = "-100";
        int result = SegmentParser.getInstructionData(line);
        Assert.assertEquals("valid instruction", -100, result);
    }
}
