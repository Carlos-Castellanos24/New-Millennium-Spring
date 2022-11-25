package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.*;

public interface AdministradorActividadService {

    /**
     * Este metodo nos permite insertar el dato relacionado en la tabla de actividad y administrador.
     * @param administrador
     * @param actividad
     */
    public void insertarAdministradorActividad(Administrador administrador, Actividad actividad);
}
