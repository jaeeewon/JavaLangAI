package com.week5;

interface Shape {
    double getArea();

    double getPerimeter();
}

interface Polygon extends Shape {
    int getNumSides();
}

class Square implements Polygon {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return this.side * this.side;
    }

    @Override
    public double getPerimeter() {
        return 4 * this.side;
    }

    @Override
    public int getNumSides() {
        return 4;
    }
}

class Triangle implements Polygon {
    private double side;

    public Triangle(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return Math.sqrt(3) / 4 * this.side * this.side;
    }

    @Override
    public double getPerimeter() {
        return 3 * this.side;
    }

    @Override
    public int getNumSides() {
        return 3;
    }
}

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

    public double getNumSides() {
        return 0;
    }
}

public class ShapeDemo {
    public static void main(String[] args) {
        Shape s1 = new Square(5);
        Shape s2 = new Triangle(4);
        Shape s3 = new Circle(3);
        // s1을 Shape처럼 다루게 되니 Shape를 받는 메서드에 사용할 수 있음
        // interface의 구현, class의 상속 같은 경우에는 변수가 다뤄지는 타입과 인스턴스 타입을 다르게 가질 수 있음:
        // Polymorphism

        System.out.println("Square: ");
        System.out.println("- Area: " + s1.getArea());
        System.out.println("- Perimeter: " + s1.getPerimeter());

        System.out.println("- Number of Sides: " + ((Polygon) s1).getNumSides());
        // s1을 Shape로 사용하기로 해서 바로 사용 불가
        // s1을 Polyton으로 잡아주면 사용 가능 -> TypeCasting 해야
        // 만약 s1이 Polygon을 구현한 클래스가 아닐 때 compile time에서는 에러 안 잡힘
        // run time에서 에러 발생하니 잘 확인해보고 코드 짜야

        System.out.println("Triangle: ");
        System.out.println("- Area: " + s2.getArea());
        System.out.println("- Perimeter: " + s2.getPerimeter());
        System.out.println("- Number of Sides: " + ((Polygon) s2).getNumSides());

        System.out.println("Circle: ");
        System.out.println("- Area: " + s3.getArea());
        System.out.println("- Perimeter: " + s3.getPerimeter());
        System.out.println("- Number of Sides: " + ((Polygon) s3).getNumSides());
        // 실행은 됨: JAVA에서 이런 것까지 검사해주지 않음
        // 실행 후 에러가 발생: ClassCastException
        // 구현을 해둬도 불가능! - 당연하지, 에러가 나서 죽었는데
        // Exception in thread "main" java.lang.ClassCastException: class
        // com.week5.Circle cannot be cast to class com.week5.Polygon (com.week5.Circle
        // and com.week5.Polygon are in unnamed module of loader 'app')
    }
}
