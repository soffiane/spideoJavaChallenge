package com.spideo.auction;

import com.spideo.auction.controller.AuctionHouseController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebAppConfiguration
@ContextConfiguration(classes = AuctionHouseController.class)
@WebMvcTest
public class AuctionHouseControllerTests extends AbstractJUnit4SpringContextTests {
   /* @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Configuration
    @EnableWebMvc
    public static class TestConfiguration {

        @Bean
        public AuctionHouseController auctionHouseController() {
            return new AuctionHouseController();
        }

    }

    @Test
    public void shouldCreateAuctionHouse() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String body = "{\n" +
                "\"auctionHouseName\":\"auctionHouse\"\n" +
                "}";

        MvcResult mvcResult = mockMvc.perform(post("")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(body))
                .andExpect(status().isOk())
                .andReturn();

        String resultDOW = mvcResult.getResponse().getContentAsString();
        assertNotNull(resultDOW);
    }*/

}
