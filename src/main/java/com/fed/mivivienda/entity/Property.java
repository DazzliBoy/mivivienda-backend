package com.fed.mivivienda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Property {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;
    private String tipo; // departamento, casa, etc.
    private Double area;
    private Double precio;
    private String descripcion;

    public Property(){}

    public Long getId(){ return id; }
    public String getDireccion(){ return direccion; }
    public void setDireccion(String direccion){ this.direccion = direccion; }
    public String getTipo(){ return tipo; }
    public void setTipo(String tipo){ this.tipo = tipo; }
    public Double getArea(){ return area; }
    public void setArea(Double area){ this.area = area; }
    public Double getPrecio(){ return precio; }
    public void setPrecio(Double precio){ this.precio = precio; }
    public String getDescripcion(){ return descripcion; }
    public void setDescripcion(String descripcion){ this.descripcion = descripcion; }
}
