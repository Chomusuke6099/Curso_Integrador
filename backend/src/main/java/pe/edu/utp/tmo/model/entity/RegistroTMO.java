package pe.edu.utp.tmo.model.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="registros_tmo")
public class RegistroTMO {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @OneToOne(fetch=FetchType.LAZY) @JoinColumn(name="caso_id",nullable=false,unique=true) private Caso caso;
 @Column(nullable=false) private LocalDateTime inicio;
 @Column(nullable=false) private LocalDateTime fin;
 @Column(name="tmo_segundos",nullable=false) private long tmoSegundos;
 protected RegistroTMO(){} public RegistroTMO(Caso caso,LocalDateTime inicio,LocalDateTime fin,long tmoSegundos){this.caso=caso;this.inicio=inicio;this.fin=fin;this.tmoSegundos=tmoSegundos;}
 public Long getId(){return id;} public Caso getCaso(){return caso;} public LocalDateTime getInicio(){return inicio;} public LocalDateTime getFin(){return fin;} public long getTmoSegundos(){return tmoSegundos;}
}
