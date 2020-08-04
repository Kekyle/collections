package com.company.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MyHashMap<K, V> implements MyMap<K, V> {

    private static final int DEF_SIZE = 16;
    private Node<K, V>[] table;
    private int size;
    private int count;
    private int currentSize = DEF_SIZE;
    private boolean flag;

    public MyHashMap() {
        this.table = new Node[DEF_SIZE];
        this.size = 0;
    }

    // TODO: 21.05.2020 Impl method resize();
    // TODO: 21.05.2020 Impl method toString();

    @Override
    public V put(K key, V value) {
        if (size > currentSize) resize();
        int hash = key.hashCode();
        // TODO: 24.05.2020 !
        int index;
        if (flag) {
            index = Math.abs(hash % currentSize);
        } else {
            index = Math.abs(hash % DEF_SIZE);
        }
        if (table[index] == null) {
            table[index] = new Node<>(hash, key, value, null);
            size++;
        } else {
            if (table[index].getKey().equals(key)) {
                table[index].setValue(value);
            } else {
                Node<K, V> node = table[index];
                while (true) {
                    if (node.getKey().equals(key)) {
                        table[index].setValue(value);
                        break;
                    }
                    if (node.next == null) {
                        node.next = new Node<>(hash, key, value, null);
                        size++;
                        return value;
                    }
                    node = node.next;
                }
            }
        }
        return null;
    }

    private void resize() {
        int newSize = currentSize * 2;
        currentSize = newSize;
        if (newSize > Integer.MAX_VALUE - 8) throw new OutOfMemoryError();
        table = Arrays.copyOf(table, newSize);
        flag = true;
    }

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int index;
        if (count > DEF_SIZE) {
            index = Math.abs(hash % currentSize);
        } else {
            index = Math.abs(hash % DEF_SIZE);
            count++;
        }
        if (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                return table[index].getValue();
            } else {
                Node<K, V> node = table[index];
                while (true) {
                    if (node == null) return null;
                    if (node.getKey().equals(key)) {
                        return node.getValue();
                    }
                    node = node.next;
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public V remove(K key) {
        int hash = key.hashCode();
        int index = Math.abs(hash % DEF_SIZE);
        if (table[index] == null) return null;
        if (table[index].getKey().equals(key)) {
            if (table[index].next == null) {
                V e = table[index].getValue();
                table[index] = null;
                size--;
                return e;
            } else {
                V e = table[index].getValue();
                Node<K, V> next = table[index].next;
                table[index].next = null;
                table[index].value = null;
                table[index].key = null;
                table[index] = next;
                size--;
                return e;
            }
        } else {
            Node<K, V> node = table[index];
            while (true) {
                if (node.next == null) break;
                if (node.next.getKey().equals(key)) {
                    V v = node.next.value;
                    Node<K, V> nextNode = node.next.next;
                    node.next.value = null;
                    node.next.key = null;
                    node.next.next = null;
                    node.next = nextNode;
                    size--;
                    return v;
                }
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < table.length; i++) {
            if (table[i].getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i].getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // FIXME: 21.05.2020
    @Override
    public List<Map.Entry<K, V>> entryList() {
        List<Map.Entry<K, V>> entryList = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Node<K, V> node = table[i];
            if (table[i].next != null) {
                while (true) {
                    if (node.next != null) break;
                    entryList.add(node);
                    node = node.next;
                }
            } else {
                entryList.add(table[i]);
            }
        }
        return entryList;
    }

    @Override
    public List<V> valueList() {
        List<Map.Entry<K, V>> entryList = entryList();
        List<V> values = new ArrayList<>();
        for (int i = 0; i < entryList.size(); i++) {
            values.add(entryList.get(i).getValue());
        }
        return values;
    }

    @Override
    public List<K> keyList() {
        List<Map.Entry<K, V>> entryList = entryList();
        List<K> keys = new ArrayList<>();
        for (int i = 0; i < entryList.size(); i++) {
            keys.add(entryList.get(i).getKey());
        }
        return keys;
    }

    public void printTable() {
        System.out.println(Arrays.toString(table));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;

            Node<K, V> node = table[i];
            if (table[i].next != null) {
                while (true) {
                    if (node.next == null) break;
                    stringBuilder.append("{key = ").append(table[i].getKey()).append(", value = ").append(table[i].getValue()).append("}, ");
                    node = node.next;
                }
            } else {
                stringBuilder.append("{key = ").append(table[i].getKey()).append(", value = ").append(table[i].getValue()).append("} ");
                if (table[i + 1] != null) {
                    stringBuilder.append(", ");
                }
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

//    @Override
//    public String toString() {
//        return "MyHashMap{" +
//                "table=" + Arrays.toString(table) +
//                ", size=" + size +
//                '}';
//    }

    private static class Node<K, V> implements Map.Entry<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public String toString() {
            return "{" +
//                    "hash=" + hash +
                    "key = " + key +
                    ", value =" + value +
                    ", next=" + next +
                    '}';
        }
    }
}
