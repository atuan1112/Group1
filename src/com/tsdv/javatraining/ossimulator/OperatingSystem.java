/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator;

/**
 * Class represent the operating system
 * @author ToanTV
 */
public class OperatingSystem {
    /* Operating system memory size */
    private static final int SYSTEM_MEMORY_SIZE = 2000;        
    private CPU cpu; 
    private Memory memory;
    
    public void loadProgram(ProgramData programData[]){
        // load user program data to memory
        memory.load(programData);

        // throw new UnsupportedOperationException();
    }

    public void setTimer(int TickTime){
        // set tick time for timer CPU
        cpu.setTimer(0, TickTime);

        // throw new UnsupportedOperationException();
    }    
    
    public void eraseProgram(){
        // clear memory
        memory.clear();
        throw new UnsupportedOperationException();
    }
    
    public void StartSimulation(){
        // Start cpu
        cpu.start();
        throw new UnsupportedOperationException();
    }
    
    private void InitComponents(){
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

    public OperatingSystem() {
        InitComponents();
    }
    
    
}
