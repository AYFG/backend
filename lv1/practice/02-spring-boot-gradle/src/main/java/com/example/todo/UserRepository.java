package com.example.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 1. 기본 제공 메서드 (안 적어도 됨)
    // save(), findById(), findAll()...

    // 2. [추가] "이름(Name)으로 찾아줘(findBy)"
    // 직접 구현할 필요 없습니다. 선언만 하면 끝!
    // -> JPA가 "SELECT * FROM user WHERE name = ?" SQL을 자동 생성
    List<User> findByName(String name);

    // 3. [응용] "나이(Age)가 특정 값보다 큰(GreaterThan) 유저 찾아줘"
    // -> "SELECT * FROM user WHERE age > ?"
    List<User> findByAgeGreaterThan(int age);
}
