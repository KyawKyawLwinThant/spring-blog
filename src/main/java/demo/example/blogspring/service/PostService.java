package demo.example.blogspring.service;

import demo.example.blogspring.model.Post;

import java.util.List;

public interface PostService {

  Post create(Post post);

  Post findById(Long id);

  List<Post> findAll();

  void update(Long id,Post post);
}
