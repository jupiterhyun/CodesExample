/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guavatest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;
import com.google.common.base.Objects;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

import java.io.IOException;
import com.google.common.base.Objects;
import com.google.common.base.Throwables;

/**
 *
 * @author jupiter
 */
public class GuavaTest {
    public static void main(String args[]){
        GuavaTest guavaTest = new GuavaTest();

        try {
            guavaTest.showcaseThrowables();
        } catch (InvalidInputException e) {
            //get the root cause
            System.out.println(Throwables.getRootCause(e));
        } catch (Exception e) {
            //get the stack trace in string format
            System.out.println(Throwables.getStackTraceAsString(e));
        }
        try {
            guavaTest.showcaseThrowables1();
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
        }
    }

    public void showcaseThrowables() throws InvalidInputException {
        try {
            sqrt(-3.0);

        } catch (Throwable e) {
            //check the type of exception and throw it
            Throwables.propagateIfInstanceOf(e, InvalidInputException.class
            );
            Throwables.propagate(e);
        }
    }

    public void showcaseThrowables1() {
        try {
            int[] data = {1, 2, 3};
            getValue(data, 4);

        } catch (Throwable e) {
            Throwables.propagateIfInstanceOf(e, IndexOutOfBoundsException.class
            );
            Throwables.propagate(e);
        }
    }

    public double sqrt(double input) throws InvalidInputException {
        if (input < 0) {
            throw new InvalidInputException();
        }
        return Math.sqrt(input);
    }

    public double getValue(int[] list, int index) throws IndexOutOfBoundsException {
        return list[index];
    }

    public void dummyIO() throws IOException {
        throw new IOException();

    }
}
class InvalidInputException extends Exception {
}
