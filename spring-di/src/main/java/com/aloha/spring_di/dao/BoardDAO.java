
package com.aloha.spring_di.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 롬복 어노테이션, 로그 객체 자동 생성
@Repository // DAO 역할로 빈 등록
public class BoardDAO {
    
    public void test() {
        log.info("BoardDAO test() 실행");
    }

}
