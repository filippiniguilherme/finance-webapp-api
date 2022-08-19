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
    private Long debitId;

    @Column(name = "DebitName")
    private String debitName;

    @Column(name = "DebitValue")
    private Double debitValue;

    @CreationTimestamp
    @Column(name = "DebitDate")
    private Timestamp debitDate;

    @Column(name = "DebitMonth")
    private Integer debitMonth;

    @Column(name = "DebitYear")
    private Integer debitYear;

    @Column(name = "AuthorId")
    private Long authorId;

    @Column(name = "CategoryId")
    private Long categoryId;
}
