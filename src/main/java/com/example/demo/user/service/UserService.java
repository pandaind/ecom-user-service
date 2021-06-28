package com.example.demo.user.service;

import com.example.demo.user.service.dto.UserDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {
  List<UserDTO> findAll();

  Optional<UserDTO> findOne(Long id);

  UserDTO findOneByName(String userName);

  UserDTO save(UserDTO user);
}
