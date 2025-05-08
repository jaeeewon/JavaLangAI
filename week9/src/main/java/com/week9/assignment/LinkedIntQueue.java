package com.week9.assignment;

public class LinkedIntQueue {
    private class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node front;
    private Node rear;
    private int size = 0;

    public LinkedIntQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(int value) {
        if (this.size == 0) {
            this.front = this.rear = new Node(value, null);
        } else {
            this.rear = this.rear.next = new Node(value, null);
        }
        this.size++;
    }

    public int dequeue() {
        if (this.size == 0) {
            throw new IllegalAccessError();
        }
        int temp = this.front.data;
        this.front = this.front.next;
        this.size--;
        return temp;
    }

    public int peek() {
        if (this.size == 0) {
            throw new IllegalAccessError();
        }
        return this.front.data;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        String str = "[";
        Node next = this.front;

        if (next != null) {
            str += next.data;
            next = next.next;
        }

        while (next != null) {
            str += ", " + next.data;
            next = next.next;
        }
        str += "]";
        return str;
    }

    public static void main(String[] args) {
        LinkedIntQueue data = new LinkedIntQueue();
        System.out.println("Initial: " + data);

        // 1. 데이터 추가
        for (int i = 0; i < 10; i++) {
            data.enqueue(i);
        }
        System.out.println("After enqueue 0-9: " + data);

        // 2. 3번 dequeue()
        for (int i = 0; i < 3; i++)
            data.dequeue();
        System.out.println("After dequeue 3 times: " + data);

        // 3. peek()
        System.out.println("Peek: " + data.peek());

        // 4. size()
        System.out.println("Size: " + data.size());

        // 5. 모든 요소 dequeue()
        for (int i = 0, s = data.size(); i < s; i++)
            data.dequeue();
        System.out.println("After dequeue all the rest: " + data);

        // 6. 요소가 없을 때 dequeue()
        System.out.println("요소가 더 이상 없을 때 dequeue 실행시 에러 발생!");
        data.dequeue();
    }
}
