package demo.example.blogspring.controller;

import demo.example.blogspring.model.Post;
import demo.example.blogspring.service.AuthorService;
import demo.example.blogspring.service.PostService;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class PostController {
  @Autowired
  private PostService postService;
  @Autowired
  private AuthorService authorService;

  @GetMapping("/post")
  public String create(Model model){
    model.addAttribute("post",new Post());
    model.addAttribute("authors",authorService.findAll());
    return "postForm";
  }
  @PostMapping("/post")
  public String process(@Valid Post post, BindingResult result){
    if(result.hasErrors()){
      return "postForm";
    }

    postService.create(post);

    return "redirect:/posts";
  }
  @GetMapping("/posts")
  public String showAllPosts(Model model){
    model.addAttribute("allposts",postService.findAll());

    return "posts";
  }
  @GetMapping("/posts/details/{id}")
  public String showDetails(Model model,@PathVariable("id") long id){
    model.addAttribute("post",postService.findById(id));
    return "postDetails";
  }

  @GetMapping("/posts/update/{id}")
  public String updatePost(@PathVariable("id") long id,Model model){
    this.updatedId=id;
    model.addAttribute("post",postService.findById(id));
    model.addAttribute("authors",authorService.findAll());
    return "postUpdateForm";
  }
  @PostMapping("/posts/update")
  public String processPost(Post post){
    postService.update(updatedId,post);
    return "redirect:/posts";
  }
  private Long updatedId;
}
