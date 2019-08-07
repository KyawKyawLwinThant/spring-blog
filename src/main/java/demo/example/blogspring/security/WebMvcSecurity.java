package demo.example.blogspring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebMvcSecurity extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/posts").permitAll()
            .antMatchers("/authors").permitAll()
            .antMatchers("/post").hasRole("ADMIN")
            .antMatchers("/author").hasRole("ADMIN")
            .and()
            .formLogin()
            .and()
            .httpBasic()
            ;

  }
}
