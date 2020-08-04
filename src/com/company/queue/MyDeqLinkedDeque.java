package com.company.queue;

import java.util.Iterator;

public class MyDeqLinkedDeque<E> implements MyDeque<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    @Override
    public void addFirst(E e) {
        if (size == 0) {
            this.first = new Node<>(e, null, null);
            last = first;
            size++;
        } else {
            Node<E> node = this.first;
            first = new Node<>(e, this.first, null);
            size++;
        }
    }

    @Override
    public void addLast(E e) {
        if (size == 0) {
            last = new Node<>(e, null, last);
            first = last;
            size++;
        } else {
            Node<E> node = this.last;
            this.last = new Node<>(e, null, this.last);
            node.next = this.last;
            size++;
        }
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
        Node<E> node = this.first;
        E e = node.getE();
        first = first.next;
        node.setE(null);
        node.setNext(null);
        size--;
        return e;
    }

    @Override
    public E removeLast() {
        Node<E> node = this.last;
        Node<E> prev = node.prev;
        prev.setNext(null);
        E e = last.getE();
        last = last.prev;
        node.setE(null);
        node.setPrev(null);
        node.setNext(null);
        size--;
        return e;
    }

    @Override
    public E pollFirst() {
        return removeFirst();
    }

    @Override
    public E pollLast() {
        return removeLast();
    }

    @Override
    public E peekFirst() {
        return first.getE();
    }

    @Override
    public E peekLast() {
        return last.getE();
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E remove() {
        return  removeFirst();
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E element() {
        return first.getE();
    }

    @Override
    public E peek() {
        return first.getE();
    }

    @Override
    public E push(E e) {
        addLast(e);
        return e;
    }

    @Override
    public E pop() {
        return removeLast();
    }

    @Override
    public boolean contains(E e) {
        Node<E> node = this.first;
        while (true){
            if (node.getE().equals(e)){
                return true;
            }
            node = node.next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private static class Node<E>{
        E e;
        Node<E> next;
        Node<E> prev;

        public Node(E e, Node<E> next, Node<E> prev) {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

        public E getE() {
            return e;
        }

        public void setE(E e) {
            this.e = e;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", next=" + next +
//                    ", prev=" + prev +
                    '}';
        }
    }

    private class Itr implements Iterator<E> {
        Node<E> cursor = first;

        @Override
        public boolean hasNext() {
            return cursor.next != null;
        }

        @Override
        public E next() {
            E e = cursor.getE();
            cursor = cursor.next;
            return e;
        }

        @Override
        public void remove() {
//            Node<E> next = cursor.next;
            Node<E> prev = cursor.prev;
            Node<E> prevPrev = prev.prev;
            prev.setE(null);
            prev.setNext(null);
            prev.setPrev(null);
            prevPrev.next = cursor;
            cursor.prev = prevPrev;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node<E> node = this.first;
        if (node == null) {
            stringBuilder.append("[").append("]");
            return stringBuilder.toString();
        }
        if (node.next == null) {
            stringBuilder.append("[").append(node.getE()).append("]");
            return stringBuilder.toString();
        }
        stringBuilder.append("[");
        while (true) {
            Node<E> next = node.next;
            stringBuilder.append(node.getE()).append(", ");
            if (next.next == null) {
                stringBuilder.append(next.getE()).append("]");
                return stringBuilder.toString();
            }
            node = node.next;
        }
    }

}
