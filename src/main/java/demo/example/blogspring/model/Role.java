package demo.example.blogspring.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Role implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;

  public Role() {
  }

  public Role(String name) {
    this.name = name;
  }

  @ManyToMany(mappedBy = "roles")
  private List<User> users=new ArrayList<>();
}
