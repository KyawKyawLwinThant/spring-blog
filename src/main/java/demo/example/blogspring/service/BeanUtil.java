package demo.example.blogspring.service;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BeanUtil implements ApplicationContextAware {
  private static ApplicationContext context;



  public static<T> PrettyTime getPrettyTime(Class<T> t){
      return (PrettyTime)context.getBean(t);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      context=applicationContext;
  }
}
