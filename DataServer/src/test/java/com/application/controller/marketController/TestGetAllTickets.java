package com.application.controller.marketController;

import com.application.controller.MarketController;
import com.application.model.Ticket;
import com.application.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestGetAllTickets {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private IndicatorsDebtRepository indicatorsDebtRepository;

    @Mock
    private IndicatorsEfficiencyRepository indicatorsEfficiencyRepository;

    @Mock
    private IndicatorsGrowthRepository indicatorsGrowthRepository;

    @Mock
    private IndicatorsProfitRepository indicatorsProfitRepository;

    @Mock
    private IndicatorsValuationRepository indicatorsValuationRepository;

    @InjectMocks
    private MarketController marketController;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void getAllTicketsReturnOkAndEmpty() {
        // Stub fixed empty inside the marketController.getAllTickets
        Mockito.when(ticketRepository.findAll()).thenReturn(new ArrayList<>());

        // Avoid using 'var' to check for proper response objects
        ResponseEntity<List<Ticket>> response = marketController.getAllTickers();

        // expected value comes first
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Response HTTP Status different than expected");
        Assertions.assertEquals(0, response.getBody().size(), "Response Ticket List size different than expected");
    }

    @Test
    public void getAllTicketsReturnOkWithValue() {
        // Stub fixed list size inside the marketController.getAllTickets
        Mockito.when(ticketRepository.findAll()).thenReturn(Arrays.asList(new Ticket(), new Ticket(), new Ticket()));

        // Avoid using 'var' to check for proper response objects
        ResponseEntity<List<Ticket>> response = marketController.getAllTickers();

        // expected value comes first
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Response HTTP Status different than expected");
        Assertions.assertEquals(3, response.getBody().size(),"Response Ticket List size different than expected");
    }

    // TODO - add getAllTickets test for Exception validation

}
