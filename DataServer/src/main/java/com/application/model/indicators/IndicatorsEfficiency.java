package com.application.model.indicators;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "INDICATORS_EFFICIENCY")
public class IndicatorsEfficiency {

    @JsonUnwrapped
    @EmbeddedId
    IndicatorPK indicatorPK;

    /***
     * Using boxed Float
     * Numbers have decimal point, but only up to 3 digits,
     * Numbers can also be NULL
     * So boxed Float will be able to support the data
     */
    @Column(name = "MARGEM_BRUTA")
    private Float margemBruta;

    @Column(name = "MARGEM_EBITDA")
    private Float margemEbitda;

    @Column(name = "MARGEM_EBIT")
    private Float margemEbit;

    @Column(name = "MARGEM_LIQ")
    private Float margemLiq;

    public IndicatorsEfficiency() {
    }

    public IndicatorPK getIndicatorPK() {
        return indicatorPK;
    }

    public void setIndicatorPK(IndicatorPK indicatorPK) {
        this.indicatorPK = indicatorPK;
    }

    public Float getMargemBruta() {
        return margemBruta;
    }

    public void setMargemBruta(Float margemBruta) {
        this.margemBruta = margemBruta;
    }

    public Float getMargemEbitda() {
        return margemEbitda;
    }

    public void setMargemEbitda(Float margemEbitda) {
        this.margemEbitda = margemEbitda;
    }

    public Float getMargemEbit() {
        return margemEbit;
    }

    public void setMargemEbit(Float margemEbit) {
        this.margemEbit = margemEbit;
    }

    public Float getMargemLiq() {
        return margemLiq;
    }

    public void setMargemLiq(Float margemLiq) {
        this.margemLiq = margemLiq;
    }
}
