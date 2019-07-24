package demo.example.blogspring.controller;

import demo.example.blogspring.model.Author;
import demo.example.blogspring.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {
  @Autowired
  private AuthorService authorService;

  @GetMapping("/author")
  public String create(Model model){

    model.addAttribute("author",new Author());

    return "authorForm";
  }

  @PostMapping("/author")
  public String process(Author author, BindingResult result){
    if(result.hasErrors()){
      return "authorForm";
    }
    authorService.create(author);

    return "redirect:/authors";
  }
  @GetMapping("/authors")
  public String showallAuthors(Model model){
    model.addAttribute("authors",authorService.findAll());
    return "authors";
  }
}
