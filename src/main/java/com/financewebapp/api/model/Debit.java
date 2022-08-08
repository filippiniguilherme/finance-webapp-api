package com.financewebapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.type.TimestampType;

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

    @Column(name = "DebitDate")
    private TimestampType DebitDate;

    @Column(name = "DebitValue")
    private Double DebitValue;

    @Column(name = "AuthorId")
    private Long AuthorId;

    @Column(name = "TypeId")
    private Long TypeId;

    @Column(name = "CategoryId")
    private Long CategoryId;
}
