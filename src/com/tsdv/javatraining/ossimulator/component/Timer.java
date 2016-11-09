/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.component;

import com.tsdv.javatraining.ossimulator.api.TimerObserver;
import com.tsdv.javatraining.ossimulator.api.TimerSubject;

/**
 *  Class represent the timer object
 * @author TrinhNX
 */
public final class Timer extends TimerSubject {

    // The tick time for this timer
    private int tickTime;
    // Store current time
    private int currentTime; 

    public Timer(int tickTime, TimerObserver observer) {
        this.tickTime = tickTime;
        this.currentTime = 0;
        attach(observer);
    }

    public int getTickTime() {
        return tickTime;
    }

    public void setTickTime(int tickTime) {
        this.tickTime = tickTime;
    }
    
    public void reset(){
        this.currentTime = 0;
    }
    
    public void updateTime(){
        // increase time
        this.currentTime ++;
        // if reach tick time
        if (this.currentTime == this.tickTime)
        {
            // notify all observers
            notifyAllObservers();
            // reset current time
            reset();
        }
    }
}
