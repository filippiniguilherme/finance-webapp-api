package com.financewebapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "Debits")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Debit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Value")
    private Double value;

    @CreationTimestamp
    @Column(name = "Date")
    private Timestamp date;

    @Column(name = "Month")
    private Integer month;

    @Column(name = "Year")
    private Integer year;

    @Column(name = "AuthorId")
    private Long authorId;

    @Column(name = "CategoryId")
    private Long categoryId;
}
