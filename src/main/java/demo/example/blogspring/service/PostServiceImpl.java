package demo.example.blogspring.service;

import demo.example.blogspring.model.Post;
import demo.example.blogspring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostRepository postRepository;

  @Override
  public Post create(Post post) {
    return postRepository.save(post);
  }

  @Override
  public Post findById(Long id) {
    return postRepository.getOne(id);
  }

  @Override
  public List<Post> findAll() {
    return postRepository.findAll();
  }
}
