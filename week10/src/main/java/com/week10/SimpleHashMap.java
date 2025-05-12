package com.week10;

public class SimpleHashMap<V> {
    class Pair<V> {
        int key;
        V value;

        Pair(int key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    private Pair<V>[] table = (Pair<V>[]) new Pair[10];
    private int size = 0;

    public void put(int key, V value) {
        int idx = key % 10; // 해시함수(% 10) 적용해 index로 사용
        table[idx] = new Pair<>(key, value);
        size++;
    }

    // 아래 함수들은 모두 DirectAccessMap.java에서 가져왔으며,
    // 인덱스만 key % 10을 적용함
    public V get(int key) {
        int idx = key % 10;
        return table[idx].value;
    }

    public boolean containsKey(int key) {
        int idx = key % 10;
        return table[idx] != null;
    }

    public V remove(int key) {
        int idx = key % 10;
        if (table[idx] != null) {
            V out = table[idx].value;
            table[idx] = null;
            size--;
            return out;
        }

        return null;
    }

    public static void main(String[] args) {
        SimpleHashMap<Integer> shMap = new SimpleHashMap<>();
        shMap.put(1, 70);
        shMap.put(15, 55);
        shMap.put(8, 99);

        System.out.println(shMap.get(15));
        System.out.println(shMap.get(8));

        System.out.println(shMap.remove((1)));
        System.out.println(shMap.get(1));
    }
}
