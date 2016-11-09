/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator;

import com.tsdv.javatraining.ossimulator.api.Peripheral;
import com.tsdv.javatraining.ossimulator.model.Instruction;
import com.tsdv.javatraining.ossimulator.model.InstructionInfo;
import com.tsdv.javatraining.ossimulator.model.Port;
import com.tsdv.javatraining.ossimulator.api.Timer;
import com.tsdv.javatraining.ossimulator.util.ErrMessage;
import java.util.List;
import java.util.Random;

/**
 *  Class represent the CPU
 * @author ToanTV
 */
public class CPU {
    /* Start user program address */
    private static final int USER_DATA_ADDRESS = 0000;
    /* Start System data address */
    private static final int SYSTEM_DATA_ADDRESS = 1000;
    /* Start timer process address */
    private static final int TIMER_PROCESS_ADDRESS = 1000;
    /* Start interrupt process address */    
    private static final int INTERUPT_PROCESS_ADDRESS = 1500;

    /* CPU's ports */
    private List<Port> portList;
    /* CPU's timer */    
    private List<Timer> timerList;
    /* program memory */    
    private Memory memory;
    /* CPU's registers  */    
    private int PC;
    private int SP;
    private int IR;
    private int AC,X,Y;

    /* end program flag */
    private boolean isEndProgram;
    
    public CPU(Memory memory) {
        // set refer to memory
        this.memory = memory;
        // init timer
        
        // init port
        Port port1 = new Port(1);
        Port port2 = new Port(2);
        
        portList.add(port1);
        portList.add(port2);
        
        // set end flag to flase
        isEndProgram = false;
        
        // throw new UnsupportedOperationException();
    }
    
    public void setTimer(int TimerID, int TickTime){
        // set Timer interval time
        throw new UnsupportedOperationException();
    }
    
    public void connectPeripheral(int portID, Peripheral peripheral){
        // connect port to peripheral
        for (int i = 0; i < portList.size(); i++ ) {
            Port port = portList.get(i);
            if (port.getId() == portID) {
                port.connect(peripheral);
            }
        }
        // throw new UnsupportedOperationException();
    }
    
    /**
     * Start the cpu operation
     */
    public void start(){
        // init registers
        initRegisters();
        // while not end of program
        while (isEndProgram == false){
            // fetch instruction
            fetchInstruction();
            // decode instruction
            Instruction instruction = decodeInstruction();
            // execute instruction
            executeInstruction(instruction);
            // update timer
            
        }
        // throw new UnsupportedOperationException();
    }
    
    private void initRegisters(){
        PC = 0;
        SP = memory.getCapacity() - 1;
        AC = 0;
        X = 0;
        Y = 0;
    }
    
    /**
     * process timer interrupt
     */
    private void processTimerInterrupt(){
        // push system state
        pushSystemState();
        // update PC = TIMER_PROCESS_ADDRESS
        PC = TIMER_PROCESS_ADDRESS;
        throw new UnsupportedOperationException();
    }
    
    /**
     * fetch next instruction in the memory
     */    
    private void fetchInstruction(){
        // read next instruction in memory at the address in PC
        // store the instrucion in IR
        IR  = memory.read(PC);
        // increase PC
        PC++;
        
        //throw new UnsupportedOperationException();
    }
    
    /**
     * decode the current instruction
     * @return decoded instruction
     */
    private Instruction decodeInstruction(){
        // Create new instruction object
        Instruction instruction = new Instruction();
        InstructionInfo instructionInfo = null;
    
        // find the instruction info from instruction set
        instructionInfo = findInstructionInfo();
        instruction.setInfo(instructionInfo);
        
        // read the operands from memory
        for (int i = 0; i < instructionInfo.numOfOperands; i ++){
            int operand = memory.read(PC);
            PC++;
            instruction.addOperands(operand);
        }

        return instruction;
        // throw new UnsupportedOperationException();
    }
    
