package com.coordinator.api.coordinator.event.controller;

import com.coordinate.model.CoordinatorEventDto;
import com.coordinate.security.repository.AuthAuthUserRepositoryImpl;
import com.coordinate.security.util.JwtUtil;
import com.coordinator.api.coordinator.event.service.ICoordinatorEvents;
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
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CoordinatorEventController.class)
public class CoordinatorEventTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICoordinatorEvents iCoordinatorEvents;

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
    public void shouldFetchCoordinatorEvents() throws Exception {
        var coordinatorId = UUID.randomUUID();
        var url = String.format("/api/v1/coordinators/%s/events", coordinatorId);

        Map<String, String> dummyQueryOptions = new HashMap<String, String>();
        dummyQueryOptions.put("page", "1");

        when(iCoordinatorEvents.getAllCoordinatorEventsByZipCode(UUID.randomUUID(), fromQueryParameters(dummyQueryOptions)))
                .thenReturn(dummyCoordinatorEventDtoList());

        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private List<CoordinatorEventDto> dummyCoordinatorEventDtoList() {
        var dummyList = new ArrayList<CoordinatorEventDto>();
        var mockCoordinatorEventDto = new CoordinatorEventDto();

        mockCoordinatorEventDto.setUserId(UUID.randomUUID());
        mockCoordinatorEventDto.setAdditionalUserComments("foo");
        mockCoordinatorEventDto.setArchived(false);
        mockCoordinatorEventDto.setCoordinatorId(UUID.randomUUID());
        mockCoordinatorEventDto.setDesiredCity("foo");
        mockCoordinatorEventDto.setDesiredServiceId(1);

        dummyList.add(mockCoordinatorEventDto);

        return dummyList;
    }
}
