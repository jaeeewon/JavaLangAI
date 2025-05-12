package com.week10;

public class DirectAccessMap<V> {
    class Pair<V> {
        int key;
        V value;

        Pair(int key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // 배열 크게 잡고, 키를 인덱스로 사용

    @SuppressWarnings("unchecked")
    private Pair<V>[] table = (Pair<V>[]) new Pair[100];
    private int size = 0;

    public void put(int key, V value) {
        // key가 우리가 만든 array capacity를 넘는 경우
        // 구현하지는 않겠지만, array 사이즈를 늘리고 있던 것들 복사
        this.table[key] = new Pair<>(key, value);
        this.size++;
    }

    public V get(int key) {
        // key가 제대로 들어오는지에 대한 구현도 건너뜀
        return table[key].value;
    }

    public boolean containsKey(int key) {
        return table[key] != null;
    }

    public V remove(int key) {
        if (table[key] != null) {
            V out = table[key].value;
            table[key] = null;
            size--;
            return out;
        }

        return null;
    }

    public static void main(String[] args) {
        DirectAccessMap<Integer> daMap = new DirectAccessMap<>();
        daMap.put(1, 70);
        daMap.put(15, 55);
        daMap.put(8, 99);

        System.out.println(daMap.get(15));
        System.out.println(daMap.get(8));

        System.out.println(daMap.remove((1)));
        System.out.println(daMap.get(1));
    }
}
