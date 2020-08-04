package com.company.stack;

public class MyLinkedStack<E> implements MyStack<E> {
    private Node<E> root;
    private int size;

    @Override
    public E push(E e) {
        if (empty()) {
            root = new Node<>(e, null);
        } else {
            root = new Node<>(e, root);
        }
        size++;
        return e;
    }

    @Override
    public E pop() {
        Node<E> next = root;
        root = next.next;
        next.setNext(null);
        E e = next.getE();
        next.setE(null);
        size--;
        return e;
    }

    @Override
    public E peek() {
        return root.getE();
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int search(E e) {
        int a = 0;
        Node<E> node = this.root;
        while (true) {
            if (node.getE().equals(e)) {
                return a;
            }
            a++;
            node = node.next;
        }
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
