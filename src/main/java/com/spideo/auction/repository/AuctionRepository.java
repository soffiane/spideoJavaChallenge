package com.spideo.auction.repository;

import com.spideo.auction.entities.Auction;
import com.spideo.auction.entities.AuctionHouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Auction repository.
 */
public interface AuctionRepository extends CrudRepository<Auction, Long> {

    /**
     * Find all by id list.
     *
     * @param auctionHouseId the auction house id
     * @return the list
     */
    @Query(value = "from Auction a where a.auctionHouse = ?1")
    List<Auction> findAllById(AuctionHouse auctionHouseId);

    /**
     * Find auction by name.
     *
     * @param name the name
     * @return the optional
     */
    @Query(value = "from Auction a where a.auctionName=?1")
    Optional<Auction> findByName(String name);

}
