package pe.edu.utp.tmo.model.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="auditoria_eventos")
public class AuditoriaEvento {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="usuario_id") private Usuario usuario;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="caso_id") private Caso caso;
 @Column(nullable=false,length=80) private String accion;
 @Column(nullable=false) private LocalDateTime fecha;
 @Column(name="detalle_seguro",nullable=false,length=500) private String detalleSeguro;
 @Column(nullable=false,length=40) private String resultado;
 protected AuditoriaEvento(){} public AuditoriaEvento(Usuario usuario,Caso caso,String accion,LocalDateTime fecha,String detalleSeguro,String resultado){this.usuario=usuario;this.caso=caso;this.accion=accion;this.fecha=fecha;this.detalleSeguro=detalleSeguro;this.resultado=resultado;}
 public Long getId(){return id;}
}
