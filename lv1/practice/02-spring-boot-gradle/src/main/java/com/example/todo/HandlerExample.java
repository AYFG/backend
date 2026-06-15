
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