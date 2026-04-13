public class GetterSetter {
    // private 필드는 클래스 내부에서만 접근 가능
    private String name;
    private int age;

    // name 필드의 값을 반환하는 getter 메서드
    public String getName() {
        return name;
    }

    // name 필드에 값을 설정하는 setter 메서드
    public void setName(String name) {
        this.name = name;
    }

    // age 필드의 값을 반환하는 getter 메서드
    public int getAge() {
        return age;
    }

    // age 필드에 값을 설정하는 setter 메서드
    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        // GetterSetter 클래스의 새 객체 생성
        GetterSetter person = new GetterSetter();

        // setter를 사용해 name과 age 필드 값 설정
        person.setName("홍길동");
        person.setAge(25);

        // getter를 사용해 필드 값을 읽어와 출력
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
    }
}
