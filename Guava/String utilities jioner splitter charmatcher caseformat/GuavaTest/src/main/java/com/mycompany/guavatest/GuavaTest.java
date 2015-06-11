/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guavatest;

import java.util.Arrays;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.CharMatcher;

/**
 *
 * @author jupiter
 */
public class GuavaTest {

    public static void main(String args[]) {
        GuavaTest tester = new GuavaTest();
        tester.testJoiner();
        tester.testSplitter();
        tester.testCharMatcher();
    }

    private void testJoiner() {
        System.out.println(Joiner.on(",")
                .skipNulls()
                .join(Arrays.asList(1, 2, 3, 4, 5, null, 6)));
    }

    private void testSplitter() {
        System.out.println(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("the ,quick, , brown , fox, jumps, over, the, lazy, little dog."));
    }

    private void testCharMatcher() {
        System.out.println(CharMatcher.DIGIT.retainFrom("mahesh123")); //only the digits 
        System.out.println(CharMatcher.WHITESPACE.trimAndCollapseFrom(
                "Mahesh Parashar ", ' '));
        // trim whitespace at ends, and replace/collapse whitespace into single spaces
        System.out.println(CharMatcher.JAVA_DIGIT.replaceFrom("mahesh123",
                "*")); // star out all digits
        System.out.println(CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE
        ).retainFrom("mahesh123"));
        // eliminate all characters that aren't digits or lowercase
    }

}
