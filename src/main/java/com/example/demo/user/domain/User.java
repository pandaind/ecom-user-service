package com.example.demo.user.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

/** A User */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
  private static final long serialVersionUID = 4061550443261513561L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String userName;

  private String password;

  private int active;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_details_id")
  private UserDetails userDetails;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private UserRole role;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    User user = (User) o;

    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
