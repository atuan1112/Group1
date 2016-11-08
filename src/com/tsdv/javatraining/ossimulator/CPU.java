/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator;

import com.tsdv.javatraining.ossimulator.api.Timer;
import com.tsdv.javatraining.ossimulator.model.Instruction;
import com.tsdv.javatraining.ossimulator.model.InstructionInfo;
import java.util.List;

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
        throw new UnsupportedOperationException();
    }

    /**
    * Execute the Load Address instruction
    */
    private void executeLoadAddr(int addr) {
        // read memory at the adrr
        // store the value to AC
        throw new UnsupportedOperationException();
    }

    private void executeLoadIndAddr(int indAddr) {
        // read memory at the indAdrr to get the direct addr
        // read memory at the direct addr to get the value
        // store the value to AC 
        throw new UnsupportedOperationException();
    }

    private void executeLoadIdxXAddr(int addr) {
        // read memory at addr + X
        // store the read value to AC        
        throw new UnsupportedOperationException();
    }

    private void executeLoadIdxYAddr(int addr) {
        // read memory at addr + Y
        // store the read value to AC
        throw new UnsupportedOperationException();
    }

    private void executeLoadSpx() {
        // read memory at SP + X
        // store the read value to AC
        throw new UnsupportedOperationException();
    }

    private void executeStoreAddr(int addr) {
        // write value of AC to addr 
        throw new UnsupportedOperationException();
    }

    private void executeGet() {
        // random number from 1 to 100 to AC
        throw new UnsupportedOperationException();
    }

    private void executePutPort(int portID) {
        // output data to port
        throw new UnsupportedOperationException();
    }

    private void executeAddX() {
        // AC = AC + X
        throw new UnsupportedOperationException();
    }

    private void executeAddY() {
        // AC = AC + Y
        throw new UnsupportedOperationException();
    }

    private void executeSubX() {
        // AC = AC - X
        throw new UnsupportedOperationException();
    }

    private void executeSubY() {
        // AC = AC - Y
        throw new UnsupportedOperationException();
    }

    private void executeCopyToX() {
        // X = AC
        throw new UnsupportedOperationException();
    }

    private void executeCopyFromX() {
        // AC = X
        throw new UnsupportedOperationException();
    }

    private void executeCopyToY() {
        // Y = AC
        throw new UnsupportedOperationException();
    }

    private void executeCopyFromY() {
        // AC = Y
        throw new UnsupportedOperationException();
    }

    private void executeCopyToSp() {
        // SP = AC
        throw new UnsupportedOperationException();
    }

    private void executeCopyFromSp() {
        // AC = SP
        throw new UnsupportedOperationException();
    }

    private void executeJumpAddr(int addr) {
        // PC = addr
        throw new UnsupportedOperationException();
    }

    private void executeJumpIfEqual(int addr) {
        // if AC == 0
            // PC = addr
        throw new UnsupportedOperationException();
    }

    private void executeJumpIfNotEqual(int addr) {
        // if AC != 0
            // PC = addr
        throw new UnsupportedOperationException();
    }

    private void executeCallAddr(int addr) {
        // push system state
        // PC = addr
        throw new UnsupportedOperationException();
    }

    private void executeRet() {
        // pop system state
        throw new UnsupportedOperationException();
    }

    private void executeIncX() {
        // X++
        throw new UnsupportedOperationException();
    }

    private void executeDecX() {
        // X--
        throw new UnsupportedOperationException();
    }

    private void executePush() {
        // write AC to memory at address in SP
        // SP--
        throw new UnsupportedOperationException();
    }

    private void executePop() {
        // read AC from memory at address in SP
        // SP++
        throw new UnsupportedOperationException();
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
