package demo.example.blogspring.service;

import demo.example.blogspring.model.Author;
import demo.example.blogspring.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
  @Autowired
  private AuthorRepository authorRepository;
  @Override
  public Author create(Author author) {
    return authorRepository.save(author);
  }

  @Override
  public Author findById(Long id) {
    return authorRepository.findById(id).orElse(null);
  }

  @Override
  public List<Author> findAll() {
    return authorRepository.findAll();
  }
}
