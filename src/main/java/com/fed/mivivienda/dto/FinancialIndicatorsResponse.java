package com.fed.mivivienda.dto;

public class FinancialIndicatorsResponse {
    private Double van;
    private Double tir;

    public FinancialIndicatorsResponse(Double van, Double tir){
        this.van = van;
        this.tir = tir;
    }

    public Double getVan(){ return van; }
    public Double getTir(){ return tir; }
}
