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
        throw new UnsupportedOperationException();
    }
    
    public void eraseProgram(){
        // clear memory
        throw new UnsupportedOperationException();
    }
    
    public void StartSimulation(){
        // Start cpu
        throw new UnsupportedOperationException();
    }
    
    private void InitComponents(){
        // create new memory
        // create new cpu 
        // connect cpu with memory
        // create text displayer
        // create number displayer
        // connect port 1 of cpu to text displyaer
        // connect port 2 os cpu to number dislayer
        throw new UnsupportedOperationException();
    }

    public OperatingSystem() {
        // InitComponents
    }
    
    
}
