package com.example.todo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RobotManager implements InvocationHandler {

    private final Object target;

    public RobotManager(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Robot manager: checking microphone...");
        System.out.println("Robot manager: called method is '" + method.getName() + "'.");

        Object result = method.invoke(target, args);

        System.out.println("Robot manager: mission complete.");
        return result;
    }
}