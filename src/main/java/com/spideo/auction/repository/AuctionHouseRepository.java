package com.spideo.auction.repository;

import com.spideo.auction.entities.AuctionHouse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuctionHouseRepository extends CrudRepository<AuctionHouse,String> {
    @Override
    List<AuctionHouse> findAll();
}
