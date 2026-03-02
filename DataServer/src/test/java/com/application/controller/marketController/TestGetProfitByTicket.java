package com.application.controller.marketController;

import com.application.controller.MarketController;
import com.application.model.indicators.IndicatorsProfit;
import com.application.repository.IndicatorsProfitRepository;
import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.util.Strings;
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
import java.util.Optional;

public class TestGetProfitByTicket {

    @Mock
    private IndicatorsProfitRepository indicatorsProfitRepository;

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
    public void getProfitByTickerReturnOkAndEmpty() {
        // Stub fixed empty inside the marketController.getProfitByTicker
        Mockito.when(indicatorsProfitRepository.findAllByTicketAndRange(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(new ArrayList<>());

        try {
            // Avoid using 'var' to check for proper response objects
            ResponseEntity<List<IndicatorsProfit>> response = marketController.getProfitByTicket("ticket-name", Optional.empty());

            // expected value comes first
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Response HTTP Status different than expected");
            Assertions.assertEquals(0, response.getBody().size(), "Response Indicators Profit List size different than expected");
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail("Got exception when not expected");
        }
    }

    @Test
    public void getProfitByTickerReturnOkWithValue() {
        // Stub fixed empty inside the marketController.getProfitByTicker
        Mockito.when(indicatorsProfitRepository.findAllByTicketAndRange(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(Arrays.asList(new IndicatorsProfit(), new IndicatorsProfit(), new IndicatorsProfit()));

        try {
            // Avoid using 'var' to check for proper response objects
            ResponseEntity<List<IndicatorsProfit>> response = marketController.getProfitByTicket("ticket-name", Optional.empty());

            // expected value comes first
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Response HTTP Status different than expected");
            Assertions.assertEquals(3, response.getBody().size(), "Response Indicators Profit List size different than expected");
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail("Got exception when not expected");
        }
    }

    @Test
    public void getProfitByTickerReturnBadRequestException_NullTicketName() {
        // Stub fixed empty inside the marketController.getProfitByTicker
        Mockito.when(indicatorsProfitRepository.findAllByTicketAndRange(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(new ArrayList<>());

        try {
            // Avoid using 'var' to check for proper response objects
            ResponseEntity<List<IndicatorsProfit>> response = marketController.getProfitByTicket(null, Optional.empty());

            // expected value comes first
            Assertions.fail("Expected BadRequestException, but got none");
        } catch (BadRequestException ex) {
            Assertions.assertTrue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail("Expected BadRequestException, but got a different Exception");
        }
    }

    @Test
    public void getProfitByTickerReturnBadRequestException_EmptyTicketName() {
        // Stub fixed empty inside the marketController.getProfitByTicker
        Mockito.when(indicatorsProfitRepository.findAllByTicketAndRange(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(new ArrayList<>());

        try {
            // Avoid using 'var' to check for proper response objects
            ResponseEntity<List<IndicatorsProfit>> response = marketController.getProfitByTicket(Strings.EMPTY, Optional.empty());

            // expected value comes first
            Assertions.fail("Expected BadRequestException, but got none");
        } catch (BadRequestException ex) {
            Assertions.assertTrue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail("Expected BadRequestException, but got a different Exception");
        }
    }

    @Test
    public void getProfitByTickerReturnBadRequestException_ZeroDayRange() {
        // Stub fixed empty inside the marketController.getProfitByTicker
        Mockito.when(indicatorsProfitRepository.findAllByTicketAndRange(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(new ArrayList<>());

        try {
            // Avoid using 'var' to check for proper response objects
            ResponseEntity<List<IndicatorsProfit>> response = marketController.getProfitByTicket("ticket-name", Optional.of(0));

            // expected value comes first
            Assertions.fail("Expected BadRequestException, but got none");
        } catch (BadRequestException ex) {
            Assertions.assertTrue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail("Expected BadRequestException, but got a different Exception");
        }
    }

    @Test
    public void getProfitByTickerReturnBadRequestException_NegativeDayRange() {
        // Stub fixed empty inside the marketController.getProfitByTicker
        Mockito.when(indicatorsProfitRepository.findAllByTicketAndRange(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(new ArrayList<>());

        try {
            // Avoid using 'var' to check for proper response objects
            ResponseEntity<List<IndicatorsProfit>> response = marketController.getProfitByTicket("ticket-name", Optional.of(-1));

            // expected value comes first
            Assertions.fail("Expected BadRequestException, but got none");
        } catch (BadRequestException ex) {
            Assertions.assertTrue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail("Expected BadRequestException, but got a different Exception");
        }
    }

}
