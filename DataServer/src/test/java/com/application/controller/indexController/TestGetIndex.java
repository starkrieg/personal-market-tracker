package com.application.controller.indexController;

import com.application.controller.IndexController;
import com.application.model.Ticket;
import com.application.repository.TicketRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestGetIndex {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private IndexController indexController;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void getIndexWithEmptyTickets() {
        Mockito.when(ticketRepository.findAll())
                .thenReturn(new ArrayList<>());

        // ConcurrentModel is one such object that implements the interface Model
        Model model = new ConcurrentModel();

        String response = indexController.getIndex(model);
        Assertions.assertEquals("index", response, "Response not expected value");

        Object modelTicketList = model.getAttribute("ticketList");

        Assert.isTrue(modelTicketList instanceof List<?>, "Expected attribute 'ticketList' to be a List");
        List<?> list = (List<?>) modelTicketList;
        Assert.isTrue(list.isEmpty(), "Expected 'ticketList' to be empty");
    }

    @Test
    public void getIndexWithTickets() {
        Mockito.when(ticketRepository.findAll())
                .thenReturn(Arrays.asList(new Ticket(), new Ticket(), new Ticket()));

        // ConcurrentModel is one such object that implements the interface Model
        Model model = new ConcurrentModel();

        String response = indexController.getIndex(model);
        Assertions.assertEquals("index", response, "Response not expected value");

        Object modelTicketList = model.getAttribute("ticketList");

        Assert.isTrue(modelTicketList instanceof List<?>, "Expected attribute 'ticketList' to be a List");
        List<?> list = (List<?>) modelTicketList;
        Assertions.assertEquals(3, list.size(), "'ticketList' of size different than expected");
    }
}
