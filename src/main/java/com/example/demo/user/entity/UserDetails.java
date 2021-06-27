package com.example.demo.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user_details")
public class UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "first_name", nullable = false, length = 50)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 50)
  private String lastName;

  @Column(name = "email", nullable = false, unique = true, length = 50)
  private String email;

  @Column(name = "phone_number", length = 15)
  private String phoneNumber;

  @Column(name = "street", length = 30)
  private String street;

  @Column(name = "street_number", length = 10)
  private String streetNumber;

  @Column(name = "zip_code", length = 6)
  private String zipCode;

  @Column(name = "locality", length = 30)
  private String locality;

  @Column(name = "country", length = 30)
  private String country;

  @OneToOne(mappedBy = "userDetails")
  @JsonIgnore
  private User user;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    UserDetails that = (UserDetails) o;

    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return 666118392;
  }
}
