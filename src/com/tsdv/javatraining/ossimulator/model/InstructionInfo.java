/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.model;

/**
 * Default set instructions
 *
 * @author TrinhNX
 */
public enum InstructionInfo {
    LOAD_VALUE(1, 1),
    LOAD_ADDR(2, 1),
    LOAD_IND_ADDR(3, 1),
    LOAD_IDX_X_ADDR(4, 1),
    LOAD_IDX_Y_ADDR(5, 1),
    LOAD_SPX(6, 1),
    STORE_ADDR(7, 1),
    GET(8, 0),
    PUT_PORT(9, 1),
    ADD_X(10, 0),
    ADD_Y(11, 0),
    SUB_X(12, 0),
    SUB_Y(13, 0),
    COPY_TO_X(14, 0),
    COPY_FROM_X(15, 0),
    COPY_TO_Y(16, 0),
    COPY_FROM_Y(17, 0),
    COPY_TO_SP(18, 0),
    COPY_FROM_SP(19, 0),
    JUMP_ADDR(20, 1),
    JUMP_IF_EQUAL(21, 1),
    JUMP_IF_NOT_EQUAL(22, 1),
    CALL_ADDR(23, 1),
    RET(24, 0),
    INCX(25, 0),
    DECX(26, 0),
    PUSH(27, 0),
    POP(28, 0),
    INT(29, 0),
    IRET(30, 0),
    END(50, 0);

    /* Instruction opt code */
    public int optCode;
    /* Number of Instruction's operands */
    public int numOfOperands;

    private InstructionInfo(int optCode, int numOfOperands) {
        this.optCode = optCode;
        this.numOfOperands = numOfOperands;
    }

    public int getOptCode() {
        return optCode;
    }

    public int getNumOfOperands() {
        return numOfOperands;
    }
}
