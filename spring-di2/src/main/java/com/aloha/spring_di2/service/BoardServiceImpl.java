package com.aloha.spring_di2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.aloha.spring_di2.dao.BoardDAO;
import com.aloha.spring_di2.dto.Board;
import com.aloha.spring_di2.dto.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary // 주입될 수 있는 빈이 여러 개일 때 우선적으로 주입될 빈으로 지정, BoardServiceImpl이 CommentServiceImpl보다
         // 우선적으로 주입됨
@Service // Ioc 컨테이너에 의존성 주입이 가능한 빈으로 등록
public class BoardServiceImpl implements PostService {

    @Autowired // BoardDAO 타입의 빈을 주입받음 의존성 자동 주입
    private BoardDAO boardDAO;

    @Override
    public List<Post> list() {
        List<Board> boardList = boardDAO.list();
        List<Post> postList = new ArrayList<>(boardList);
        log.info("Board List: {}", boardList);
        return postList;
    }

}
