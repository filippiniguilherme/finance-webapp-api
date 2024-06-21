package com.financewebapp.api.infrastructure.model;

import lombok.Data;

@Data
public class EntryBalance {
    private Double balanceValue;
    private Double totalEntries;
    private Number countEntries;
    private Number month;
    private Number year;
}
