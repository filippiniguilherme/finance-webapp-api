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
    private Long DebitId;

    @Column(name = "DebitName")
    private String DebitName;

    @Column(name = "DebitValue")
    private Double DebitValue;

    @CreationTimestamp
    @Column(name = "DebitDate")
    private Timestamp DebitDate;

    @Column(name = "DebitMonth")
    private Integer DebitMonth;

    @Column(name = "DebitYear")
    private Integer DebitYear;

    @Column(name = "AuthorId")
    private Long AuthorId;

    @Column(name = "CategoryId")
    private Long CategoryId;
}
