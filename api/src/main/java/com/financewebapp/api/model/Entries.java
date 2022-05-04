package com.financewebapp.api.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "entries")
@Data
public class Entries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Double value;
}
