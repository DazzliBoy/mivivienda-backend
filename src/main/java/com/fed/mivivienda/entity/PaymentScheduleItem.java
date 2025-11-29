package com.fed.mivivienda.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "payment_schedule_items")
public class PaymentScheduleItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Loan loan;

    private Integer numeroCuota;
    private LocalDate fechaPago;

    private Double cuota;
    private Double interes;
    private Double amortizacion;
    private Double saldo;

    public PaymentScheduleItem(){}

    public Long getId(){ return id; }
    public Loan getLoan(){ return loan; }
    public void setLoan(Loan loan){ this.loan = loan; }
    public Integer getNumeroCuota(){ return numeroCuota; }
    public void setNumeroCuota(Integer numeroCuota){ this.numeroCuota = numeroCuota; }
    public LocalDate getFechaPago(){ return fechaPago; }
    public void setFechaPago(LocalDate fechaPago){ this.fechaPago = fechaPago; }
    public Double getCuota(){ return cuota; }
    public void setCuota(Double cuota){ this.cuota = cuota; }
    public Double getInteres(){ return interes; }
    public void setInteres(Double interes){ this.interes = interes; }
    public Double getAmortizacion(){ return amortizacion; }
    public void setAmortizacion(Double amortizacion){ this.amortizacion = amortizacion; }
    public Double getSaldo(){ return saldo; }
    public void setSaldo(Double saldo){ this.saldo = saldo; }
}
