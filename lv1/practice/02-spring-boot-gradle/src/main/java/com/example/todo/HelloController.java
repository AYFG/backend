package com.example.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public HelloResponse hello() {
        return new HelloResponse("Hello World");
    }

    @GetMapping("/hello/{name}")
    public HelloResponse helloName(@PathVariable String name) {
        return new HelloResponse("hello" + name);
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User("woong", 10);
        return user;
    }

    @GetMapping("/users")
    public List<User> getUserList() {
        User user1 = new User("김철스", 24);
        User user2 = new User("이믄이", 34);
        User user3 = new User("박갑두", 44);

        List<User> userList = List.of(user1, user2, user3);
        return userList;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        String userName = "User " + id;
        int userAge = (int) (id % 30 + 20);
        User user = new User(userName, userAge);

        return user;
    }

    private Map<Long, User> users = new HashMap<>();
    private long nextId = 1L; // 'L'은 이 숫자가 Long 타입임을 의미

    @PostMapping("/users")
    public User createUser(@RequestBody User newUser) {
        newUser.setId(nextId++);

        users.put(newUser.getId(), newUser);

        return newUser;
    }

}
