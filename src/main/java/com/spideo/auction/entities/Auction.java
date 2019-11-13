package com.spideo.auction.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/*@Entity
@Table(name = "auction")
@EntityListeners(AuditingEntityListener.class)*/
public class Auction {
    /*@Id
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "startingTime", nullable = false)
    private Date startingTime;
    @Column(name = "endTime", nullable = false)
    private Date endTime;
    @Column(name = "startPrice", nullable = false)
    private BigDecimal startPrice;
    @Column(name = "currentPrice", nullable = false)
    private BigDecimal currentPrice;

    public Auction(String name, String description, Date startingTime, Date endTime, BigDecimal startPrice, BigDecimal currentPrice) {
        this.name = name;
        this.description = description;
        this.startingTime = startingTime;
        this.endTime = endTime;
        this.startPrice = startPrice;
        this.currentPrice = currentPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }*/
}
