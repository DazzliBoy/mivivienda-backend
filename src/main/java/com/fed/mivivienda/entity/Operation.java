package com.fed.mivivienda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "operations")
public class Operation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Client client;

    @ManyToOne(optional=false)
    private Property property;

    private Double tipoCambio; // si aplica conversión
    private Boolean bonoTechoPropio; // opción

    public Operation(){}

    public Long getId(){ return id; }
    public Client getClient(){ return client; }
    public void setClient(Client client){ this.client = client; }
    public Property getProperty(){ return property; }
    public void setProperty(Property property){ this.property = property; }
    public Double getTipoCambio(){ return tipoCambio; }
    public void setTipoCambio(Double tipoCambio){ this.tipoCambio = tipoCambio; }
    public Boolean getBonoTechoPropio(){ return bonoTechoPropio; }
    public void setBonoTechoPropio(Boolean bonoTechoPropio){ this.bonoTechoPropio = bonoTechoPropio; }
}
