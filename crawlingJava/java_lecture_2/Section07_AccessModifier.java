package java_lecture_2;

/**
 * ## 섹션 7. 접근 제어자
 * (주의: 섹션 6 패키지는 폴더 구조 실습이라 하나의 파일로 표현하기 어려워 스킵하거나 간략히 주석 처리합니다.)
 */
public class Section07_AccessModifier {
    public static void main(String[] args) {

        // 1, 2. [강의: 접근 제어자 이해1, 2]
        /*
         * 중요한 값은 임의로 바뀌지 않도록 숨기는 것이 좋습니다.
         * Speaker에서 volume은 private으로 숨기고,
         * 메서드로만 변경할 수 있도록 만들었습니다.
         */
        Speaker speaker = new Speaker(90);
        speaker.volumeUp();
        speaker.volumeUp();
        // speaker.volume = 100; // private 설정 시 외부 접근 불가 (에러!)
        speaker.showStatus();

        // 3~5. [강의: 접근 제어자 종류 및 사용]
        /*
         * private: 같은 클래스 안에서만 사용 가능
         * default: 같은 패키지 안에서만 사용 가능
         * protected: 상속 관계일 때 사용할 수 있음
         * public: 어디서나 사용할 수 있음
         */

        // 6. [강의: 캡슐화]
        /*
         * 캡슐화는 데이터를 숨기고 필요한 기능만 공개하는 방법입니다.
         */
    }
}

class Speaker {
    private int volume; // private: 외부에서 직접 접근 불가!

    Speaker(int volume) {
        this.volume = volume;
    }

    void volumeUp() {
        if (volume >= 100) {
            System.out.println("더 이상 올릴 수 없습니다.");
        } else {
            volume += 10;
        }
    }

    void showStatus() {
        System.out.println("현재 음량: " + volume);
    }
}
