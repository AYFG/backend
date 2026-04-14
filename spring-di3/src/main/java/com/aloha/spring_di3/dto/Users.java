package com.aloha.spring_di3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 파라미터 없는 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 받는 생성자 자동 생성
public class Users {
    private String username;
    private String password;

}
