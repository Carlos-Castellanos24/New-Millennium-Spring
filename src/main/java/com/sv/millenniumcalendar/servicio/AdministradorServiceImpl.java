package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.*;
import com.sv.millenniumcalendar.dao.AdministradorDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorDAO administradorDao;

    @Autowired
    private BitacoraService bitacoraService;

    @Override
    @Transactional(readOnly = true)
    public List<Administrador> listarAdministradores() {
        return (List<Administrador>) administradorDao.listarAdministradores();
    }

    @Override
    @Transactional
    public void insertarAdministrador(Administrador administrador) {
        administradorDao.save(administrador);
    }

    @Override
    @Transactional
    public void eliminarAdministrador(Administrador administrador) {
        administradorDao.delete(administrador);
    }

    @Override
    @Transactional(readOnly = true)
    public Administrador buscarAdministrador(Administrador administrador) {
        return administradorDao.findById(administrador.getIdAdministrador()).orElse(null);
    }

    @Override
    @Transactional
    public void actualizarEstadoAdministrador(Integer idAministrador) {
        administradorDao.editarEstadoAdministrador(idAministrador);
    }

    @Override
    @Transactional(readOnly = true)
    public Administrador buscarAdministradorId(Integer idAdministrador) {
        return administradorDao.buscarAdministradorId(idAdministrador);
    }

    @Override
    @Transactional
    public void editarAdministrador(String nombreAdministrador, String apellidoAdministrador, String estadoAdministrador, Integer idAdministrador) {
        administradorDao.editarAdministrador(nombreAdministrador, apellidoAdministrador, estadoAdministrador, idAdministrador);
    }   

    @Override
    @Transactional
    public void agregarBitacoraAdministrador(Model model, String tipoRegistro, String accion, String nombreTabla, String nombreFacilitador) {
        Fecha fecha = new Fecha();

        Bitacora bitacora = new Bitacora();
        bitacora.setIdAdministrador((Integer) model.getAttribute("idAdministrador"));
        bitacora.setTipoRegistro(tipoRegistro);
        bitacora.setFechaRegistro(fecha.getFechaRegistro());
        bitacora.setDescripcionRegistro(bitacoraService.descripcionBitacora((String) model.getAttribute("nombreAdministrador"), accion, nombreTabla, nombreFacilitador));
        bitacoraService.insertarBitacora(bitacora);
    }
}
