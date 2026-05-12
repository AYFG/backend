# GoF Design Patterns (Java 17)

이 프로젝트는 GoF(Gang of Four)의 23가지 디자인 패턴을 Java 17 버전을 기준으로 학습하기 위해 구성되었습니다.

## 구조

- **Creational (생성 패턴)**: 객체 생성 메커니즘을 다룹니다.
    - Singleton (싱글톤)
    - Factory Method (팩토리 메서드)
    - Abstract Factory (추상 팩토리)
    - Builder (빌더)
    - Prototype (프로토타입)
- **Structural (구조 패턴)**: 클래스나 객체를 조합해 더 큰 구조를 만드는 방법을 다룹니다.
    - Adapter (어댑터)
    - Bridge (브릿지)
    - Composite (컴포지트)
    - Decorator (데코레이터)
    - Facade (퍼사드)
    - Flyweight (플라이웨이트)
    - Proxy (프록시)
- **Behavioral (행위 패턴)**: 객체 간의 통신과 역할 분담을 다룹니다.
    - Strategy (전략)
    - Observer (옵저버)
    - State (상태)
    - Command (커맨드)
    - Template Method (템플릿 메서드)
    - Iterator (이터레이터)
    - Visitor (비지터)
    - Memento (메멘토)
    - Mediator (중재자)
    - Interpreter (인터프리터)
    - Chain of Responsibility (책임 연쇄)

## Java 17 특징 활용
- `record`: 데이터 전달 객체(DTO) 표현 시 활용
- `sealed classes/interfaces`: 구현체 제한 및 타입 안전성 강화
- `switch pattern matching`: 상태에 따른 행위 분기 시 활용
- `var`: 로컬 변수 타입 추론 활용

각 폴더 내의 `.java` 파일은 해당 패턴의 정의, 구현 클래스, 그리고 실행 가능한 `main` 메서드를 포함하고 있습니다.
