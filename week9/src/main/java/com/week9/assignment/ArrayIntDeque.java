package com.week9.assignment;

public class ArrayIntDeque {
    private int[] elements;
    private int front, end, count;
    private static final int CAPACITY = 16;

    public ArrayIntDeque() {
        this.elements = new int[CAPACITY];
        this.front = 0;
        this.end = 0;
        this.count = 0;
    }

    public void addFirst(int value) {
        if (this.count == this.elements.length) {
            grow();
        }

        this.front = (this.front - 1 + this.elements.length) % this.elements.length;
        this.elements[this.front] = value;
        this.count++;
    }

    public void addLast(int value) {
        if (this.count == this.elements.length) {
            grow();
        }

        this.elements[this.end] = value;
        this.end = (this.end + 1) % this.elements.length;
        this.count++;
    }

    public int removeFirst() {
        if (this.count == 0) {
            throw new IllegalAccessError();
        }
        int value = this.elements[this.front];
        this.front = (this.front + 1) % this.elements.length;
        this.count--;
        return value;
    }

    public int removeLast() {
        if (this.count == 0) {
            throw new IllegalAccessError();
        }
        this.end = (this.end - 1 + this.elements.length) % this.elements.length;
        int value = this.elements[this.end];
        this.count--;
        return value;
    }

    public int peekFirst() {
        if (this.count == 0) {
            throw new IllegalAccessError();
        }
        return this.elements[this.front];
    }

    public int peekLast() {
        if (this.count == 0) {
            throw new IllegalAccessError();
        }
        return this.elements[(this.end - 1 + this.elements.length) % this.elements.length];
    }

    private void grow() {
        int[] new_array = new int[this.elements.length * 2];
        for (int i = this.front; i < this.front + this.elements.length; i++) {
            new_array[i] = this.elements[i % this.elements.length];
        }
        this.elements = new_array;
    }

    public int size() {
        return this.count;
    }

    @Override
    public String toString() {
        String str = "[";
        if (this.count > 0)
            str += this.elements[this.front];
        for (int i = 1; i < this.count; i++)
            str += ", " + this.elements[(this.front + i + this.elements.length) % this.elements.length];
        str += "]";
        return str;
    }

    public static void main(String[] args) {
        ArrayIntDeque data = new ArrayIntDeque();
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
