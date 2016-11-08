/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsdv.javatraining.ossimulator.util;

import com.tsdv.javatraining.ossimulator.model.DataSegment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author TrinhNX
 */
public class SegmentParser {

    /**
     * Fix the pattern : Either number or the .plusnumber or the -plusnumber (only 1)
     */
    private static final String SEGMENT_REGEX = "(^\\d++)|(^[\\.\\-]{1}\\d++)";
    /**
     * Compile the pattern
     */
    private static final Pattern SEGMENT_PATTERN = Pattern.compile(SEGMENT_REGEX);

    /**
     * Get the datasegment from file
     *
     * @param segmentFile
     * @return
     */
    public static List<DataSegment> parseFile(File segmentFile) {
        List<DataSegment> dataSegment = new ArrayList<>();
        try (FileReader fileReader = new FileReader(segmentFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);) {
            // Initialize a boolean to indicate should add the datasegment
            // Step 1: Read the line (don't care about the word except .) : Use regex to parse
            // Skip the invalid line
            // Step 2: If not the address => add into datasegment
            //          If need address => create new datasegment
            // Step 3: Return list as array            
            String line;
            do {
                DataSegment.Builder builder = new DataSegment.Builder();
                line = bufferedReader.readLine().trim();
                if (isValidInstruction(line)) {
                    int data = getInstructionData(line);
                    if (line.startsWith(".")) {
                        if (!builder.isEmpty()) {
                            DataSegment segment = builder.build();
                            dataSegment.add(segment);
                        }
                        // Update the builder
                        builder = new DataSegment.Builder();
                        builder.setAddress(data);
                    } else {
                        builder.addValue(data);
                    }
                }
                if (!builder.isEmpty()) {
                    dataSegment.add(builder.build());
                }
            } while (line != null);

        } catch (IOException ex) {
            Logger.getLogger(SegmentParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataSegment;
    }

    /**
     * Check whether a line had value value
     *
     * @param line
     * @return
     */
    private static boolean isValidInstruction(final String line) {
        return matchInstruction(line).find();
    }

    /**
     * Get the number value from line
     *
     * @param line
     * @return -1 if the line is not matcher
     */
    private static int getInstructionData(final String line) {
        int instructionAddress = -1;
        Matcher matcher = matchInstruction(line);
        boolean findMatcher = matcher.find();
        if (findMatcher) {
            final String address = matcher.group();
            int dotIndex = address.indexOf('.');
            if (dotIndex == -1) {
                instructionAddress = Integer.parseInt(address.substring(dotIndex));
            } else {
                instructionAddress = Integer.parseInt(address);
            }
        }
        return instructionAddress;
    }

    /**
     * Return the matcher pattern
     *
     * @param line
     * @return
     */
    private static Matcher matchInstruction(final String line) {
        return SEGMENT_PATTERN.matcher(line);
    }

    public static void main(String args[]) {
        Pattern pattern = Pattern.compile(SEGMENT_REGEX);
        final String match_1 = " .100 //abc";
        final String match_2 = " -1, //abc";
        final String match_3 = "0 a";
        final String match_4 = "3";

        final String not_match_1 = "a";
        final String not_match_2 = "a1";
        final String not_match_3 = ".a";
        final String not_match_4 = "#3";

        for (String matched : new String[]{match_1, match_2, match_3, match_4}) {
            Matcher matcher = pattern.matcher(matched.trim());

            System.out.println(matcher.find());
            System.out.println(matcher.group());
        }

        for (String not_matched : new String[]{not_match_1, not_match_2, not_match_3, not_match_4}) {
            Matcher matcher = pattern.matcher(not_matched.trim());
            System.out.println(matcher.find());
        }

    }
}
