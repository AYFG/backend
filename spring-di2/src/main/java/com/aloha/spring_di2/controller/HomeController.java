package com.aloha.spring_di2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.aloha.spring_di2.service.PostService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class HomeController {
    @Autowired
    // @Qualifier("boardServiceImpl") // 주입할 빈의 이름을 명시적으로 지정하여 BoardServiceImpl 빈을
    // 주입받음
    @Qualifier("commentServiceImpl") // 주입할 빈의 이름을 명시적으로 지정하여 CommentServiceImpl 빈을 주입받음
    private PostService postService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        postService.list();
        log.info("Home page requested");
        return "index";
    }
}
