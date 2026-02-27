package com.application.model.indicators;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "INDICATORS_GROWTH")
public class IndicatorsGrowth {

    @JsonUnwrapped
    @EmbeddedId
    IndicatorPK indicatorPK;

    /***
     * Using boxed Float
     * Numbers have decimal point, but only up to 3 digits,
     * Numbers can also be NULL
     * So boxed Float will be able to support the data
     */
    @Column(name = "CAGR_RECEITAS_5_ANOS")
    private Float cagrReceitas5Anos;

    @Column(name = "CAGR_LUCROS_5_ANOS")
    private Float cagrLucros5Anos;

    public IndicatorsGrowth() {
    }

    public IndicatorPK getIndicatorPK() {
        return indicatorPK;
    }

    public void setIndicatorPK(IndicatorPK indicatorPK) {
        this.indicatorPK = indicatorPK;
    }

    public Float getCagrReceitas5Anos() {
        return cagrReceitas5Anos;
    }

    public void setCagrReceitas5Anos(Float cagrReceitas5Anos) {
        this.cagrReceitas5Anos = cagrReceitas5Anos;
    }

    public Float getCagrLucros5Anos() {
        return cagrLucros5Anos;
    }

    public void setCagrLucros5Anos(Float cagrLucros5Anos) {
        this.cagrLucros5Anos = cagrLucros5Anos;
    }
}
