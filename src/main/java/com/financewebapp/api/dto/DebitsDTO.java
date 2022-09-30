package com.financewebapp.api.dto;

import com.financewebapp.api.model.Debit;
import com.financewebapp.api.model.DebitDetail;

import lombok.Data;

import java.util.List;

@Data
public class DebitsDTO {
    private DebitDetail detail;
    private List<Debit> debits;
}