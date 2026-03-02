package com.application.model.indicators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Date;

public class TestIndicatorsGrowth {

    @Test
    public void TestEmptyConstructor() {
        IndicatorsGrowth indicatorsGrowth = new IndicatorsGrowth();

        Assert.isNull(indicatorsGrowth.getIndicatorPK(), "Expected null when empty constructor");
        Assert.isNull(indicatorsGrowth.getCagrReceitas5Anos(), "Expected null when empty constructor");
        Assert.isNull(indicatorsGrowth.getCagrLucros5Anos(), "Expected null when empty constructor");
    }

    @Test
    public void TestFieldIndicatorPk() {
        long epochMillis = 1000000;
        Date storageDate = Date.from(Instant.ofEpochMilli(epochMillis));

        IndicatorPK indicatorPK = new IndicatorPK(
                "ticket-name",
                storageDate
        );

        // Indicator PK is tested by itself in another file

        IndicatorsGrowth indicatorsGrowth = new IndicatorsGrowth();
        Assert.isNull(indicatorsGrowth.getIndicatorPK(), "Expected null when empty constructor");

        indicatorsGrowth.setIndicatorPK(indicatorPK);
        Assert.isTrue(indicatorsGrowth.getIndicatorPK() != null, "Expected not null IndicatorPK");
        Assertions.assertEquals("ticket-name", indicatorsGrowth.getIndicatorPK().getTicketName());
        Assertions.assertEquals(storageDate, indicatorsGrowth.getIndicatorPK().getStorageDate());
    }

    @Test
    public void TestFieldCagrReceitas5Anos() {
        Float value = 0.1f;

        IndicatorsGrowth indicatorsGrowth = new IndicatorsGrowth();
        Assert.isNull(indicatorsGrowth.getCagrReceitas5Anos(), "Expected null when empty constructor");

        indicatorsGrowth.setCagrReceitas5Anos(value);
        Assertions.assertEquals(value, indicatorsGrowth.getCagrReceitas5Anos());
    }

    @Test
    public void TestFieldCagrLucros5Anos() {
        Float value = 0.1f;

        IndicatorsGrowth indicatorsGrowth = new IndicatorsGrowth();
        Assert.isNull(indicatorsGrowth.getCagrLucros5Anos(), "Expected null when empty constructor");

        indicatorsGrowth.setCagrLucros5Anos(value);
        Assertions.assertEquals(value, indicatorsGrowth.getCagrLucros5Anos());
    }

}
