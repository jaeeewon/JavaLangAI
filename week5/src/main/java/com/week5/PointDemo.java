package com.week5;

// public 빼주기: Java에서는 public class가 파일당 하나씩만 가질 수 있음
class Point {
    // Fields
    private int x; // field는 주로 private로 만듦 -> 클래스 밖에서는 x, y에 접근할 수 없음: 캡슐화
    private int y;
    // public: scope 밖에서 접근할 수 있음
    // private: scope 안에서만 접근할 수 있음

    // Behaviors: Methods
    // 생성자: Instance를 만들어주는 메서드
    // new Point();
    public Point() {
        // this: 이 method가 실행되는 instance를 참조
        // ~~ python의 self
        this.x = 0;
        this.y = 0;
    }

    // 생성자 하나 더 만들기
    // Overloading: 위에도 밑에도 Point가 있는데?
    // 입력으로 들어오는 파라미터를 다르게 하는 같은 이름의 method 사용 가능
    // 아래는 Overloading됨
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // System.out.println: toString의 반환값을 출력
    // 없다면 기본 toString 함수: com.week5.Point@5ca881b5
    // 덮어쓰기: Override 데코레이터 - 손가락 이슈 등 제대로 덮어씌워주는지 확인해줌
    // The method toStringㄴ() of type Point must override or implement a supertype
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    // 바람직한 코딩 방식: private field, getter
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // method가 호출되는 instance(this)와 other instance 사이의 거리
    public double distance(Point other) {
        double sqDist = Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2);
        return Math.pow(sqDist, 0.5);
    }

    public Point midPoint(Point other) {
        int midX = (this.x + other.x) / 2;
        int midY = (this.y + other.y) / 2;
        return new Point(midX, midY);
    }
}

public class PointDemo {
    public static void main(String[] args) {
        Point p1 = new Point();
        Point p2 = new Point(1, 2);
        /*
         * 실행 안 됨: The field Point.x is not visible
         * System.out.println(p.x);
         * System.out.println(p.y);
         */
        System.out.println(p1);
        System.out.println(p2);

        p1.setX(4); // p1 인스턴스에서 제공하는 setX method 사용용
        p1.setY(-2);
        System.out.println(p1.distance(p2));

        Point p3 = p1.midPoint(p2); // (4, 2), (1, 2)의 중점
        System.out.println(p3);
    }
}
