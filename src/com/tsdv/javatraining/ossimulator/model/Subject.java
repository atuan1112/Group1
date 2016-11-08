/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.model;

import com.tsdv.javatraining.ossimulator.api.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * Require Java8
 * @author TrinhNX
 */
public class Subject {

    /**
     * Contain the list of the observer items
     */
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Attach an observer to the list
     *
     * @param observer
     */
    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Remove an observer from list
     *
     * @param observer
     */
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Trigger the list observers
     */
    public void notifyAllObservers() {
        observers.forEach((observer) -> {
            observer.onTimerEvent();
        });
    }
}
