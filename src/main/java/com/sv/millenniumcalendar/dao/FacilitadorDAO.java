package com.sv.millenniumcalendar.dao;

import com.sv.millenniumcalendar.clases.Facilitador;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FacilitadorDAO extends JpaRepository<Facilitador, Integer>{
    
    /**
     * Este metodo se encarga de inhabilitar el estado del facilitador a partir de un UPDATE.
     * @param idFacilitador
     */
    @Modifying
    @Query("UPDATE Facilitador f set f.estadoFacilitador = 'I' WHERE f.idFacilitador = :idFacilitador")
    void editarEstadoFacilitador(Integer idFacilitador);  

    /**
     * Este metodo se encarga de traer una lista de facilitadores que estan activas.
     * @return Retorna la lista de facilitadores activos. 
     */
    @Query("SELECT f FROM Facilitador f WHERE f.estadoFacilitador = 'A'")
    List<Facilitador> listarFacilitadoresActivos();  
    
    /**
     * Este metodo se encarga de traer una lista de facilitadores.
     * @return Retorna la lista de facilitadores. 
     */
    @Query("SELECT f FROM Facilitador f ORDER BY f.idFacilitador DESC")
    List<Facilitador> listarFacilitadores();
    
    /**
     * Este metodo se encarga de encontrar el facilitador inhabilitado para llenar la tabla bitacora.
     * @param idFacilitador
     * @return Retorna un objeto de tipo Facilitador.
     */
    @Query("SELECT f FROM Facilitador f WHERE f.idFacilitador = :idFacilitador")
    Facilitador encontrarFacilitadorInhabilitado(Integer idFacilitador);
}
