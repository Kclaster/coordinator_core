package com.coordinator.core.coordinator.bid.controller;

import com.coordinator.core.auth.config.ApplicationSecurityConfig;
import com.coordinator.core.auth.service.AuthUserServiceImpl;
import com.coordinator.core.coordinator.bid.service.IBid;
import com.coordinator.core.coordinator.bid.models.BidDto;
import com.coordinator.core.general.main.models.QueryOptions;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.coordinator.core.auth.JwtFixtures.validToken;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BidController.class)
public class BidControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private IBid iBid;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Before
    public void startMocks(){
       var controller = webApplicationContext.getBean(BidController.class);

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .alwaysDo(print())
                .apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
                .build();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetAllCoordinatorsBids() throws Exception {
        UUID id = UUID.randomUUID();

        when(iBid.getAllCoordinatorsBids(id, new QueryOptions() {
        }))
                .thenReturn(dummyBids());


        mockMvc.perform(get("/api/v1/coordinators/{coordinatorId}/bids")
            .header("Authorization ","Bearer " + validToken(3))
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    private List<BidDto> dummyBids() {
        List<BidDto> results = new ArrayList<>();

        BidDto result = new BidDto();
        result.setId(UUID.fromString("429283da-0bbc-4f57-ae26-7bcda701fa77"));
        result.setBidAmount(25);
        result.setBidStatusId(1);
        result.setCoordinatorId(UUID.fromString("d3ad08cd-163b-4b35-b278-d257daabf586"));
        result.setMessageToUser("message foo-bar");
        results.add(result);

        return results;
    }
}
