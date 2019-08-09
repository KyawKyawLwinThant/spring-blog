package demo.example.blogspring.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class User implements UserDetails,Serializable  {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY )
  private Integer id;
  @NotEmpty(message = "first name cannot be empty.")
  private String firstName;
  @NotEmpty(message = "last name cannot be empty.")
  private String lastName;
  @Transient
  private String fullName;
  @NotEmpty(message = "password cannot be empty.")
  private String password;
  @Email(message = "email cannot be empty.")
  private String email;
  @Transient
  private String confirmPassword;


  public User() {
  }

  public User(@NotEmpty(message = "first name cannot be empty.") String firstName, @NotEmpty(message = "last name cannot be empty.") String lastName, @NotEmpty(message = "password cannot be empty.") String password, @Email(message = "email cannot be empty.") String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.email = email;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Role> roles=new ArrayList<>();


  public void addRole(Role role){
    roles.add(role);
  }

  public  void addRoles(List<Role> roles){
    roles.forEach(this::addRole);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.
            stream().
            map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
