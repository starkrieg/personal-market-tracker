package com.application.model.indicators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Date;

public class TestIndicatorPK {

    @Test
    public void TestEmptyConstructor() {
        IndicatorPK indicatorPK = new IndicatorPK();

        Assert.isNull(indicatorPK.getTicketName(), "Expected empty with empty constructor");
        Assert.isNull(indicatorPK.getStorageDate(), "Expected empty with empty constructor");
    }

    @Test
    public void TestFullConstructor() {
        long epochMillis = 1000000;
        Date storageDate = Date.from(Instant.ofEpochMilli(epochMillis));
        IndicatorPK indicatorPK = new IndicatorPK(
                "ticket-name",
                storageDate
        );

        Assertions.assertEquals("ticket-name", indicatorPK.getTicketName());
        Assertions.assertEquals(Date.from(Instant.ofEpochMilli(epochMillis)), indicatorPK.getStorageDate());
    }

    @Test
    public void TestFieldTicketName() {
        IndicatorPK indicatorPK = new IndicatorPK();

        Assert.isNull(indicatorPK.getTicketName(), "Expected empty with empty constructor");

        indicatorPK.setTicketName("ticket-name");

        Assertions.assertEquals("ticket-name", indicatorPK.getTicketName());
    }

    @Test
    public void TestFieldStorageDate() {
        long epochMillis = 1000000;
        Date storageDate = Date.from(Instant.ofEpochMilli(epochMillis));

        IndicatorPK indicatorPK = new IndicatorPK();

        Assert.isNull(indicatorPK.getStorageDate(), "Expected empty with empty constructor");

        indicatorPK.setStorageDate(storageDate);

        Assertions.assertEquals(Date.from(Instant.ofEpochMilli(epochMillis)), indicatorPK.getStorageDate());
    }

    // TODO - add test to validate json serialization

}
