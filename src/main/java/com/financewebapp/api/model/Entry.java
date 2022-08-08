package com.financewebapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "AuthorId")
    private Long AuthorId;

    @Column(name = "CategoryId")
    private Long CategoryId;
}