package com.financewebapp.api.infrastructure.model;

import lombok.Data;

@Data
public class Balance {
    private DebitDetail debitDetail;
    private EntryDetail entryDetail;
    private Double balanceValue;
    private Number month;
    private Number year;
}
