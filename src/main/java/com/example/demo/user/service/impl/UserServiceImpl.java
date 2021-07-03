package com.example.demo.user.service.impl;

import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.service.UserService;
import com.example.demo.user.service.dto.UserDTO;
import com.example.demo.user.service.mapper.UserMapper;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Service Implementation for managing {@link User}. */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  private final UserMapper mapper;

  @Autowired
  public UserServiceImpl(UserRepository repository, UserMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserDTO> findAll() {
    log.debug("Request to get all Products");
    return mapper.toDto(repository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UserDTO> findOne(Long id) {
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UserDTO> findOneByName(String userName) {
    return repository.findByUserName(userName).map(mapper::toDto);
  }

  @Override
  public UserDTO save(UserDTO userDTO) {
    log.debug("Request to save User : {}", userDTO);
    User user = mapper.toEntity(userDTO);
    user = repository.save(user);
    return mapper.toDto(user);
  }
}
