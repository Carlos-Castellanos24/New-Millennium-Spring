package com.sv.millenniumcalendar.clases;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "bitacora")
public class Bitacora implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private Integer idRegistro;
    
    @JoinColumn(name = "id_administrador")
    private Integer idAdministrador;
    
    @NotEmpty
    @Column(name = "tipo_registro")
    private String tipoRegistro;
    
    @NotEmpty
    @Column(name = "fecha_registro")
    private String fechaRegistro;
    
    @NotEmpty
    @Column(name = "descripcion_registro")
    private String descripcionRegistro;   
}
