package com.application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TICKET")
public class Ticket {

    @Id
    @Column(name = "NAME", nullable = false)
    private String name;

    public Ticket() {
    }

    public Ticket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
