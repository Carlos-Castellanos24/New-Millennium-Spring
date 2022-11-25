package com.sv.millenniumcalendar.clases;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "facilitador")
public class Facilitador implements Serializable{

   private static final long serialVersionUID = 1L; 
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_facilitador")
   private Integer idFacilitador;
   
   @Column(name = "nombre_facilitador")
   @NotEmpty
   private String nombreFacilitador;
   
   @Column(name = "estudio")
   @NotEmpty
   private String estudio;
   
   @Column(name = "estado_facilitador")
   @NotEmpty
   private String estadoFacilitador;
}
