package com.company.queue;

import java.util.NoSuchElementException;

public class MyArrayQueue<E> implements MyQueue<E> {
    private static final int DEF = 10;
    private Object[] arr;
    private int size;

    public MyArrayQueue() {
        this.arr = new Object[DEF];
        this.size = 0;
    }

    @Override
    public boolean add(E e) {
        if (size == 10) throw new IllegalStateException();
        arr[size++] = e;
        return true;
    }

    @Override
    public boolean offer(E e) {
        if (size == 10) return false;
        arr[size++] = e;
        return true;
    }

    @Override
    public E remove() {
        if(size == 0) throw new NoSuchElementException();
        Object o = arr[0];
        System.arraycopy(arr, 1, arr, 0, arr.length - 1);
        return (E) o;
    }

    @Override
    public E poll() {
        if (size == 0){
            return null;
        }
        Object o = arr[0];
        System.arraycopy(arr, 1, arr, 0, arr.length - 1);
        return (E) o;
    }

    @Override
    public E element() {
        if (size == 0) throw new NoSuchElementException();
        Object o = arr[0];
        return (E) o;
    }

    @Override
    public E peek() {
        if (size == 0){
            return null;
        }
        Object o = arr[0];
        return (E) o;
    }

}
