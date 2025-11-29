package com.fed.mivivienda.dto;

import java.time.LocalDate;

public class LoanRequest {
    private Long operationId;
    private Double monto;
    private String moneda;
    private Double tasaAnual;
    private String tipoTasa; // EFECTIVA o NOMINAL
    private Integer capitalizacionPorAnio; // si NOMINAL, ej 12
    private Integer plazoMeses;
    private Integer periodoGraciaMeses;
    private LocalDate fechaInicio;

    public Long getOperationId(){ return operationId; }
    public void setOperationId(Long operationId){ this.operationId = operationId; }
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
