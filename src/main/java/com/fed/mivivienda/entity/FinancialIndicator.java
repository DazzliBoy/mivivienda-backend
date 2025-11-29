package com.fed.mivivienda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "financial_indicators")
public class FinancialIndicator {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Loan loan;

    private Double van;
    private Double tir;

    public FinancialIndicator(){}

    public Long getId(){ return id; }
    public Loan getLoan(){ return loan; }
    public void setLoan(Loan loan){ this.loan = loan; }
    public Double getVan(){ return van; }
    public void setVan(Double van){ this.van = van; }
    public Double getTir(){ return tir; }
    public void setTir(Double tir){ this.tir = tir; }
}
