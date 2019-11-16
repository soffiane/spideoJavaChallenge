package com.spideo.auction.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "auctionId")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Auction implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "auctionId", nullable = false, unique = true)
    private Long auctionId;

    @Column(name = "auctionName", nullable = false, unique = true)
    private String auctionName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "startingTime", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startingTime;

    @Column(name = "endTime", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    @Column(name = "startPrice", nullable = false)
    private BigDecimal startPrice;

    @Column(name = "currentPrice", nullable = false)
    private BigDecimal currentPrice;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name="idAuctionHouse")
    private AuctionHouse auctionHouse;

}
