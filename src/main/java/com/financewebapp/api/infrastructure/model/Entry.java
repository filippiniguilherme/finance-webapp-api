package com.financewebapp.api.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.sql.Timestamp;

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

    @OneToOne
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    private Entry parentId;

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
