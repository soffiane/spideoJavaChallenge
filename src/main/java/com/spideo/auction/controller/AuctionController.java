package com.spideo.auction.controller;

import com.spideo.auction.dto.AuctionDTO;
import com.spideo.auction.entities.Auction;
import com.spideo.auction.entities.AuctionHouse;
import com.spideo.auction.entities.Bid;
import com.spideo.auction.error.CustomErrorType;
import com.spideo.auction.repository.AuctionHouseRepository;
import com.spideo.auction.repository.AuctionRepository;
import com.spideo.auction.repository.BidRepository;
import com.spideo.auction.repository.UserRepository;
import com.spideo.auction.status.AuctionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * The type Auction controller.
 */
@RestController
@RequestMapping("/api/v1/auction")
public class AuctionController {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuctionHouseRepository auctionHouseRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Create auction on specified auctionHouse.
     *
     * @param auctionDTO the auction to be created
     * @return the auction created
     */
    @PostMapping("")
    public ResponseEntity createAuction(@RequestBody final AuctionDTO auctionDTO) {
        if (auctionDTO == null) {
            return ResponseEntity.badRequest().body("Cannot create auction with empty fields");
        }

        Optional<AuctionHouse> auctionHouseStored = auctionHouseRepository.findById(auctionDTO.getAuctionHouseId());
        if (!auctionHouseStored.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to create new auction. An auctionHouse with id " +
                    auctionDTO.getAuctionHouseId() + " does not exist."), HttpStatus.NOT_FOUND);
        }


        if (auctionRepository.findByName(auctionDTO.getAuctionName()).isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A auction with name " +
                    auctionDTO.getAuctionName() + " already exist."), HttpStatus.CONFLICT);
        }
        AuctionHouse auctionHouse = auctionHouseStored.get();
        Auction auction = new Auction();
        auction.setAuctionName(auctionDTO.getAuctionName());
        auction.setDescription(auctionDTO.getDescription());
        auction.setStartingTime(auctionDTO.getStartingTime());
        auction.setEndTime(auctionDTO.getEndTime());
        auction.setStartPrice(auctionDTO.getStartPrice());
        auction.setCurrentPrice(auctionDTO.getCurrentPrice());

        auctionHouse.addAuction(auction);

        Auction createdAuction = auctionRepository.save(auction);
        return new ResponseEntity<>(createdAuction, HttpStatus.CREATED);
    }

    /**
     * Retrieve all auction on selected auctionHouse.
     *
     * @param auctionHouseId the auction house id
     * @return the auctionHouse list
     */
    @GetMapping("/{auctionHouseId}")
    @ResponseBody
    public ResponseEntity getAllAuctionHouse(@PathVariable(value = "auctionHouseId") final Long auctionHouseId) {
        Optional<AuctionHouse> auctionHouse = auctionHouseRepository.findById(auctionHouseId);
        if (!auctionHouse.isPresent()) {
            return ResponseEntity.badRequest().body("Selected auction house with id " + auctionHouseId + " does not exist");
        }
        List<Auction> auctions = auctionRepository.findAllById(auctionHouse.get());
        if (auctions.isEmpty()) {
            return new ResponseEntity<Auction>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<Auction>>(auctions, HttpStatus.OK);
        }
    }

    /**
     * Delete auction on specified houseAuction.
     *
     * @param id the auction id
     * @return the http response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuction(@PathVariable(value = "id") final Long id) {
        Optional<Auction> auction =
                auctionRepository
                        .findById(id);
        if (auction.isPresent()) {
            auctionRepository.delete(auction.get());
            return ResponseEntity.ok("auction " + id + " successfully deleted");
        } else {
            return new ResponseEntity(new CustomErrorType("Unable to delete. An auction with id "
                    + id + " does not exist."), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update auction.
     *
     * @param auctionDTO the auction to be updated
     * @return the auction created
     */
    @PutMapping("")
    public ResponseEntity updateAuction(@RequestBody final AuctionDTO auctionDTO) {
        if (auctionDTO == null) {
            return ResponseEntity.badRequest().body("Cannot update auction with empty fields");
        }

        Optional<AuctionHouse> auctionHouseStored = auctionHouseRepository.findById(auctionDTO.getAuctionHouseId());
        if (!auctionHouseStored.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to create new auction. An auctionHouse with id " +
                    auctionDTO.getAuctionHouseId() + " does not exist."), HttpStatus.NOT_FOUND);
        }

        Optional<Auction> currentAuction = auctionRepository.findByName(auctionDTO.getAuctionName());
        if (currentAuction.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A auction with name " +
                    auctionDTO.getAuctionName() + " already exist."), HttpStatus.CONFLICT);
        }
        Auction auction = currentAuction.get();
        auction.setStartingTime(auctionDTO.getStartingTime());
        auction.setEndTime(auctionDTO.getEndTime());

        Auction createdAuction = auctionRepository.save(auction);
        return new ResponseEntity<>(createdAuction, HttpStatus.CREATED);
    }

    /**
     * Show the winner for a terminated auction.
     *
     * @param auctionId the auction id
     * @return the http response
     */
    @GetMapping("/winner/{auctionId}")
    public ResponseEntity showAuctionWinner(@PathVariable(value = "auctionId") Long auctionId) {
        ResponseEntity responseEntity = null;
        Optional<Auction> auction =
                auctionRepository
                        .findById(auctionId);
        //check if the auction exists
        if (auction.isPresent()) {
            Auction auctionFound = auction.get();
            //check if the auction is terminated
            if (AuctionStatus.TERMINATED.equals(auctionFound.getStatus())) {
                //retrieve the list of bids for this auction
                List<Bid> bids = bidRepository.findByAuctionId(auctionFound.getAuctionId());
                if (!bids.isEmpty()) {
                    //finding bid with higher price
                    Bid bid = bids.stream().max(Comparator.comparing(Bid::getBidPrice)).get();
                    responseEntity = ResponseEntity.ok("the winner of the auction " + auctionFound.getAuctionName() + " is " + bid.getUser().getUserName());
                } else {
                    responseEntity = new ResponseEntity(new CustomErrorType("There is no bids for the auction with the id "
                            + auctionId), HttpStatus.NOT_FOUND);
                }

            } else {
                responseEntity = new ResponseEntity(new CustomErrorType("An auction with id " +
                        auctionId + " is not terminated."), HttpStatus.BAD_REQUEST);
            }
        } else {
            responseEntity = new ResponseEntity(new CustomErrorType("An auction with id " +
                    auctionId + " does not exist."), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
