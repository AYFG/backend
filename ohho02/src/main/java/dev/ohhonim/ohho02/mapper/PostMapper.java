package dev.ohhonim.ohho02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.ohhonim.ohho02.model.Post;

@Mapper
public interface PostMapper {
    public List<Post> listPost();
}
