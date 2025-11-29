package com.fed.mivivienda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "app_config")
public class AppConfigEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String moneda; // PEN, USD

    @Column(nullable=false)
    private String tipoTasa; // EFECTIVA o NOMINAL

    private Integer capitalizacionPorAnio; // si nominal: 12, 360, etc.

    private Integer periodoGracia; // meses de gracia

    public AppConfigEntity(){}

    public Long getId(){ return id; }
    public String getMoneda(){ return moneda; }
    public void setMoneda(String moneda){ this.moneda = moneda; }
    public String getTipoTasa(){ return tipoTasa; }
    public void setTipoTasa(String tipoTasa){ this.tipoTasa = tipoTasa; }
    public Integer getCapitalizacionPorAnio(){ return capitalizacionPorAnio; }
    public void setCapitalizacionPorAnio(Integer capitalizacionPorAnio){ this.capitalizacionPorAnio = capitalizacionPorAnio; }
    public Integer getPeriodoGracia(){ return periodoGracia; }
    public void setPeriodoGracia(Integer periodoGracia){ this.periodoGracia = periodoGracia; }
}
