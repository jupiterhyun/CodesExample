package com.mycompany.guavatest;

import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 *
 * @author jupiter
 */
public class GuavaTest {

    public static void main(String args[]) {
        GuavaTest tester = new GuavaTest();
        /*
         Map                Corresponding Multiset	Supports null elements
         HashMap	HashMultiset	Yes
         TreeMap	TreeMultiset	Yes (if the comparator does)
         LinkedHashMap	LinkedHashMultiset	Yes
         ConcurrentHashMap	ConcurrentHashMultiset	No
         ImmutableMap	ImmutableMultiset	No
        */

        //how many times a word occurs in a document
        Multiset<String> multiset = HashMultiset.create();//multiset
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");
        //print the occurrence of an element
        System.out.println("Occurrence of 'b' : " + multiset.count("b"));
        //print the total size of the multiset
        System.out.println("Total Size : " + multiset.size());
        //get the distinct elements of the multiset as set
        Set<String> set = multiset.elementSet();
        //display the elements of the set
        System.out.println("Set [");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("]");
        //display all the elements of the multiset using iterator
        Iterator<String> iterator = multiset.iterator();
        System.out.println("MultiSet [");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("]");
        //display the distinct elements of the multiset with their occurrence count
        System.out.println("MultiSet [");
        for (Multiset.Entry<String> entry : multiset.entrySet()) {
            System.out.println("Element: " + entry.getElement()
                    + ",Occurrence(s): " + entry.getCount());
        }
        System.out.println("]");
        //remove extra occurrences
        multiset.remove("b", 2);
        //print the occurrence of an element
        System.out.println("Occurence of 'b' : " + multiset.count("b"));

        /**
         * ********************** multimap *******************************************
         Implementation	Keys          behave like...	    Values behave like..                 
         ArrayListMultimap	HashMap                                ArrayList 
         HashMultimap	                  HashMap	                             HashSet 
         LinkedListMultimap               LinkedHashMap	           LinkedList
         LinkedHashMultimap**	LinkedHashMap                      LinkedHashSet 
         TreeMultimap	                  TreeMap	                             TreeSet 
         ImmutableListMultimap         ImmutableMap	           ImmutableList 
         ImmutableSetMultimap	ImmutableMap                       ImmutableSet
         */
        //Guava's Multimap framework makes it easy to handle a mapping from keys to multiple values.
        Multimap<String, String> multimap = tester.getMultimap();
        
        Collection<String> upperSet = multimap.get("upper");
        List<String> lowerList = (List<String>) multimap.get("lower");
        System.out.println("Initial lower case list");
        System.out.println(lowerList.toString());
        lowerList.add("f");
        System.out.println("Modified lower case list");
        System.out.println(lowerList.toString());
        
        List<String> upperList = (List<String>) multimap.get("upper");
        System.out.println("Initial upper case list");
        System.out.println(upperList.toString());
        upperList.remove("D");
        System.out.println("Modified upper case list");
        System.out.println(upperList.toString());
    
        Map<String, Collection<String>> map = multimap.asMap();
        System.out.println("Multimap as a map");
        for (Map.Entry<String, Collection<String>> entry
                : map.entrySet()) {
            String key = entry.getKey();
            Collection<String> value = multimap.get("lower");
            System.out.println(key + ":" + value);
        }
        //do the same thing
        for (Map.Entry<String, String> entry : multimap.entries()) {
             String key = entry.getKey();
             Collection<String> value = multimap.get("lower");
            System.out.println(key + ":" + value);
        }
        
        System.out.println("Keys of Multimap");
        Set<String> keys = multimap.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
        
        System.out.println("Values of Multimap");
        Collection<String> values = multimap.values();
        System.out.println(values);

        /*
        Key-Value Map Impl	Value-Key Map Impl	Corresponding BiMap
         HashMap	HashMap	HashBiMap
         ImmutableMap	ImmutableMap	ImmutableBiMap
         EnumMap	EnumMap	EnumBiMap
         EnumMap	HashMap	EnumHashBiMap
        */
        /**
         * ******************bi map value can get
         * key************************************************
         */
        BiMap<Integer, String> empIDNameMap = HashBiMap.create();
        empIDNameMap.put(new Integer(101), "Mahesh");
        empIDNameMap.put(new Integer(102), "Sohan");
        empIDNameMap.put(new Integer(103), "Ramesh");
        //Emp Id of Employee "Mahesh"
        System.out.println(empIDNameMap.inverse().get("Mahesh"));

        /*
        HashBasedTable, which is essentially backed by a HashMap<R, HashMap<C, V>>.
        TreeBasedTable, which is essentially backed by a TreeMap<R, TreeMap<C, V>>.
        ImmutableTable, which is essentially backed by an ImmutableMap<R, ImmutableMap<C, V>>. 
        (Note: ImmutableTable has optimized implementations for sparser and denser data sets.)
        ArrayTable, which requires that the complete universe of rows and columns be specified at construction time, 
        but is backed by a two-dimensional array to improve speed and memory efficiency when the table is dense. 
        ArrayTable works somewhat differently from other implementations; consult the Javadoc for details.
        */
        /**
         * ********************Table interface**********************************************
         */
        //Table<R,C,V> == Map<R,Map<C,V>>
         /*
         * Company: IBM, Microsoft, TCS
         * IBM -> {101:Mahesh, 102:Ramesh, 103:Suresh}
         * Microsoft -> {101:Sohan, 102:Mohan, 103:Rohan }
         * TCS -> {101:Ram, 102: Shyam, 103: Sunil }
         *
         * */
        //create a table
        Table<String, String, String> employeeTable
                = HashBasedTable.create();
        //initialize the table with employee details
        employeeTable.put("IBM", "101", "Mahesh");
        employeeTable.put("IBM", "102", "Ramesh");
        employeeTable.put("IBM", "103", "Suresh");
        employeeTable.put("Microsoft", "111", "Sohan");
        employeeTable.put("Microsoft", "112", "Mohan");
        employeeTable.put("Microsoft", "113", "Rohan");
        employeeTable.put("TCS", "121", "Ram");
        employeeTable.put("TCS", "122", "Shyam");
        employeeTable.put("TCS", "123", "Sunil");
        //get Map corresponding to IBM
        Map<String, String> ibmEmployees = employeeTable.row("IBM");
        System.out.println("List of IBM Employees");
        for (Map.Entry<String, String> entry : ibmEmployees.entrySet()) {
            System.out.println("Emp Id: " + entry.getKey() + ", Name: "
                    + entry.getValue());
        }
        //get all the unique keys of the table
        Set<String> employers = employeeTable.rowKeySet();
        System.out.print("Employers: ");
        for (String employer : employers) {
            System.out.print(employer + " ");
        }
        System.out.println();
        //get a Map corresponding to 102
        Map<String, String> EmployerMap = employeeTable.column("102");
        for (Map.Entry<String, String> entry : EmployerMap.entrySet()) {
            System.out.println("Employer: " + entry.getKey() + ", Name: "
                    + entry.getValue());
        }

    }

    private Multimap<String, String> getMultimap() {
        //Map<String, List<String>>
        // lower -> a, b, c, d, e
        // upper -> A, B, C, D
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");
        multimap.put("lower", "d");
        multimap.put("lower", "e");
        multimap.put("upper", "A");
        multimap.put("upper", "B");
        multimap.put("upper", "C");
        multimap.put("upper", "D");
        return multimap;
    }
}
