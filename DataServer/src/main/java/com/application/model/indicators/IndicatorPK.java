package com.application.model.indicators;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Date;

@Embeddable
public class IndicatorPK {

    @JsonIgnore
    @Column(name = "TICKET_NAME", nullable = false)
    String ticketName;

    @Column(name = "STORAGE_DATE", nullable = false)
    Date storageDate;

    public IndicatorPK(String ticketName, Date storageDate) {
        this.ticketName = ticketName;
        this.storageDate = storageDate;
    }

    public IndicatorPK() {
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public Date getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
    }
}
