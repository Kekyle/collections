package com.company.list;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;

public class MyDeqLinkedList<E> implements MyList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public void add(E o) {
        if (isEmpty()) {
            Node<E> eNode = new Node<>(o, null, null);
            this.head = eNode;
            this.tail = eNode;
            size++;
        } else {
            Node<E> newNode = new Node<>(o, null, this.tail);
            this.tail.setNext(newNode);
            this.tail = newNode;
            size++;
        }
    }

    @Override
    public E remove(int index) {
//        int count2 = 0;
        int count = 0;
        E e;
        if (index == 0) {
            Node<E> head = this.head;
            Node<E> next = this.head.next;
            e = head.getE();
            this.head = next;
            head.setE(null);
            head.setNext(null);
            size--;
            return e;
        }
        if (index >= size / 2) {
            Node<E> node = this.tail;
            count = size - 1;
            while (true) {
                if (count == index) {
                    e = node.getE();
                    Node<E> prev = node.prev;
                    Node<E> next = node.next;
                    node.setE(null);
                    node.setNext(null);
                    node.setPrev(null);
                    prev.next = next;
                    next.prev = prev;
                    size--;
//                    System.out.println(count2);
                    return e;
                }
//                count2++;
                count--;
                node = node.prev;
            }
        } else if (index < size / 2) {
            Node<E> node = head;
            while (true) {
                if (count == index) {
                    e = node.getE();
                    Node<E> prev = node.prev;
                    Node<E> next = node.next;
                    node.setE(null);
                    node.setNext(null);
                    node.setPrev(null);
                    prev.next = next;
                    next.prev = prev;
                    size--;
//                    System.out.println(count2);
                    return e;
                }
//                count2++;
                count++;
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public boolean contains(E o) {
        Node<E> node = head;
        while (true) {
            if (node.getE().equals(o)) {
                return true;
            }
            if (node.next == null) return false;
            node = node.next;
        }
    }

    @Override
    public void add(E o, int index) {
        int count = 0;
//        int count2 = 0;
        if (index == size - 1){
            add(o);
            return;
        }
        if (index >= size / 2) {
            int count1 = size - 1;
            Node<E> node = this.tail;
            while (true) {
                if (count1 == index) {
                    Node<E> prev = node.prev;
                    Node<E> next = node.next;
                    Node<E> newNode = new Node<>(o, node, prev);
                    node.setPrev(newNode);
                    node.setNext(next);
                    prev.setNext(newNode);
                    next.setPrev(node);
                    size++;
//                    System.out.println(count2);
                    return;
                }
//                count2++;
                count1--;
                node = node.prev;
            }
        }
        if (index < size / 2) {
            Node<E> node = head;
            while (true) {
                if (count == index) {
                    Node<E> prev = node.prev;
                    Node<E> next = node.next;
                    Node<E> newNode = new Node<>(o, node, prev);
                    prev.setNext(newNode);
                    node.setPrev(newNode);
                    node.setNext(next);
                    next.setPrev(newNode);
                    size++;
//                    System.out.println(count2);
                    break;
                }
//                count2++;
                count++;
                node = node.next;
            }
        }
    }

    @Override
    public E set(E o, int index) {
//        int count2 = 0;
        int count = 0;
        if (index >= size / 2) {
            int count1 = size - 1;
            Node<E> node = this.tail;
            while (true) {
                if (count1 == index) {
                    E old = node.getE();
                    node.setE(o);
//                    System.out.println(count2);
                    return old;
                }
//                count2++;
                count1--;
                node = node.prev;
            }
        } else if (index < size / 2) {
            Node<E> node = head;
            while (true) {
                if (count == index) {
                    E old = node.getE();
                    node.setE(o);
//                    System.out.println(count2);
                    return old;
                }
//                count2++;
                count++;
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public E get(int index) {
//        int count2 = 0;
        int count = 0;
        if (index >= size / 2) {
            Node<E> node = this.tail;
            int count1 = size - 1;
            while (true) {
                if (count1 == index) {
//                    System.out.println(count2);
                    return node.getE();
                }
                count1--;
//                count2++;
                node = node.prev;
            }
        } else if (index < size / 2) {
            Node<E> node = head;
            while (true) {
                if (count == index) {
//                    System.out.println(count2);
                    return node.getE();
                }
                count++;
//                count2++;
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public E[] toArray() {
        Node<E> node = head;
        Object[] objects = new Object[size];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = node.getE();
            node = node.next;
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
        Node<E> node = head;
        int count = 0;
        if (node.next == null) {
            node.setE(null);
            node.setNext(null);
            node.setPrev(null);
            size--;
            return;
        }
        while (count != size) {
            Node<E> next = node.next;
            Node<E> nextNext = node.next.next;
            if (next.next == null) {
                next.setE(null);
                next.setNext(null);
                next.setPrev(null);
                node.setE(null);
                node.setNext(null);
                node.setPrev(null);
                size = 0;
                return;
            }
            next.setE(null);
            next.setNext(null);
            next.setPrev(null);
            node.next = nextNext;
            nextNext.prev = node;
            count++;
        }
        size = 0;
    }

    @Override
    public void trimToSize() {
        removeAll();
    }

    @Override
    public MyList<E> subList(int begin, int to) {
        MyDeqLinkedList<E> sublist = new MyDeqLinkedList<>();
        Node<E> node = head;
        int counter = 0;
        while (begin < to) {
            if (counter == begin) {
                sublist.add(node.getE());
                begin++;
            }
            counter++;
            node = node.next;
        }
        return sublist;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node<E> node = this.head;
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

    //    @Override
//    public String toString() {
//        return "MyDeqLinkedList{" +
//                "head=" + head +
//                ", tail=" + tail +
//                ", size=" + size +
//                '}';
//    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr();
    }

    private class Itr implements Iterator<E> {
        Node<E> cursor = head;
        int index = 0;
        boolean pr;
        boolean nx;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            nx = true;
            pr = false;
            index++;
            if (cursor == null) {
                return null;
            }
            E e = cursor.getE();
            cursor = cursor.next;
            return e;
        }

        @Override
        public void remove() {
            if (index == 0) {
                throw new IllegalStateException();
            }
            if (pr) {
                MyDeqLinkedList.this.remove(index);
            } else if (nx) {
                MyDeqLinkedList.this.remove(index - 1);
            }
//            MyDeqLinkedList.this.remove(index - 1);
        }
    }

    private class ListItr extends Itr implements ListIterator<E>{

        @Override
        public boolean hasPrevious() {
            return cursor.prev != null;
        }

        @Override
        public E previous() {
            pr = true;
            nx = false;
            index--;
            E e = cursor.prev.getE();
            cursor = cursor.prev;
            return e;
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            if (cursor == head) {
                throw new IndexOutOfBoundsException();
            }
            return index - 1;
        }

        @Override
        public void set(E e) {
            cursor.setE(e);
        }

        @Override
        public void add(E e) {
            if (index == 0){
                MyDeqLinkedList.this.add(e, 0);
            } else if (pr) {
                MyDeqLinkedList.this.add(e, index);
            } else if (nx) {
                MyDeqLinkedList.this.add(e, index - 1);
            }
//            MyDeqLinkedList.this.add(e, index);
        }
    }

    public static class Node<E> {
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(e, node.e);
        }

        @Override
        public int hashCode() {
            return Objects.hash(e);
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
}
