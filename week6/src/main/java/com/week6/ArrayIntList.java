package com.week6;

// ADT 정의 역할
interface IntList {
    int get(int index);

    int set(int index, int value);

    void append(int value);

    void insert(int index, int value);

    int remove(int index);

    int size();
}

public class ArrayIntList implements IntList {
    // field
    private static final int DEFAULT_CAPACITY = 10;
    private int[] elementData;
    private int size;

    // method
    public ArrayIntList() {
        this.size = 0;
        this.elementData = new int[DEFAULT_CAPACITY];
    }

    public void append(int value) {
        ensureCapacity(this.size); // 가득 찼으면 늘리게
        this.elementData[this.size++] = value;
    }

    public void insert(int index, int value) {
        this.size += 1;
        rangeCheck(index);
        ensureCapacity(this.size - 1);

        for (int i = this.size - 2; i >= index; i--) {
            // 맨 뒤 요소부터 index까지 한 칸 뒤로 밀기
            this.elementData[i + 1] = this.elementData[i];
        }

        this.elementData[index] = value;
    }

    public int get(int index) {
        return this.elementData[index];
    }

    public int set(int index, int value) {
        rangeCheck(index);
        int previous = this.elementData[index];
        this.elementData[index] = value;
        return previous; // 원래 있던 친구 반환
    }

    public int remove(int index) {
        rangeCheck(index);
        int removed = this.elementData[index];
        for (int i = index; i < size - 1; i++) {
            this.elementData[i] = this.elementData[i + 1];
        }
        size -= 1;
        return removed;
    }

    public int size() {
        return this.size;
    }

    private void ensureCapacity(int index) {
        if (index >= this.elementData.length) {
            int[] oldData = this.elementData;
            this.elementData = new int[this.elementData.length + DEFAULT_CAPACITY];
            for (int i = 0; i < oldData.length; i++) {
                this.elementData[i] = oldData[i];
            }
        }
    }

    private void rangeCheck(int index) {
        if (index >= this.size || index < 0) { // 정의되지 않는 index
            throw new IndexOutOfBoundsException();
        }
    }

    public String toString() {
        // [1, 2, 3, 4]
        String outstr = "[";

        for (int i = 0; i < this.size; i++) {
            outstr += this.elementData[i];
            outstr += ", ";
        }
        // "[1, 2, 3, 4, "

        outstr = outstr.substring(0, Math.max(1, outstr.length() - 2)) + "]";
        // "[1, 2, 3, 4]"

        return outstr;
    }

    public static void main(String[] args) {
        ArrayIntList list = new ArrayIntList();

        for (int i = 1; i <= 5; i++) {
            list.append(i);
        } // [1, 2, 3, 4, 5]
        System.out.println(list);

        list.insert(2, -1); // [1, 2, -1, 3, 4, 5] // 조금 이상함
        System.out.println(list);

        list.remove(4);
        System.out.println(list);

    }
}
