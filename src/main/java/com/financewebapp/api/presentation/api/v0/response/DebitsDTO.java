package com.financewebapp.api.presentation.api.v0.response;

import com.financewebapp.api.infrastructure.model.Debit;
import com.financewebapp.api.infrastructure.model.DebitDetail;
import lombok.Data;

import java.util.List;

@Data
public class DebitsDTO {

    private DebitDetail detail;

    private List<Debit> debits;

}