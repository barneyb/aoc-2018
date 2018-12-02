package com.barneyb.aoc2018.api;

// taken from Algorithms, pg 363
public interface ST<K, V> {

    // ST();

    void put(K key, V val);

    V get(K key);

    default void delete(K key) {
        put(key, null);
    }

    default boolean contains(K key) {
        return get(key) != null;
    }

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    Iterable<K> keys();

}
