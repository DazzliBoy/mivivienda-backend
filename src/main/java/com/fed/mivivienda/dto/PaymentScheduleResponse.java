package com.fed.mivivienda.dto;

import com.fed.mivivienda.entity.PaymentScheduleItem;

import java.util.List;

public class PaymentScheduleResponse {
    private Long loanId;
    private List<PaymentScheduleItem> items;

    public PaymentScheduleResponse(Long loanId, List<PaymentScheduleItem> items){
        this.loanId = loanId;
        this.items = items;
    }

    public Long getLoanId(){ return loanId; }
    public List<PaymentScheduleItem> getItems(){ return items; }
}
