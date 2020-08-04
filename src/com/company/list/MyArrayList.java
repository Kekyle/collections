package com.company.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class MyArrayList<E> implements MyList<E> {
    private static final int INIT_ARRAY_SIZE = 10;
    private Object[] arr;
    private int size;

    public MyArrayList(MyList<E> list) {
        this.arr = list.toArray();
        this.size = list.size();
    }

    public MyArrayList() {
        this.arr = new Object[INIT_ARRAY_SIZE];
        this.size = 0;
    }

    @Override
    public void add(E on) {//O(1) //O(n)
        if (size == arr.length) grow();
        arr[size++] = on;
    }

    private void grow() {
        int newSize = size + (size / 2);
        if (newSize > Integer.MAX_VALUE - 8) throw new OutOfMemoryError();
        arr = Arrays.copyOf(arr, newSize);
    }

    @Override
    public E remove(int index) {//O(1)
        Object o = arr[index];
        if (arr.length - 1 - index >= 0) System.arraycopy(arr, index + 1, arr, index, arr.length - 1 - index);
        if (size == arr.length) arr[size - 1] = null;
        size--;
        return (E) o;
    }

    @Override
    public boolean contains(E ob) {//O(n)
        for (Object o : arr) {
            if (o == null) break;
            if (o.equals(ob)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(E o, int index) {
        if (size == arr.length) grow();
        if (arr.length - 1 - index >= 0) System.arraycopy(arr, index, arr, index + 1, arr.length - 1 - index);
        size++;
        arr[index] = o;
    }

    @Override
    public E set(E o, int index) {
        Object old = arr[index];
        arr[index] = o;
        return (E) old;
    }

    @Override
    public E get(int index) {
        return (E) arr[index];
    }

    @Override
    public E[] toArray() {
        Object[] objects = new Object[size];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = arr[i];
        }
        return (E[]) objects;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void removeAll() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public void trimToSize() {
        arr = Arrays.copyOf(arr, size);
    }

    @Override
    public MyList<E> subList(int begin, int to) {
        MyList<E> sublist = new MyArrayList<>();
        for (int j = begin; j < to; j++) {
            sublist.add((E) arr[j]);
        }
        return sublist;
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "arr=" + Arrays.toString(arr) +
                ", size=" + size +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr();
    }

    private class Itr implements Iterator<E> {
        int cursor = 0;
        boolean pr;
        boolean nx;

        @Override
        public boolean hasNext() {
            return arr[cursor] != null;
        }

        @Override
        public E next() {
            nx = true;
            pr = false;
            return (E) arr[cursor++];
        }

        @Override
        public void remove() {
            if (cursor == 0) {
                throw new IllegalStateException();
            }
            if (pr) {
                MyArrayList.this.remove(cursor);
            } else if (nx) {
                MyArrayList.this.remove(cursor - 1);
            }
        }
    }

    // TODO: 07.05.2020 Исправить баг с методом remove()
    // TODO: 07.05.2020 Добавить листИтератор в LinkedList

    private class ListItr extends Itr implements ListIterator<E> {

        @Override
        public boolean hasPrevious() {
            return cursor > 0 && arr[cursor - 1] != null;
        }

        @Override
        public E previous() {
            pr = true;
            nx = false;
            return (E) arr[--cursor];
        }

        @Override
        public int nextIndex() {
            return cursor + 1;
        }

        @Override
        public int previousIndex() {
            if (cursor == 0) {
                throw new IllegalStateException();
            }
            return cursor - 1;
        }

        @Override
        public void set(E e) {
            arr[cursor] = e;
        }

        @Override
        public void add(E e) {
            if (arr.length == size) grow();
            if (cursor == 0){
                MyArrayList.this.add(e,0);
            } else if (pr) {
                MyArrayList.this.add(e, cursor);
            } else if (nx) {
                MyArrayList.this.add(e, cursor - 1);
            }
        }
    }
}
