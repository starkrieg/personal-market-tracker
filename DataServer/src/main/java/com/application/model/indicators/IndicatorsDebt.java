package com.application.model.indicators;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "INDICATORS_DEBT")

public class IndicatorsDebt {

    @JsonUnwrapped
    @EmbeddedId
    IndicatorPK indicatorPK;

    /***
     * Using boxed Float
     * Numbers have decimal point, but only up to 3 digits,
     * Numbers can also be NULL
     * So boxed Float will be able to support the data
     */
    @Column(name = "DIV_LIQ_OVER_PL")
    Float divLiqOverPl;

    @Column(name = "DIV_LIQ_OVER_EBIT")
    Float divLiqOverEbit;

    @Column(name = "PL_OVER_ATIVOS")
    Float plOverAtivos;

    @Column(name = "PASS_OVER_ATIVOS")
    Float passOverAtivos;

    @Column(name = "LIQ_CORRENTE")
    Float liqCorrente;

    public IndicatorsDebt() {
    }

    public IndicatorsDebt(IndicatorPK indicatorPK, Float divLiqOverPl, Float divLiqOverEbit, Float plOverAtivos, Float passOverAtivos, Float liqCorrente) {
        this.indicatorPK = indicatorPK;
        this.divLiqOverPl = divLiqOverPl;
        this.divLiqOverEbit = divLiqOverEbit;
        this.plOverAtivos = plOverAtivos;
        this.passOverAtivos = passOverAtivos;
        this.liqCorrente = liqCorrente;
    }

    public IndicatorPK getIndicatorPK() {
        return indicatorPK;
    }

    public void setIndicatorPK(IndicatorPK indicatorPK) {
        this.indicatorPK = indicatorPK;
    }

    public Float getDivLiqOverPl() {
        return divLiqOverPl;
    }

    public void setDivLiqOverPl(Float divLiqOverPl) {
        this.divLiqOverPl = divLiqOverPl;
    }

    public Float getDivLiqOverEbit() {
        return divLiqOverEbit;
    }

    public void setDivLiqOverEbit(Float divLiqOverEbit) {
        this.divLiqOverEbit = divLiqOverEbit;
    }

    public Float getPlOverAtivos() {
        return plOverAtivos;
    }

    public void setPlOverAtivos(Float plOverAtivos) {
        this.plOverAtivos = plOverAtivos;
    }

    public Float getPassOverAtivos() {
        return passOverAtivos;
    }

    public void setPassOverAtivos(Float passOverAtivos) {
        this.passOverAtivos = passOverAtivos;
    }

    public Float getLiqCorrente() {
        return liqCorrente;
    }

    public void setLiqCorrente(Float liqCorrente) {
        this.liqCorrente = liqCorrente;
    }
}
