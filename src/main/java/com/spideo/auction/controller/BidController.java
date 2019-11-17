package com.spideo.auction.controller;

import com.spideo.auction.dto.BidDTO;
import com.spideo.auction.entities.Auction;
import com.spideo.auction.entities.Bid;
import com.spideo.auction.entities.User;
import com.spideo.auction.exceptions.AuctionNotFoundException;
import com.spideo.auction.exceptions.UserNotFoundException;
import com.spideo.auction.repository.AuctionRepository;
import com.spideo.auction.repository.BidRepository;
import com.spideo.auction.repository.UserRepository;
import com.spideo.auction.status.AuctionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Bid controller.
 */
@RestController
@RequestMapping("/api/v1/bid")
public class BidController {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Create a bid on an auction by a registered user.
     *
     * @param bidDTO the user to be created
     * @return the user created
     */
    @PostMapping("")
    public ResponseEntity createBid(@RequestBody BidDTO bidDTO) {
        //check if auctionId exists
        Auction auction = auctionRepository.findById(bidDTO.getAuctionId()).orElseThrow(() -> new AuctionNotFoundException(bidDTO.getAuctionId()));

        //check if user bidding exists
        User user = userRepository.findById(bidDTO.getUserId()).orElseThrow(() -> new UserNotFoundException(bidDTO.getUserId()));

        //check if bid price is higher than current auction price
        int diff = bidDTO.getBidPrice().compareTo(auction.getCurrentPrice());

        //check if auction is started : starting time is filled with a past date and end time empty
        AuctionStatus status = auction.getStatus();
        ResponseEntity responseEntity = null;
        if (diff > 0 && AuctionStatus.RUNNING.equals(status)) {
            Bid bid = new Bid();
            bid.setAuction(auction);
            bid.setBidPrice(bidDTO.getBidPrice());
            user.addBid(bid);
            Bid savedBid = bidRepository.save(bid);
            auction.setCurrentPrice(bidDTO.getBidPrice());
            auctionRepository.save(auction);
            responseEntity = ResponseEntity.ok(savedBid);
        } else {
            responseEntity = ResponseEntity.badRequest().body(diff <= 0 ? "current price is higher than your bid" : "auction is not in running state");
        }
        return responseEntity;
    }

    /**
     * Retrieve all bids.
     *
     * @return the bids list
     */
    @GetMapping("")
    public ResponseEntity getAllBids() {
        ResponseEntity responseEntity = null;
        List<Bid> bids = bidRepository.findAll();
        if (bids.isEmpty()) {
            responseEntity = ResponseEntity.noContent().build();
        } else {
            responseEntity = ResponseEntity.ok(bids);
        }
        return responseEntity;
    }

}
