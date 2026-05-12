package gof.behavioral.strategy;

/**
 * Strategy Pattern (전략 패턴)
 * 
 * 목적: 알고리즘군을 정의하고 각각을 캡슐화하여 교체해서 사용할 수 있게 만듭니다.
 * 전략 패턴을 사용하면 클라이언트와 독립적으로 알고리즘을 변경할 수 있습니다.
 */

// 1. 전략 인터페이스 (Java 17의 Sealed Interface를 사용하여 구현체를 제한할 수도 있음)
interface PaymentStrategy {
    void pay(int amount);
}

// 2. 구체적인 전략 클래스들
class CreditCardStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println(amount + "원 신용카드로 결제되었습니다.");
    }
}

class KakaoPayStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println(amount + "원 카카오페이로 결제되었습니다.");
    }
}

// 3. 컨텍스트 (전략을 사용하는 객체)
class ShoppingCart {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void checkout(int amount) {
        if (strategy == null) {
            System.out.println("결제 수단을 선택해주세요.");
            return;
        }
        strategy.pay(amount);
    }
}

/**
 * 실행 예제
 */
public class StrategyExample {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // 신용카드 결제 전략 선택
        cart.setPaymentStrategy(new CreditCardStrategy());
        cart.checkout(10000);

        // 카카오페이 결제 전략으로 변경
        cart.setPaymentStrategy(new KakaoPayStrategy());
        cart.checkout(20000);
        
        // Java 8+ 람다를 이용한 즉석 전략 정의
        cart.setPaymentStrategy(amount -> System.out.println(amount + "원 비트코인으로 결제되었습니다."));
        cart.checkout(5000);
    }
}
