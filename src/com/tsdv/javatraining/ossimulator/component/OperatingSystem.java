/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.component;

import com.tsdv.javatraining.ossimulator.data.DataSegment;
import java.util.List;

/**
 * Class represent the operating system
 *
 * @author ToanTV
 */
public class OperatingSystem {

    /* Operating system memory size */
    private static final int SYSTEM_MEMORY_SIZE = 2000;
    private CPU cpu;
    private Memory memory;

    public OperatingSystem() {
        InitComponents();
    }

    /**
     * Initialize system components
     */
    private void InitComponents() {
        // create new memory
        memory = new Memory(SYSTEM_MEMORY_SIZE);

        // create new cpu 
        // connect cpu with memory
        cpu = new CPU(memory);

        // create text displayer
        TextDisplayer textDisplayer = new TextDisplayer();
        // create number displayer
        NumberDisplayer numDisplayer = new NumberDisplayer();
        // connect port 1 of cpu to text displyaer
        cpu.connectPeripheral(1, numDisplayer);
        // connect port 2 os cpu to number dislayer
        cpu.connectPeripheral(2, textDisplayer);
    }

    /**
     * Load user program to system memory
     * @param programData 
     */
    public void loadProgram(List<DataSegment> programData) {
        // load user program data to memory
        memory.load(programData);
    }

    /**
     * clear system memory
     */
    public void eraseProgram() {
        // clear memory
        memory.clear();
    }

    /**
     * set timer period for CPU
     * @param TickTime timer period
     */
    public void setTimer(int TickTime) {
        // set tick time for timer CPU
        cpu.setTimerPeriod(0, TickTime);
    }

    /**
     * start system
     */
    public void start() {
        // start cpu
        cpu.start();
    }

}
