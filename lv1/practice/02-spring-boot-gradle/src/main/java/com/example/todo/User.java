package com.example.todo;

public class User {
    private Long id;
    private String name;
    private int age;

    // 생성자: 객체를 만들 때 사용
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
