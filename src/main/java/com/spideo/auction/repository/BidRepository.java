package com.spideo.auction.repository;

import com.spideo.auction.entities.Bid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The interface Bid repository.
 */
public interface BidRepository extends CrudRepository<Bid, Long> {
    @Override
    List<Bid> findAll();

    /**
     * Find bids by auction id.
     *
     * @param auctionId the auction id
     * @return the list
     */
    @Query("from Bid b where b.auction=?1")
    List<Bid> findByAuctionId(Long auctionId);
}
