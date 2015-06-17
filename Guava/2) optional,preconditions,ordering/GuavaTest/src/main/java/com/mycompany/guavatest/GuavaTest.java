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

/**
 *
 * @author jupiter
 */
public class GuavaTest {
    public static void main(String args[]){
        GuavaTest guavaTest = new GuavaTest();
        
        Integer value1 = null;
        Integer value2 = new Integer(10);
        //Optional.fromNullable - allows passed parameter to be null.
        Optional<Integer> a = Optional.fromNullable(value1);
        //Optional.of - throws NullPointerException if passed parameter is null
        Optional<Integer> b = Optional.of(value2);
        System.out.println(guavaTest.sum1(a,b));
        
        /***************** predict **************************************/
        try {
            System.out.println(guavaTest.sqrt(-3.0));
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(guavaTest.sum(null,3));
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(guavaTest.getValue(6));
        }catch(IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        
        /********************Ordering Class************************************/
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(new Integer(5));
        numbers.add(new Integer(2));
        numbers.add(new Integer(15));
        numbers.add(new Integer(51));
        numbers.add(new Integer(53));
        numbers.add(new Integer(35));
        numbers.add(new Integer(45));
        numbers.add(new Integer(32));
        numbers.add(new Integer(43));
        numbers.add(new Integer(16));
        Ordering ordering = Ordering.natural();
        System.out.println("Input List: ");
        System.out.println(numbers);

        Collections.sort(numbers,ordering );
        System.out.println("Sorted List: ");
        System.out.println(numbers);

        System.out.println("======================");
        System.out.println("List is sorted: " + ordering.isOrdered(numbers));
        System.out.println("Minimum: " + ordering.min(numbers));
        System.out.println("Maximum: " + ordering.max(numbers));
        Collections.sort(numbers,ordering.reverse());
        System.out.println("Reverse: " + numbers);
        numbers.add(null);
        System.out.println("Null added to Sorted List: ");
        System.out.println(numbers);
        Collections.sort(numbers,ordering.nullsFirst());
        System.out.println("Null first Sorted List: ");
        System.out.println(numbers);
        System.out.println("======================");
        List<String> names = new ArrayList<String>();
        names.add("Ram");
        names.add("Shyam");
        names.add("Mohan");
        names.add("Sohan");
        names.add("Ramesh");
        names.add("Suresh");
        names.add("Naresh");
        names.add("Mahesh");
        names.add(null);
        names.add("Vikas");
        names.add("Deepak");
        System.out.println("Another List: ");
        System.out.println(names);
        Collections.sort(names,ordering.nullsFirst().reverse());
        System.out.println("Null first then reverse sorted list: ");
        System.out.println(names);
    }
    public Integer sum1(Optional<Integer> a, Optional<Integer> b){
        //Optional.isPresent - checks the value is present or not
        System.out.println("First parameter is present: " +
        a.isPresent());
        System.out.println("Second parameter is present: " + b.isPresent());
        //Optional.or - returns the value if present otherwise returns
        //the default value passed.
        Integer value1 = a.or(new Integer(0));
        //Optional.get - gets the value, value should be present
        Integer value2 = b.get();
        return value1 + value2;
    }
    
    public double sqrt(double input) throws IllegalArgumentException {
        Preconditions.checkArgument(input > 0.0,
            "Illegal Argument passed: Negative value %s.", input);
        return Math.sqrt(input);
    }
    public int sum(Integer a, Integer b){
        a = Preconditions.checkNotNull(a,
            "Illegal Argument passed: First parameter is Null.");
        b = Preconditions.checkNotNull(b,
            "Illegal Argument passed: Second parameter is Null.");
        return a+b;
    }
    public int getValue(int input){
        int[] data = {1,2,3,4,5};
        Preconditions.checkElementIndex(input,data.length,
            "Illegal Argument passed: Invalid index.");
        return 0;
    }
    
}
