package com.sv.millenniumcalendar.dao;

import com.sv.millenniumcalendar.clases.Administrador;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AdministradorDAO extends JpaRepository<Administrador, Integer>{
    
    /**
     * Este metodo se encarga de inhabilitar el estado del administrador a partir de un UPDATE.
     * @param idAdministrador
     */
    @Modifying
    @Query("UPDATE Administrador a set a.estadoAdministrador = 'I' WHERE a.idAdministrador = :idAdministrador")
    void editarEstadoAdministrador(Integer idAdministrador);  
    
    /**
     * Este metodo se encarga de traer una lista de actividades
     * @return Retorna la lista de actividades. 
     */
    @Query("SELECT a FROM Administrador a ORDER BY a.idAdministrador DESC")
    List<Administrador> listarAdministradores(); 
    
    /**
     * Este metodo se encarga de encontrar un administrador por id.
     * @param idAdministrador
     * @return Retorna un objeto de tipo Administrador.
     */
    @Query("SELECT a FROM Administrador a WHERE a.idAdministrador = :idAdministrador")
    Administrador buscarAdministradorId(Integer idAdministrador);    
    
    /**
     * Este metodo no se ocupa, solo fue para prueba, pero funciona para cambiar todos los campos del administrador.
     * @param nombreAdministrador
     * @param apellidoAdministrador
     * @param estadoAdministrador
     * @param idAdministrador
     */
    @Modifying
    @Query("UPDATE Administrador a SET a.nombreAdministrador = :nombreAdministrador,"
            + " a.apellidoAdministrador = :apellidoAdministrador, a.estadoAdministrador = :estadoAdministrador WHERE a.idAdministrador = :idAdministrador")
    void editarAdministrador(String nombreAdministrador, String apellidoAdministrador, String estadoAdministrador, Integer idAdministrador);
}
