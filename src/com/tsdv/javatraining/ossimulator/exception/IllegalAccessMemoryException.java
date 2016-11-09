/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.exception;

/**
 *  Access memory exception
 * @author TrinhNX
 */
public class IllegalAccessMemoryException extends Exception {

    public IllegalAccessMemoryException(String msg) {
        super(msg);
    }

    public IllegalAccessMemoryException(Throwable e) {
        super(e);
    }

}
