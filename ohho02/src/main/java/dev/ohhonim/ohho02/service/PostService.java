package dev.ohhonim.ohho02.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.ohhonim.ohho02.model.Post;
import dev.ohhonim.ohho02.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> postList() {
        return postRepository.findAll();
    }

}
