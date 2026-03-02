package com.application.model.indicators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Date;

public class TestIndicatorsDebt {

    @Test
    public void TestEmptyConstructor() {
        IndicatorsDebt indicatorsDebt = new IndicatorsDebt();

        Assert.isNull(indicatorsDebt.getIndicatorPK(), "Expected null when empty constructor");
        Assert.isNull(indicatorsDebt.getDivLiqOverPl(), "Expected null when empty constructor");
        Assert.isNull(indicatorsDebt.getDivLiqOverEbit(), "Expected null when empty constructor");
        Assert.isNull(indicatorsDebt.getPlOverAtivos(), "Expected null when empty constructor");
        Assert.isNull(indicatorsDebt.getPassOverAtivos(), "Expected null when empty constructor");
        Assert.isNull(indicatorsDebt.getLiqCorrente(), "Expected null when empty constructor");
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

        IndicatorsDebt indicatorsDebt = new IndicatorsDebt();
        Assert.isNull(indicatorsDebt.getIndicatorPK(), "Expected null when empty constructor");

        indicatorsDebt.setIndicatorPK(indicatorPK);
        Assert.isTrue(indicatorsDebt.getIndicatorPK() != null, "Expected not null IndicatorPK");
        Assertions.assertEquals("ticket-name", indicatorsDebt.getIndicatorPK().getTicketName());
        Assertions.assertEquals(storageDate, indicatorsDebt.getIndicatorPK().getStorageDate());
    }

    @Test
    public void TestFieldDivLiqOverPl() {
        Float value = 0.1f;

        IndicatorsDebt indicatorsDebt = new IndicatorsDebt();
        Assert.isNull(indicatorsDebt.getDivLiqOverPl(), "Expected null when empty constructor");

        indicatorsDebt.setDivLiqOverPl(value);
        Assertions.assertEquals(value, indicatorsDebt.getDivLiqOverPl());
    }

    @Test
    public void TestFieldDivLiqOverEbit() {
        Float value = 1.1f;

        IndicatorsDebt indicatorsDebt = new IndicatorsDebt();
        Assert.isNull(indicatorsDebt.getDivLiqOverEbit(), "Expected null when empty constructor");

        indicatorsDebt.setDivLiqOverEbit(value);
        Assertions.assertEquals(value, indicatorsDebt.getDivLiqOverEbit());
    }

    @Test
    public void TestFieldPlOverAtivos() {
        Float value = 2.1f;

        IndicatorsDebt indicatorsDebt = new IndicatorsDebt();
        Assert.isNull(indicatorsDebt.getPlOverAtivos(), "Expected null when empty constructor");

        indicatorsDebt.setPlOverAtivos(value);
        Assertions.assertEquals(value, indicatorsDebt.getPlOverAtivos());
    }

    @Test
    public void TestFieldPassOverAtivos() {
        Float value = 3.1f;

        IndicatorsDebt indicatorsDebt = new IndicatorsDebt();
        Assert.isNull(indicatorsDebt.getPassOverAtivos(), "Expected null when empty constructor");

        indicatorsDebt.setPassOverAtivos(value);
        Assertions.assertEquals(value, indicatorsDebt.getPassOverAtivos());
    }

    @Test
    public void TestFieldLiqCorrente() {
        Float value = 4.1f;

        IndicatorsDebt indicatorsDebt = new IndicatorsDebt();
        Assert.isNull(indicatorsDebt.getLiqCorrente(), "Expected null when empty constructor");

        indicatorsDebt.setLiqCorrente(value);
        Assertions.assertEquals(value, indicatorsDebt.getLiqCorrente());
    }

}
