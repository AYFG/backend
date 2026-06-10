package com.example.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.jdbc.core.JdbcTemplate; // 1. JdbcTemplate import
import org.springframework.jdbc.core.RowMapper; // 2. RowMapper import
import org.springframework.jdbc.support.GeneratedKeyHolder; // 3. KeyHolder import
import org.springframework.jdbc.support.KeyHolder; // 4. KeyHolder import
import java.sql.PreparedStatement; // 5. PreparedStatement import
import java.sql.ResultSet; // 6. ResultSet import
import java.sql.SQLException; // 7. SQLException import
import java.sql.Statement; // 8. Statement import
import java.util.Optional;

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

    // private Map<Long, User> users = new HashMap<>();
    // private long nextId = 1L; // 'L'은 이 숫자가 Long 타입임을 의미

    // (1) 'JdbcTemplate'을 보관할 'final' 변수 생성
    // (2) "생성자 주입" (Dependency Injection)
    // "HelloController(점원)를 만들 때(생성자),
    // 스프링 매니저님! 'JdbcTemplate' 도구를
    // '자동으로' 찾아서 '연결(주입)'해 주세요!"
    // (스프링이 application.yml의 DB 설정을 보고 자동으로 만들어 줍니다)
    private final JdbcTemplate jdbcTemplate;

    public HelloController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final Object idLock = new Object();

    @GetMapping("/users/{id}")
    // public User getUserById(@PathVariable("id") Long id) {
    // String userName = "User " + id;
    // int userAge = (int) (id % 30 + 20);
    // User user = new User(userName, userAge);

    // return user;
    // }
    public User getUserById(@PathVariable("id") Long id) {
        // [고통 1] "SELECT" SQL 문자열을 "직접" 작성합니다.
        String sql = "SELECT * FROM users WHERE id = ?";

        try {
            // [고통 2] DB 결과를 자바 객체로 "수동 변환"하는 'RowMapper'
            // "jdbcTemplate님! 이 SQL을 실행하고, 결과가 나오면
            // 'userRowMapper'의 규칙대로 'User' 객체로 변환해서 주세요."
            User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    // 이 변환 로직을 "개발자가 직접" 작성해야 합니다.
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setAge(rs.getInt("age"));
                    // (만약 User 필드가 50개라면? 50줄의 코드를 작성해야 합니다)
                    return user;
                }
            }, id); // SQL의 ?에 id 값을 전달

            return user;
        } catch (Exception e) {
            // [고통 3] 데이터가 없으면 'EmptyResultDataAccessException' 예외가 터집니다.
            // 이 예외 처리도 "직접" 해야 합니다.
            return null; // 데이터가 없으면 null 반환
        }
    }

    // (참고: findAll()은 query() 메서드와 RowMapper를 써야 하므로 더 복잡합니다. 일단 생략)

    @PostMapping("/users")
    // public User createUser(@RequestBody User newUser) {
    // newUser.setId(nextId++);

    // users.put(newUser.getId(), newUser);

    // return newUser;
    // }
    public User createUser(@RequestBody User newUser) {
        // [고통 1] "INSERT" SQL 문자열을 "직접" 작성합니다.
        // (오타가 나도 IntelliJ는 모릅니다. 서버 실행 후에야 에러를 알 수 있습니다.)
        // 애플리케이션에서 id를 생성하여 명시적으로 INSERT
        String sql = "INSERT INTO users (id, name, age) VALUES (?, ?, ?)";

        Long finalId;
        synchronized (idLock) {

            Long nextId = jdbcTemplate.queryForObject("SELECT COALESCE(MAX(id), 0) + 1 FROM users", Long.class);
            if (nextId == null) {
                nextId = 1L;
            }
            finalId = nextId;

            // "jdbcTemplate님! 이 SQL을 실행해 주세요."
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, finalId);
                preparedStatement.setString(2, newUser.getName());
                preparedStatement.setInt(3, newUser.getAge());
                return preparedStatement;
            });
        }

        newUser.setId(finalId);

        return newUser; // ID가 설정된 User 객체 반환
    }
}
