package com.company.tree;

import java.util.List;
import java.util.Map;

public interface MyTreeMap<K, V> {
    V put(K k, V v);
    V get(K k);
    V remove(K k);
    boolean containsKey(K k);
    boolean containsValue(V v);
    List<V> values();
    List<K> keys();
    List<Map.Entry<K, V>> entryList();
    int size();
    boolean isEmpty();
}
