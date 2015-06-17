/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guavatest;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.primitives.Ints;
/**
 *
 * @author jupiter
 */
public class GuavaTest {
    public static void main(String args[]){
        /*
        
         Interface	JDK or Guava?	Corresponding Guava utility class
         Collection	JDK	Collections2 (avoiding conflict with java.util.Collections)
         List	JDK	Lists
         Set	JDK	Sets
         SortedSet	JDK	Sets
         Map	JDK	Maps
         SortedMap	JDK	Maps
         Queue	JDK	Queues
         Multiset	Guava	Multisets
         Multimap	Guava	Multimaps
         BiMap	Guava	Maps
         Table	Guava	Tables
        */
         List<Integer> list = Lists.newArrayList();
         Map<String, Object> map = Maps.newLinkedHashMap();
         /*Iterables*/
        
         /*
         List
         */
        List<Integer> countUp = Ints.asList(1, 2, 3, 4, 5);
        List<Integer> countDown = Lists.reverse(countUp); // {5, 4, 3, 2, 1}
        List<List<Integer>> parts = Lists.partition(countUp, 2); // {{1, 2}, {3, 4}, {5}}
        /*
        Set
        */
        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");
        SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength); // contains "two", "three", "seven"
        // I can use intersection as a Set directly, but copying it can be more efficient if I use it a lot.
        /*
        maps
        */
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        diff.entriesInCommon(); // {"b" => 2}
        diff.entriesDiffering(); // {"c" => (3, 4)}
        diff.entriesOnlyOnLeft(); // {"a" => 1}
        diff.entriesOnlyOnRight(); // {"d" => 5}
        
        /*
        multisets
        */
        Multiset<String> multiset1 = HashMultiset.create();
        multiset1.add("a", 2);

        Multiset<String> multiset2 = HashMultiset.create();
        multiset2.add("a", 5);

        multiset1.containsAll(multiset2); // returns true: all unique elements are contained, 
        // even though multiset1.count("a") == 2 < multiset2.count("a") == 5
        Multisets.containsOccurrences(multiset1, multiset2); // returns false

//        multiset2.removeOccurrences(multiset1); // multiset2 now contains 3 occurrences of "a"

        multiset2.removeAll(multiset1); // removes all occurrences of "a" from multiset2, even though multiset1.count("a") == 2
        multiset2.isEmpty(); // returns true
        
       }

    
}
