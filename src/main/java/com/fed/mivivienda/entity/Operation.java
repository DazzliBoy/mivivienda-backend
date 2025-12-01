package com.fed.mivivienda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(name = "tipo_cambio")
    private Double tipoCambio;

    // Forzar booleano como TINYINT(1) para evitar BIT(1) en Railway
    @Column(name = "bono_techo_propio", columnDefinition = "TINYINT(1)")
    private Boolean bonoTechoPropio;

    public Operation() {}

    public Long getId() { return id; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }

    public Double getTipoCambio() { return tipoCambio; }
    public void setTipoCambio(Double tipoCambio) { this.tipoCambio = tipoCambio; }

    public Boolean getBonoTechoPropio() { return bonoTechoPropio; }
    public void setBonoTechoPropio(Boolean bonoTechoPropio) { this.bonoTechoPropio = bonoTechoPropio; }
}
