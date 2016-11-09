/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator;

import com.tsdv.javatraining.ossimulator.component.CPU;
import com.tsdv.javatraining.ossimulator.component.Memory;
import com.tsdv.javatraining.ossimulator.component.TextDisplayer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rintaro
 */
public class CPUTest {

    public CPUTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setTimer method, of class CPU.
     */
    @Test
    public void testSetTimer() {
        Memory memory = new Memory(2000);
        try {
            CPU cpu = new CPU(memory);
            cpu.setTimer(0, 500);
        } catch (UnsupportedOperationException e) {
            fail("Not implement");
        }
    }

    /**
     * Test of connectPeripheral method, of class CPU.
     */
    @Test
    public void testConnectPeripheral() {
        Memory memory = new Memory(2000);
        TextDisplayer textDisplayer = new TextDisplayer();
        try {
            CPU cpu = new CPU(memory);
            cpu.connectPeripheral(0, textDisplayer);
        } catch (UnsupportedOperationException e) {
            fail("Not implement");
        }
    }

    /**
     * Test of start method, of class CPU.
     */
    @Test
    public void testStart() {
        Memory memory = new Memory(2000);
        try {
            CPU cpu = new CPU(memory);
            cpu.start();
        } catch (UnsupportedOperationException e) {
            fail("Not implement");
        }
    }

}
