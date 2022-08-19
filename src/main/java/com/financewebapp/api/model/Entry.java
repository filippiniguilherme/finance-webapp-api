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
    private Long entryId;

    @Column(name = "EntryName")
    private String entryName;

    @Column(name = "EntryValue")
    private Float entryValue;

    @CreationTimestamp
    @Column(name = "EntryDate")
    private Timestamp entryDate;

    @Column(name = "EntryMonth")
    private Integer entryMonth;

    @Column(name = "EntryYear")
    private Integer entryYear;

    @Column(name = "AuthorId")
    private Long authorId;

    @Column(name = "CategoryId")
    private Long categoryId;
}
