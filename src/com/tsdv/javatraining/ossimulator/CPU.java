/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator;

/**
 *
 * @author TrinhNX
 */
import com.tsdv.javatraining.ossimulator.api.Timer;
import com.tsdv.javatraining.ossimulator.model.Instruction;
import java.util.List;
import javax.sound.sampled.Port;

/**
 * Class represent the CPU
 *
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
    private int AC, X, Y;

    public CPU(Memory memory) {
        throw new UnsupportedOperationException();
    }

    /**
     * Start the cpu operation
     */
    public void start() {
        throw new UnsupportedOperationException();
    }

    /**
     * process timer interrupt
     */
    private void processTimerInterrupt() {
        throw new UnsupportedOperationException();
    }

    /**
     * fetch next instruction in the memory
     */
    private void fetchInstruction() {
        throw new UnsupportedOperationException();
    }

    /**
     * decode the current instruction
     *
     * @return decoded instruction
     */
    private Instruction decodeInstruction() {
        throw new UnsupportedOperationException();
    }

    /**
     * Execute instruction
     *
     * @param instruction instruction to be executed
     */
    private void executeInstruction(Instruction instruction) {
        throw new UnsupportedOperationException();
    }

    /**
     * Execute the Load value instruction
     */
    private void executeLoadValue(int value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Execute the Load Address instruction
     */
    private void executeLoadAddr(int addr) {
        throw new UnsupportedOperationException();
    }

    private void executeLoadIndAddr(int addr) {
        throw new UnsupportedOperationException();
    }

    private void executeLoadIdxXAddr(int addr) {
        throw new UnsupportedOperationException();
    }

    private void executeLoadIdxYAddr(int addr) {
        throw new UnsupportedOperationException();
    }

    private void executeLoadSpx() {
        throw new UnsupportedOperationException();
    }

    private void executeStoreAddr() {
        throw new UnsupportedOperationException();
    }

    private void executeGet() {
        throw new UnsupportedOperationException();
    }

    private void executePutPort() {
        throw new UnsupportedOperationException();
    }

    private void executeAddX() {
        throw new UnsupportedOperationException();
    }

    private void executeAddY() {
        throw new UnsupportedOperationException();
    }

    private void executeSubX() {
        throw new UnsupportedOperationException();
    }

    private void executeSubY() {
        throw new UnsupportedOperationException();
    }

    private void executeCopyToX() {
        throw new UnsupportedOperationException();
    }

    private void executeCopyFromX() {
        throw new UnsupportedOperationException();
    }

    private void executeCopyToY() {
        throw new UnsupportedOperationException();
    }

    private void executeCopyFromY() {
        throw new UnsupportedOperationException();
    }

    private void executeCopyToSp() {
        throw new UnsupportedOperationException();
    }

    private void executeCopyFromSp() {
        throw new UnsupportedOperationException();
    }

    private void executeJumpAddr() {
        throw new UnsupportedOperationException();
    }

    private void executeJumpIfEqual() {
        throw new UnsupportedOperationException();
    }

    private void executeJumpIfNotEqual() {
        throw new UnsupportedOperationException();
    }

    private void executeCallAddr() {
        throw new UnsupportedOperationException();
    }

    private void executeRet() {
        throw new UnsupportedOperationException();
    }

    private void executeIncx() {
        throw new UnsupportedOperationException();
    }

    private void executeDecx() {
        throw new UnsupportedOperationException();
    }

    private void executePush() {
        throw new UnsupportedOperationException();
    }

    private void executePop() {
        throw new UnsupportedOperationException();
    }

    private void executeInt() {
        throw new UnsupportedOperationException();
    }

    private void executeIret() {
        throw new UnsupportedOperationException();
    }

    private void executeEnd() {
        throw new UnsupportedOperationException();
    }

    /**
     * Store system state before interrupt or function call
     */
    private void pushSystemState() {
        throw new UnsupportedOperationException();
    }

    /**
     * Restore system state after interrupt or function call
     */
    private void popSystemState() {
        throw new UnsupportedOperationException();
    }

}
