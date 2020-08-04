package com.company.tree;

public interface MySortTree<E> {

    boolean add(E e);

    E first();
    E last();

    E pollFirst();
    E pollLast();

    E[] toArray();
}
