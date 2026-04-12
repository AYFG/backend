package com.aloha.spring_di2.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.aloha.spring_di2.dto.Board;

@Repository
public class BoardDAO {

    public List<Board> list() {
        return List.of(
                new Board(1L, UUID.randomUUID().toString(), "title", "content", "writer", new Date(), new Date()),
                new Board(2L, UUID.randomUUID().toString(), "title", "content", "writer", new Date(), new Date()),
                new Board(3L, UUID.randomUUID().toString(), "title", "content", "writer", new Date(), new Date()));
    }
}
