package com.spideo.auction;

import com.spideo.auction.entities.Auction;
import com.spideo.auction.entities.AuctionHouse;
import com.spideo.auction.entities.Bid;
import com.spideo.auction.entities.User;
import com.spideo.auction.repository.AuctionHouseRepository;
import com.spideo.auction.repository.AuctionRepository;
import com.spideo.auction.repository.BidRepository;
import com.spideo.auction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

@SpringBootApplication
public class AuctionApplication {

	//definir un traitement a executer au demarrage de l'application
	@Autowired
	private AuctionHouseRepository auctionHouseRepository;
	@Autowired
	private AuctionRepository auctionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BidRepository bidRepository;

	@Component
	class DataSetup implements ApplicationRunner{

		@Override
		public void run(ApplicationArguments args) throws Exception {
			AuctionHouse auctionHouse1 = auctionHouseRepository.save(AuctionHouse.builder()
					.auctionHouseId(1L)
					.auctionHouseName("auctionHouse1")
					.build());

			Auction auction = auctionRepository.save(Auction.builder()
					.auctionHouse(auctionHouse1)
					.auctionId(1L)
					.auctionName("auction1")
					.description("premiere enchere")
					.currentPrice(new BigDecimal(10))
					.startingTime(new Date())
					.startPrice(new BigDecimal(10))
					.build());

			User user = userRepository.save(User.builder()
					.currentBids(new HashSet<>())
					.userName("soffiane")
					.build());

			Bid bid = bidRepository.save(Bid.builder()
					.auction(auction)
					.bidPrice(new BigDecimal(11))
					.id(1L)
					.user(user)
					.build());
			user.addBid(bid);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(AuctionApplication.class, args);
	}

}
