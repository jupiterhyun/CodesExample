package com.mycompany.java8filter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.OptionalDouble;
import java.util.IntSummaryStatistics;

import java.util.Random;
import java.util.regex.Pattern;
import java.util.function.Function;

/**
 *
 * @author jupiter
 */
public class JavaFilter {

    public static void main(String[] args) {
        /*
         Difference between Lambda Expression and Anonymous class
         One key difference between using Anonymous class and Lambda expression is the use of this keyword. 
         For anonymous class ‘this’ keyword resolves to anonymous class, 
         whereas for lambda expression ‘this’ keyword resolves to enclosing class where lambda is written.
         (arg1, arg2...) -> { body }
         (type1 arg1, type2 arg2...) -> { body }

         (int a, int b) -> {  return a + b; }
         () -> System.out.println("Hello World");
         (String s) -> { System.out.println(s); }
         () -> 42
         () -> { return 3.1415 }; equals to () -> 3.1415;
         //Lambdas as Objects
         public interface MyComparator {
         public boolean compare(int a1, int a2);
         }
         MyComparator myComparator = (a1, a2) -> return a1 > a2;
         boolean result = myComparator.compare(2, 5);
        
        
         */
        /**
         * *********************Lambda expression exmaple
         * ***********************************
         */
        System.out.println("=== RunnableTest ===");
        // Anonymous Runnable
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world one!");
            }
        };
        // Lambda Runnable
        Runnable r2 = () -> System.out.println("Hello world two!");
        // Run em!
        r1.run();
        r2.run();
        /**
         * ********************************************************
         */
        //print out everything
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        list.forEach(n -> System.out.println(n));
        //use predacte
        System.out.println("Print all numbers:");
        evaluate(list, (n) -> true);
        System.out.println("Print no numbers:");
        evaluate(list, (n) -> false);
        System.out.println("Print even numbers:");
        evaluate(list, (n) -> n % 2 == 0);
        System.out.println("Print odd numbers:");
        evaluate(list, (n) -> n % 2 == 1);
        System.out.println("Print numbers greater than 5:");
        evaluate(list, (n) -> n > 5);
        //foreach sorted
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
        //map
        List<Integer> squaresList = list.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        squaresList.forEach(s -> System.out.println(s));
        //map returns a one key collection like List
        List<String> words = Arrays.asList("Oracle", "Java", "Magazine");
        List<Integer> wordLengths
                = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        //map reduce sum of power of 2
        int sum = list.stream().map(x -> x * x).reduce((x, y) -> x + y).get();
        /*
         int sum = 0;
         for(Integer n : list) {
         int x = n * n;
         sum = sum + x;
         }
         System.out.println(sum);
         */
        System.out.println(sum);

        //another example
        List<Person> roster = new ArrayList<>();
        roster.add(new Person("li si", "11201977", Person.Sex.MALE, "abc@g.com", 30));
        roster.add(new Person("li wu", "10201987", Person.Sex.FEMALE, "abcDD@A.com", 30));
        roster.add(new Person("li LIU", "11201677", Person.Sex.MALE, "BBB@g.com", 19));
        roster.add(new Person("li BB", "112019799", Person.Sex.MALE, "GGE@g.com", 30));
        roster//works like unix pipe
                .stream()
                .filter(
                        p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25)
                .map(p -> p.getEmailAddress())
                .forEach(email -> System.out.println(email));

        // Predicates
        Predicate<Person> allDrivers = p -> p.getAge() >= 16;
        Predicate<Person> allDraftees = p -> p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Person.Sex.MALE;
        Predicate<Person> allPilots = p -> p.getAge() >= 23 && p.getAge() <= 65;
        //write a function to do filter， just another way to do it, don't think it works widely
        personEval(roster, allDrivers);
        personEval(roster, allDraftees);
        personEval(roster, allPilots);
        //use stream to get a filter result
        List<Person> pilotList = roster
                .stream()
                .filter(allDrivers)
                .collect(Collectors.toList());
        //lets do all together and you can use or
        Predicate<Person> fullPredicate = allDrivers.and(allDraftees).and(allPilots);
        List<Person> last = roster
                .stream()
                .filter(fullPredicate)
                .collect(Collectors.toList());
        /**
         * *********************************statistic
         * methods*******************************************************************
         */
        //count number
        long totalPilots = roster
                .stream()
                .filter(allPilots)
                .count();
        // Get sum of ages
        System.out.println("\n== Calc New Style ==");
        long totalAge = roster
                .stream()
                .filter(allPilots)
                .mapToInt(p -> p.getAge())
                .sum();
        // Get average of ages
        OptionalDouble averageAge = roster
                .stream()
                .filter(allPilots)
                .mapToDouble(p -> p.getAge())
                .average();
        //summary statistics
        IntSummaryStatistics stats = roster
                .stream()
                .filter(allPilots)
                .mapToInt(p -> p.getAge())
                .summaryStatistics();
        System.out.println("Highest number in List : " + stats.getMax());
        System.out.println("Lowest  number in List : " + stats.getMin());
        System.out.println("Sum of all numbers : " + stats.getSum());
        System.out.println("Average of all  numbers : " + stats.getAverage());
        /**
         * ***********************************************************************************************************
         */
        /*reduce get the total age sum same as
         Integer totalAge = roster
         .stream()
         .mapToInt(Person::getAge)
         .sum();
         */
        Integer totalAgeReduce = roster
                .stream()
                .map(Person::getAge)
                .reduce(
                        0,//identity
                        (a, b) -> a + b);//accumulator
        Integer totalAge2 = roster
                .stream()
                //        Collectors.averagingDouble(null)
                .collect(Collectors.summingInt(Person::getAge)
                );
        //check the meanings, 
        /*collect has supplier, accumulator, cmbiner,
         Averager averageCollect = roster.stream()
         .filter(p -> p.getGender() == Person.Sex.MALE)
         .map(Person::getAge)
         .collect(Averager::new, Averager::accept, Averager::combine);
         System.out.println("Average age of male members: "
         + averageCollect.average());
         */
        //map a email and a name 
        Map<String, String> groupEmail = roster
                .stream()
                .collect(Collectors.toMap(t -> t.getEmailAddress(), n -> n.getName())
                );
        //map sex and different Person
        Map<Person.Sex, List<Person>> groupSexPerson
                = roster
                .stream()
                .collect(Collectors.groupingBy(Person::getGender)
                );
        //map sex and names 
        Map<Person.Sex, List<String>> namesByGender
                = roster
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getGender,
                                Collectors.mapping(
                                        Person::getName,
                                        Collectors.toList()))
                );
        //map sex and total sum
        Map<Person.Sex, Integer> totalAgeByGender
                = roster
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getGender,
                                Collectors.reducing(
                                        0,
                                        Person::getAge,
                                        Integer::sum))
                );
        //map sex and average age
        Map<Person.Sex, Double> averageAgeByGender = roster
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getGender,
                                Collectors.averagingInt(Person::getAge))
                );
        //map age and names 自己居然想出来的 屌 好例子
        Map<Integer, List<String>> ageOfNames = roster
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge,
                                Collectors.mapping(//这种是一个key的
                                        Person::getName,
                                        Collectors.toList()))
                );
        //map age and a map of names to email 自己居然想出来的 屌 好例子
        Map<Integer, Map<String, String>> ageOfMap = roster
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge,
                                Collectors.toMap(//这种是一个2个key的
                                        Person::getName,
                                        Person::getEmailAddress))
                );

        //other pattern do filter stuff at the same time
        Pattern paramGarbage = Pattern.compile("[\\\\\\*\\$]");
        List<String> wordsGarbage = Arrays.asList("Or\\acle", "J*ava", "M$$agazine");
        //traditional way 
        //Pattern paramGarbage = Pattern.compile("[\\\\\\*\\$]");
        //Matcher m = paramGarbage.matcher(wordsGarbage.get(2));
        //String my = m.replaceAll("s");

        List<String> clean = wordsGarbage
                .stream()
                .map(t -> paramGarbage.matcher(t).replaceAll(""))
                .collect(Collectors.toList());

        clean.forEach(n -> System.out.println(n));

        //Function identity res = a -> a, b->b, c->c 
        Map<String, String> res = Arrays.asList("a", "b", "c")
                .stream()
                .collect(Collectors.toMap(Function.identity(), str -> str));
        
        
        

        //open file and sort words
    }

    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
//        for (Integer n : list) {
//            if (predicate.test(n)) {
//                System.out.println(n + " ");
//            }
//        }
        list.stream().filter((n) -> (predicate.test(n))).forEach((n) -> {
            System.out.println(n + " ");
        });
    }

    public static void personEval(List<Person> list, Predicate<Person> predicate) {
        list.stream().filter((n) -> (predicate.test(n))).forEach((n) -> {
            System.out.println(n.toString() + " ");
        });
    }

}

class Person {

    public enum Sex {

        MALE, FEMALE
    }
    String name;
    String birthday;
    Sex gender;
    String emailAddress;
    public Integer age;

    public Person(String name, String birthday, Sex gender, String emailAddress, Integer age) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Sex getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
