package com.sv.millenniumcalendar.clases;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "administrador_actividad")
public class AdministradorActividad implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proceso")
    private Integer idProceso;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_administrador" ,referencedColumnName = "id_administrador")
    private Administrador administrador;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad")
    private Actividad actividad;
    
    @NotEmpty
    @Column(name = "fecha_proceso")
    private String fechaProceso;   
}
