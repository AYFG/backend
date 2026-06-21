package com.example.todo;

import java.lang.reflect.Proxy;

public class DynamicProxyMain {
    public static void main(String[] args) {
        Singer realIU = new IU();

        Singer robotProxy = (Singer) Proxy.newProxyInstance(Singer.class.getClassLoader(), new Class[] { Singer.class },
                new RobotManager(realIU));

        System.out.println("robot manager start---");

        robotProxy.sing();

        System.out.println("정체 공개");
        System.out.println("robotProxy의 진짜 클래스 이름: " + robotProxy.getClass().getName());

    };
}

// package ding.co.hellospring.proxy;

// import java.lang.reflect.Proxy;

// public class DynamicProxyMain {
// public static void main(String[] args) {
// // 1. 진짜 가수 준비 (Target)
// Singer realIU = new IU();

// // 2. 동적 프록시 생성 (Factory 가동!)
// // "자바야, 내가 시키는 대로 가짜 객체를 하나 찍어내라."
// Singer robotProxy = (Singer) Proxy.newProxyInstance(

// // 재료 1: 어디에 만들까? (클래스 로더)
// Singer.class.getClassLoader(),

// // 재료 2: 누구 행세를 할까? (인터페이스 명찰)
// // "Singer 인터페이스를 구현한 것처럼 만들어주세요."
// new Class[]{Singer.class},

// // 재료 3: 뇌(지침서)는 뭘로 채울까? (Handler)
// // "아까 만든 RobotManager를 뇌로 이식해주세요. 담당은 realIU입니다."
// new RobotManager(realIU)
// );

// // 3. 실행
// System.out.println("--- 로봇 매니저 가동 ---");

// // 관객은 이게 로봇인지 사람인지 모릅니다. 그냥 Singer니까 노래시킵니다.
// robotProxy.sing();

// // 4. [검증] 너 정체가 뭐냐?
// System.out.println("\n--- 정체 공개 ---");
// System.out.println("robotProxy의 진짜 클래스 이름: " +
// robotProxy.getClass().getName());
// }
// }