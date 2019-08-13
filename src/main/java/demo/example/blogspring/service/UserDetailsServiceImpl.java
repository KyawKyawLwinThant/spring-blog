package demo.example.blogspring.service;

import demo.example.blogspring.model.Role;
import demo.example.blogspring.model.User;
import demo.example.blogspring.repository.RoleRepository;
import demo.example.blogspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                                BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.bCryptPasswordEncoder=bCryptPasswordEncoder;
  }

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


  public User register(User user){
    Role adminRole=roleRepository.findByName("ROLE_ADMIN");
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.addRole(adminRole);
    adminRole.getUsers().add(user);
    return userRepository.save(user);
  }

}
