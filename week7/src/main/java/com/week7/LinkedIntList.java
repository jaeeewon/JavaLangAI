package com.week7;

public class LinkedIntList {
    private class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node front;
    private int size;

    public LinkedIntList() {
        this.front = null;
        this.size = 0;
    }

    public void add(int value) {
        // 아무것도 없을 때 this.front == null
        // => error!

        if (this.front == null) {
            this.front = new Node(value, null);
            this.size++;
        } else {

            Node curr = this.front;
            while (curr.next != null) {
                curr = curr.next;
            }
            // crr -> 가장 마지막 노드
            curr.next = new Node(value, null);
            this.size++;
        }
    }

    public int get(int index) {
        // 의미상 아무것도 없는 거에 get은 어차피 error!
        Node curr = refAt(index);
        return curr.data;
    }

    public int set(int index, int value) {
        Node curr = refAt(index);
        int temp = curr.data;
        curr.data = value;
        return temp;
    }

    public void add(int index, int value) {
        // List가 비어있는 경우 예외 처리 필요
        if (this.front == null) {
            // index == 0 이어야
            if (index == 0) {
                this.front = new Node(value, null);
                this.size++;
            } else
                throw new IndexOutOfBoundsException();
        } else {
            Node curr = refAt(index - 1);
            // index-1번째 접근, 새로운 노드 만들고 ref 연결

            // Node new_node = new Node(value, curr.next);
            // curr.next = new_node;

            curr.next = new Node(value, curr.next);
            this.size++;
        }
    }

    public int remove(int index) {
        if (this.front == null) {
            throw new IndexOutOfBoundsException();
        } else {
            // 지울 애 전의 Node와 지울 애 후의 Node를 연결
            Node curr = refAt(index - 1);

            int removed = curr.next.data;
            curr.next = curr.next.next;

            this.size--;

            // Java는 GarabageCollector가 알아서 지워주니
            // 지워주는 구현이 필요 없음
            return removed;
        }
    }

    public int getSize() {
        return size;
    }

    private Node refAt(int index) {
        Node curr = this.front;

        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }
}
