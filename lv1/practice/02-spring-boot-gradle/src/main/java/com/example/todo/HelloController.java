package com.example.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.jdbc.core.JdbcTemplate; // 1. JdbcTemplate import
// import org.springframework.jdbc.core.RowMapper; // 2. RowMapper import
// import org.springframework.jdbc.support.GeneratedKeyHolder; // 3. KeyHolder import
// import org.springframework.jdbc.support.KeyHolder; // 4. KeyHolder import
// import java.sql.PreparedStatement; // 5. PreparedStatement import
// import java.sql.ResultSet; // 6. ResultSet import
// import java.sql.SQLException; // 7. SQLException import
// import java.sql.Statement; // 8. Statement import
import java.util.Optional;

@RestController
public class HelloController {
    private final UserRepository userRepository;

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

    // (2) "생성자 주입" (Dependency Injection)
    // "HelloController(점원)를 만들 때(생성자),
    // 스프링 매니저님! 'UserRepository' 제어판(JPA 로봇)을
    // '자동으로' 찾아서 '연결(주입)'해 주세요!"
    // (이것이 바로 '의존성 주입'입니다.)
    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // [ /users (전체 조회) API 추가 ]
    // (JdbcTemplate 버전으로는 복잡해서 생략했었지만, 지금은 1줄입니다!)
    @GetMapping("/users")
    public List<User> getUserList() {
        // 'JPA 제어판'의 '전체 조회' 버튼을 누름
        return userRepository.findAll();
    }

    // private Map<Long, User> users = new HashMap<>();
    // private long nextId = 1L; // 'L'은 이 숫자가 Long 타입임을 의미

    // (1) 'JdbcTemplate'을 보관할 'final' 변수 생성
    // (2) "생성자 주입" (Dependency Injection)
    // "HelloController(점원)를 만들 때(생성자),
    // 스프링 매니저님! 'JdbcTemplate' 도구를
    // '자동으로' 찾아서 '연결(주입)'해 주세요!"
    // (스프링이 application.yml의 DB 설정을 보고 자동으로 만들어 줍니다)
    // private final JdbcTemplate jdbcTemplate;

    // public HelloController(JdbcTemplate jdbcTemplate) {
    // this.jdbcTemplate = jdbcTemplate;
    // }

    private final Object idLock = new Object();

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        // 그 길었던 "SELECT SQL + RowMapper + try-catch" 코드가...
        // 'JPA 제어판'의 'ID로 조회' 버튼 하나로 끝납니다.

        // (주의!) findById는 'Optional'이라는 '상자'에 담겨 반환됩니다.
        // "데이터가 '있을 수도, 없을 수도(null)' 있기 때문"
        // (이것이 try-catch를 대체합니다)
        Optional<User> userOptional = userRepository.findById(id);

        // 상자를 열어서, "만약 있으면(isPresent) 내용물을 꺼내고,
        // "없으면(else) null을 반환해"
        return userOptional.orElse(null);
    }

    // (참고: findAll()은 query() 메서드와 RowMapper를 써야 하므로 더 복잡합니다. 일단 생략)

    // [ /users (생성) API 수정 ]
    @PostMapping("/users")
    public User createUser(@RequestBody User newUser) {
        // 그 복잡했던 "INSERT SQL + KeyHolder" 코드가...
        // 'JPA 제어판'의 '저장' 버튼 하나로 끝납니다.

        // '@GeneratedValue' 덕분에 ID는 DB가 알아서 생성하며,
        // 'save'는 저장된 최종 User 객체(ID가 포함된)를 반환해 줍니다.
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }
}
