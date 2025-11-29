package com.fed.mivivienda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nombre;

    @Column(nullable=false)
    private String apellido;

    @Column(unique = true, nullable=false, length=8)
    private String dni;

    private String direccion;
    private String telefono;
    private String email;
    private Double ingresosMensuales;
    private String estadoCivil;
    private String ocupacion;

    public Client(){}

    // getters & setters
    public Long getId(){ return id; }
    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    public String getApellido(){ return apellido; }
    public void setApellido(String apellido){ this.apellido = apellido; }
    public String getDni(){ return dni; }
    public void setDni(String dni){ this.dni = dni; }
    public String getDireccion(){ return direccion; }
    public void setDireccion(String direccion){ this.direccion = direccion; }
    public String getTelefono(){ return telefono; }
    public void setTelefono(String telefono){ this.telefono = telefono; }
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }
    public Double getIngresosMensuales(){ return ingresosMensuales; }
    public void setIngresosMensuales(Double ingresosMensuales){ this.ingresosMensuales = ingresosMensuales; }
    public String getEstadoCivil(){ return estadoCivil; }
    public void setEstadoCivil(String estadoCivil){ this.estadoCivil = estadoCivil; }
    public String getOcupacion(){ return ocupacion; }
    public void setOcupacion(String ocupacion){ this.ocupacion = ocupacion; }
}
