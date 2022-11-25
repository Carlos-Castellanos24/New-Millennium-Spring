package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.Administrador;
import com.sv.millenniumcalendar.clases.Login;
import java.util.List;

public interface LoginService {
    
    /**
     * Este metodo trae un listado de login de manera descendente.
     * @return Retorna una lista de tipo Login
     */
    public List<Login> listarLogin();
    
    /**
     * Este metodo inserta un login.
     * @param login
     */
    public void insertarLogin(Login login);
    
    /**
     * Este metodo permite eliminar un login.
     * @param login
     */
    public void eliminarLogin(Login login);
    
    /**
     * Este metodo permite buscar un login.
     * @param login
     * @return Retorna un objeto de tipo Login
     */
    public Login buscarLogin(Login login);
    
    /**
     * Este metodo es el encargado de realizar la validacion del login, pidiendo el correo y validando el administrador.
     * @param correo
     * @return Retorna un objeto de tipo Login
     */
    public Login validarLogin(String correo);  
    
    /**
     * Este metodo permite buscar un login por medio del id.
     * @param idAdministrador
     * @return Retorna un objeto de tipo Login
     */
    public Login buscarLoginAdministrador(Integer idAdministrador);
    
    /**
     * Este metodo nos permite inhabilitar un login (cambiar el estado) por medio del id.
     * @param idAdministrador
     */
    public void inhabilitarLoginAdmin(Integer idAdministrador);  
    
    /**
     * Este metodo permite insertar un login de manera automatica, cuando se inserta un administrador.
     * @param administrador
     * @param correo
     * @param clave
     * @param estado
     */
    public void insertarLoginAdmin(Administrador administrador, String correo, String clave, String estado);
    
    /**
     * Este metodo ayuda a editar el login del administrador, siempre y cuando se edite el administrador.
     * @param correo
     * @param estado
     * @param idLogin
     */
    public void editarLoginAdministrador(String correo, String estado, Integer idLogin);
}