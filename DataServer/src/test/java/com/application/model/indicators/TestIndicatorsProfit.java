package com.application.model.indicators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Date;

public class TestIndicatorsProfit {

    @Test
    public void TestEmptyConstructor() {
        IndicatorsProfit indicatorsProfit = new IndicatorsProfit();

        Assert.isNull(indicatorsProfit.getIndicatorPK(), "Expected null when empty constructor");
        Assert.isNull(indicatorsProfit.getRoe(), "Expected null when empty constructor");
        Assert.isNull(indicatorsProfit.getRoa(), "Expected null when empty constructor");
        Assert.isNull(indicatorsProfit.getRoic(), "Expected null when empty constructor");
        Assert.isNull(indicatorsProfit.getGiroAtivos(), "Expected null when empty constructor");
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

        IndicatorsProfit indicatorsProfit = new IndicatorsProfit();
        Assert.isNull(indicatorsProfit.getIndicatorPK(), "Expected null when empty constructor");

        indicatorsProfit.setIndicatorPK(indicatorPK);
        Assert.isTrue(indicatorsProfit.getIndicatorPK() != null, "Expected not null IndicatorPK");
        Assertions.assertEquals("ticket-name", indicatorsProfit.getIndicatorPK().getTicketName());
        Assertions.assertEquals(storageDate, indicatorsProfit.getIndicatorPK().getStorageDate());
    }

    @Test
    public void TestFieldRoe() {
        Float value = 0.1f;

        IndicatorsProfit indicatorsProfit = new IndicatorsProfit();
        Assert.isNull(indicatorsProfit.getRoe(), "Expected null when empty constructor");

        indicatorsProfit.setRoe(value);
        Assertions.assertEquals(value, indicatorsProfit.getRoe());
    }

    @Test
    public void TestFieldRoa() {
        Float value = 1.1f;

        IndicatorsProfit indicatorsProfit = new IndicatorsProfit();
        Assert.isNull(indicatorsProfit.getRoa(), "Expected null when empty constructor");

        indicatorsProfit.setRoa(value);
        Assertions.assertEquals(value, indicatorsProfit.getRoa());
    }

    @Test
    public void TestFieldRoic() {
        Float value = 2.1f;

        IndicatorsProfit indicatorsProfit = new IndicatorsProfit();
        Assert.isNull(indicatorsProfit.getRoic(), "Expected null when empty constructor");

        indicatorsProfit.setRoic(value);
        Assertions.assertEquals(value, indicatorsProfit.getRoic());
    }

    @Test
    public void TestFieldGiroAtivos() {
        Float value = 3.1f;

        IndicatorsProfit indicatorsProfit = new IndicatorsProfit();
        Assert.isNull(indicatorsProfit.getGiroAtivos(), "Expected null when empty constructor");

        indicatorsProfit.setGiroAtivos(value);
        Assertions.assertEquals(value, indicatorsProfit.getGiroAtivos());
    }

}
