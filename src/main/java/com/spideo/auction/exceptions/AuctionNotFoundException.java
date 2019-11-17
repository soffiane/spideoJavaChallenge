package com.spideo.auction.exceptions;

/**
 * Exception for auction not found cases.
 */
public class AuctionNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Auction not found exception.
     *
     * @param id the id
     */
    public AuctionNotFoundException(final Long id) {
        super("Auction with id " + id + " does not exist");
    }
}
