package com.example.demo.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "role_name")
  private String roleName;

  @OneToMany(mappedBy = "role")
  @JsonIgnore
  @ToString.Exclude
  private List<User> users;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    UserRole userRole = (UserRole) o;

    return Objects.equals(id, userRole.id);
  }

  @Override
  public int hashCode() {
    return 1195449715;
  }
}
