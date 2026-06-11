package pe.edu.utp.tmo.model.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import pe.edu.utp.tmo.exception.TicketNoCerrableException;
import pe.edu.utp.tmo.model.enums.*;
@Entity @Table(name="casos")
public class Caso {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=40) private String codigo;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="analista_id",nullable=false) private Usuario analista;
 @Enumerated(EnumType.STRING) @Column(name="tipo_caso",nullable=false,length=40) private TipoCaso tipoCaso;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private EstadoCaso estado;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="tipificacion_id") private Tipificacion tipificacion;
 @Column(nullable=false,length=160) private String referencia;
 @Column(length=1000) private String observacion;
 @Column(name="fecha_inicio",nullable=false) private LocalDateTime fechaInicio;
 @Column(name="fecha_fin") private LocalDateTime fechaFin;
 @Column(name="created_at",nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
 protected Caso(){}
 public static Caso abrir(String codigo,Usuario analista,TipoCaso tipoCaso,String referencia,LocalDateTime inicio){Caso c=new Caso();c.codigo=codigo;c.analista=analista;c.tipoCaso=tipoCaso;c.referencia=referencia;c.fechaInicio=inicio;c.estado=EstadoCaso.ABIERTO;return c;}
 public static Caso prueba(Long id,Usuario usuario,LocalDateTime inicio){Caso c=abrir("TMO-TEST-"+id,usuario,TipoCaso.CORPORATIVO,"REF",inicio);c.id=id;return c;}
 public void cerrar(Tipificacion tipificacion,String observacion,LocalDateTime fin){if(estado==EstadoCaso.CERRADO) throw new TicketNoCerrableException("El ticket ya está cerrado."); this.tipificacion=tipificacion;this.observacion=observacion;this.fechaFin=fin;this.estado=EstadoCaso.CERRADO;}
 public Long getId(){return id;} public String getCodigo(){return codigo;} public Usuario getAnalista(){return analista;} public TipoCaso getTipoCaso(){return tipoCaso;} public EstadoCaso getEstado(){return estado;} public Tipificacion getTipificacion(){return tipificacion;} public String getReferencia(){return referencia;} public String getObservacion(){return observacion;} public LocalDateTime getFechaInicio(){return fechaInicio;} public LocalDateTime getFechaFin(){return fechaFin;} public LocalDateTime getCreatedAt(){return createdAt;}
}
