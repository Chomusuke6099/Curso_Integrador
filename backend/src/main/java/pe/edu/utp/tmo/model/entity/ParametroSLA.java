package pe.edu.utp.tmo.model.entity;
import jakarta.persistence.*;
import pe.edu.utp.tmo.model.enums.TipoCaso;
@Entity @Table(name="parametros_sla")
public class ParametroSLA {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Enumerated(EnumType.STRING) @Column(name="tipo_caso",nullable=false,length=40) private TipoCaso tipoCaso;
 @Column(name="minutos_objetivo",nullable=false) private int minutosObjetivo;
 @Column(name="umbral_alerta",nullable=false) private int umbralAlerta;
 @Column(nullable=false) private boolean activo=true;
 protected ParametroSLA(){} public ParametroSLA(TipoCaso tipoCaso,int minutosObjetivo,int umbralAlerta,boolean activo){this.tipoCaso=tipoCaso;this.minutosObjetivo=minutosObjetivo;this.umbralAlerta=umbralAlerta;this.activo=activo;}
 public Long getId(){return id;} public TipoCaso getTipoCaso(){return tipoCaso;} public int getMinutosObjetivo(){return minutosObjetivo;} public int getUmbralAlerta(){return umbralAlerta;} public boolean isActivo(){return activo;}
}
