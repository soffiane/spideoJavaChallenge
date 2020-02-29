package com.spideo.auction.controller;

import com.spideo.auction.dto.AuctionHouseDTO;
import com.spideo.auction.entities.AuctionHouse;
import com.spideo.auction.error.CustomErrorType;
import com.spideo.auction.repository.AuctionHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Auction house controller.
 */
@RestController
@RequestMapping("/api/v1/auctionHouse")
public class AuctionHouseController {

    @Autowired
    private AuctionHouseRepository auctionHouseRepository;

    /**
     * Create auctionHouse.
     *
     * @param auctionHouseDTO the auctionHouse to be created
     * @return the auctionHouse created
     */
    @PostMapping("")
    public ResponseEntity createAuctionHouse(@RequestBody final AuctionHouseDTO auctionHouseDTO) {
        if (auctionHouseDTO == null) {
            return ResponseEntity.badRequest().body("Cannot create auction House with empty fields");
        }

        if (auctionHouseRepository.findByName(auctionHouseDTO.getName()).isPresent()) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A auctionHouse with name "
                    + auctionHouseDTO.getName() + " already exist."), HttpStatus.CONFLICT);
        }

        AuctionHouse auctionHouse = new AuctionHouse();
        auctionHouse.setAuctionHouseName(auctionHouseDTO.getName());
        auctionHouse.setAuctions(auctionHouseDTO.getAuctions());

        AuctionHouse auctionHouseCreated = auctionHouseRepository.save(auctionHouse);
        return new ResponseEntity<AuctionHouse>(auctionHouseCreated, HttpStatus.CREATED);
    }

    /**
     * Retrieve all auctionHouses.
     *
     * @return the auctionHouse list
     */
    @GetMapping("")
    @ResponseBody
    public ResponseEntity getAllAuctionHouse() {
        ResponseEntity result;
        List<AuctionHouse> auctionHouses = auctionHouseRepository.findAll();
        if (auctionHouses.isEmpty()) {
            result = ResponseEntity.noContent().build();
        } else {
            result = ResponseEntity.ok(auctionHouses);
        }
        return result;
    }

    /**
     * Delete houseAuction.
     *
     * @param id the houseAuction id
     * @return the http response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuctionHouse(@PathVariable(value = "id") final Long id) {
        ResponseEntity result;
        Optional<AuctionHouse> auctionHouse =
                auctionHouseRepository
                        .findById(id);
        if (auctionHouse.isPresent()) {
            auctionHouseRepository.delete(auctionHouse.get());
            result = ResponseEntity.ok("auctionHouse " + id + " successfully deleted");
        } else {
            result = new ResponseEntity<>(new CustomErrorType("Unable to delete. An auctionHouse with id "
                    + id + " does not exist."), HttpStatus.NOT_FOUND);
        }
        return result;
    }
}
