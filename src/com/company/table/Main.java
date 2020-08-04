package com.company.table;

public class Main {
    public static void main(String[] args) {
//        MyHashMap<String, Integer> stringIntegerMyMap = new MyHashMap<>();

//        int i = "Hello 8".hashCode();
//        System.out.println(i % 22);
        MyHashMap<String, Integer> stringIntegerMyMap = new MyHashMap<>();
//        MyMap<String, Integer> stringIntegerMyMap = new TableMap<>();
        for (int j = 0; j < 120; j++) {
            stringIntegerMyMap.put(String.format("Hello %d", j+1), j+1);
        }

        for (int i = 0; i < 120; i++) {
            System.out.println(stringIntegerMyMap.get(String.format("Hello %d", i + 1)));
        }
//
//        System.out.printf("Hello %d", 5);

//        stringIntegerMyMap.put("Hello 23", 23);
//        stringIntegerMyMap.put("Hello 24", 24);
//        stringIntegerMyMap.put("Hello 25", 25);
//        stringIntegerMyMap.printTable();
//        System.out.println(stringIntegerMyMap.keyList());
//        System.out.println(stringIntegerMyMap);
//        System.out.println(stringIntegerMyMap.entryList());

//        System.out.println(stringIntegerMyMap.entryList());

//        System.out.println(stringIntegerMyMap.get("Hello 1"));
//        System.out.println(stringIntegerMyMap.get("Hello 2"));
//        System.out.println(stringIntegerMyMap.get("Hello 3"));
//        System.out.println(stringIntegerMyMap.get("Hello 4"));
//        System.out.println(stringIntegerMyMap.get("Hello 5"));
//        System.out.println(stringIntegerMyMap.get("Hello 6"));
//        System.out.println(stringIntegerMyMap.get("Hello 7"));
//        System.out.println(stringIntegerMyMap.get("Hello 8"));
//        System.out.println(stringIntegerMyMap.get("Hello 9"));
//        System.out.println(stringIntegerMyMap.get("Hello 10"));
//        System.out.println(stringIntegerMyMap.get("Hello 11"));
//        System.out.println(stringIntegerMyMap.get("Hello 12"));
//        System.out.println(stringIntegerMyMap.get("Hello 13"));
//        System.out.println(stringIntegerMyMap.get("Hello 14"));
//        System.out.println(stringIntegerMyMap.get("Hello 15"));
//        System.out.println(stringIntegerMyMap.get("Hello 16"));
//        System.out.println(stringIntegerMyMap.get("Hello 17"));
//        System.out.println(stringIntegerMyMap.get("Hello 18"));
//        System.out.println(stringIntegerMyMap.get("Hello 19"));
//        System.out.println(stringIntegerMyMap.get("Hello 20"));
//        System.out.println(stringIntegerMyMap.get("Hello 21"));
//        System.out.println(stringIntegerMyMap.get("Hello 22"));
//        System.out.println(stringIntegerMyMap.get("Hello 23"));
//        System.out.println(stringIntegerMyMap.get("Hello 24"));
//        System.out.println(stringIntegerMyMap.get("Hello 25"));

//        stringIntegerMyMap.remove("Hello 10");
//        stringIntegerMyMap.remove("Hello 15");
//        stringIntegerMyMap.remove("Hello 17");
//        stringIntegerMyMap.remove("Hello 11");
//        stringIntegerMyMap.remove("Hello 12");
//        stringIntegerMyMap.remove("Hello 13");
//        stringIntegerMyMap.remove("Hello 1");
//        stringIntegerMyMap.remove("Hello 2");
//        stringIntegerMyMap.remove("Hello 3");
//        stringIntegerMyMap.remove("Hello 4");
//        stringIntegerMyMap.remove("Hello 5");
//        stringIntegerMyMap.remove("Hello 6");
//        stringIntegerMyMap.remove("Hello 7");
//        stringIntegerMyMap.remove("Hello 8");
//        stringIntegerMyMap.remove("Hello 9");
//        stringIntegerMyMap.remove("Hello 14");
//        stringIntegerMyMap.remove("Hello 16");


//        System.out.println(stringIntegerMyMap.entryList());

//        System.out.println(stringIntegerMyMap.keyList());
//        System.out.println(stringIntegerMyMap.valueList());

//        System.out.println(stringIntegerMyMap);


//        System.out.println(stringIntegerMyMap.keyList());
//        System.out.println(stringIntegerMyMap.valueList());

//        System.out.println(stringIntegerMyMap.get("Hello 3"));

//        System.out.println(stringIntegerMyMap.remove("Hello 4"));

//        System.out.println(stringIntegerMyMap);

//        System.out.println(stringIntegerMyMap.keyList());
//        System.out.println(stringIntegerMyMap.valueList());

//        System.out.println(stringIntegerMyMap.entryList());
    }
}
