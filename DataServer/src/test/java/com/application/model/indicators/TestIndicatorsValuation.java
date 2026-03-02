package com.application.model.indicators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Date;

public class TestIndicatorsValuation {

    @Test
    public void TestEmptyConstructor() {
        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();

        Assert.isNull(indicatorsValuation.getIndicatorPK(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getDivYield(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getPrecoOverLucro(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getPegRatio(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getpOverVp(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getEvOverEbitda(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getEvOverEbit(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getpOverEbitda(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getpOverEbit(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getVpa(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getpOverAtivo(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getLpa(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getpOverSr(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getpOverCapGiro(), "Expected null when empty constructor");
        Assert.isNull(indicatorsValuation.getpOverAtivoCircLiq(), "Expected null when empty constructor");
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

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getIndicatorPK(), "Expected null when empty constructor");

        indicatorsValuation.setIndicatorPK(indicatorPK);
        Assert.isTrue(indicatorsValuation.getIndicatorPK() != null, "Expected not null IndicatorPK");
        Assertions.assertEquals("ticket-name", indicatorsValuation.getIndicatorPK().getTicketName());
        Assertions.assertEquals(storageDate, indicatorsValuation.getIndicatorPK().getStorageDate());
    }

    @Test
    public void TestFieldDivYield() {
        Float value = 0.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getDivYield(), "Expected null when empty constructor");

        indicatorsValuation.setDivYield(value);
        Assertions.assertEquals(value, indicatorsValuation.getDivYield());
    }

    @Test
    public void TestFieldPrecoOverLucro() {
        Float value = 1.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getPrecoOverLucro(), "Expected null when empty constructor");

        indicatorsValuation.setPrecoOverLucro(value);
        Assertions.assertEquals(value, indicatorsValuation.getPrecoOverLucro());
    }

    @Test
    public void TestFieldPegRatio() {
        Float value = 2.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getPegRatio(), "Expected null when empty constructor");

        indicatorsValuation.setPegRatio(value);
        Assertions.assertEquals(value, indicatorsValuation.getPegRatio());
    }

    @Test
    public void TestFieldPOverVp() {
        Float value = 3.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getpOverVp(), "Expected null when empty constructor");

        indicatorsValuation.setpOverVp(value);
        Assertions.assertEquals(value, indicatorsValuation.getpOverVp());
    }

    @Test
    public void TestFieldEvOverEbitda() {
        Float value = 4.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getEvOverEbitda(), "Expected null when empty constructor");

        indicatorsValuation.setEvOverEbitda(value);
        Assertions.assertEquals(value, indicatorsValuation.getEvOverEbitda());
    }

    @Test
    public void TestFieldEvOverEbit() {
        Float value = 5.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getEvOverEbit(), "Expected null when empty constructor");

        indicatorsValuation.setEvOverEbit(value);
        Assertions.assertEquals(value, indicatorsValuation.getEvOverEbit());
    }

    @Test
    public void TestFieldPOverEbitda() {
        Float value = 6.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getpOverEbitda(), "Expected null when empty constructor");

        indicatorsValuation.setpOverEbitda(value);
        Assertions.assertEquals(value, indicatorsValuation.getpOverEbitda());
    }

    @Test
    public void TestFieldPOverEbit() {
        Float value = 7.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getpOverEbit(), "Expected null when empty constructor");

        indicatorsValuation.setpOverEbit(value);
        Assertions.assertEquals(value, indicatorsValuation.getpOverEbit());
    }

    @Test
    public void TestFieldVpa() {
        Float value = 8.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getVpa(), "Expected null when empty constructor");

        indicatorsValuation.setVpa(value);
        Assertions.assertEquals(value, indicatorsValuation.getVpa());
    }

    @Test
    public void TestFieldPOverAtivo() {
        Float value = 9.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getpOverAtivo(), "Expected null when empty constructor");

        indicatorsValuation.setpOverAtivo(value);
        Assertions.assertEquals(value, indicatorsValuation.getpOverAtivo());
    }

    @Test
    public void TestFieldLpa() {
        Float value = 10.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getLpa(), "Expected null when empty constructor");

        indicatorsValuation.setLpa(value);
        Assertions.assertEquals(value, indicatorsValuation.getLpa());
    }

    @Test
    public void TestFieldPOverSr() {
        Float value = 11.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getpOverSr(), "Expected null when empty constructor");

        indicatorsValuation.setpOverSr(value);
        Assertions.assertEquals(value, indicatorsValuation.getpOverSr());
    }

    @Test
    public void TestFieldPOverCapGiro() {
        Float value = 12.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getpOverCapGiro(), "Expected null when empty constructor");

        indicatorsValuation.setpOverCapGiro(value);
        Assertions.assertEquals(value, indicatorsValuation.getpOverCapGiro());
    }

    @Test
    public void TestFieldPOverAtivoCirqLiq() {
        Float value = 13.1f;

        IndicatorsValuation indicatorsValuation = new IndicatorsValuation();
        Assert.isNull(indicatorsValuation.getpOverAtivoCircLiq(), "Expected null when empty constructor");

        indicatorsValuation.setpOverAtivoCircLiq(value);
        Assertions.assertEquals(value, indicatorsValuation.getpOverAtivoCircLiq());
    }

}
