/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.model.util;

import com.tsdv.javatraining.ossimulator.model.DataSegment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TrinhNX
 */
public class SegmentParser {

    /**
     * Get the datasegment from file
     * @param segmentFile
     * @return 
     */
    public static DataSegment[] parserFile(File segmentFile) {
        try (FileReader fileReader = new FileReader(segmentFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);) {
            // Initialize a boolean to indicate should add the datasegment
            // Step 1: Read the line (don't care about the word except .) : Use regex to parse
            // Step 2: If not the address => add into datasegment
            //          If need address => create new datasegment
            // Step 3: Return list as array            

        } catch (IOException ex) {
            Logger.getLogger(SegmentParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException();
    }
}
