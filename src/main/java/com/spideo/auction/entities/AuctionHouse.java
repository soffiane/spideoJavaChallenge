package com.spideo.auction.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class AuctionHouse {

    @Id
    private String name;
    /*@Column(name = "auctions")
    @ElementCollection(targetClass=Auction.class)
    private List<Auction> auctions;*/

    public AuctionHouse() {
    }

    public AuctionHouse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }*/
}
