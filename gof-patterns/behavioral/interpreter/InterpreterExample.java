package gof.behavioral.interpreter;

import java.util.Map;

/**
 * Interpreter Pattern (인터프리터 패턴)
 * 
 * 목적: 언어의 문법 규칙을 클래스화하여 정의하고, 해당 언어로 된 문장을 해석하는 
 * 인터프리터를 제공합니다.
 */

// 1. 추상 표현식
interface Expression {
    int interpret(Map<String, Integer> context);
}

// 2. 종단 표현식 (Terminal Expression)
class NumberExpression implements Expression {
    private final String name;
    public NumberExpression(String name) { this.name = name; }
    @Override
    public int interpret(Map<String, Integer> context) {
        return context.getOrDefault(name, 0);
    }
}

// 3. 비종단 표현식 (Non-terminal Expression)
class AddExpression implements Expression {
    private final Expression left;
    private final Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        return left.interpret(context) + right.interpret(context);
    }
}

class InterpreterMain {
    public static void main(String[] args) {
        // 문법 구성: x + y
        Expression expression = new AddExpression(
            new NumberExpression("x"),
            new NumberExpression("y")
        );

        // 컨텍스트(변수 값) 정의
        Map<String, Integer> context = Map.of("x", 10, "y", 20);

        // 해석 실행
        int result = expression.interpret(context);
        System.out.println("결과: " + result); // 30 출력
    }
}
