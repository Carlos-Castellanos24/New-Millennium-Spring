package com.sv.millenniumcalendar.dao;

import com.sv.millenniumcalendar.clases.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LoginDAO extends JpaRepository<Login, Integer>{
    
    /**
     * Este metodo se encarga de validar el correo y el estado del login.
     * @param correo
     * @return Retorna un objeto de tipo Login.
     */
    @Query("SELECT l FROM Login l WHERE l.correo = :correo AND l.estadoLogin = 'A'")
    Login validarLogin(String correo);
    
    /**
     * Este metodo se encarga de buscar el login del administrador por medio del Id.
     * @param idAdministrador
     * @return Retorna un objeto de tipo Login.
     */
    @Query("SELECT l FROM Login l WHERE l.administrador.idAdministrador = :idAdministrador")
    Login buscarLoginAdministrador(Integer idAdministrador);
    
    /**
     * Este metodo se encarga de inhabilitar el estado del login a partir de un UPDATE.
     * @param idAdministrador
     */
    @Modifying
    @Query("UPDATE Login l SET l.estadoLogin = 'I' WHERE l.administrador.idAdministrador = :idAdministrador")
    void inhabilitarLoginAdmin(Integer idAdministrador); 
    
    /**
     * Este metodo se encarga de editar todos los campos del login a excepto la contraseña a partir de un UPDATE.
     * @param correo
     * @param estado
     * @param idLogin
     */
    @Modifying
    @Query("UPDATE Login l SET l.correo = :correo, l.estadoLogin = :estado WHERE l.idLogin = :idLogin")
    void editarLoginAdministrador(String correo, String estado, Integer idLogin);
}
