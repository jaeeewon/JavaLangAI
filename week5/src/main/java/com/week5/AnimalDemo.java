package com.week5;

class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void makeSound() {
        // 내부를 구현하지 않을 것임
    }

    public void eat(String food) {
        System.out.println(name + "가 " + food + "(을)를 먹습니다.");
    }
}

class Dog extends Animal {
    // Animal에 name field가 있으니 name field를 정의할 필요 없음
    public Dog(String name) {
        super(name); // super의 생성자 호출
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + "가 멍멍 짖습니다!");
        // 상속 관계에서도 private은 접근 불가
    }

    public void wagTail() {
        System.out.println(getName() + "가 꼬리를 흔듭니다!");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + "가 야옹하고 웁니다!");
    }
}

class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + "가 삐약삐약하고 지저귑니다!");
    }

    public void fly() {
        System.out.println(getName() + "가 하늘로 날아갑니다!");
    }
}

public class AnimalDemo {
    public static void main(String[] args) {
        Animal dog = new Dog("흰둥이");
        Animal cat = new Cat("나비");
        Animal bird = new Bird("앵무");

        // Override된 makeSound
        dog.makeSound();
        cat.makeSound();
        bird.makeSound();

        dog.eat("뼈다귀");
        cat.eat("물고기");
        bird.eat("지렁이");

        // Casting
        ((Dog) dog).wagTail();
        ((Bird) bird).fly();
    }
}
