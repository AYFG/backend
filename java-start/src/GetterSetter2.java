public class GetterSetter2 {
    private String name;
    private int age;

    public String GetName() {
        return name;
    }

    public int GetAge() {
        return age;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public void SetAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        GetterSetter2 human = new GetterSetter2();
        human.name = "test";
        human.age = 1234;

        System.out.println(human.GetName());
        System.out.println(human.GetAge());
    }
}
