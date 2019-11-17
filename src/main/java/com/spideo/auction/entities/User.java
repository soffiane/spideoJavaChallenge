package com.spideo.auction.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The type User.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @JsonManagedReference(value = "user")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Bid> currentBids = new HashSet<>();

    /**
     * Add bid.
     *
     * @param bid the bid
     */
    public void addBid(final Bid bid) {
        bid.setUser(this);
        currentBids.add(bid);
    }
}
