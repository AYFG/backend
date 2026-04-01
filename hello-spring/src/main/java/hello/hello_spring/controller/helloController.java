package hello.hello_spring.controller;


import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class helloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","spring!!");
        return "hello";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name")String name, Model model){
        model.addAttribute("name",name);
        //http://localhost:8080/hello-mvc?name=spring
        // 쿼리스트링 name=spring → @RequestParam이 꺼냄
        return "hello-template";
    }
}
/*
* ./gradlew build
* cd build/libs
* java -jar hello-spring-0.0.1-SNAPSHOT.jar
* */