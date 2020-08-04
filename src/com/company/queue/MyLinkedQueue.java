package com.company.queue;

public class MyLinkedQueue<E> implements MyQueue<E> {
    private Node<E> root;
    private int size;

    @Override
    public boolean add(E e) {
//        if (e == null) throw new IllegalStateException();
        if (root == null) {
            root = new Node<>(e, null);
            size++;
        } else {
//            if (size == 0) throw new IllegalStateException();
            Node<E> node = root;
            while (true) {
                if (node.next == null) {
                    node.next = new Node<>(e, null);
                    size++;
                    break;
                }
                node = node.next;
            }
        }
        return true;
    }

    @Override
    public boolean offer(E e) {
//        if (e == null) return false;
        if (root == null) {
            root = new Node<>(e, null);
            size++;
        } else {
//            if (size == 0) return false;
            Node<E> node = root;
            while (true) {
                if (node.next == null) {
                    node.next = new Node<>(e, null);
                    size++;
                    break;
                }
                node = node.next;
            }
        }
        return true;
    }

    @Override
    public E remove() {
        if (size == 0) throw new IllegalStateException();
        Node<E> node = this.root;
        E e = root.getE();
        root = root.next;
        node.setE(null);
        node.setNext(null);
        size--;
        return e;
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        Node<E> node = this.root;
        E e = root.getE();
        root = root.next;
        node.setE(null);
        node.setNext(null);
        size--;
        return e;
    }

    @Override
    public E element() {
        if (size == 0) throw new IllegalStateException();
        return root.getE();
    }

    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }
        return root.getE();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        MyLinkedQueue.Node<E> node = this.root;
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
            MyLinkedQueue.Node<E> next = node.next;
            stringBuilder.append(node.getE()).append(", ");
            if (next.next == null) {
                stringBuilder.append(next.getE()).append("]");
                return stringBuilder.toString();
            }
            node = node.next;
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
    }
}
