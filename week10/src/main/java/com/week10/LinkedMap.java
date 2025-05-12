package com.week10;

public class LinkedMap<K, V> {
    class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V> front = null;
    private int size = 0;

    public void put(K key, V value) {
        Node<K, V> curr = front;
        while (curr != null) {
            if (curr.key == key) {
                curr.value = value;
                return;
            }
            curr = curr.next;
        }

        // key가 없으면 새로운 키 추가
        front = new Node<>(key, value, front);
        // 굳이 맨 뒤에 붙이지 말고 앞에 붙이자
        // Map에서는 순서가 중요하지 않으니까
        this.size++;
    }

    public V get(K key) {
        for (Node<K, V> n = this.front; n != null; n = n.next) {
            if (n.key == key) {
                return n.value;
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        for (Node<K, V> n = this.front; n != null; n = n.next) {
            if (n.key == key) {
                return true;
            }
        }

        return false;
    }

    public V remove(K key) {
        Node<K, V> curr = front;
        Node<K, V> prev = null;
        while (curr != null) {
            if (curr.key == key) {
                // 건너뛰어서 잘 연결해주기
                // 검사: prev == null (curr이 첫 번째 node인 경우)
                if (prev == null)
                    front = front.next; // 맨 앞일 경우
                else
                    prev.next = curr.next;
                this.size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }

        return null;
    }

    public static void main(String[] args) {
        LinkedMap<String, Integer> linkedMap = new LinkedMap<>();
        linkedMap.put("apple", 1);
        linkedMap.put("banana", 2);
        linkedMap.put("apple", 4);

        System.out.println(linkedMap.get("apple"));
        System.out.println(linkedMap.get("banana"));

        System.out.println(linkedMap.remove("banana"));
        System.out.println(linkedMap.get("banana"));
    }
}
