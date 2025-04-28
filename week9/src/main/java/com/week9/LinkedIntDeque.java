package com.week9;

public class LinkedIntDeque {
    // Doubly Linked Node
    private class Node {
        int value;
        Node prev;
        Node next;

        Node(int value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node front;
    private Node back;

    public LinkedIntDeque() {
        // initialize sentinel node
        this.front = new Node(0, null, null);
        this.back = new Node(0, null, null);

        this.front.next = this.back;
        this.back.prev = this.front;

        this.size = 0;
    }

    public void addFirst(int value) {

    }

    public void addLast(int value) {

    }

    public int removeFirst() {
        return 0;
    }

    public int removeLast() {
        return 0;
    }

    public int peekFirst() {
        return 0;
    }

    public int peekLast() {
        return 0;
    }

    // prev, next 노드 사이에 값이 value인 새로운 노드를 만들고 링크
    private void addBetween(int value, Node prev, Node next) {
        Node n = new Node(value, prev, next);
        prev.next = n;
        next.prev = n;
        // Lecture 5-48 slide
    }

    // node를 연결에서 지워버리는 메소드
    private int unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node.value;
    }
}
// ASSIGNMENT: addBetween과 unlink를 이용해 6개의 메소드 구현하기