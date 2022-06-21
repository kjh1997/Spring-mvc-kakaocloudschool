package study;

import java.util.*;
import java.util.stream.Collectors;

public class test {
    static private List<String> arrayList = new ArrayList<>();
    static public List<String> vector = new Vector<>();
    static public List<String> linkedList = new LinkedList<>();
    static public List<String> stack = new Stack<>();

    static public List<String> a = new ArrayList<>();
    public static void main(String[] args) {
//        test t = new test();
//        t.arrayList();
//        stack();
        arraylist();
        vector();
        stack();
        linkedList();
    }

    private static void arraylist() {
        arrayList.add("arr test1231231111222");
        arrayList.add("arr test2");
        arrayList.add("arr test2341234134");
        arrayList.stream().filter(s -> s.length()>=10 ).map(s -> s.toUpperCase()).forEach(System.out::println);
        List<String> arr2 = arrayList.stream().filter(s -> s.length() >= 10).map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(arr2);

        System.out.println(arrayList);
    }

    private static void vector() {
        vector.add("vec test");
        vector.add("vec test2");
        System.out.println(vector);

    }

    private  static void stack() {
        stack.add("stack test");
        stack.add("stack test2");
        System.out.println(stack);

    }

    private  static void linkedList() {
        linkedList.add("linkedlist test");
        linkedList.add("linkedlist test2");
        System.out.println(linkedList);

    }

}
