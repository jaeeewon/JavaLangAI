package com.week10.assignment;

import java.util.Objects;

class Pair<K, V> {
    K key;
    V value;

    Pair(K k, V v) {
        key = k;
        value = v;
    }
}

interface SimpleMap<K, V> {
    void put(K key, V value);

    V get(K key);

    boolean containsKey(K key);

    V remove(K key);

    int size();
}

class ChainingHashMap<K, V> implements SimpleMap<K, V> {

    private class Node<K, V> {
        Pair<K, V> data;
        Node<K, V> next;

        Node(Pair<K, V> d, Node<K, V> n) {
            data = d;
            next = n;
        }
    }

    @SuppressWarnings("unchecked")
    private static final String keyAssertionMessage = "key must not be null";
    private Node<K, V>[] buckets = (Node<K, V>[]) new Node[16]; // power-of-two
    private int size = 0;

    @Override
    public void put(K key, V value) {
        // 구현시 활용 가능한 기본 함수
        // Objects.requireNonNull(key, msg): key가 null일 경우 msg를 에러 메시지로 띄웁니다.
        // Objects.equals(a, b): a와 b가 같은 경우 true, 다른 경우 false입니다.
        // String과 같은 object는 "==" 연산자를 사용할 시 어디로 reference되는지를 비교하기 때문에 값을 비교할 때 이
        // method를 사용해야 합니다.

        Objects.requireNonNull(key, keyAssertionMessage);
        int bucketIdx = hash(key) % this.buckets.length;
        Node<K, V> bucket = this.buckets[bucketIdx];
        // 버킷 찾았고

        while (bucket != null) {
            if (bucket.data.key.equals(key)) {
                bucket.data.value = value;
                return;
            }

            bucket = bucket.next;
        }
        // 버킷에 해당 키가 없으면

        this.buckets[bucketIdx] = new Node<>(new Pair<>(key, value), this.buckets[bucketIdx]);
        this.size++;
        ensureLoadFactor();
    }

    @Override
    public V get(K key) {
        Objects.requireNonNull(key, keyAssertionMessage);
        int bucketIdx = hash(key) % this.buckets.length;
        Node<K, V> currBucket = this.buckets[bucketIdx];

        while (currBucket != null) {
            if (currBucket.data.key.equals(key))
                return currBucket.data.value;
            currBucket = currBucket.next;
        }

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        Objects.requireNonNull(key, keyAssertionMessage);
        int bucketIdx = hash(key) % this.buckets.length;
        Node<K, V> currBucket = this.buckets[bucketIdx];

        while (currBucket != null) {
            if (currBucket.data.key.equals(key))
                return true;
            currBucket = currBucket.next;
        }

        return false;
    }

    @Override
    public V remove(K key) {
        Objects.requireNonNull(key, keyAssertionMessage);
        int bucketIdx = hash(key) % this.buckets.length;
        Node<K, V> curr = this.buckets[bucketIdx];
        Node<K, V> prev = null;

        while (curr != null) {
            if (curr.data.key.equals(key)) {
                // 지울 게 첫 노드면
                if (prev == null) {
                    this.buckets[bucketIdx] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                this.size--;
                return curr.data.value;
            }
            prev = curr;
            curr = curr.next;
        }

        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    // hash함수 구현:
    private int hash(K key) {
        return key.hashCode();
    }

    /* ---------- helper 함수 ---------- */
    private void ensureLoadFactor() {
        // map에 들어온 item 개수가 일정 비율을 넘어서면 collision을 최소화하기 위해 2배 크기로 사이즈 조정 후 다시 해쉬 진행
        if ((double) size / buckets.length <= 0.75)
            return;
        rehash(buckets.length << 1);
    }

    @SuppressWarnings("unchecked")
    private void rehash(int newCap) {
        Node<K, V>[] old = buckets;
        buckets = (Node<K, V>[]) new Node[newCap];
        size = 0;
        for (Node<K, V> head : old)
            for (Node<K, V> n = head; n != null; n = n.next)
                put(n.data.key, n.data.value);
    }

    /*
     * =========================================================
     * 3. 테스트 코드
     * =========================================================
     */
    /* 간단한 custom class */
    private static final class Point {
        final int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }

        @Override
        public boolean equals(Object o) {
            return (o instanceof Point p) && p.x == x && p.y == y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    public static void main(String[] args) {
        SimpleMap<Object, String> ch = new ChainingHashMap<>();
        test("ChainingHashMap", ch);
    }

    private static void test(String name, SimpleMap<Object, String> m) {
        System.out.println("---- " + name + " ----");
        m.put("apple", "fruit");
        m.put(new Point(1, 2), "P(1,2)");
        m.put(42, "answer");

        System.out.println("size = " + m.size());
        System.out.println("get(\"apple\") = " + m.get("apple"));
        System.out.println("get(Point(1,2)) = " + m.get(new Point(1, 2)));
        System.out.println("containsKey(42) = " + m.containsKey(42));
        System.out.println("remove(\"apple\") = " + m.remove("apple"));
        System.out.println("size after remove = " + m.size());
        System.out.println();
    }
}