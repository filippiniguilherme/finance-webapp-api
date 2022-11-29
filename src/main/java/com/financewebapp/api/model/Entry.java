package com.financewebapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "Entries")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Entry {
    @Id
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
    @JoinColumn(name = "categoryId", referencedColumnName = "CategoryId")
    private Category category;
}
