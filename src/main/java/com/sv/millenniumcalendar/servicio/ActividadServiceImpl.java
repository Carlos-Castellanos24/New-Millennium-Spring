package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.*;
import com.sv.millenniumcalendar.dao.ActividadDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ActividadServiceImpl implements ActividadService{

    @Autowired
    private ActividadDAO actividadDao;
    
    @Autowired
    private BitacoraService bitacoraService;
    
    @Override
    @Transactional(readOnly = true)
    public List<Actividad> listarActividades() {
        return (List<Actividad>) actividadDao.listarActividades();
    }

    @Override
    @Transactional
    public void insertarActividad(Actividad actividad) {
        actividadDao.save(actividad);
    }

    @Override
    @Transactional
    public void eliminarActividad(Actividad actividad) {
        actividadDao.delete(actividad);
    }   

    @Override
    @Transactional(readOnly = true)
    public Actividad buscarActividad(Actividad actividad) {
        return actividadDao.findById(actividad.getIdActividad()).orElse(null);
    }   

    @Override
    @Transactional
    public void actualizarEstadoActividad(Integer idActividad) {
        actividadDao.editarEstadoActividad(idActividad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Actividad> listarActividadesActivas() {
        return (List<Actividad>) actividadDao.listarActividadesActivas();
    }
    
    @Override
    @Transactional
    public void agregarBitacoraActividad(Model model, String tipoRegistro, String accion, String nombreTabla, String nombreActividad) {
        Fecha fecha = new Fecha();
        
        Bitacora bitacora = new Bitacora();
        bitacora.setIdAdministrador((Integer) model.getAttribute("idAdministrador"));
        bitacora.setTipoRegistro(tipoRegistro);
        bitacora.setFechaRegistro(fecha.getFechaRegistro());
        bitacora.setDescripcionRegistro(bitacoraService.descripcionBitacora((String) model.getAttribute("nombreAdministrador"), accion, nombreTabla, nombreActividad));
        bitacoraService.insertarBitacora(bitacora);
    }

    @Override
    @Transactional(readOnly = true)
    public Actividad encontrarActividadInhabilitada(Integer idActividad) {
        return actividadDao.encontrarActividadInhabilitada(idActividad);
    }

    @Override
    @Transactional(readOnly = true)
    public Actividad buscarActividadPorId(Integer idActividad) {
        return actividadDao.buscarActividadPorId(idActividad);
    }
}
