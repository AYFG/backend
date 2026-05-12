package gof.structural.facade;

/**
 * Facade Pattern (퍼사드 패턴)
 * 
 * 목적: 서브시스템에 있는 인터페이스들에 대해 통합된 인터페이스를 제공합니다. 
 * 복잡한 서브시스템을 쉽게 사용할 수 있게 하는 고수준 인터페이스를 정의합니다.
 */

// 1. 복잡한 서브시스템들
class Amplifier { void on() { System.out.println("앰프 켜짐"); } }
class Projector { void on() { System.out.println("프로젝터 켜짐"); } }
class Lights { void dim() { System.out.println("조명 조절"); } }
class StreamingService { void play(String movie) { System.out.println(movie + " 시작"); } }

// 2. 퍼사드 클래스 (복잡함을 캡슐화)
class HomeTheaterFacade {
    private final Amplifier amp;
    private final Projector projector;
    private final Lights lights;
    private final StreamingService service;

    public HomeTheaterFacade(Amplifier amp, Projector projector, Lights lights, StreamingService service) {
        this.amp = amp;
        this.projector = projector;
        this.lights = lights;
        this.service = service;
    }

    public void watchMovie(String movie) {
        System.out.println("영화 관람 준비 중...");
        lights.dim();
        projector.on();
        amp.on();
        service.play(movie);
    }
}

class FacadeMain {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(
            new Amplifier(), new Projector(), new Lights(), new StreamingService()
        );
        
        homeTheater.watchMovie("인터스텔라");
    }
}
