package com.company.tree;

import java.util.Arrays;
import java.util.Comparator;

// FIXME: 27.05.2020 Доработать удаление левых нод
public class MySortTreeImpl<E> implements MySortTree<E> {
    private Node<E> root;
    private int size;
    private Comparator<E> comparator;

    public MySortTreeImpl(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public static void main(String[] args) {
        MySortTree<Integer> integerMySortTree = new MySortTreeImpl<>(Integer::compareTo);
        integerMySortTree.add(45);
        integerMySortTree.add(25);
        integerMySortTree.add(15);
        integerMySortTree.add(70);
        integerMySortTree.add(85);
        integerMySortTree.add(5);
        integerMySortTree.add(1);
        integerMySortTree.add(75);
        integerMySortTree.add(95);
        integerMySortTree.add(87);
        System.out.println(Arrays.toString(integerMySortTree.toArray()));
//        System.out.println(integerMySortTree);
        System.out.println(integerMySortTree.pollFirst());
        System.out.println(integerMySortTree.pollFirst());
        System.out.println(integerMySortTree.pollLast());
        System.out.println(integerMySortTree.pollLast());

        System.out.println(integerMySortTree.first());
        System.out.println(integerMySortTree.last());
//        System.out.println(integerMySortTree);
        System.out.println(Arrays.toString(integerMySortTree.toArray()));
    }

    @Override
    public String toString() {
        return "MySortTreeImpl{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean add(E e) {//O(log n)
        if (size == 0) {
            root = new Node<>(e, null, null, null, false);
            size++;
            return true;
        } else {
            Node<E> node = root;
            while (true) {
                if (comparator.compare(e, node.e) == 1) {
                    if (node.right == null) {
                        node.right = new Node<>(e, null, null, node, false);
                        size++;
                        return true;
                    }
                    node = node.right;
                    continue;
                }
                if (comparator.compare(e, node.e) == -1) {
                    if (node.left == null) {
                        node.left = new Node<>(e, null, null, node, true);
                        size++;
                        return true;
                    }
                    node = node.left;
                    continue;
                }
                if (comparator.compare(e, node.e) == 0) {
                    return false;
                }
            }
        }
    }

    @Override
    public E first() {
        if (size == 0) return null;
        else {
            Node<E> node = root;
            while (true) {
                if (node.left == null) {
                    return node.e;
                }
                node = node.left;
            }
        }
    }

    @Override
    public E last() {
        if (size == 0) return null;
        else {
            Node<E> node = root;
            while (true) {
                if (node.right == null) {
                    return node.e;
                }
                node = node.right;
            }
        }
    }

    @Override
    public E pollFirst() {
        if (size == 0) return null;
        else {
            Node<E> node = root;
            while (true) {
                if (node.left.left == null) {
                    E old = node.left.e;
                    node.left.setE(null);
                    node.left = null;
                    size--;
                    return old;
                }
                node = node.left;
            }
        }
    }

    @Override
    public E pollLast() {
        if (size == 0) return null;
        else {
            Node<E> node = root;
            while (true) {
                if (node.right.right == null) {
                    if (node.right.left != null) {
//                        if (node.right.left.isLeft) {
                            E old = node.right.e;
                            node.right.setE(node.right.left.e);
                            node.right.left.e = null;
                            node.right.left = null;
                            size--;
                            return old;
//                        }
                    }else {
                        E old = node.right.e;
                        node.right.setE(null);
                        node.right = null;
                        size--;
                        return old;
                    }
                }
                node = node.right;
            }
        }
    }

    @Override
    public E[] toArray() {
        Object[] objects = new Object[size];
        int count = 0;
        Node<E> node = root;
        while (node.left != null) {
            node = node.left;
        }
        while (true) {
            objects[count++] = node.getE();
            if (node.right != null) {
                Node<E> right = node.right;
                objects[count++] = right.getE();
            }
            if (node.parent == null) {
                node = root.right;
                while (node.right != null){
                    if (node.right.left != null) {
//                        if (node.right.left.isLeft) {
                            Node<E> rightLeft = node.right.left;
                            objects[count++] = rightLeft.getE();
//                        }
                    }
                    Node<E> right = node.right;
                    objects[count++] = right.getE();
                    node = node.right;
                }
                return (E[]) objects;
            }
            node = node.parent;
        }
    }

    private static class Node<E> {
        E e;
        Node<E> left;
        Node<E> right;
        Node<E> parent;
        boolean isLeft;

        public Node(E e, Node<E> left, Node<E> right, Node<E> parent, boolean isLeft) {
            this.e = e;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.isLeft = isLeft;
        }

        public E getE() {
            return e;
        }

        public void setE(E e) {
            this.e = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public boolean isLeft() {
            return isLeft;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setLeft(boolean left) {
            isLeft = left;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
