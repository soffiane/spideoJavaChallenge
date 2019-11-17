package com.spideo.auction.repository;

import com.spideo.auction.entities.AuctionHouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * The Auction house repository.
 */
public interface AuctionHouseRepository extends CrudRepository<AuctionHouse, Long> {
    @Override
    List<AuctionHouse> findAll();

    /**
     * Find auctionHouse by name.
     *
     * @param name the auctionHouse name
     * @return the auctionHouse or nothing
     */
    @Query("from AuctionHouse a where a.auctionHouseName=?1")
    Optional<AuctionHouse> findByName(String name);
}
