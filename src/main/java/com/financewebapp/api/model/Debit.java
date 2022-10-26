package com.financewebapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "Debits")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Debit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Double value;

    @CreationTimestamp
    @Column(name = "date")
    private Timestamp date;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "authorId")
    private Long authorId;

    @Column(name = "categoryId")
    private Long categoryId;
}
