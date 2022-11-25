package com.sv.millenniumcalendar.clases;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "login")
public class Login implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_login")
    private Integer idLogin;
            
    @JoinColumn(name = "id_administrador", referencedColumnName = "id_administrador")
    @OneToOne(cascade = CascadeType.ALL)
    private Administrador administrador;
    
    @NotEmpty
    @Column(name = "correo")
    private String correo;
    
    @NotEmpty
    @Column(name = "clave")
    private String clave;
    
    @NotEmpty
    @Column(name = "estado_login")
    private String estadoLogin;
}
