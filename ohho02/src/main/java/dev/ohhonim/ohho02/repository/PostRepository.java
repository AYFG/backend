package dev.ohhonim.ohho02.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ohhonim.ohho02.model.Post;

public interface PostRepository extends JpaRepository<Post, UUID> {

}
