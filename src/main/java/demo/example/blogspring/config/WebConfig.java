package demo.example.blogspring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Locale;

@Configuration
@ControllerAdvice
public class WebConfig implements WebMvcConfigurer {

  private static Logger logger= LoggerFactory
          .getLogger(WebConfig.class);


  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    sessionLocaleResolver.setDefaultLocale(Locale.US);
    return sessionLocaleResolver;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }
  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Bean
  public LocalValidatorFactoryBean validator() {
    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource());
    return bean;
  }


 /* @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleException(EntityNotFoundException ex){
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
  }
  */

  @ExceptionHandler({EntityNotFoundException.class, ConstraintViolationException.class})
  public ModelAndView handleNotFoundException(HttpServletRequest request
          , Exception ex){
    logger.info("Exception type:"+ ex);
    ModelAndView mv=new ModelAndView();
    mv.addObject("message",ex.getMessage());
    mv.addObject("exception",ex);
    mv.addObject("url",request.getRequestURL());
    mv.setViewName("postNotFound");
    return mv;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder(){
    BCryptPasswordEncoder bCryptPasswordEncoder
            =new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }


  @Bean
  public SpringSecurityDialect securityDialect() {
    return new SpringSecurityDialect();
  }


  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
      registry.addRedirectViewController("/","/posts");
  }
}
