package com.coordinator.api.coordinator.bid.controller;

import com.coordinate.model.bids.BidPostRequest;
import com.coordinate.model.coordinator.CoordinatorBidDto;
import com.coordinate.security.repository.AuthAuthUserRepositoryImpl;
import com.coordinate.security.util.JwtUtil;
import com.coordinator.api.coordinator.bid.service.ICoordinatorBid;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static com.coordinator.api.general.main.helpers.QueryOptionsHelper.fromQueryParameters;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CoordinatorBidController.class)
public class CoordinatorBidTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICoordinatorBid iCoordinatorBid;

    @MockBean
    private AuthAuthUserRepositoryImpl authAuthUserRepository;

    @MockBean
    private JwtUtil jwtUtil;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(value = "spring")
    @Test
    public void shouldFetchCoordinatorBids() throws Exception {
        var coordinatorId = UUID.randomUUID();
        var url = String.format("/api/v1/coordinators/%s/bids", coordinatorId);

        Map<String, String> dummyQueryOptions = new HashMap<String, String>();
        dummyQueryOptions.put("page", "1");

        when(iCoordinatorBid.getAllCoordinatorsBids(UUID.randomUUID(), fromQueryParameters(dummyQueryOptions)))
                .thenReturn(dummyCoordinatorBidDtoList());

        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "spring", roles={"COORDINATOR"})
    @Test
    public void shouldCreateBid() throws Exception {
        var coordinatorId = UUID.randomUUID();
        var url = String.format("/api/v1/coordinators/%s/bids", coordinatorId);
        var bidPostRequest = new BidPostRequest(
                UUID.randomUUID(),
                "foo",
                "foo",
                "foo",
                100);

        doNothing().when(iCoordinatorBid).createBid(coordinatorId, bidPostRequest);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(bidPostRequest)))
                .andExpect(status().isOk());
    }

    private List<CoordinatorBidDto> dummyCoordinatorBidDtoList() {
        var dummyList = new ArrayList<CoordinatorBidDto>();
        var mockCoordinatorBidDto = new CoordinatorBidDto();

        mockCoordinatorBidDto.setBidAmount(200);
        mockCoordinatorBidDto.setBidStatusId(1);
        mockCoordinatorBidDto.setCoordinatorId(UUID.randomUUID());
        mockCoordinatorBidDto.setId(UUID.randomUUID());

        dummyList.add(mockCoordinatorBidDto);

        return dummyList;
    }
}
