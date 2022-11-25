package com.sv.millenniumcalendar.clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;

@Data
public class Fecha {
    
    Date fechaActual = new Date();
    
    SimpleDateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
    
    private String fechaRegistro = fecha.format(fechaActual) + ' ' + formatoHora.format(fechaActual); 
}
