package com.example.demo.user.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.demo.user.domain.User} entity.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 5491034184831004025L;

    private Long id;
    private String userName;
    private String password;
    private int active;
}
