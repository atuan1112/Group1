/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.api;

/**
 * Observer Timer tick event Class
 * @author TrinhNX
 */
public abstract class TimerObserver {

    /**
     * Call whenever the observer got update
     */
    public abstract void onTimerEvent();
}
