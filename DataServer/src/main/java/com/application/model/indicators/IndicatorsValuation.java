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
    @Column(name = "DIV_YIELD")
    private Float divYield;

    @Column(name = "PRECO_OVER_LUCRO")
    private Float precoOverLucro;

    @Column(name = "PEG_RATIO")
    private Float pegRatio;

    @Column(name = "P_OVER_VP")
    private Float pOverVp;

    @Column(name = "EV_OVER_EBITDA")
    private Float evOverEbitda;
    @Column(name = "EV_OVER_EBIT")
    private Float evOverEbit;
    @Column(name = "P_OVER_EBITDA")
    private Float pOverEbitda;
    @Column(name = "P_OVER_EBIT")
    private Float pOverEbit;

    @Column(name = "VPA")
    private Float vpa;
    @Column(name = "P_OVER_ATIVO")
    private Float pOverAtivo;
    @Column(name = "LPA")
    private Float lpa;

    @Column(name = "P_OVER_SR")
    private Float pOverSr;

    @Column(name = "P_OVER_CAP_GIRO")
    private Float pOverCapGiro;

    @Column(name = "P_OVER_ATIVO_CIRC_LIQ")
    private Float pOverAtivoCircLiq;

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

    public Float getpOverVp() {
        return pOverVp;
    }

    public void setpOverVp(Float pOverVp) {
        this.pOverVp = pOverVp;
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

    public Float getpOverEbitda() {
        return pOverEbitda;
    }

    public void setpOverEbitda(Float pOverEbitda) {
        this.pOverEbitda = pOverEbitda;
    }

    public Float getpOverEbit() {
        return pOverEbit;
    }

    public void setpOverEbit(Float pOverEbit) {
        this.pOverEbit = pOverEbit;
    }

    public Float getVpa() {
        return vpa;
    }

    public void setVpa(Float vpa) {
        this.vpa = vpa;
    }

    public Float getpOverAtivo() {
        return pOverAtivo;
    }

    public void setpOverAtivo(Float pOverAtivo) {
        this.pOverAtivo = pOverAtivo;
    }

    public Float getLpa() {
        return lpa;
    }

    public void setLpa(Float lpa) {
        this.lpa = lpa;
    }

    public Float getpOverSr() {
        return pOverSr;
    }

    public void setpOverSr(Float pOverSr) {
        this.pOverSr = pOverSr;
    }

    public Float getpOverCapGiro() {
        return pOverCapGiro;
    }

    public void setpOverCapGiro(Float pOverCapGiro) {
        this.pOverCapGiro = pOverCapGiro;
    }

    public Float getpOverAtivoCircLiq() {
        return pOverAtivoCircLiq;
    }

    public void setpOverAtivoCircLiq(Float pOverAtivoCircLiq) {
        this.pOverAtivoCircLiq = pOverAtivoCircLiq;
    }
}
