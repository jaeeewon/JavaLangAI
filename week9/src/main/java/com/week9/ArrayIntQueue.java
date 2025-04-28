package com.week9;

public class ArrayIntQueue {
    // fields
    private int[] elements;
    private int front, end, count;
    private static final int CAPACITY = 16;

    public ArrayIntQueue() {
        this.elements = new int[CAPACITY];
        this.front = 0; // 시작점 포함
        this.end = 0; // 끝점 미포함 -> ex. this.end == 9 --> 8까지 데이터
        this.count = 0;
    }

    public void enqueue(int value) {
        // this.end == this.front: 가득 찬 경우 or 텅 비었을 때
        // 비어있는 경우는 기차느니까 넘어가쟈~ 헤헤,, 그치만 다시 추가햇다ㅜ
        if (this.count == this.elements.length) {
            grow();
        }

        // circular array structure: 원형 구조
        this.elements[this.end] = value;
        this.end = (this.end + 1) % this.elements.length;
        // end: 0, 1, 2, 3 --> 변화 없음
        // end: length - 1 --> length ==> 0
        this.count += 1;

        // end가 가리키는 위치에 값 넣고, end를 하나 늘려주는
        // this.elements[this.end] = value;
        // this.end += 1;
        // 한 줄로는 다음과 같음음
        // this.elements[this.end++] = value;
    }

    public int dequeue() {
        if (this.count == 0) {
            throw new IllegalAccessError();
        }
        // 원형 구조로 수정
        int value = this.elements[this.front];
        this.front = (this.front + 1) % this.elements.length;
        this.count -= 1;
        return value;

        // return this.elements[this.front++]; // this.front번째 요소를 반환하고 this.front가 1
        // 증가하는
    }

    public int peek() {
        if (this.count == 0) {
            throw new IllegalAccessError();
        }
        return this.elements[this.front];
    }

    private void grow() {
        int[] new_array = new int[this.elements.length * 2]; // 더 큰 배열 만들어주기
        for (int i = this.front; i < this.front + this.elements.length; i++) { // 시작점부터터
            new_array[i] = this.elements[i % this.elements.length]; // 나머지 연산으로 대응되는 인덱스,, 원형에 갇혀서 돌게 되는
            // i는 new_array에서 벗어나지 않음은 자명함
        }
        this.elements = new_array;
    }
}
// Q. 덮어쓰여지는 예외 처리는?