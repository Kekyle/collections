package com.company.tree;

import java.util.*;

public class MyTreeMapImpl<K, V> implements MyTreeMap<K, V> {
    private int size;
    private Entry<K, V> root;
    private Comparator<K> comparator;

    public MyTreeMapImpl(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public static void main(String[] args) {
        MyTreeMap<Integer, String> i = new MyTreeMapImpl<>(Integer::compareTo);
        i.put(4, "Hello 4");
        i.put(2, "Hello 2");
        i.put(3, "Hello 3");
        i.put(1, "Hello 1");
        i.put(8, "Hello 8");
        i.put(5, "Hello 5");
        i.put(9, "Hello 9");
        i.put(7, "Hello 7");

        System.out.println(i.get(1));
        System.out.println(i.get(2));
        System.out.println(i.get(3));
        System.out.println(i.get(4));

        System.out.println(i);

        System.out.println(i.remove(8));

        System.out.println(i);
        i.put(6, "Hello 6");
        System.out.println(i);
    }

    @Override
    public V put(K k, V v) {
        if (size == 0) {
            root = new Entry<>(v, k, null, null, null);
            size++;
            return v;
        } else {
            Entry<K, V> entry = root;
            while (true) {
                if (comparator.compare(k, entry.k) == 1) {
                    if (entry.right == null) {
                        entry.right = new Entry<>(v, k, null, null, entry);
                        size++;
                    }
                    entry = entry.right;
                }
                if (comparator.compare(k, entry.k) == -1) {
                    if (entry.left == null) {
                        entry.left = new Entry<>(v, k, null, null, entry);
                        size++;
                    }
                    entry = entry.left;
                }
                if (comparator.compare(k, entry.k) == 0) {
                    return null;
                }
            }
        }
    }

    @Override
    public V get(K k) {
        Entry<K, V> entry = root;
        while (true) {
            if (comparator.compare(k, entry.k) == 1) {
                entry = entry.right;
            }
            if (comparator.compare(k, entry.k) == -1) {
                entry = entry.left;
            }
            if (comparator.compare(k, entry.k) == 0) {
                return entry.v;
            }
        }
    }

    @Override
    public V remove(K k) {
        if (size == 0) return null;
        else {
            Entry<K, V> entry = root;
            while (true) {
                if (comparator.compare(k, entry.k) == 1) {
                    entry = entry.right;
                }
                if (comparator.compare(k, entry.k) == -1) {
                    entry = entry.left;
                }
                if (comparator.compare(k, entry.k) == 0) {
                    if (entry.left == null && entry.right == null) {
                        V old = entry.v;
                        entry.v = null;
                        Entry<K, V> parent = entry.parent;
                        if (parent.right.equals(entry)) {
                            parent.right = null;
                            size--;
                        }
                        if (parent.left.equals(entry)) {
                            parent.left = null;
                            size--;
                        }
                        return old;
                    }
                    // TODO: 31.05.2020
                    if (entry.left != null && entry.right != null) {
                        V old = entry.v;
                        Entry<K, V> parent = entry.parent;
                        Entry<K, V> entry1;
                        entry1 = entry.left;
                        while (entry1.right != null) {
                            entry1 = entry1.right;
                        }
                        if (parent.left.equals(entry)) {
                            parent.left.v = entry1.v;
                            parent.left.k = entry1.k;
                            entry1.v = null;
                            entry1.k = null;
                            entry1.parent.right = null;
                            size--;
                        }
                        if (parent.right.equals(entry)) {
                            parent.right.v = entry1.v;
                            parent.right.k = entry1.k;
                            entry1.v = null;
                            entry1.k = null;
                            entry1.parent.right = null;
                            entry1.parent = null;
                            size--;
                        }
                        return old;
                    }
                    // TODO: 31.05.2020 unlink
                    if (entry.left != null || entry.right != null) {
                        V old = entry.v;
                        Entry<K, V> parent = entry.parent;
                        if (entry.right != null) {
                            parent.right = null;
                            entry.right.parent = null;
                            entry.k = null;
                            entry.v = null;
                            parent.right = entry.right;
//                            entry = null;
                            size--;
                        }
                        if (entry.left != null) {
                            parent.left = null;
                            entry.k = null;
                            entry.v = null;
//                            entry = null;
                            parent.left = entry.left;
                            size--;
                        }
                        return old;
                    }
                }
            }
        }
    }

    @Override
    public boolean containsKey(K k) {
        return false;
    }

    @Override
    public boolean containsValue(V v) {
        return false;
    }

    @Override
    public List<V> values() {
        return null;
    }

    @Override
    public List<K> keys() {
        return null;
    }

    @Override
    public List<Map.Entry<K, V>> entryList() {
        List<Map.Entry<K, V>> entries = new ArrayList<>();
        Entry<K, V> entry = root;
//        for (int i = 0; i < size; i++) {
//            Entry<K, V> objectEntry = new Entry<>(entry.v, entry.k, entry.left, entry.right, null);
//            entry = entry.right;
//        }
        return entries;
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
    public String toString() {
        return "MyTreeMapImpl{" +
                "root=" + root +
                '}';
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        V v;
        K k;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;

        public Entry(V v, K k, Entry<K, V> left, Entry<K, V> right, Entry<K, V> parent) {
            this.v = v;
            this.k = k;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        @Override
        public V setValue(V value) {
            V old = this.v;
            this.v = value;
            return old;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(v, entry.v) &&
                    Objects.equals(k, entry.k);
        }

        @Override
        public int hashCode() {
            return Objects.hash(v, k);
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "v=" + v +
                    ", k=" + k +
                    ", left=" + left +
                    ", right=" + right +
//                    ", parent=" + parent +
                    '}';
        }
    }
}