    private InstructionInfo findInstructionInfo()
    {
        for (InstructionInfo info : InstructionInfo.values()) 
        {
            if (info.optCode == IR){
                return info;
            }
        }
        throw new IllegalArgumentException(ErrMessage.NOT_SUPPORT_INSTRUCTION);
        // throw new UnsupportedOperationException();
    }
    
    /**
     * Execute instruction
     * @param instruction instruction to be executed
     */
    private void executeInstruction(Instruction instruction) {
        InstructionInfo info = instruction.getInfo();
        List<Integer> operands = instruction.getOperands();

        // switch base on instruction info
        // call the coresponding function
        switch (info) {
            case LOAD_VALUE:
                executeLoadValue(operands.get(0));
                break;
            case LOAD_ADDR:
                executeLoadAddr(operands.get(0));
                break;
            case LOAD_IND_ADDR:
                executeLoadIndAddr(operands.get(0));
                break;
            case LOAD_IDX_X_ADDR:
                executeLoadIdxXAddr(operands.get(0));
                break;
            case LOAD_IDX_Y_ADDR:
                executeLoadIdxYAddr(operands.get(0));
                break;
            case LOAD_SPX:
                executeLoadSpx();
                break;
            case STORE_ADDR:
                executeStoreAddr(operands.get(0));
                break;
            case GET:
                executeGet();
                break;
            case PUT_PORT:
                executePutPort(operands.get(0));
                break;
            case ADD_X:
                executeAddX();
                break;
            case ADD_Y:
                executeAddY();
                break;
            case SUB_X:
                executeSubX();
                break;
            case SUB_Y:
                executeSubY();
                break;
            case COPY_TO_X:
                executeCopyToX();
                break;
            case COPY_FROM_X:
                executeCopyFromX();
                break;
            case COPY_TO_Y:
                executeCopyToY();
                break;
            case COPY_FROM_Y:
                executeCopyFromY();
                break;
            case COPY_TO_SP:
                executeCopyToSp();
                break;
            case COPY_FROM_SP:
                executeCopyFromSp();
                break;
            case JUMP_ADDR:
                executeJumpAddr(operands.get(0));
                break;
            case JUMP_IF_EQUAL:
                executeJumpIfEqual(operands.get(0));
                break;
            case JUMP_IF_NOT_EQUAL:
                executeJumpIfNotEqual(operands.get(0));
                break;
            case CALL_ADDR:
                executeCallAddr(operands.get(0));
                break;
            case RET:
                executeRet();
                break;
            case INCX:
                executeIncX();
                break;
            case DECX:
                executeDecX();
                break;
            case PUSH:
                executePush();
                break;
            case POP:
                executePop();
                break;
            case INT:
                executeInt();
                break;
            case IRET:
                executeIret();
                break;
            case END:
                executeEnd();
                break;
            default:
                throw new IllegalArgumentException(ErrMessage.NOT_SUPPORT_INSTRUCTION);
        }

        // throw new UnsupportedOperationException();
    }
    
    /**
     * Execute the Load value instruction
     */
    private void executeLoadValue(int value) {
        // AC = value
        this.AC = value;
    }

    /**
    * Execute the Load Address instruction
    */
    private void executeLoadAddr(int addr) {
        // read memory at the adrr
        // store the value to AC
        this.AC = this.memory.read(addr);
    }

    private void executeLoadIndAddr(int indAddr) {
        // read memory at the indAdrr to get the direct addr
        // read memory at the direct addr to get the value
        // store the value to AC 
        int realAddr = this.memory.read(indAddr);
        this.AC = this.memory.read(realAddr);
    }

    private void executeLoadIdxXAddr(int addr) {
        // read memory at addr + X
        // store the read value to AC        
        this.AC = this.memory.read(addr + this.X);
    }

