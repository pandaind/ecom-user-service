package com.example.demo.user.service;

import com.example.demo.user.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.example.demo.user.domain.User}
 */
public interface UserService {
    /**
     * Get all the Users
     *
     * @return the list of entity
     */
    List<UserDTO> findAll();

    /**
     * Get the "id" user
     *
     * @param id the id for the entity
     * @return the entity
     */
    Optional<UserDTO> findOne(Long id);

    /**
     * Get the "name" user
     *
     * @param userName the name for the entity
     * @return the entity
     */
    Optional<UserDTO> findOneByName(String userName);

    /**
     * Save a user
     *
     * @param user the entity to save
     * @return the persisted entity
     */
    UserDTO save(UserDTO user);
}
