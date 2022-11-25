package com.sv.millenniumcalendar.dao;

import com.sv.millenniumcalendar.clases.Bitacora;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BitacoraDAO extends JpaRepository<Bitacora, Integer>{
    
    /**
     * Este metodo se encarga de traer una lista de bitacoras.
     * @return Retorna la lista de bitacoras. 
     */
    @Query("SELECT b FROM Bitacora b ORDER BY b.idRegistro DESC")
    List<Bitacora> listarBitacoras();
}
