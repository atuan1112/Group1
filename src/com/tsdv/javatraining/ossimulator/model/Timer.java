/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.model;

import com.tsdv.javatraining.ossimulator.api.Observer;
import com.tsdv.javatraining.ossimulator.model.Subject;

/**
 *
 * @author TrinhNX
 */
public final class Timer extends Observer {

    // The tick time for this timer
    private final int tickTime;

    private Timer(int tickTime) {
        this.tickTime = tickTime;
    }

    public int getTickTime() {
        return tickTime;
    }

    @Override
    public void onTimerEvent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static Timer buildTimer(Subject subject, int tickTime) {
        Timer timer = new Timer(tickTime);
        subject.attach(timer);
        return timer;
    }
}
