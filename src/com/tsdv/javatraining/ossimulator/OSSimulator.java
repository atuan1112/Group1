/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator;

import com.tsdv.javatraining.ossimulator.data.DataSegment;
import com.tsdv.javatraining.ossimulator.component.OperatingSystem;
import com.tsdv.javatraining.ossimulator.util.SegmentParser;
import java.io.File;
import java.util.List;

/**
 *
 * @author TrinhNX
 */
public class OSSimulator {

    public static final String FILE_PATH = "resources/program1.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO: Parse argument
        // TODO: Hardcoded

        final int tickTime = 100;
        final String instructionFile = FILE_PATH;
        File programFile = new File(instructionFile);
        OperatingSystem operatingSystem = new OperatingSystem();

        // Read program file
        List<DataSegment> programData = SegmentParser.parseFile(programFile);
        // Load program data to memory
        operatingSystem.loadProgram(programData);
        // Set timer period
        operatingSystem.setTimer(tickTime);
        // start simulation
        operatingSystem.Start();

    }

}
