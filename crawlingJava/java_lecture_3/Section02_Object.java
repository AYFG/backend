package java_lecture_3;

/**
 * ## 섹션 2. Object 클래스
 */
public class Section02_Object {
    public static void main(String[] args) {
        // 1. [강의: Object 다형성]
        /*
         * Object는 모든 클래스의 최상위 부모입니다.
         * 따라서 모든 객체는 Object 타입으로 참조될 수 있습니다.
         */
        Dog dog = new Dog();
        Car car = new Car();

        action(dog);
        action(car);

        // 2. [강의: toString()]
        /*
         * toString()은 객체의 정보를 문자열로 반환합니다.
         * 기본적으로는 '클래스명@해시코드'를 반환하지만, 오버라이딩하여 의미 있는 정보를 제공할 수 있습니다.
         */
        System.out.println("dog.toString() = " + dog.toString());
        System.out.println("car.toString() = " + car.toString());

        // 3. [강의: equals() - 동일성과 동등성]
        /*
         * 동일성(Identity): == 연산자, 두 객체의 참조값이 같은지 확인
         * 동등성(Equality): equals() 메서드, 두 객체의 논리적 값이 같은지 확인
         */
        User user1 = new User("id-100");
        User user2 = new User("id-100");

        System.out.println("동일성: " + (user1 == user2)); // false
        System.out.println("동등성: " + (user1.equals(user2))); // true (오버라이딩 시)
    }

    private static void action(Object obj) {
        if (obj instanceof Dog dog) {
            dog.sound();
        } else if (obj instanceof Car car) {
            car.move();
        }
    }
}

class Dog {
    public void sound() { System.out.println("멍멍"); }
    @Override
    public String toString() { return "강아지"; }
}

class Car {
    public void move() { System.out.println("자동차가 이동합니다."); }
}

class User {
    private String id;
    public User(String id) { this.id = id; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id.equals(user.id);
    }
}
