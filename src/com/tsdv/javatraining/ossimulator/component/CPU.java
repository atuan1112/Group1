/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.component;

import com.tsdv.javatraining.ossimulator.api.Peripheral;
import com.tsdv.javatraining.ossimulator.api.TimerObserver;
import com.tsdv.javatraining.ossimulator.data.Instruction;
import com.tsdv.javatraining.ossimulator.data.InstructionInfo;
import com.tsdv.javatraining.ossimulator.exception.IllegalAccessMemoryException;
import com.tsdv.javatraining.ossimulator.util.ErrMessage;
import java.util.ArrayList;
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
    /* Default timer tick */
    private static final int DEFAULT_TIMER_TICK = 100;
    
    /* CPU state */
    private static final int NORMAL_MODE = 0;
    private static final int SYSTEM_MODE = 1;
    
    /* CPU's ports */
    private final List<Port> portList;
    /* CPU's timer */    
    private final List<Timer> timerList;
    /* program memory */    
    private final Memory memory;
    /* CPU's registers  */    
    private int PC;
    private int SP;
    private int IR;
    private int AC,X,Y;
    
    /* CPU state */
    private int CPUState;
    /* end program flag */
    private boolean isEndProgram;
    
    /**
     * Constructor to create CPU
     * hard CPU configuration
     * @param memory 
     */
    public CPU(Memory memory) {
        // set refer to memory
        this.memory = memory;
        this.timerList = new ArrayList<>();
        this.portList = new ArrayList<>();
        // init timer
        Timer timer1 = new Timer(DEFAULT_TIMER_TICK, new TimerObserver() {
            @Override
            public void onTimerEvent() {
                processTimerInterrupt();
            }
        });
        timerList.add(timer1);
        
        // init port
        Port port1 = new Port(1);
        Port port2 = new Port(2);
        
        portList.add(port1);
        portList.add(port2);
        
        // set end flag to flase
        isEndProgram = false;
        
    }
    
    public void setTimerPeriod(int TimerID, int TickTime){
        // set Timer interval time
        Timer timer = timerList.get(TimerID);
        timer.setTickTime(TickTime);
        timer.reset();
    }
    
    public void connectPeripheral(int portID, Peripheral peripheral){
        // connect port to peripheral
        for (int i = 0; i < portList.size(); i++ ) {
            Port port = portList.get(i);
            if (port.getId() == portID) {
                port.connect(peripheral);
            }
        }
    }
    
    /**
     * Start the cpu operation
     */
    public void start(){
        // init registers
        initRegisters();
        // reset system state
        CPUState = NORMAL_MODE;
        
        // while not end of program
        while (isEndProgram == false){
            try {
                // fetch instruction
                fetchInstruction();
                // decode instruction
                Instruction instruction = decodeInstruction();
                // execute instruction
                executeInstruction(instruction);
                // update timer
                updateTimer();
            } catch(IllegalAccessMemoryException | IllegalArgumentException e) {
                portErr(e.getMessage());
                endProgram();
            }
        }
    }
    
    private void initRegisters(){
        this.PC = 0;
        this.SP = memory.getCapacity() - 1;
        this.AC = 0;
        this.X = 0;
        this.Y = 0;
    }
    

    
    /**
     * fetch next instruction in the memory
     */    
    private void fetchInstruction() throws IllegalAccessMemoryException{
        // read next instruction in memory at the address in PC
        // store the instrucion in IR
        this.IR  = readMemory(this.PC);
        // increase PC
        this.PC++;
        
    }
    
    /**
     * decode the current instruction
     * @return decoded instruction
     */
    private Instruction decodeInstruction() throws IllegalAccessMemoryException{
        // Create new instruction object
        Instruction instruction = new Instruction();
        InstructionInfo instructionInfo;
    
        // find the instruction info from instruction set
        instructionInfo = findInstructionInfo();
        instruction.setInfo(instructionInfo);
        
        // read the operands from memory
        for (int i = 0; i < instructionInfo.numOfOperands; i ++){
            int operand = readMemory(this.PC);
            this.PC++;
            instruction.addOperands(operand);
        }

        return instruction;
    }
    
    /**
     * find instruction info base on opt code
     * @return 
     */
    private InstructionInfo findInstructionInfo()
    {
        for (InstructionInfo info : InstructionInfo.values()) 
        {
            if (info.optCode == this.IR){
                return info;
            }
        }
        throw new IllegalArgumentException(ErrMessage.NOT_SUPPORT_INSTRUCTION);
    }
    
    /**
     * Execute instruction
     * @param instruction instruction to be executed
     */
    private void executeInstruction(Instruction instruction) throws IllegalAccessMemoryException {
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

    }
    
    /**
     * update all timers
     */
    private void updateTimer() {
        /* stop counting when in interrupt process or when just return from interrupt */
        if (CPUState != SYSTEM_MODE && IR != InstructionInfo.IRET.optCode) {
            timerList.forEach((timer) -> {
                timer.updateTime();
            });
        }
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
    private void executeLoadAddr(int addr) throws IllegalAccessMemoryException {
        // read memory at the adrr
        // store the value to AC
        this.AC = this.readMemory(addr);
    }

    private void executeLoadIndAddr(int indAddr) throws IllegalAccessMemoryException {
        // read memory at the indAdrr to get the direct addr
        int realAddr = this.readMemory(indAddr);
        // read memory at the direct addr to get the value
        // store the value to AC 
        this.AC = this.readMemory(realAddr);
    }

    private void executeLoadIdxXAddr(int addr) throws IllegalAccessMemoryException {
        // read memory at addr + X
        int value = this.readMemory(addr + this.X);
        // store the read value to AC        
        this.AC = value;
    }

    private void executeLoadIdxYAddr(int addr) throws IllegalAccessMemoryException {
        // read memory at addr + Y
        // store the read value to AC
        this.AC = this.readMemory(addr + this.Y);
    }

    private void executeLoadSpx() throws IllegalAccessMemoryException {
        // read memory at SP + X
        // store the read value to AC
        this.AC = this.readMemory(this.SP - this.X); // SP grow down toward 0
    }

    private void executeStoreAddr(int addr) throws IllegalAccessMemoryException {
        // write value of AC to addr 
        this.writeMemory(addr, this.AC);
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
        Port port = this.portList.get(portID - 1);
        if (port != null) {
            port.outData(this.AC);
        } else {
            throw new IllegalArgumentException(ErrMessage.INVALID_PORT_INSTRUCTION);
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
        {
            this.PC = addr;
        }
    }

    private void executeJumpIfNotEqual(int addr) {
        // if AC != 0
            // PC = addr
        if (this.AC != 0)
        {
            this.PC = addr;
        }
    }

    private void executeCallAddr(int addr) throws IllegalAccessMemoryException {
        // push system state
        // PC = addr
        pushSystemState();
        this.PC = addr;
    }

    private void executeRet() throws IllegalAccessMemoryException {
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

    private void executePush() throws IllegalAccessMemoryException {
        // write AC to memory at address in SP
        // SP--
        this.writeSystemStackMemory(this.SP, this.AC);
        this.SP--;
    }

    private void executePop() throws IllegalAccessMemoryException {
        // SP++        
        // read AC from memory at address in SP
        this.SP++;
        this.AC = this.readMemory(this.SP);
    }

    private void executeInt() throws IllegalAccessMemoryException {
        processProgramInterrupt();
    }

    private void executeIret() throws IllegalAccessMemoryException {
        returnFromInterupt();
    }

    private void executeEnd() {
        // set end of program flag to true
        endProgram();
    }
    
    
    /**
     * process timer interrupt
     */
    private void processTimerInterrupt(){
        if (this.CPUState != SYSTEM_MODE && hasTimerHandle()) {
            try {
                // push system state
                pushSystemState();
            } catch (IllegalAccessMemoryException ex) {
                portErr(ex.getMessage());
            }
            // update PC = TIMER_PROCESS_ADDRESS
            this.PC = TIMER_PROCESS_ADDRESS;
            this.CPUState = SYSTEM_MODE;
        }
    }
    
    /**
     * process timer interrupt
     */
    private void processProgramInterrupt(){
        if (this.CPUState != SYSTEM_MODE && hasInterruptHandle()) {
            try {
                // push system state
                pushSystemState();
            } catch (IllegalAccessMemoryException ex) {
                portErr(ex.getMessage());
            }
            // update PC = TIMER_PROCESS_ADDRESS
            this.PC = INTERUPT_PROCESS_ADDRESS;
            this.CPUState = SYSTEM_MODE;
        }
    }
    
    /**
     * return from a interrupt process
     */
    private void returnFromInterupt() throws IllegalAccessMemoryException {
        if (this.CPUState == SYSTEM_MODE){
            popSystemState();
            this.CPUState = NORMAL_MODE;
        }
    }    
    
    /**
    * Store system state before interrupt or function call
    */
    private void pushSystemState() throws IllegalAccessMemoryException{
        // write PC to memory at the address of SP in system memory stack
        // write SP to memory at the address of SP - 1 in system memory stack
        // write AC to memory at the address of SP - 2 in system memory stack
        // write X to memory at the address of SP - 3 in system memory stack
        // write Y to memory at the address of SP - 4 in system memory stack        
        // SP = SP - 5
        writeSystemStackMemory(this.SP, PC);
        writeSystemStackMemory(this.SP - 1, SP);
        writeSystemStackMemory(this.SP - 2, AC);
        writeSystemStackMemory(this.SP - 3, X);
        writeSystemStackMemory(this.SP - 4, Y);
        this.SP = this.SP - 5;
    }
    
    /**
    * Restore system state after interrupt or function call
    */
    private void popSystemState() throws IllegalAccessMemoryException{
        // SP = SP + 5
        // read new PC from memory at the address of SP in system memory stack
        // read new SP from memory at the address of SP - 1 in system memory stack
        // read new AC from memory at the address of SP - 2 in system memory stack
        // read new X from memory at the address of SP - 3 in system memory stack
        // read new Y from memory at the address of SP - 4 in system memory stack
        this.SP = this.SP + 5;
        this.PC = readMemory(this.SP);
        this.SP = readMemory(this.SP - 1);
        this.AC = readMemory(this.SP - 2);
        this.X = readMemory(this.SP - 3);
        this.Y = readMemory(this.SP - 4);        
    }
    
    /**
     * print error to port 2
     * @param errMsg 
     */
    private void portErr(String errMsg){
        for (int i = 0; i < errMsg.length(); i ++) {
            portList.get(1).outData(errMsg.charAt(i));
        }
        portList.get(1).outData('\n');
    }
    
    /**
     * stop CPU execution cycle
     */
    private void endProgram(){
        isEndProgram = true;
    }
    
    /**
     * check if timer is handled in the process 
     * @return false if no timer handle
     */
    private boolean hasTimerHandle(){
        int timerFirstOptcode = 0;
        try {
            timerFirstOptcode = readMemory(TIMER_PROCESS_ADDRESS);
        } catch (IllegalAccessMemoryException ex) {
            return false;
        }
        return timerFirstOptcode != 0;
    }
    
    /**
     * check if interrupt is handled in the process
     * @return 
     */
    private boolean hasInterruptHandle(){
        int interruptFirstOptcode = 0;
        try {
            interruptFirstOptcode = readMemory(INTERUPT_PROCESS_ADDRESS);
        } catch (IllegalAccessMemoryException ex) {
            return false;
        }
        return interruptFirstOptcode != 0;
    }
    
    /**
     * read from memory
     * @param addr
     * @return
     * @throws IllegalAccessMemoryException 
     */
    private int readMemory(int addr) throws IllegalAccessMemoryException
    {
        return this.memory.read(addr);
    }
    
    /**
     * write to memory for common operation 
     * @param addr
     * @param data
     * @throws IllegalAccessMemoryException 
     */
    private void writeMemory(int addr, int  data) throws IllegalAccessMemoryException
    {
        if (addr >= SYSTEM_DATA_ADDRESS) {
            throw new IllegalAccessMemoryException(ErrMessage.INVALID_ACCESS_MEMORY);
        }
        this.memory.write(addr, data);
    }
    
     /**
     * write to memory to system stack 
     * @param addr
     * @param data
     * @throws IllegalAccessMemoryException 
     */
    private void writeSystemStackMemory(int addr, int  data) throws IllegalAccessMemoryException
    {
        if (addr < SYSTEM_DATA_ADDRESS) {
            throw new IllegalAccessMemoryException(ErrMessage.INVALID_ACCESS_MEMORY);
        }
        this.memory.write(addr, data);
    }
}
