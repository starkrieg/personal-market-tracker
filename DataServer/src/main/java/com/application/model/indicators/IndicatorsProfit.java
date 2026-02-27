package com.application.model.indicators;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "INDICATORS_PROFIT")
public class IndicatorsProfit {

    @JsonUnwrapped
    @EmbeddedId
    IndicatorPK indicatorPK;

    /***
     * Using boxed Float
     * Numbers have decimal point, but only up to 3 digits,
     * Numbers can also be NULL
     * So boxed Float will be able to support the data
     */
    @Column(name = "ROE")
    private Float roe;

    @Column(name = "ROA")
    private Float roa;

    @Column(name = "ROIC")
    private Float roic;

    @Column(name = "GIRO_ATIVOS")
    private Float giroAtivos;

    public IndicatorsProfit() {
    }

    public IndicatorPK getIndicatorPK() {
        return indicatorPK;
    }

    public void setIndicatorPK(IndicatorPK indicatorPK) {
        this.indicatorPK = indicatorPK;
    }

    public Float getRoe() {
        return roe;
    }

    public void setRoe(Float roe) {
        this.roe = roe;
    }

    public Float getRoa() {
        return roa;
    }

    public void setRoa(Float roa) {
        this.roa = roa;
    }

    public Float getRoic() {
        return roic;
    }

    public void setRoic(Float roic) {
        this.roic = roic;
    }

    public Float getGiroAtivos() {
        return giroAtivos;
    }

    public void setGiroAtivos(Float giroAtivos) {
        this.giroAtivos = giroAtivos;
    }
}
