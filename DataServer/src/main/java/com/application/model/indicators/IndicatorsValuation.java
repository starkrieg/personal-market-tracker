package com.application.model.indicators;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "INDICATORS_VALUATION")
public class IndicatorsValuation {

    @JsonUnwrapped
    @EmbeddedId
    IndicatorPK indicatorPK;

    /***
     * Using boxed Float
     * Numbers have decimal point, but only up to 3 digits,
     * Numbers can also be NULL
     * So boxed Float will be able to support the data
     */
    @Column(name = "DAY_VALUE")
    private Float dayValue;

    @Column(name = "DIV_YIELD")
    private Float divYield;

    @Column(name = "PRECO_OVER_LUCRO")
    private Float precoOverLucro;

    @Column(name = "PEG_RATIO")
    private Float pegRatio;

    @Column(name = "P_OVER_VP")
    private Float priceOverVp;

    @Column(name = "EV_OVER_EBITDA")
    private Float evOverEbitda;
    @Column(name = "EV_OVER_EBIT")
    private Float evOverEbit;
    @Column(name = "P_OVER_EBITDA")
    private Float priceOverEbitda;
    @Column(name = "P_OVER_EBIT")
    private Float priceOverEbit;

    @Column(name = "VPA")
    private Float vpa;
    @Column(name = "P_OVER_ATIVO")
    private Float priceOverAtivo;
    @Column(name = "LPA")
    private Float lpa;

    @Column(name = "P_OVER_SR")
    private Float priceOverSr;

    @Column(name = "P_OVER_CAP_GIRO")
    private Float priceOverCapGiro;

    @Column(name = "P_OVER_ATIVO_CIRC_LIQ")
    private Float priceOverAtivoCircLiq;

    public IndicatorsValuation() {
    }

    public IndicatorPK getIndicatorPK() {
        return indicatorPK;
    }

    public void setIndicatorPK(IndicatorPK indicatorPK) {
        this.indicatorPK = indicatorPK;
    }

    public Float getDivYield() {
        return divYield;
    }

    public void setDivYield(Float divYield) {
        this.divYield = divYield;
    }

    public Float getPrecoOverLucro() {
        return precoOverLucro;
    }

    public void setPrecoOverLucro(Float precoOverLucro) {
        this.precoOverLucro = precoOverLucro;
    }

    public Float getPegRatio() {
        return pegRatio;
    }

    public void setPegRatio(Float pegRatio) {
        this.pegRatio = pegRatio;
    }

    public Float getPriceOverVp() {
        return priceOverVp;
    }

    public void setPriceOverVp(Float priceOverVp) {
        this.priceOverVp = priceOverVp;
    }

    public Float getEvOverEbitda() {
        return evOverEbitda;
    }

    public void setEvOverEbitda(Float evOverEbitda) {
        this.evOverEbitda = evOverEbitda;
    }

    public Float getEvOverEbit() {
        return evOverEbit;
    }

    public void setEvOverEbit(Float evOverEbit) {
        this.evOverEbit = evOverEbit;
    }

    public Float getPriceOverEbitda() {
        return priceOverEbitda;
    }

    public void setPriceOverEbitda(Float priceOverEbitda) {
        this.priceOverEbitda = priceOverEbitda;
    }

    public Float getPriceOverEbit() {
        return priceOverEbit;
    }

    public void setPriceOverEbit(Float priceOverEbit) {
        this.priceOverEbit = priceOverEbit;
    }

    public Float getVpa() {
        return vpa;
    }

    public void setVpa(Float vpa) {
        this.vpa = vpa;
    }

    public Float getPriceOverAtivo() {
        return priceOverAtivo;
    }

    public void setPriceOverAtivo(Float priceOverAtivo) {
        this.priceOverAtivo = priceOverAtivo;
    }

    public Float getLpa() {
        return lpa;
    }

    public void setLpa(Float lpa) {
        this.lpa = lpa;
    }

    public Float getPriceOverSr() {
        return priceOverSr;
    }

    public void setPriceOverSr(Float priceOverSr) {
        this.priceOverSr = priceOverSr;
    }

    public Float getPriceOverCapGiro() {
        return priceOverCapGiro;
    }

    public void setPriceOverCapGiro(Float priceOverCapGiro) {
        this.priceOverCapGiro = priceOverCapGiro;
    }

    public Float getPriceOverAtivoCircLiq() {
        return priceOverAtivoCircLiq;
    }

    public void setPriceOverAtivoCircLiq(Float priceOverAtivoCircLiq) {
        this.priceOverAtivoCircLiq = priceOverAtivoCircLiq;
    }

    public Float getDayValue() {
        return dayValue;
    }

    public void setDayValue(Float dayValue) {
        this.dayValue = dayValue;
    }
}
