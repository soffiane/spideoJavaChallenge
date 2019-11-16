package com.spideo.auction.controller;

import com.spideo.auction.dto.AuctionDTO;
import com.spideo.auction.entities.Auction;
import com.spideo.auction.entities.AuctionHouse;
import com.spideo.auction.error.CustomErrorType;
import com.spideo.auction.repository.IAuctionHouseRepository;
import com.spideo.auction.repository.IAuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Auction controller.
 */
@RestController
@RequestMapping("/api/v1/auction")
public class AuctionController {

    @Autowired
    private IAuctionRepository IAuctionRepository;

    @Autowired
    private IAuctionHouseRepository IAuctionHouseRepository;

    /**
     * Create auction on specified auctionHouse.
     *
     * @param auctionDTO the auction to be created
     * @return the auction created
     */
    @PostMapping("")
    public ResponseEntity createAuction(@RequestBody AuctionDTO auctionDTO) {
        if(auctionDTO == null){
            return ResponseEntity.badRequest().body("Cannot create auction with empty fields");
        }

        Optional<AuctionHouse> auctionHouseStored = IAuctionHouseRepository.findById(auctionDTO.getAuctionHouseId());
        if(!auctionHouseStored.isPresent()){
            return new ResponseEntity(new CustomErrorType("Unable to create new auction. An auctionHouse with id " +
                    auctionDTO.getAuctionHouseId() + " does not exist."), HttpStatus.NOT_FOUND);
        }

        if(IAuctionRepository.findByName(auctionDTO.getAuctionName()).isPresent()){
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

        Auction createdAuction = IAuctionRepository.save(auction);
        return new ResponseEntity<>(createdAuction,HttpStatus.CREATED);
    }

    /**
     * Retrieve all auction on selected auctionHouse.
     *
     * @param auctionHouseId the auction house id
     * @return the auctionHouse list
     */
    @GetMapping("/{auctionHouseId}")
    @ResponseBody
    public ResponseEntity getAllAuctionHouse(@PathVariable(value = "auctionHouseId") Long auctionHouseId) {
        Optional<AuctionHouse> auctionHouse = IAuctionHouseRepository.findById(auctionHouseId);
        if(!auctionHouse.isPresent()){
            return ResponseEntity.badRequest().body("Selected auction house with id "+auctionHouseId+" does not exist");
        }
        List<Auction> auctions = IAuctionRepository.findAllById(auctionHouse.get());
        if(auctions.isEmpty()){
            return new ResponseEntity<Auction>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<Auction>>(auctions,HttpStatus.OK);
        }
    }

    /**
     * Delete auction on specified houseAuction.
     *
     * @param id the auction id
     * @return the http response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuction(@PathVariable(value = "id") Long id) {
        Optional<Auction> auction =
                IAuctionRepository
                        .findById(id);
        if(auction.isPresent()){
            IAuctionRepository.delete(auction.get());
            return ResponseEntity.ok("auction "+id+" successfully deleted");
        } else {
            return new ResponseEntity(new CustomErrorType("Unable to delete. An auction with id " +
                    id + " does not exist."), HttpStatus.NOT_FOUND);
        }
    }
}
