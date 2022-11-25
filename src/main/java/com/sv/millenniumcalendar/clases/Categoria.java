package com.sv.millenniumcalendar.clases;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable{

    private static final long serialVersionUID = 1L; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;
    
    @Column(name = "nombre_categoria")
    @NotEmpty
    private String nombreCategoria;
    
    @Column(name = "descripcion")
    @NotEmpty
    private String descripcion;
    
    @Column(name = "estado_categoria")
    @NotEmpty
    private String estadoCategoria;
}