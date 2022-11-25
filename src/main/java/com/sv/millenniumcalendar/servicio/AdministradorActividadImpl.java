package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.*;
import com.sv.millenniumcalendar.dao.AdministradorActividadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorActividadImpl implements AdministradorActividadService{

    @Autowired
    private AdministradorActividadDAO administradorActividadDao;
    
    @Override
    public void insertarAdministradorActividad(Administrador administrador, Actividad actividad) {
        Fecha fecha = new Fecha();
    
        AdministradorActividad administradorActividad = new AdministradorActividad();
        administradorActividad.setAdministrador(administrador);
        administradorActividad.setActividad(actividad);
        administradorActividad.setFechaProceso(fecha.getFechaRegistro());
        administradorActividadDao.save(administradorActividad);        
    }    
}
