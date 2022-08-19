package com.financewebapp.api.model;

import lombok.Data;

@Data
public class Balance {
    private Double balanceValue;
    private Double totalDebits;
    private Number countDebits;
    private Double totalEntries;
    private Number countEntries;
    private Number month;
    private Number year;
}
