
package com.example.todo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Calculator {
    int add(int a, int b);
}

class MyLogicHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("핸들러 계산 요청 가로챔");
        System.out.println("핸들러 호출된 메서드 이름 = " + method.getName());

        int arg1 = (int) args[0];
        int arg2 = (int) args[1];
        System.out.println("핸들러 들어온 숫자 = " + arg1 + ", " + arg2);

        return arg1 + arg2 + 100;
    }

}

public class HandlerExample {
    public static void main(String[] args) {
        Calculator fakeCal = (Calculator) Proxy.newProxyInstance(
                Calculator.class.getClassLoader(),
                new Class[] { Calculator.class },
                new MyLogicHandler());
        System.out.println("--- 실행 시작 --- ");
        int result = fakeCal.add(10, 20);

        System.out.println("---실행 결과---");
        System.out.println("결과값: " + result);
    }
}

// import java.lang.reflect.InvocationHandler;
// import java.lang.reflect.Method;
// import java.lang.reflect.Proxy;

// // 1. 인터페이스 (필수: JDK Dynamic Proxy는 인터페이스가 있어야 함)
// interface Calculator {
// int add(int a, int b);
// }

// // 2. 핸들러 (중간에서 간섭하는 로직)
// class MyLogicHandler implements InvocationHandler {

// // 이 예제는 'target(진짜 객체)' 없이 핸들러 자체에서 답을 줘보겠습니다.
// @Override
// public Object invoke(Object proxy, Method method, Object[] args) throws
// Throwable {

// System.out.println("🤖 핸들러: 계산 요청을 가로챘습니다!");
// System.out.println("🤖 핸들러: 호출된 메서드 이름 = " + method.getName());

// // 인자(args) 확인
// int arg1 = (int) args[0];
// int arg2 = (int) args[1];
// System.out.println("🤖 핸들러: 들어온 숫자 = " + arg1 + ", " + arg2);

// // 원래는 여기서 method.invoke(target)을 해서 진짜 객체에게 넘기지만,
// // 여기서는 핸들러가 직접 조작해서 답을 줘버리겠습니다.
// return arg1 + arg2 + 100; // (원래 값에 100을 더해서 사기치기)
// }
// }

// public class HandlerExample {
// public static void main(String[] args) {

// // 3. 프록시 생성 (가짜 계산기 만들기)
// Calculator fakeCal = (Calculator) Proxy.newProxyInstance(
// Calculator.class.getClassLoader(),
// new Class[] { Calculator.class },
// new MyLogicHandler() // <-- 여기에 핸들러 탑재!
// );

// // 4. 실행
// System.out.println("--- 실행 시작 ---");
// int result = fakeCal.add(10, 20); // 핸들러의 invoke()가 실행됨

// System.out.println("--- 실행 결과 ---");
// System.out.println("결과값: " + result);
// }
// }
// import java.lang.reflect.Method;

// // 1. 실험 대상 클래스
// class Person {
// public void sayHello(String name) {
// System.out.println("안녕? 나는 " + name + "(이)라고 해.");
// }
// }

// public class ReflectionExample {
// public static void main(String[] args) throws Exception {
// Person person = new Person();

// // -------------------------------------------------------
// // [방식 1] 일반적인 호출 (우리가 아는 방식)
// // -------------------------------------------------------
// System.out.println("=== 1. 일반 호출 ===");
// person.sayHello("철수");

// // -------------------------------------------------------
// // [방식 2] 리플렉션 호출 (마법의 거울)
// // -------------------------------------------------------
// System.out.println("\n=== 2. 리플렉션 호출 ===");

// // 1. 클래스 정보(거울) 가져오기
// Class<?> clazz = person.getClass();

// // 2. 메서드 정보 찾기 ("sayHello"라는 이름의 메서드를 찾아라!)
// // 파라미터로 String.class를 받는다는 것까지 명시해야 함
// Method methodInfo = clazz.getMethod("sayHello", String.class);

// // 3. 실행 (invoke)
// // 해석: "methodInfo(지침)대로 실행해라. 대상은 person이고, 재료는 '영희'다."
// methodInfo.invoke(person, "영희");
// }
// }