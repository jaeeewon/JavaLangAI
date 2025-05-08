package com.week9.assignment;

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
        addBetween(value, this.front, this.front.next);
        this.size++;
    }

    public void addLast(int value) {
        addBetween(value, this.back.prev, this.back);
        this.size++;
    }

    public int removeFirst() {
        if (this.size == 0) {
            throw new IllegalAccessError();
        }
        int temp = unlink(this.front.next);
        this.size--;
        return temp;
    }

    public int removeLast() {
        if (this.size == 0) {
            throw new IllegalAccessError();
        }
        int temp = unlink(this.back.prev);
        this.size--;
        return temp;
    }

    public int peekFirst() {
        if (this.size == 0) {
            throw new IllegalAccessError();
        }
        return this.front.next.value;
    }

    public int peekLast() {
        if (this.size == 0) {
            throw new IllegalAccessError();
        }
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

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        String str = "[";
        Node next = this.front.next;

        if (this.front.next != this.back) {
            str += next.value;
            next = next.next;
        }

        while (next.next != null) {
            str += ", " + next.value;
            next = next.next;
        }
        str += "]";
        return str;
    }

    public static void main(String[] args) {
        LinkedIntDeque data = new LinkedIntDeque();
        System.out.println("Initial: " + data);

        // 1. 데이터 추가
        for (int i = 0; i < 5; i++) {
            data.addLast(i);
        }
        System.out.println("After addLast 0-4: " + data);

        for (int i = 5; i < 10; i++) {
            data.addFirst(i);
        }
        System.out.println("After addFirst 5-9: " + data);

        // 2. 3번 removeFirst, removeLast()
        for (int i = 0; i < 3; i++)
            data.removeFirst();
        System.out.println("After removeFirst 3 times: " + data);

        for (int i = 0; i < 3; i++)
            data.removeLast();
        System.out.println("After removeLast 3 times: " + data);

        // 3. peekFirst, peekLast()
        System.out.println("PeekFirst: " + data.peekFirst());
        System.out.println("PeekLast: " + data.peekLast());

        // 4. size()
        System.out.println("Size: " + data.size());

        // 5. 모든 요소 removeFirst()
        for (int i = 0, s = data.size(); i < s; i++)
            data.removeFirst();
        System.out.println("After removeFirst all the rest: " + data);

        // 6. 요소가 없을 때 removeFirst()
        System.out.println("요소가 더 이상 없을 때 removeFirst 실행시 에러 발생!");
        data.removeFirst();
    }
}
// ASSIGNMENT: addBetween과 unlink를 이용해 6개의 메소드 구현하기