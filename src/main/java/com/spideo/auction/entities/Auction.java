package com.spideo.auction.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spideo.auction.status.AuctionStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Auction.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auction implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "auctionId", nullable = false, unique = true)
    private Long auctionId;

    @Column(name = "auctionName", nullable = false, unique = true)
    private String auctionName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "startingTime")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startingTime;

    @Column(name = "endTime")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date endTime;

    @Column(name = "startPrice", nullable = false)
    private BigDecimal startPrice;

    @Column(name = "currentPrice")
    private BigDecimal currentPrice;

    /**
     * the auction House where the auction occurs
     */
    @JsonBackReference(value = "auction")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idAuctionHouse")
    private AuctionHouse auctionHouse;

    /**
     * the list of bids for an auction (a RUNNONG one)
     */
    @JsonManagedReference(value = "bid")
    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Bid> currentBids = new HashSet<>();

    /**
     * Gets status.
     *
     * @return the auction status
     */
    @JsonIgnore
    public AuctionStatus getStatus() {
        final Date now = new Date();
        AuctionStatus status = null;
        if (getStartingTime() == null) {
            status = AuctionStatus.NOT_STARTED;
        } else if (getStartingTime() != null && getEndTime() == null && getStartingTime().before(now)) {
            status = AuctionStatus.RUNNING;
        } else if (getEndTime() != null && getEndTime().before(now)) {
            status = AuctionStatus.TERMINATED;
        }
        return status;
    }
}
