package com.week10;

import java.util.ArrayList;

public class ArrayMap<K, V> {
    class Pair<K, V> {
        K key;
        V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // field
    private ArrayList<Pair<K, V>> items = new ArrayList<>();

    public void put(K key, V value) {
        // 이미 해당 키가 존재하면 덮어쓰고 반환환
        for (Pair<K, V> e : items) {
            if (e.key == key) {
                e.value = value;
                return;
            }
        }

        // 해당 키가 존재하지 않으면 아래가 실행됨
        items.add(new Pair<>(key, value));
    }

    public V get(K key) {
        for (Pair<K, V> e : items) {
            if (e.key == key) {
                return e.value;
            }
        }

        // 존재하지 않을 경우 null 반환
        return null;
    }

    public boolean containsKey(K key) {
        for (Pair<K, V> e : items) {
            if (e.key == key) {
                return true;
            }
        }

        // 키가 존재하지 않으면 false 반환
        return false;
    }

    public V remove(K key) {
        // for (Pair<K, V> e : items) {
        // if (e.key == k) {
        // V temp = e.value;
        // items.remove(e);
        // return temp;
        // }
        // }

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).key == key) {
                return items.remove(i).value;
            }
        }

        // 키가 없을 경우에는 null
        return null;
    }

    public static void main(String[] args) {
        ArrayMap<String, Integer> arrayMap = new ArrayMap<>();
        arrayMap.put("apple", 1);
        arrayMap.put("banana", 2);
        arrayMap.put("apple", 4);

        System.out.println(arrayMap.get("apple"));
        System.out.println(arrayMap.get("banana"));

        System.out.println(arrayMap.remove("banana"));
        System.out.println(arrayMap.get("banana"));
    }
}
