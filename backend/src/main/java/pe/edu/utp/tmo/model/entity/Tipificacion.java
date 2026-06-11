package pe.edu.utp.tmo.model.entity;
import jakarta.persistence.*;
@Entity @Table(name="tipificaciones")
public class Tipificacion {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=120) private String nombre;
 @Column(nullable=false) private boolean activo=true;
 protected Tipificacion(){} public Tipificacion(String nombre, boolean activo){this.nombre=nombre;this.activo=activo;}
 public Long getId(){return id;} public String getNombre(){return nombre;} public boolean isActivo(){return activo;}
}
