package com.spideo.auction.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionHouse implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "auctionHouseId", nullable = false, unique = true)
    private long auctionHouseId;

    @Column(name = "auctionHouseName", nullable = false, unique = true)
    private String auctionHouseName;

    @JsonManagedReference
    @OneToMany(mappedBy = "auctionHouse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Auction> auctions = new HashSet<>();

    public void addAuction(Auction auction){
        auction.setAuctionHouse(this);
        auctions.add(auction);
    }

}
