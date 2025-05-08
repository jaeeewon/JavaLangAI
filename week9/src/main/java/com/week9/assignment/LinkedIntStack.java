package com.week9.assignment;

public class LinkedIntStack {
    private class Node {
        private int data;
        private Node prev;

        public Node(int data, Node next) {
            this.data = data;
            this.prev = next;
        }
    }

    private Node rear;
    private int top;

    public LinkedIntStack() {
        this.rear = null;
        this.top = 0;
    }

    public void push(int value) {
        this.rear = new Node(value, this.rear);
        this.top++;
    }

    public int pop() {
        if (this.top == 0) {
            throw new IllegalAccessError();
        }

        int temp = this.rear.data;
        this.rear = this.rear.prev;
        this.top--;
        return temp;
    }

    public int peek() {
        if (this.top == 0) {
            throw new IllegalAccessError();
        }

        return this.rear.data;
    }

    public int size() {
        return this.top;
    }

    @Override
    public String toString() {
        String str = "[";
        Node next = this.rear;

        if (next != null) {
            str += next.data;
            next = next.prev;
        }

        while (next != null) {
            str += ", " + next.data;
            next = next.prev;
        }
        str += "]";
        return str;
    }

    public static void main(String[] args) {
        LinkedIntStack data = new LinkedIntStack();
        System.out.println("Initial: " + data);

        // 1. 데이터 추가
        for (int i = 0; i < 10; i++) {
            data.push(i);
        }
        System.out.println("After append 0-9: " + data);

        // 2. 3번 pop()
        for (int i = 0; i < 3; i++)
            data.pop();
        System.out.println("After pop 3 times: " + data);

        // 3. peek()
        System.out.println("Peek: " + data.peek());

        // 4. size()
        System.out.println("Size: " + data.size());

        // 5. 모든 요소 pop()
        for (int i = 0, s = data.size(); i < s; i++)
            data.pop();
        System.out.println("After pop all the rest: " + data);

        // 6. 요소가 없을 때 pop()
        System.out.println("요소가 더 이상 없을 때 pop 실행시 에러 발생!");
        data.pop();
    }
}
