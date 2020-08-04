package com.company.list;

import java.util.ListIterator;

public interface MyList<E> extends Iterable<E> {
    void add(E o);
    E remove(int index);
    boolean contains(E o);
    void add(E o, int index);
    E set(E o, int index);
    E get(int index);
    E[] toArray();
    int size();
    boolean isEmpty();
    void removeAll();
    void trimToSize();
    MyList<E> subList(int begin, int to);
    ListIterator<E> listIterator();
}
