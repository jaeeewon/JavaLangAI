package com.week9.assignment.answer;

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
        // front: sentinel node -> this.front, this.front.next 사이에 추가
        addBetween(value, this.front, this.front.next);
    }

    public void addLast(int value) {
        addBetween(value, this.back.prev, this.back);
    }

    public int removeFirst() {
        // 예외 처리 -> deque empty || 이건 채점하지 않음(잘 넣고 잘 끊을 수 있는지만)
        return unlink(this.front.next);
    }

    public int removeLast() {
        return unlink(this.back.prev);
    }

    public int peekFirst() {
        return this.front.next.value;
    }

    public int peekLast() {
        return this.back.prev.value;
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