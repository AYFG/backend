package dev.ohhonim.ohho02;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.ohhonim.ohho02.model.Post;
import dev.ohhonim.ohho02.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Ohho02Application implements CommandLineRunner {
	private final PostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ohho02Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Post> postList = List.of(
				new Post(null, "title1", "contnet1", "", LocalDateTime.now()),
				new Post(null, "title2", "contnet2", "", LocalDateTime.now()),
				new Post(null, "title3", "contnet3", "", LocalDateTime.now()));

		postRepository.saveAll(postList);
	}

}
