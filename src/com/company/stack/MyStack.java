package com.company.stack;

//LIFO
public interface MyStack<E> {
    E push(E e);//O(1)
    E pop();//O(1)
    E peek();//O(1)
    boolean empty();//O(1)
    int search(E e);//O(n)
}
