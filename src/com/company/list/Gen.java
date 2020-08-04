package com.company.list;

import java.util.List;

public class Gen<E>{
    E e;

//    public <T> Gen(T t){
//        System.out.println(t.getClass());
//    }

//    public <T> void test(List<?> t){
//        System.out.println(t.getClass());
//    }

    public void add(E e){

    }

    public static void main(String[] args) {
        Gen<String> stringGen = new Gen<>();
        stringGen.add("sdsd");
//        stringGen.test();
//        stringGen.test("Hello");
//        stringGen.test(2323);
    }
}
