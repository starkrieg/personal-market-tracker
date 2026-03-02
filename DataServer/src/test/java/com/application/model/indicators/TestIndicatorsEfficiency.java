package com.application.model.indicators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Date;

public class TestIndicatorsEfficiency {

    @Test
    public void TestEmptyConstructor() {
        IndicatorsEfficiency IndicatorsEfficiency = new IndicatorsEfficiency();

        Assert.isNull(IndicatorsEfficiency.getIndicatorPK(), "Expected null when empty constructor");
        Assert.isNull(IndicatorsEfficiency.getMargemBruta(), "Expected null when empty constructor");
        Assert.isNull(IndicatorsEfficiency.getMargemEbitda(), "Expected null when empty constructor");
        Assert.isNull(IndicatorsEfficiency.getMargemEbit(), "Expected null when empty constructor");
        Assert.isNull(IndicatorsEfficiency.getMargemLiq(), "Expected null when empty constructor");
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

        IndicatorsEfficiency indicatorsEfficiency = new IndicatorsEfficiency();
        Assert.isNull(indicatorsEfficiency.getIndicatorPK(), "Expected null when empty constructor");

        indicatorsEfficiency.setIndicatorPK(indicatorPK);
        Assert.isTrue(indicatorsEfficiency.getIndicatorPK() != null, "Expected not null IndicatorPK");
        Assertions.assertEquals("ticket-name", indicatorsEfficiency.getIndicatorPK().getTicketName());
        Assertions.assertEquals(storageDate, indicatorsEfficiency.getIndicatorPK().getStorageDate());
    }

    @Test
    public void TestFieldMargemBruta() {
        Float value = 0.1f;

        IndicatorsEfficiency IndicatorsEfficiency = new IndicatorsEfficiency();
        Assert.isNull(IndicatorsEfficiency.getMargemBruta(), "Expected null when empty constructor");

        IndicatorsEfficiency.setMargemBruta(value);
        Assertions.assertEquals(value, IndicatorsEfficiency.getMargemBruta());
    }

    @Test
    public void TestFieldMargemEbitda() {
        Float value = 1.1f;

        IndicatorsEfficiency IndicatorsEfficiency = new IndicatorsEfficiency();
        Assert.isNull(IndicatorsEfficiency.getMargemEbitda(), "Expected null when empty constructor");

        IndicatorsEfficiency.setMargemEbitda(value);
        Assertions.assertEquals(value, IndicatorsEfficiency.getMargemEbitda());
    }

    @Test
    public void TestFieldMargemEbit() {
        Float value = 2.1f;

        IndicatorsEfficiency IndicatorsEfficiency = new IndicatorsEfficiency();
        Assert.isNull(IndicatorsEfficiency.getMargemEbit(), "Expected null when empty constructor");

        IndicatorsEfficiency.setMargemEbit(value);
        Assertions.assertEquals(value, IndicatorsEfficiency.getMargemEbit());
    }

    @Test
    public void TestFieldMargemLiq() {
        Float value = 2.1f;

        IndicatorsEfficiency IndicatorsEfficiency = new IndicatorsEfficiency();
        Assert.isNull(IndicatorsEfficiency.getMargemLiq(), "Expected null when empty constructor");

        IndicatorsEfficiency.setMargemLiq(value);
        Assertions.assertEquals(value, IndicatorsEfficiency.getMargemLiq());
    }

}
