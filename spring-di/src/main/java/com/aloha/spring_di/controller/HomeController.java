package com.aloha.spring_di.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.spring_di.dao.BoardDAO;
import com.aloha.spring_di.service.BoardService;
import com.aloha.spring_di.service.BoardServiceImpl;

@Slf4j
@Controller
public class HomeController {

    // private BoardService boardService = new BoardServiceImpl(new BoardDAO()); //
    // new BoardServiceImpl()로 직접 객체를 생성하는 것은 Spring의 DI(의존성 주입) 원칙에 위배

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        boardService.test(); // => test() => BoardDAO의 test() 실행
        return "index";
    }

}
