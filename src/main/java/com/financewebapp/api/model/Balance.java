package com.financewebapp.api.model;

import lombok.Data;

@Data
public class Balance {
    private Double BalanceValue;
    private Double TotalDebits;
    private Number CountDebits;
    private Double TotalEntries;
    private Number CountEntries;
    private Number Month;
    private Number Year;
}
