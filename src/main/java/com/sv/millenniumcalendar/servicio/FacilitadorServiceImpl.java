package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.*;
import com.sv.millenniumcalendar.dao.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class FacilitadorServiceImpl implements FacilitadorService{

    @Autowired
    private FacilitadorDAO facilitadorDao;
    
    @Autowired
    private BitacoraService bitacoraService;
    
    @Override
    @Transactional(readOnly = true)
    public List<Facilitador> listarFacilitadores() {
        return (List<Facilitador>) facilitadorDao.listarFacilitadores();
    }

    @Override
    @Transactional
    public void insertarFacilitador(Facilitador facilitador) {
        facilitadorDao.save(facilitador);
    }

    @Override
    @Transactional
    public void eliminarFacilitador(Facilitador facilitador) {
        facilitadorDao.delete(facilitador);
    }

    @Override
    @Transactional(readOnly = true)
    public Facilitador buscarFacilitador(Facilitador facilitador) {
        return facilitadorDao.findById(facilitador.getIdFacilitador()).orElse(null);
    }    

    @Override
    @Transactional
    public void actualizarEstadoFacilitador(Integer idFacilitador) {
        facilitadorDao.editarEstadoFacilitador(idFacilitador);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Facilitador> listarFacilitadoresActivos() {
        return (List<Facilitador>) facilitadorDao.listarFacilitadoresActivos();
    }

    @Override
    @Transactional(readOnly = true)
    public Facilitador encontrarFacilitadorInhabilitado(Integer idFacilitador) {
        return facilitadorDao.encontrarFacilitadorInhabilitado(idFacilitador);
    }

    @Override
    @Transactional
    public void agregarBitacoraFacilitador(Model model, String tipoRegistro, String accion, String nombreTabla, String nombreFacilitador) {
        Fecha fecha = new Fecha();
        
        Bitacora bitacora = new Bitacora();
        bitacora.setIdAdministrador((Integer) model.getAttribute("idAdministrador"));
        bitacora.setTipoRegistro(tipoRegistro);
        bitacora.setFechaRegistro(fecha.getFechaRegistro());
        bitacora.setDescripcionRegistro(bitacoraService.descripcionBitacora((String) model.getAttribute("nombreAdministrador"), accion, nombreTabla, nombreFacilitador));
        bitacoraService.insertarBitacora(bitacora);
    }
}
