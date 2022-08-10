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
    private Long EntryId;

    @Column(name = "EntryName")
    private String EntryName;

    @Column(name = "EntryValue")
    private Float EntryValue;

    @CreationTimestamp
    @Column(name = "EntryDate")
    private Timestamp EntryDate;

    @Column(name = "EntryMonth")
    private Integer EntryMonth;

    @Column(name = "EntryYear")
    private Integer EntryYear;

    @Column(name = "AuthorId")
    private Long AuthorId;

    @Column(name = "CategoryId")
    private Long CategoryId;
}
