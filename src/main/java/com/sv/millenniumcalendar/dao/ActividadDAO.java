package com.sv.millenniumcalendar.dao;

import com.sv.millenniumcalendar.clases.Actividad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ActividadDAO extends JpaRepository<Actividad, Integer>{
    
    /**
     * Este metodo se encarga de inhabilitar el estado de la actividad a partir de un UPDATE.
     * @param idActividad
     */
    @Modifying
    @Query("UPDATE Actividad a set a.estadoActividad = 'I' WHERE a.idActividad = :idActividad")
    void editarEstadoActividad(Integer idActividad);
    
    /**
     * Este metodo se encarga de traer una lista de actividades que estan activas.
     * @return Retorna la lista de actividades activas. 
     */
    @Query("SELECT a FROM Actividad a WHERE a.estadoActividad = 'A'")
    List<Actividad> listarActividadesActivas(); 
    
    /**
     * Este metodo se encarga de traer una lista de actividades
     * @return Retorna la lista de actividades. 
     */
    @Query("SELECT a FROM Actividad a ORDER BY a.idActividad DESC")
    List<Actividad> listarActividades(); 
    
    /**
     * Este metodo se encarga de encontrar la actividad inhabilitada para llenar la tabla bitacora.
     * @param idActividad
     * @return Retorna un objeto de tipo Actividad.
     */
    @Query("SELECT a FROM Actividad a WHERE a.idActividad = :idActividad")
    Actividad encontrarActividadInhabilitada(Integer idActividad);
       
    /**
     * Este metodo se encarga de encontrar la actividad por id.
     * @param idActividad
     * @return Retorna un objeto de tipo Actividad.
     */
    @Query("SELECT a FROM Actividad a WHERE a.idActividad = :idActividad")
    Actividad buscarActividadPorId(Integer idActividad);
}
