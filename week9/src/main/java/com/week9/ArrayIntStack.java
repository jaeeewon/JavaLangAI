package com.week9;

public class ArrayIntStack {
    // Fields
    private int[] elements;
    private int top;
    private static final int CAPACITY = 16;

    public ArrayIntStack() {
        this.elements = new int[CAPACITY];
        this.top = 0;
    }

    public void push(int value) {
        // 가득 찼을 떄
        if (this.top == elements.length) {
            grow();
        }

        this.elements[this.top] = value;
        this.top += 1;
    }

    public int pop() {
        // 아무것도 없을 때
        if (this.elements.length == 0) {
            throw new IllegalAccessError();
        }

        this.top -= 1;
        return this.elements[this.top];
    }

    public int peek() {
        // 아무것도 없을 때
        if (this.elements.length == 0) {
            throw new IllegalAccessError();
        }
        return this.elements[this.top - 1];
    }

    public int size() {
        return this.top;
    }

    private void grow() {
        int[] new_array = new int[this.elements.length * 2]; // 더 큰 배열 만들어주기
        for (int i = 0; i < this.elements.length; i++) {
            new_array[i] = this.elements[i];
        }
        this.elements = new_array;
    }
}
// Q. 아무것도 없을 때 this.elements.length 대신 this.top으로 해야하는 게 아닌지?