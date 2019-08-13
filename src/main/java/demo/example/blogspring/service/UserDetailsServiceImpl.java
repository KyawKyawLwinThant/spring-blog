package demo.example.blogspring.service;

import demo.example.blogspring.model.User;
import demo.example.blogspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email)
          throws UsernameNotFoundException {
    Optional<User> user=userRepository.findByEmail(email);

    if(!user.isPresent()){
        throw new UsernameNotFoundException(email + " Not Found!");
    }
   // System.out.println("User:"+ user.get().getEmail() +" "+ user.get().getPassword());
    return user.get();

  }
}
