package com.sv.millenniumcalendar.clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "actividad")
public class Actividad implements Serializable{
   
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private int idActividad;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private Categoria categoria;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_facilitador", referencedColumnName = "id_facilitador")
    private Facilitador facilitador;
    
    @NotEmpty
    @Column(name = "nombre_actividad")
    private String nombreActividad;
    
    @NotEmpty
    @Column(name = "fecha_inicio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;
    
    @NotEmpty
    @Column(name = "fecha_final")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFinal;
    
    @NotEmpty
    @Column(name = "dias_semana")
    private String diasSemana;
    
    @NotEmpty
    @Column(name = "horas_dias")
    private String horasDias;
    
    @NotEmpty
    @Column(name = "descripcion")
    private String descripcion;
    
    @NotEmpty
    @Column(name = "estado_actividad")
    private String estadoActividad;
    
}
