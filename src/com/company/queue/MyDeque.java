package com.company.queue;

public interface MyDeque<E> extends Iterable<E> {
    void addFirst(E e);
    void addLast(E e);
    boolean offerFirst(E e);
    boolean offerLast(E e);
    E removeFirst();
    E removeLast();
    E pollFirst();
    E pollLast();
    E peekFirst();
    E peekLast();
    boolean add(E e);
    boolean offer(E e);
    E remove();
    E poll();
    E element();
    E peek();
    E push(E e);
    E pop();
    boolean contains(E e);
    int size();
}
