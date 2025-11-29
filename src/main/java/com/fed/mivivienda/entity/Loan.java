package com.fed.mivivienda.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Operation operation;

    @Column(nullable=false)
    private Double monto;           // principal

    @Column(nullable=false)
    private String moneda;          // PEN o USD

    @Column(nullable=false)
    private Double tasaAnual;       // nominal o efectiva anual

    @Column(nullable=false)
    private String tipoTasa;        // "EFECTIVA" o "NOMINAL"

    private Integer capitalizacionPorAnio; // si NOMINAL: 12, 360, etc.

    @Column(nullable=false)
    private Integer plazoMeses;

    private Integer periodoGraciaMeses; // total o parcial (aquí modelamos total)

    private LocalDate fechaInicio;

    public Loan(){}

    public Long getId(){ return id; }
    public Operation getOperation(){ return operation; }
    public void setOperation(Operation operation){ this.operation = operation; }
    public Double getMonto(){ return monto; }
    public void setMonto(Double monto){ this.monto = monto; }
    public String getMoneda(){ return moneda; }
    public void setMoneda(String moneda){ this.moneda = moneda; }
    public Double getTasaAnual(){ return tasaAnual; }
    public void setTasaAnual(Double tasaAnual){ this.tasaAnual = tasaAnual; }
    public String getTipoTasa(){ return tipoTasa; }
    public void setTipoTasa(String tipoTasa){ this.tipoTasa = tipoTasa; }
    public Integer getCapitalizacionPorAnio(){ return capitalizacionPorAnio; }
    public void setCapitalizacionPorAnio(Integer capitalizacionPorAnio){ this.capitalizacionPorAnio = capitalizacionPorAnio; }
    public Integer getPlazoMeses(){ return plazoMeses; }
    public void setPlazoMeses(Integer plazoMeses){ this.plazoMeses = plazoMeses; }
    public Integer getPeriodoGraciaMeses(){ return periodoGraciaMeses; }
    public void setPeriodoGraciaMeses(Integer periodoGraciaMeses){ this.periodoGraciaMeses = periodoGraciaMeses; }
    public LocalDate getFechaInicio(){ return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio){ this.fechaInicio = fechaInicio; }
}
