package com.spideo.auction.dto;

import com.spideo.auction.entities.Auction;
import lombok.Data;

import java.util.Set;

@Data
public class AuctionHouseDTO {

    private String name;

    private Set<Auction> auctions;
}
