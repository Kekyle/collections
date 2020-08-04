package com.company.list;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;

public class MyLinkedList<E> implements MyList<E> {

    private Node<E> root;
    private int size;

    @Override
    public void add(E o) {
        if (isEmpty()) {
            root = new Node<>(o, null);
            size++;
        } else {
            Node<E> node = root;
            while (true) {
                if (node.next == null) {
                    node.next = new Node<>(o, null);
                    size++;
                    break;
                }
                node = node.next;
            }
        }
    }

    @Override
    public E remove(int index) {
        if (index == 0) {
            Node<E> root = this.root;
            Node<E> next = this.root.next;
            this.root = next;
            size--;
            E old = root.getE();
            root.setE(null);
            return old;
        }
        Node<E> node = root;
        int count = 0;
        E e;
        while (true) {
            Node<E> prev = node;
            Node<E> current = prev.next;
            Node<E> next = prev.next.next;
            if (count == index - 1) {
                e = current.getE();
                current.setE(null);
                current.setNext(null);
                prev.next = next;
                size--;
                return e;
            }
            count++;
            node = node.next;
        }
    }

    @Override
    public boolean contains(E ob) {
        Node<E> node = root;
        while (true) {
            if (node.getE().equals(ob)) {
                return true;
            }
            if (node.next == null) return false;
            node = node.next;
        }
    }

    @Override
    public void add(E o, int index) {
        Node<E> node = root;
        int count = 0;
        while (true) {
            if (count == index - 1) {
                Node<E> prev = node;
                Node<E> next = prev.next;
                prev.next = new Node<>(o, next);
                size++;
                break;
            }
            count++;
            node = node.next;
        }
    }

    @Override
    public E set(E o, int index) {
        int count = 0;
        Node<E> node = root;
        E old;
        while (true) {
            if (count == index) {
                old = node.getE();
                node.setE(o);
                return old;
            }
            count++;
            node = node.next;
        }

    }

    @Override
    public E get(int index) {
        if (index > size) throw new IndexOutOfBoundsException();
        int count = 0;
        Node<E> node = root;
        while (true) {
            if (count == index) {
                return node.getE();
            }
            count++;
            node = node.next;
        }
    }

    @Override
    public E[] toArray() {
        Node<E> node = root;
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

    // TODO: 30.04.2020 Подумать над реализацией
    @Override
    public void removeAll() {
        int count = 0;
        Node<E> node = root;
        if (isEmpty()) {
            return;
        }
        if (node.next == null) {
            node.setE(null);
            node.setNext(null);
            return;
        }
        while (count != size) {
            Node<E> prev = node;
            Node<E> current = prev.next;
            Node<E> next = prev.next.next;
            if (current.next == null) {
                prev.setE(null);
                prev.setNext(null);
                break;
            }
            current.setE(null);
            current.setNext(null);
            prev.next = next;
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
        MyLinkedList<E> sublist = new MyLinkedList<>();
        Node<E> node = root;
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
        Node<E> node = this.root;
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

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr();
    }

    private class Itr implements Iterator<E> {
        Node<E> cursor = root;
        boolean isNew = true;
        int cursorIndex = 0;
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
            isNew = false;
            cursorIndex++;
//            if (cursor == null) return null;
            E e = cursor.getE();
            cursor = cursor.next;
            return e;
        }

        @Override
        public void remove() {
            if (isNew) {
                throw new IllegalStateException();
            }
            if (pr) {
                MyLinkedList.this.remove(cursorIndex);
            } else if (nx) {
                MyLinkedList.this.remove(cursorIndex - 1);
            }
        }
    }

    private class ListItr extends Itr implements ListIterator<E> {

        @Override
        public boolean hasPrevious() {
            return cursor != root;
        }

        @Override
        public E previous() {
            pr = true;
            nx = false;
            if (isNew) return null;
            int count = 0;
            Node<E> prev = root;
            while (true) {
                if (count == cursorIndex - 1) {
//                    cursor = prev.next;
                    cursorIndex--;
                    return prev.getE();
                }
                count++;
                prev = prev.next;
            }
        }

        @Override
        public int nextIndex() {
            return cursorIndex + 1;
        }

        @Override
        public int previousIndex() {
            if (cursor == root) {
                throw new IllegalStateException();
            }
            return cursorIndex - 1;
        }

        @Override
        public void set(E e) {
            cursor.setE(e);
        }

        @Override
        public void add(E e) {
            if (cursorIndex == 0){
                MyLinkedList.this.add(e, 0);
            } else if (pr) {
                MyLinkedList.this.add(e, cursorIndex);
            } else if (nx) {
                MyLinkedList.this.add(e, cursorIndex - 1);
            }
//            MyLinkedList.this.add(e, cursorIndex - 1);
        }
    }

    private static class Node<E> {
        E e;
        Node<E> next;

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
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
                    '}';
        }
    }
}
