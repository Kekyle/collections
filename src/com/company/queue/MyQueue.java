package com.company.queue;

//FIFO
public interface MyQueue<E> {
    boolean add(E e);//O(1)
    boolean offer(E e);//O(1)
    E remove();//O(1)
    E poll();//O(1)
    E element();//O(1)
    E peek();//O(1)
}
