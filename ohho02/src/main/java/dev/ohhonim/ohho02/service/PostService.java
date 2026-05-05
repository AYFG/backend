package dev.ohhonim.ohho02.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.ohhonim.ohho02.mapper.PostMapper;
import dev.ohhonim.ohho02.model.Post;
import dev.ohhonim.ohho02.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    // JPA 방식
    private final PostRepository postRepository;

    // mybatis 방식
    // 컨트롤러에서는 어떤 방식이든 알 필요 없음 레이어드 아키텍처의 장점
    private final PostMapper postMapper;

    public List<Post> postList(String title) {
        // JPA 쿼리 크리에이션
        // return postRepository.findAll(); // 전체 GET
        // return postRepository.findByTitle(title); // Params 완전 일치하는것만 GET
        return postRepository.findByTitleContains(title); // Params 일부 일치하는 것들 GET

        // return postMapper.listPost();
    }

}
