package com.spideo.auction.controller;

import com.spideo.auction.entities.AuctionHouse;
import com.spideo.auction.exception.CustomErrorType;
import com.spideo.auction.repository.AuctionHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AuctionController {

    @Autowired
    private AuctionHouseRepository studentRepository;

    /**
     * Create auctionHouse.
     *
     * @param name the auctionHouse name
     * @return the auctionHouse created
     */
    @PostMapping("/auctionHouse")
    public ResponseEntity<AuctionHouse> createAuctionHouse(String name) {
        if(studentRepository.findById(name).isPresent()){
            return new ResponseEntity(new CustomErrorType("Unable to create. A auctionHouse with name " +
                    name + " already exist."), HttpStatus.CONFLICT);
        }
        AuctionHouse auctionHouse = studentRepository.save(new AuctionHouse(name));
        return new ResponseEntity<AuctionHouse>(auctionHouse,HttpStatus.CREATED);
    }

    /**
     * Retrieve all auctionHouses.
     *
     * @return the auctionHouse list
     */
    @GetMapping("/auctionHouses")
    @ResponseBody
    public ResponseEntity<?> getAllAuctionHouse() {
        List<AuctionHouse> auctionHouses = studentRepository.findAll();
        if(auctionHouses.isEmpty()){
            return new ResponseEntity<AuctionHouse>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<AuctionHouse>>(auctionHouses,HttpStatus.OK);
        }
    }

    /**
     * Delete houseAuction.
     *
     * @param name the houseAuction name
     * @return the http response
     */
    @DeleteMapping("/auctionHouse/{name}")
    public ResponseEntity<?> deleteAuctionHouse(@PathVariable(value = "name") String name) throws Exception {
        Optional<AuctionHouse> auctionHouse =
                studentRepository
                        .findById(name);
        if(auctionHouse.isPresent()){
            studentRepository.delete(auctionHouse.get());
            return new ResponseEntity("auctionHouse successfully deleted",HttpStatus.OK);
        } else {
            return new ResponseEntity(new CustomErrorType("Unable to delete. A auctionHouse with name " +
                    name + " does not exist."), HttpStatus.NOT_FOUND);
        }
    }


}