    private void executeLoadIdxYAddr(int addr) {
        // read memory at addr + Y
        // store the read value to AC
        this.AC = this.memory.read(addr + this.Y);
    }

    private void executeLoadSpx() {
        // read memory at SP + X
        // store the read value to AC
        this.AC = this.memory.read(this.SP - this.X); // SP grow down toward 0
    }

    private void executeStoreAddr(int addr) {
        // write value of AC to addr 
        this.memory.write(addr, this.AC);
    }

    private void executeGet() {
        // random number from 1 to 100 to AC
        Random r = new Random();
        int low = 1;
        int high = 100;
        this.AC = r.nextInt(high - low) + low;
    }

    private void executePutPort(int portID) {
        // output data to port
        Port port = this.portList.get(portID);
        if (port != null) {
            port.outData(AC);
        }
    }

    private void executeAddX() {
        // AC = AC + X
        this.AC += this.X;
    }

    private void executeAddY() {
        // AC = AC + Y
        this.AC += this.Y;
    }

    private void executeSubX() {
        // AC = AC - X
        this.AC -= this.X;
    }

    private void executeSubY() {
        // AC = AC - Y
        this.AC -= this.Y;
    }

    private void executeCopyToX() {
        // X = AC
        this.X = this.AC;
    }

    private void executeCopyFromX() {
        // AC = X
        this.AC = this.X;
    }

    private void executeCopyToY() {
        // Y = AC
        this.Y = this.AC;
    }

    private void executeCopyFromY() {
        // AC = Y
        this.AC = this.Y;
    }

    private void executeCopyToSp() {
        // SP = AC
        this.SP = this.AC;
    }

    private void executeCopyFromSp() {
        // AC = SP
        this.AC = this.SP;
    }

    private void executeJumpAddr(int addr) {
        // PC = addr
        this.PC = addr;
    }

    private void executeJumpIfEqual(int addr) {
        // if AC == 0
            // PC = addr
        if (this.AC == 0)
            this.PC = addr;
    }

    private void executeJumpIfNotEqual(int addr) {
        // if AC != 0
            // PC = addr
        if (this.AC != 0)
            this.PC = addr;
    }

    private void executeCallAddr(int addr) {
        // push system state
        // PC = addr
        pushSystemState();
        this.PC = addr;
    }

    private void executeRet() {
        // pop system state
        popSystemState();
    }

    private void executeIncX() {
        // X++
        this.X++;
    }

    private void executeDecX() {
        // X--
        this.X--;
    }

    private void executePush() {
        // write AC to memory at address in SP
        // SP--
        this.memory.write(SP, AC);
        this.SP--;
    }

    private void executePop() {
        // read AC from memory at address in SP
        // SP++
        this.AC = this.memory.read(SP);
        SP++;
    }

    private void executeInt() {
        // push system state
        // PC = INTERUPT_PROCESS_ADDRESS
        throw new UnsupportedOperationException();
    }

    private void executeIret() {
        // pop system state
        throw new UnsupportedOperationException();
    }

    private void executeEnd() {
        // set end of program flag to true
        throw new UnsupportedOperationException();
    }
    
    /**
    * Store system state before interrupt or function call
    */
    private void pushSystemState(){
        // write PC to memory at the address of SP - 2 in system memory stack
        // write SP to memory at the address of SP - 3 in system memory stack
        // SP = SP - 2
        throw new UnsupportedOperationException();
    }
    
    /**
    * Restore system state after interrupt or function call
    */
    private void popSystemState(){
        // read new PC from memory at the address of SP in system memory stack
        // read new SP from memory at the address of SP - 1 in system memory stack
        throw new UnsupportedOperationException();
    }
    
    private void portErr(String errMsg){
        for (int i = 0; i < errMsg.length(); i ++) {
            portList.get(1).outData(errMsg.charAt(i));
        }
        portList.get(1).outData('\n');
    }
    
    private void endProgram(){
        isEndProgram = true;
    }
}
