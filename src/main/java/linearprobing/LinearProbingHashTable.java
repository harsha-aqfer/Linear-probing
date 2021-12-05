package linearprobing;

import java.util.ArrayList;
import java.util.List;

public class LinearProbingHashTable<K, V> implements Map<K, V> {

    private static class MapEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        /**
         * Constructor to set the key and value of this entry
         *
         * @param key   the key
         * @param value the value
         */
        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        /**
         * Sets the value of this Entry with specified value
         *
         * @param newValue the new value
         * @return old value of this entry
         */
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public int getHash() {
            return Math.abs(getKey().hashCode());
        }

        /**
         * String representation for map entry
         */
        @Override
        public String toString() {
            return "<" + key + ", " + value + ">";
        }

    }


    private static final double LOADFACTOR = 0.5;
    private static final int INITCAPACITY = 4;
    private static final int MAXCAPACITY = 1 << 30;
    private int capacity;
    private double loadFactor;
    private int size = 0;

    private List<MapEntry<K, V>> entries;

    private int capacity() {
        return this.capacity;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    private int getIndex(int hash) {
        return hash % capacity();
    }

    /**
     * no argument constructor Construct a map with the default capacity and load
     * factor
     */
    public LinearProbingHashTable() {
        this.capacity = INITCAPACITY;
        this.entries = new ArrayList<>(INITCAPACITY);
        this.loadFactor = LOADFACTOR;
    }

    /**
     * One argument constructor Construct a map with the specified initial capacity
     * and default load factor
     */
    public LinearProbingHashTable(int capacity) {
        this.capacity = capacity;
        this.entries = new ArrayList<>(capacity);
        this.loadFactor = LOADFACTOR;
    }

    /**
     * Construct a map with the specified initial capacity and load factor. Note:
     * the capacity of map must be power of 2 User/client can specify any value as
     * map capacity. You should make sure that the map is created with the power of
     * 2 capacity that is greater than the user's given capacity. For example, if
     * the user specifies the input capacity as 13, you should create a map with a
     * capacity of 16.
     *
     * @param capacity   map capacity specified by client
     * @param loadFactor map loading factor
     */
    public LinearProbingHashTable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.entries = new ArrayList<>(capacity);
        this.loadFactor = loadFactor;
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
    public V get(K key) throws NullPointerException {
        int hash = hash(key);
        MapEntry<K, V> e;
        int index = getIndex(hash);
        int startIndex = index;
        while ((e = entries.get(index)) != null) {
            if (hash == e.getHash() && e.key.equals(key)) {
                return entries.get(index).value;
            }
            index = linearProbing(index);
            if (index == startIndex) return null;
        }
        return null;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode());
    }

    @Override
    public void clear() {
        entries.clear();
    }

    @Override
    public V put(K key, V value) throws NullPointerException {
        int hash = Math.abs(key.hashCode());
        int index = getIndex(hash);
        int startIndex = index;
        MapEntry<K, V> e;
        while ((e = entries.get(index)) != null) {
            if (hash == e.getHash() && e.key.equals(key)) {
                entries.get(index).value = value;
                return value;
            }
            index = linearProbing(index);
            if (index == startIndex) {
                resize(capacity() * 2);
                index = getIndex(hash);
                startIndex = index;
            }
        }
        entries.set(index, new MapEntry<>(key, value));
        size++;
        return null;

    }

    private int linearProbing(int index) {
        return (index + 1) % capacity();
    }

    private void resize(int newCapacity) {
        setCapacity(newCapacity);
        List<MapEntry<K, V>> oldTab = entries;
        entries = new ArrayList<>(newCapacity);
        size = 0;
        for (MapEntry<K, V> hashNode : oldTab) {
            if (hashNode != null) {
                put(hashNode.key, hashNode.value);
            }
        }
    }

    @Override
    public V remove(K key) throws NullPointerException {
        int hash = hash(key);
        MapEntry<K, V> e;
        int index = getIndex(hash);
        int startIndex = index;
        while ((e = entries.get(index)) != null) {
            if (hash == e.getHash() && e.key.equals(key)) {
                entries.remove(index);
                return e.value;
            }
            index = linearProbing(index);
            if (index == startIndex) return null;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) throws NullPointerException {
        return get(key) == null;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public Iterable<K> keySet() {
        List<K> keySet = new ArrayList<>();
        for (MapEntry<K, V> hashNode : entries) {
            if (hashNode != null) {
                keySet.add(hashNode.key);
            }
        }
        return keySet;
    }

    @Override
    public Iterable<V> values() {
        List<V> valueSet = new ArrayList<>();
        for (MapEntry<K, V> hashNode : entries) {
            if (hashNode != null) {
                valueSet.add(hashNode.value);
            }
        }
        return valueSet;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> entrySet = new ArrayList<>();
        for (MapEntry<K, V> hashNode : entries) {
            if (hashNode != null) {
                entrySet.add(hashNode);
            }
        }
        return entrySet;
    }

    /**
     * Return String value represent the content of map
     */
    @Override
    public String toString() {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (MapEntry<K, V> hashNode : entries) {
            if (hashNode != null) {
                sb.append("<" + hashNode.key + ", " + hashNode.value + ">");
            }
            if (i < entries.size() - 1) sb.append(",");
            i++;
        }
        sb.append("]");
        return sb.toString();

    }

}
