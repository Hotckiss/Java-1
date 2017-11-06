package ru.spbau.kirilenko;

import java.util.*;

/**
 * A class that implements HashMap if key-value pairs
 * @param <K> key
 * @param <V> value
 */
class MyHashMap<K, V> extends AbstractMap<K, V> {
    private Set entries = null;
    private ArrayList list;

    /**
     * Constructs empty HashMap
     * @param initialCapacity
     */
    public MyHashMap(int initialCapacity) {
        list = new ArrayList(initialCapacity);
    }

    /**
     * Puts element to the Map
     * @param key pey of pair
     * @param value value of pair
     * @return old value
     */
    public V put(K key, V value) {
        int size = list.size();
        Entry<V> entry = null;
        int i = 0;
        for (i = 0; i < size; i++) {
            entry = (Entry) (list.get(i));
            if (key.equals(entry.getKey())) {
                break;
            }
        }

        V old = null;
        if (i < size) {
            old = entry.getValue();
            entry.setValue(value);
        } else {
            list.add(new Entry(key, value));
        }

        return old;
    }

    /**
     * Gets element to the Map
     * @param key pey of pair
     * @return value
     */
    public V get(Object key) {
        int size = list.size();
        Entry<V> entry;
        int i;
        for (i = 0; i < size; i++) {
            entry = (Entry) (list.get(i));
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsKey(Object key) {
        Entry<V> entry = null;
        for (int i = 0; i < list.size(); i++) {
            entry = (Entry) (list.get(i));
            if (key.equals(entry.getKey())) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsValue(Object value) {
        Entry<V> entry = null;
        for (int i = 0; i < list.size(); i++) {
            entry = (Entry) (list.get(i));
            if (value.equals(entry.getValue())) {
                return true;
            }
        }

        return false;
    }

    public Set entrySet() {
        if (entries == null) {
            entries = new AbstractSet() {
                /**
                 * {@inheritDoc}
                 */
                public void clear() {
                    list.clear();
                }

                public Iterator iterator() {
                    return list.iterator();
                }

                public int size() {
                    return list.size();
                }
            };
        }
        return entries;
    }

    private class Entry<V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }
}