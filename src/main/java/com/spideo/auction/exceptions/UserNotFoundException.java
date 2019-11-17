package com.spideo.auction.exceptions;

/**
 * The type User not found exception.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Instantiates a new User not found exception.
     *
     * @param id the id
     */
    public UserNotFoundException(final Long id) {
        super("user with id " + id + " does not exist");
    }
}
