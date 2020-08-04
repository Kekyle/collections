package com.company.queue;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayDeque<E> implements MyDeque<E> {
    private static final int DEF = 10;
    private Object[] arr;
    private int size;

    public MyArrayDeque() {
        this.arr = new Object[DEF];
        this.size = 0;
    }

    @Override
    public void addFirst(E e) {
        if (size == arr.length) grow();
        if (arr.length - 1 >= 0) System.arraycopy(arr, 0, arr, 1, arr.length - 1);
        arr[0] = e;
        size++;
    }

    private void grow() {
        int newSize = size + (size / 2);
        if (newSize > Integer.MAX_VALUE - 8) throw new OutOfMemoryError();
        arr = Arrays.copyOf(arr, newSize);
    }

    @Override
    public void addLast(E e) {
        if (size == arr.length) grow();
        arr[size++] = e;
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E removeFirst() {
        E e = (E) arr[0];
        System.arraycopy(arr, 1, arr, 0, arr.length - 1);
        size--;
        return e;
    }

    @Override
    public E removeLast() {
        Object o = arr[size - 1];
        arr[size - 1] = null;
        size--;
        return (E) o;
    }

    @Override
    public E pollFirst() {
        Object o = arr[0];
        System.arraycopy(arr, 1, arr, 0, arr.length - 1);
        size--;
        return (E) o;
    }

    @Override
    public E pollLast() {
        Object o = arr[size - 1];
        arr[size - 1] = null;
        size--;
        return (E) o;
    }

    @Override
    public E peekFirst() {
        return (E) arr[0];
    }

    @Override
    public E peekLast() {
        return (E) arr[size - 1];
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return false;
    }

    @Override
    public boolean offer(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E remove() {
        E e = removeFirst();
        return e;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        return peekFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @Override
    public E push(E e) {
        addLast(e);
        return e;
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public boolean contains(E e) {
        for (Object o : arr) {
            if (o.equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return arr[cursor] != null;
        }

        @Override
        public E next() {
            Object o = arr[cursor++];
            return (E) o;
        }

        @Override
        public void remove() {
            Object[] arr = MyArrayDeque.this.arr;
            if (arr.length - 1 - cursor - 1 >= 0)
                System.arraycopy(arr, cursor, arr, cursor - 1, arr.length - 1 - cursor);
            size--;
        }
    }

    @Override
    public String toString() {
        return "MyArrayDeque{" +
                "arr=" + Arrays.toString(arr) +
                ", size=" + size +
                '}';
    }
}
