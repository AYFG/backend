package dev.ohhonim.ohho02.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.ohhonim.ohho02.model.Post;
import dev.ohhonim.ohho02.service.PostService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // @RequiredArgsConstructor가 이걸 만들어줌
    // public PostController(PostService postService) {
    // this.postService = postService;
    // }

    @GetMapping("/list")
    public List<Post> postList(@RequestParam String title) {
        return postService.postList(title);
    }

    @GetMapping("/{postId}")
    public Post postDetail(@PathVariable("postId") String postId) {
        return new Post(UUID.randomUUID(), "title1", "contnet1", "", LocalDateTime.now());
    }

}
