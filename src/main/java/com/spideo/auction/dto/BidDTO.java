package com.spideo.auction.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BidDTO {

    private BigDecimal bidPrice;

    private Long userId;

    private Long auctionId;

}
