package com.sv.millenniumcalendar.clases;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "administrador")
public class Administrador implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrador")
    private Integer idAdministrador;
    
    @NotEmpty
    @Column(name = "nombre")
    private String nombreAdministrador;
    
    @NotEmpty
    @Column(name = "apellido")
    private String apellidoAdministrador;
    
    @NotEmpty
    @Column(name = "estado_administrador")
    private String estadoAdministrador; 
}
