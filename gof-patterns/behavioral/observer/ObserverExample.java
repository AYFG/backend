package gof.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern (옵저버 패턴)
 * 
 * 목적: 객체 사이에 일대다 의존 관계를 정의하여, 어떤 객체의 상태가 변할 때 
 * 그 객체에 의존성을 가진 다른 객체들이 자동으로 통지받고 갱신되게 합니다.
 */

// 1. 관찰 대상 (Subject)
interface NewsAgency {
    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers(String news);
}

// 2. 관찰자 인터페이스
interface Observer {
    void update(String news);
}

// 3. 구체적인 관찰 대상
class TechNewsAgency implements NewsAgency {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String news) {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }

    public void publishNews(String news) {
        System.out.println("뉴스 발행: " + news);
        notifyObservers(news);
    }
}

// 4. 구체적인 관찰자들
class NewsSubscriber implements Observer {
    private final String name;

    public NewsSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " 수신 완료: " + news);
    }
}

class ObserverMain {
    public static void main(String[] args) {
        TechNewsAgency agency = new TechNewsAgency();
        
        Observer user1 = new NewsSubscriber("사용자A");
        Observer user2 = new NewsSubscriber("사용자B");

        agency.register(user1);
        agency.register(user2);

        agency.publishNews("Java 21이 출시되었습니다!");
        
        agency.unregister(user1);
        agency.publishNews("새로운 디자인 패턴 강의가 업데이트되었습니다.");
    }
}
