package com.financewebapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "Debits")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Debit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Double value;

    @CreationTimestamp
    @Column(name = "dateCreated")
    private Timestamp dateCreated;

    @Column(name = "date")
    private String date;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @OneToOne
    @JoinColumn(name = "authorId", referencedColumnName = "AuthorId")
    private Author author;

    @OneToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Category category;    
}
