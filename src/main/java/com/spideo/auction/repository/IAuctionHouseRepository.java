package com.spideo.auction.repository;

import com.spideo.auction.entities.AuctionHouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IAuctionHouseRepository extends CrudRepository<AuctionHouse,Long> {
    @Override
    List<AuctionHouse> findAll();

    @Query("from AuctionHouse a where a.auctionHouseName=?1")
    Optional<AuctionHouse> findByName(String name);
}
