package com.spideo.auction.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The type Auction dto.
 */
@Data
public class AuctionDTO {

    private String auctionName;

    private String description;

    private Date startingTime;

    private Date endTime;

    private BigDecimal startPrice;

    private BigDecimal currentPrice;

    private long auctionHouseId;
}
