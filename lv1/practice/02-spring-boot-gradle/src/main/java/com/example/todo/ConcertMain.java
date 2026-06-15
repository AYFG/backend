package com.example.todo;

public class ConcertMain {
    public static void main(String[] args) {
        // 1. 진짜 가수 아이유가 대기실에 있습니다.
        Singer realIU = new IU();

        // 2. 매니저를 고용합니다. "당신은 오늘부터 아이유 담당입니다."
        // (아이유 객체를 매니저에게 넘겨줍니다)
        Singer manager = new IUManager(realIU);

        // 3. 공연 시작!
        // 관객은 'manager'에게 노래를 시킵니다.
        System.out.println("--- 공연 시작 ---");
        manager.sing();
    }
}
/**
 * 1. 우리는 `IU` 클래스 안에 System.out.println("마이크 테스트")를 적은 적이 없습니다.
 * 2. 하지만 실행 결과에는 마이크 테스트가 출력되었습니다.
 * 3. 결론: 원본 코드(`IU.java`)를 전혀 건드리지 않고, 프록시(`IUManager.java`)를 중간에 끼워 넣어서 기능을
 * 추가했습니다.
 * 이것이 바로 스프링이 추구하는 'OCP(개방-폐쇄 원칙)'의 핵심입니다. "기존 코드는 변경하지 말고(Closed), 기능은
 * 확장하라(Open).
 * ”정적 프록시(Static Proxy)의 한계:
 * 우리는 방금 IUManager라는 클래스를 직접 손으로 짰습니다.
 * 그렇다면 BTS를 위해서는 BTSManager를 만들어야 하고, NewJeans를 위해서는 NewJeansManager를 또 만들어야
 * 합니다.
 */