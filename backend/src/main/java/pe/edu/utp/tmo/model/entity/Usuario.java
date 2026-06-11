package pe.edu.utp.tmo.model.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import pe.edu.utp.tmo.model.enums.RolUsuario;
@Entity @Table(name="usuarios")
public class Usuario {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=120) private String nombres;
 @Column(nullable=false,unique=true,length=180) private String correo;
 @Column(name="password_hash",nullable=false,length=120) private String passwordHash;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private RolUsuario rol;
 @Column(nullable=false,length=80) private String area;
 @Column(nullable=false) private boolean activo=true;
 @Column(name="created_at",nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
 protected Usuario(){}
 public Usuario(String nombres,String correo,String passwordHash,RolUsuario rol,String area,boolean activo){this.nombres=nombres;this.correo=correo;this.passwordHash=passwordHash;this.rol=rol;this.area=area;this.activo=activo;}
 public Long getId(){return id;} public String getNombres(){return nombres;} public String getCorreo(){return correo;} public String getPasswordHash(){return passwordHash;}
 public RolUsuario getRol(){return rol;} public String getArea(){return area;} public boolean isActivo(){return activo;} public LocalDateTime getCreatedAt(){return createdAt;}
 public void setNombres(String n){this.nombres=n;} public void setCorreo(String c){this.correo=c;} public void setPasswordHash(String p){this.passwordHash=p;} public void setRol(RolUsuario r){this.rol=r;} public void setArea(String a){this.area=a;} public void setActivo(boolean a){this.activo=a;}
}
