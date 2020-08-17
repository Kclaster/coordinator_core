package com.coordinator.api.coordinator.main.controller;

import com.coordinate.model.CoordinatorDto;
import com.coordinate.model.CoordinatorPutRequest;
import com.coordinate.security.repository.AuthAuthUserRepositoryImpl;
import com.coordinate.security.util.JwtUtil;
import com.coordinator.api.coordinator.main.service.ICoordinator;
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

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CoordinatorController.class)
public class CoordinatorTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICoordinator iCoordinator;

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
    public void shouldFetchCoordinator() throws Exception {
        var coordinatorId = UUID.randomUUID();
        var url = String.format("/api/v1/coordinators/%s", coordinatorId);

        when(iCoordinator.getCoordinator(coordinatorId))
                .thenReturn(dummyCoordinatorDto());

        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "spring", roles={"COORDINATOR"})
    @Test
    public void shouldUpdateCoordinator() throws Exception {
        var coordinatorId = UUID.randomUUID();
        var url = String.format("/api/v1/coordinators/%s/update", coordinatorId);
        var coordinatorPutRequest = new CoordinatorPutRequest(
                UUID.randomUUID(),
                "foo",
                "bar",
                "foobar",
                "12345",
                "foobar",
                "foo@gmail.com",
                1,
                100,
                150,
                200);

        when(iCoordinator.getCoordinator(coordinatorId))
                .thenReturn(dummyCoordinatorDto());

        when(iCoordinator.updateCoordinator(coordinatorId, coordinatorPutRequest))
                .thenReturn(dummyCoordinatorDto());
        ObjectMapper mapper = new ObjectMapper();
// TODO: you are here, you didn't add the request body (idiot)
        mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(coordinatorPutRequest)))
                .andExpect(status().isOk());
    }

    private CoordinatorDto dummyCoordinatorDto() {
        var mockCoordinatorDto = new CoordinatorDto();

        mockCoordinatorDto.setContactEmail("foo");
        mockCoordinatorDto.setArchived(false);

        return mockCoordinatorDto;
    }
}
