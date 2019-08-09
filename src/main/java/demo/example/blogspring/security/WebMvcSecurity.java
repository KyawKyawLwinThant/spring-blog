package demo.example.blogspring.security;

import demo.example.blogspring.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebMvcSecurity extends WebSecurityConfigurerAdapter {

  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public WebMvcSecurity(UserDetailsServiceImpl userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

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
            .loginPage("/login")
            .permitAll()
            .usernameParameter("email")
            .and()
            .logout()
            .and()
            .rememberMe()
            .and()
            .csrf()
            .disable()
            ;

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }
}
