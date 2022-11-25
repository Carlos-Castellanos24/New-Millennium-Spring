package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.*;
import com.sv.millenniumcalendar.dao.LoginDAO;
import com.sv.millenniumcalendar.security.EncriptarPassword;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDAO loginDao;

    @Override
    @Transactional(readOnly = true)
    public List<Login> listarLogin() {
        return (List<Login>) loginDao.findAll();
    }

    @Override
    @Transactional
    public void insertarLogin(Login login) {
        loginDao.save(login);
    }

    @Override
    @Transactional
    public void eliminarLogin(Login login) {
        loginDao.delete(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Login buscarLogin(Login login) {
        return loginDao.findById(login.getIdLogin()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Login validarLogin(String correo) {
        return loginDao.validarLogin(correo);
    }

    @Override
    @Transactional
    public void insertarLoginAdmin(Administrador administrador, String correo, String clave, String estado) {
        Login login = new Login();
        login.setAdministrador(administrador);
        login.setCorreo(correo);
        login.setClave(EncriptarPassword.encriptarPassword(clave));
        login.setEstadoLogin(estado);
        loginDao.save(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Login buscarLoginAdministrador(Integer idAdministrador) {
        return loginDao.buscarLoginAdministrador(idAdministrador);
    }

    @Override
    @Transactional
    public void inhabilitarLoginAdmin(Integer idAdministrador) {
        loginDao.inhabilitarLoginAdmin(idAdministrador);
    }

    @Override
    @Transactional
    public void editarLoginAdministrador(String correo, String estado, Integer idLogin) {
        loginDao.editarLoginAdministrador(correo, estado, idLogin);
    }
}
