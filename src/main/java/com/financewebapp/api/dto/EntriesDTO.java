package com.financewebapp.api.dto;

import com.financewebapp.api.model.Entry;
import com.financewebapp.api.model.EntryDetail;

import lombok.Data;

import java.util.List;

@Data
public class EntriesDTO {
    private EntryDetail detail;
    private List<Entry> entries;
}