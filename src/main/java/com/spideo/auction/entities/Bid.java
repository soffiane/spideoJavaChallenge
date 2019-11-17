package com.spideo.auction.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * The type Bid.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bid {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private BigDecimal bidPrice;

    /**
     * the id of the user who did this bid.
     */
    @JsonBackReference(value = "user")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser")
    private User user;

    /**
     * the id of the auction on which the user is bidding.
     */
    @JsonBackReference(value = "bid")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idAuction")
    private Auction auction;
}
