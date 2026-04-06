package com.aloha.spring_di.dto;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 롬복 어노테이션, getter/setter, toString, equals, hashCode 자동 생성
@NoArgsConstructor // 롬복 어노테이션, 기본 생성자 자동 생성
@AllArgsConstructor // 롬복 어노테이션, 모든 필드를 매개변수로 하는 생성자 자동 생성
// 빈등록
@Component // 클래스를 빈으로 등록하는 어노테이션

public class Board {

    private String title;
    private String writer;
    private String content;

}
