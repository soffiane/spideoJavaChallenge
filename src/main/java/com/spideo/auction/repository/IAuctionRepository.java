package com.spideo.auction.repository;

import com.spideo.auction.entities.Auction;
import com.spideo.auction.entities.AuctionHouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IAuctionRepository extends CrudRepository<Auction,Long> {

    @Query(value = "from Auction a where a.auctionHouse = ?1")
    List<Auction> findAllById(AuctionHouse auctionHouseId);

    @Query(value = "from Auction a where a.auctionName=?1")
    Optional<Auction> findByName(String name);
}
