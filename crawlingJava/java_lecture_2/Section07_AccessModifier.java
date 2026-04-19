/**
 * ## 섹션 7. 접근 제어자
 * (주의: 섹션 6 패키지는 폴더 구조 실습이라 하나의 파일로 표현하기 어려워 스킵하거나 간략히 주석 처리합니다.)
 */
public class Section07_AccessModifier {
    public static void main(String[] args) {

        // 1, 2. [강의: 접근 제어자 이해1, 2]
        /* 외부에서 함부로 필드 값을 변경하지 못하도록 데이터를 숨겨야 합니다. */
        Speaker speaker = new Speaker(90);
        speaker.volumeUp();
        speaker.volumeUp();
        // speaker.volume = 100; // private 설정 시 외부 접근 불가 (에러!)
        speaker.showStatus();

        // 3~5. [강의: 접근 제어자 종류 및 사용]
        /* 
         * private: 내 클래스 안에서만
         * default: 내 패키지 안에서만
         * protected: 패키지가 달라도 상속 관계면 허용
         * public: 모든 곳에서 허용
         */

        // 6. [강의: 캡슐화]
        /* 데이터는 숨기고(private), 외부에는 꼭 필요한 기능(public 메서드)만 공유하는 것! */
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
