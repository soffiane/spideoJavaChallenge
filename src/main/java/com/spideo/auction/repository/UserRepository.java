package com.spideo.auction.repository;

import com.spideo.auction.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    /**
     * Find user by name .
     *
     * @param userName the user name
     * @return the optional
     */
    @Query("from User u where u.userName=?1")
    Optional<User> findByName(String userName);
}
