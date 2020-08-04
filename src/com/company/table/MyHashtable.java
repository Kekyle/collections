package com.company.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MyHashtable<K, V> implements MyMap<K, V> {
    private static final int DEF = 16;
    private Object[] keys;
    private Object[] values;
    private int size;

    public MyHashtable() {
        this.keys = new Object[DEF];
        this.values = new Object[DEF];
        this.size = 0;
    }

    // TODO: 17.05.2020 resize()
    // TODO: 17.05.2020 Impl toString


    @Override
    public V put(K key, V value) {//O(1)
        if (keys.length == size) resize();
        int index = hash(key);
        keys[index] = key;
        values[index] = value;
        size++;
        return value;
    }

    private void resize() {
        int newSize = size + (size / 2);
        if (newSize > Integer.MAX_VALUE - 8) throw new OutOfMemoryError();
        keys = Arrays.copyOf(keys, newSize);
        values = Arrays.copyOf(values, newSize);
    }

    private int hash(K key) {
        int i = key.hashCode();
        return Math.abs(i % DEF);
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        return (V) values[index];
    }

    @Override
    public V remove(K key) {
        int index = hash(key);
        Object old = values[index];
        this.values[index] = null;
        this.keys[index] = null;
        return (V) old;
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key);
        if (keys[index] == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(value)){
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

    @Override
    public List<Map.Entry<K, V>> entryList() {
        List<Map.Entry<K,V>> entries = new ArrayList<>();
        for (int i = 0; i < keys.length; i++) {
            entries.add(new Entr(keys[i], values[i]));
        }
        return entries;
    }

    @Override
    public List<V> valueList() {
        return (List<V>) Arrays.asList(this.values);
    }

    @Override
    public List<K> keyList() {
        return (List<K>) Arrays.asList(this.keys);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < keys.length; i++) {
            stringBuilder.append("{key = ").append(keys[i]).append(", value = ").append(values[i]).append("} ");
            if (i == keys.length - 1){
                stringBuilder.append("]");
                return stringBuilder.toString();
            }else {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

//    @Override
//    public String toString() {
//        return "MyHashtable{" +
//                "keys=" + Arrays.toString(keys) +
//                ", values=" + Arrays.toString(values) +
//                ", size=" + size +
//                '}';
//    }

    private static class Entr<K, V> implements Map.Entry<K, V>{
        K key;
        V value;

        public Entr(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V setValue(V value) {
            V old = value;
            this.value = value;
            return old;
        }

        @Override
        public String toString() {
            return "Entr{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
