package com.company.list;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        List<Integer> integerList = new ArrayList<>();
//        integerList.add(1);
//        integerList.add(2);
//        integerList.add(3);

//        Queue<String> strings = new LinkedList<>();
//        Deque<String> strings1 = new ArrayDeque<>();


        Map<String, Integer> stringIntegerMap = new HashMap<>();
        stringIntegerMap.put(null, 1);
        stringIntegerMap.put(null, 2);
        System.out.println(stringIntegerMap);

//        Map<String, Integer> stringIntegerMap1 = new TreeMap<>();
//        stringIntegerMap1.put(null, 2);
//        System.out.println(stringIntegerMap1);

        Set<String> strings = new TreeSet<>();
        strings.add(null);
    }


    public static void print(Iterable<?> list){
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
