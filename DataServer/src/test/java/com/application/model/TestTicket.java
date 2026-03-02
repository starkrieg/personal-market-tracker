package com.application.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class TestTicket {

    // Test basic structure of the model

    @Test
    public void testTicketConstructorEmpty() {
        Ticket tic = new Ticket();

        // Empty constructor must exist
        Assert.isNull(tic.getName(), "Ticket constructor not empty");
    }

    @Test
    public void testTicketConstructorWithName() {
        Ticket tic = new Ticket("name");

        // expected value comes first
        Assertions.assertEquals("name", tic.getName(),  "Ticket constructor not setting 'name'");
    }

    @Test
    public void testTicketFieldName() {
        Ticket tic = new Ticket();
        Assert.isNull(tic.getName(), "Ticket name has value");

        tic.setName("name");

        // expected value comes first
        Assertions.assertEquals("name", tic.getName(), "Ticket 'name' is different than expected");
    }

}
