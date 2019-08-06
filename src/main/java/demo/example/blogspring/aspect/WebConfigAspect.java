package demo.example.blogspring.aspect;

import demo.example.blogspring.config.WebConfig;
import demo.example.blogspring.model.Post;
import demo.example.blogspring.service.PostService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Aspect
@Component
public class WebConfigAspect {
  private static Logger logger= LoggerFactory.getLogger(WebConfig.class);
  private PostService postService;

  public WebConfigAspect(PostService postService) {
    this.postService = postService;
  }

  @Before("execution(* *.showAllPosts(..))")
  public void logging(JoinPoint joinPoint){
    Object[] objects=joinPoint.getArgs();
    logger.info("Current Time:"+ LocalDateTime.now() +" "+
            joinPoint.getSignature().getName() +
            " called with:"+ objects
    );
  }

  @Before("execution(* *.showDetails(..))")
  public void notFoundAspect(JoinPoint joinPoint){
    if(postService.findById((Long)joinPoint.getArgs()[1])==null){
      throw new EntityNotFoundException((Long)joinPoint.getArgs()[1] +" Not Found.");
    }
  }


}
