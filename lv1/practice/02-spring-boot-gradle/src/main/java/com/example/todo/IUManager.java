package com.example.todo;

//매니저(Proxy)의 2가지 조건

//1. 위장술:겉보기에 가수랑 똑같아야 합니다.(같은 인터페이스`Singer`구현)
//2. 내통:진짜 가수를 알고 있어야 합니다.(변수로`Singer`를 가지고 있음)

// 1. 위장술: "저도 가수(Singer)입니다." (그래야 클라이언트가 의심 없이 호출함)
public class IUManager implements Singer {

    // 2. 내통: 진짜 가수를 품고 있습니다.
    private final Singer realSinger;

    // 생성자: 고용될 때 "누구를 담당할지" 주입받습니다.
    public IUManager(Singer realSinger) {
        this.realSinger = realSinger;
    }

    @Override
    public void sing() {
        // [부가 기능 1] 노래 부르기 전 (Pre-processing)
        System.out.println("🎤 매니저: 아아, 마이크 테스트. 하나 둘 셋.");

        // [위임] "아이유 씨, 나오세요!" (진짜 객체 호출)
        realSinger.sing();

        // [부가 기능 2] 노래 부른 후 (Post-processing)
        System.out.println("👏 매니저: 감사합니다! 지금까지 아이유였습니다.");
    }
}
