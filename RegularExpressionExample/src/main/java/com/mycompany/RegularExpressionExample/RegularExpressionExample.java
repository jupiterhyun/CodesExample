package com.mycompany.RegularExpressionExample;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author jupiter
 */
public class RegularExpressionExample {

    public static void main(String args[]) {
        Pattern pattern = Pattern.compile("\\$");
        String s = "asdfva$bb$bc$$ads";
        Matcher matcher = pattern.matcher(s);
        //for fun
         boolean found = false;
            while (matcher.find()) {
                System.out.println("I found the text" +
                    matcher.group() + "starting at " +
                    "index " + matcher.start() + " and ending at index " + matcher.end());
                found = true;
            }
            if(!found){
                System.out.println("No match found.%n");
            }
        //replace
        String res = matcher.replaceAll("****");
        
        System.out.println(res);
        
        //split
        final String INPUT =
        "one:two:three:four:five";
        Pattern p = Pattern.compile(":");
        String[] items = p.split(INPUT);
        for (String ss : items) {
            System.out.println(ss);
        }
        
    }
    
    /*
    
    Construct	Description
     [abc]	                  a, b, or c (simple class)
     [^abc]	                  Any character except a, b, or c (negation)
     [a-zA-Z]	                  a through z, or A through Z, inclusive (range)
     [a-d[m-p]]	a through d, or m through p: [a-dm-p] (union)
     [a-z&&[def]]	d, e, or f (intersection)
     [a-z&&[^bc]]	a through z, except for b and c: [ad-z] (subtraction)
     [a-z&&[^m-p]]	a through z, and not m through p: [a-lq-z] (subtraction
    
    
    Construct	Description
.	Any character (may or may not match line terminators)
\d	A digit: [0-9] Pattern.compile("\\d");
\D	A non-digit: [^0-9] Pattern.compile("\\D");
\s	A whitespace character: [ \t\n\x0B\f\r]
\S	A non-whitespace character: [^\s]
\w	A word character: [a-zA-Z_0-9]
\W	A non-word character: [^\w]
    Greedy	Reluctant	Possessive	Meaning
X?	X??	X?+	X, once or not at all
X*	X*?	X*+	X, zero or more times
X+	X+?	X++	X, one or more times
X{n}	X{n}?	X{n}+	X, exactly n times
X{n,}	X{n,}?	X{n,}+	X, at least n times
X{n,m}	X{n,m}?	X{n,m}+	X, at least n but not more than m times
    Boundary Construct	Description
^	The beginning of a line
$	The end of a line
\b	A word boundary
\B	A non-word boundary
\A	The beginning of the input
\G	The end of the previous match
\Z	The end of the input but for the final terminator, if any
\z	The end of the input
    
    
    */
}
