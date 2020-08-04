package com.company.table;

import java.util.List;
import java.util.Map;

public interface MyMap<K,V> {
    V put(K key, V value);
    V get(K key);
    V remove(K key);

    boolean containsKey(K key);
    boolean containsValue(V value);

    int size();

    boolean isEmpty();

    List<Map.Entry<K, V>> entryList();
    List<V> valueList();
    List<K> keyList();

    interface MyEntry<K, V>{
        V getValue();
        K getKey();
        V setValue(V value);
    }
}
