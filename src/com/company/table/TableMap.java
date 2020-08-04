package com.company.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TableMap<K, V> implements MyMap<K, V> {
    private static final int DEF = 10;
    private Object[] keys;
    private Object[] values;
    private int size;

    public TableMap() {
        this.keys = new Object[DEF];
        this.values = new Object[DEF];
        this.size = 0;
    }
    // TODO: 17.05.2020 grow()
    // TODO: 17.05.2020 Impl toString
    private void grow() {
        int newSize = size + (size / 2);
        if (newSize > Integer.MAX_VALUE - 8) throw new OutOfMemoryError();
        keys = Arrays.copyOf(keys, newSize);
        values = Arrays.copyOf(values, newSize);
    }

    @Override
    public V put(K key, V value) {//O(n)
        if(keys.length == size) grow();
        this.keys[size] = key;
        this.values[size++] = value;
        return value;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < this.keys.length; i++) {
            if (this.keys[i].equals(key)) {
                return (V) this.values[i];
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        V old = null;
        for (int i = 0; i < this.keys.length; i++) {
            if (this.keys[i] == null) break;
            if (this.keys[i].equals(key)) {
                old = (V) values[i];
                for (int j = i; j < this.values.length - 1; j++) {
                    keys[j] = keys[j + 1];
                    values[j] = values[j + 1];
                }
                size--;
            }
        }
        return old;
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(value)) {
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
        List<Map.Entry<K, V>> entries = new ArrayList<>();
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == null) break;
            Entr objectObjectEntr = new Entr(keys[i], values[i]);
            entries.add(objectObjectEntr);
        }
        return entries;
    }

    @Override
    public List<V> valueList() {
        List<V> values = new ArrayList<>();
        values = (List<V>) Arrays.asList(this.values);
        return values;
    }

    @Override
    public List<K> keyList() {
        List<K> keys = new ArrayList<>();
        keys = (List<K>) Arrays.asList(this.keys);
        return keys;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < keys.length; i++) {
            stringBuilder.append("{key = ").append(keys[i]).append(", value = ").append(values[i]).append("}");
            if (i == keys.length - 1) {
                stringBuilder.append("]");
                return stringBuilder.toString();
            } else {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static class Entr<K, V> implements Map.Entry<K, V> {
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
            V old = this.value;
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

//    @Override
//    public String toString() {
//        return "TableMap{" +
//                "keys=" + Arrays.toString(keys) +
//                ", values=" + Arrays.toString(values) +
//                ", size=" + size +
//                '}';
//    }
}
