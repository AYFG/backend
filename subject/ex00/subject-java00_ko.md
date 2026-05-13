# Java - Module 00
**패키지, 클래스, 메서드, System.out 스트림, 생성자, static, final, 그리고 기타 기본적인 것들**

**요약:**
이 문서는 C++ Module 00 과정을 **Java 버전으로 각색**한 것입니다.

## 목차
1. [제 1장. 소개 (Introduction)](#제-1장-소개-introduction)
2. [제 2장. 일반 규칙 (General rules)](#제-2장-일반-규칙-general-rules)
3. [제 3장. 과제 00: Megaphone](#제-3장-과제-00-megaphone)
4. [제 4장. 과제 01: My Awesome PhoneBook](#제-4장-과제-01-my-awesome-phonebook)
5. [제 5장. 과제 02: The Job Of Your Dreams](#제-5장-과제-02-the-job-of-your-dreams)

---

## 제 1장. 소개 (Introduction)
Java는 강력한 객체 지향 프로그래밍 언어입니다.
이 모듈들의 목표는 여러분에게 **객체 지향 프로그래밍(OOP)**을 소개하는 것입니다.
여러분은 새로운 개념들을 단계별로 발견하게 될 것입니다. 과제들의 복잡성은 점진적으로 증가할 것입니다.

---

## 제 2장. 일반 규칙 (General rules)

### 컴파일링 및 실행 (Compiling)
- 코드는 터미널에서 `javac` 명령어를 사용하여 컴파일하고, `java` 명령어로 실행해야 합니다.

### 포매팅 및 명명 규칙 (Formatting and naming conventions)
- 과제 디렉토리는 다음과 같이 명명됩니다: `ex00`, `ex01`, ... , `exn`
- 클래스 이름은 `UpperCamelCase` 형식으로 작성하세요. **파일 이름은 반드시 public 클래스 이름과 정확히 일치해야 합니다** (예: `ClassName.java`).
- 변수와 메서드 이름은 `lowerCamelCase` 형식으로 작성하세요.
- 별도로 명시되지 않는 한, 모든 출력 메시지는 개행 문자(new-line)로 끝나야 하며 표준 출력(`System.out`)으로 표시되어야 합니다.

### 허용 / 금지 사항 (Allowed/Forbidden)
- Java 표준 라이브러리(`java.util.*` 등)의 사용이 허용됩니다.
- 그러나, **외부 라이브러리(Maven, Gradle 등을 통한 다운로드)는 사용할 수 없습니다.** 순수 Java로만 작성하세요.
- `java.util.ArrayList` 등의 컬렉션 프레임워크 사용은 (명시적으로 허용되기 전까지) 원리 이해를 위해 **금지**됩니다. 배열(`[]`)을 직접 다뤄보세요.

### 몇 가지 설계 요구 사항 (A few design requirements)
- Java는 가비지 컬렉터(GC)가 있어 메모리를 관리해주지만, 파일이나 입출력 스트림(`Scanner` 등)을 다룰 때는 반드시 `close()`를 호출하여 **리소스 누수를 방지해야 합니다.**
- 클래스를 설계할 때 접근 제어자(`private`, `public`, `protected`)를 캡슐화 원칙에 맞게 적절히 사용하세요.

---

## 제 3장. 과제 00: Megaphone

**Exercise : 00**  
**Megaphone**
- 제출 디렉토리 : `ex00/`
- 제출할 파일 : `Megaphone.java`
- 금지된 사항 : 없음

모두가 깨어 있는지 확인하기 위해 다음과 같이 동작하는 프로그램을 작성하세요:
```sh
$> javac Megaphone.java
$> java Megaphone "shhhhh... I think the students are asleep..."
SHHHHH... I THINK THE STUDENTS ARE ASLEEP...
$> java Megaphone Damnit " ! " "Sorry students, I thought this thing was off."
DAMNIT ! SORRY STUDENTS, I THOUGHT THIS THING WAS OFF.
$> java Megaphone
* LOUD AND UNBEARABLE FEEDBACK NOISE *
$>
```

---

## 제 4장. 과제 01: My Awesome PhoneBook

**Exercise : 01**  
**My Awesome PhoneBook**
- 제출 디렉토리 : `ex01/`
- 제출할 파일 : `PhoneBook.java`, `Contact.java` 및 필요한 소스 파일들
- 금지된 사항 : `ArrayList` 등 컬렉션 클래스 사용 금지. (일반 배열을 사용하세요)

형편없지만 꽤나 멋진 전화번호부 소프트웨어처럼 동작하는 프로그램을 작성하세요.

다음 두 개의 클래스를 구현해야 합니다:
1. **PhoneBook**
   - 연락처(contacts) 배열을 갖습니다.
   - 최대 **8개**의 연락처를 저장할 수 있습니다. 9번째를 추가하려 하면 가장 오래된 연락처를 새 연락처로 덮어씌웁니다.
2. **Contact**
   - 전화번호부의 개별 연락처를 나타냅니다. (이름, 성, 별명, 전화번호, 비밀 등)

프로그램이 시작되면 세 가지 명령어 중 하나를 입력받아야 합니다. (`java.util.Scanner` 활용)

- **ADD**: 새 연락처 저장
  - 연락처 정보를 차례대로 입력받아 전화번호부에 추가합니다. 빈 칸은 허용되지 않습니다.
- **SEARCH**: 특정 연락처 표시
  - 저장된 연락처를 **인덱스, 이름, 성, 별명**의 4개 열로 출력합니다.
  - 각 열은 **10자** 너비여야 하며 파이프(`|`)로 구분됩니다. 텍스트가 10자를 넘으면 잘라내고 끝을 점(`.`)으로 대체합니다. (`System.out.printf`나 `String.format`을 찾아보세요)
  - 인덱스를 입력받아 해당 연락처의 세부 정보를 출력합니다.
- **EXIT**
  - 프로그램이 종료됩니다.

---

## 제 5장. 과제 02: The Job Of Your Dreams

**Exercise : 02**  
**The Job Of Your Dreams**

*(참고: 이 과제는 C++의 소멸자 호출 순서와 포인터를 다루는 특수 과제이므로, Java로 완벽히 1:1 변환하는 것은 언어 특성상 적합하지 않습니다. 대신 Java에서는 `static` 변수와 인스턴스 초기화 블록 등을 다루는 방식으로 변형하여 공부할 수 있습니다.)*

> 이 과제는 넘어가셔도 좋습니다.
