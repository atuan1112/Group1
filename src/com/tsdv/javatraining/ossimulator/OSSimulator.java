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
 * The main program for simulation
 * @author TrinhNX
 */
public class OSSimulator {

    /**
    Default file path for OS
     */
    private static final String FILE_PATH = "resources/program1.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO: Parse argument
        // TODO: Hardcoded
        final int tickTime;
        final String instructionFilePath;
        if (args == null || args.length == 0 || args.length < 2) {
            tickTime = 1;
            instructionFilePath = FILE_PATH;
        } else {
            int tickParsed = Integer.parseInt(args[0]);
            if (tickParsed > 0) {
                tickTime = tickParsed;
            } else {
                tickTime = 1;
            }
            instructionFilePath = args[1];
        }

        File programFile = new File(instructionFilePath);
        OperatingSystem operatingSystem = new OperatingSystem();
        // Read program file
        List<DataSegment> programData = SegmentParser.parseFile(programFile);
        // Load program data to memory
        operatingSystem.loadProgram(programData);
        // Set timer period
        operatingSystem.setTimer(tickTime);
        // start simulation
        operatingSystem.start();
    }
}
