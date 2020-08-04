package com.company.stack;

import java.util.Arrays;

public class MyArrayStack<E> implements MyStack<E> {
    private static final int DEF = 10;
    private Object[] arr;
    private int size;

    public MyArrayStack() {
        this.arr = new Object[DEF];
        this.size = 0;
    }

    @Override
    public E push(E e) {
        arr[size++] = e;
        return e;
    }

    @Override
    public E pop() {
        Object o = arr[size - 1];
        arr[size - 1] = null;
        size--;
        return (E) o;
    }

    @Override
    public E peek() {
        return (E) arr[size - 1];
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int search(E e) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(e)){
                return size - i - 1;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "MyArrayStack{" +
                "arr=" + Arrays.toString(arr) +
                ", size=" + size +
                '}';
    }
}
