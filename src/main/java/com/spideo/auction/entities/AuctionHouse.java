package com.spideo.auction.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Auction house.
 */
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

    /**
     * list of auctions occuring in this auction House
     */
    @JsonManagedReference(value = "auction")
    @OneToMany(mappedBy = "auctionHouse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Auction> auctions = new HashSet<>();

    /**
     * Add auction to the auction House.
     *
     * @param auction the auction
     */
    public void addAuction(final Auction auction) {
        auction.setAuctionHouse(this);
        auctions.add(auction);
    }

}
