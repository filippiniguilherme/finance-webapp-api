package com.financewebapp.api.presentation.api.v0.response;

import com.financewebapp.api.infrastructure.model.Entry;
import com.financewebapp.api.infrastructure.model.EntryDetail;
import lombok.Data;

import java.util.List;

@Data
public class EntriesDTO {

    private EntryDetail detail;

    private List<Entry> entries;

}