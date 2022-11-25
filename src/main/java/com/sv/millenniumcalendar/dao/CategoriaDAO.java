package com.sv.millenniumcalendar.dao;

import com.sv.millenniumcalendar.clases.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaDAO extends JpaRepository<Categoria, Integer>{
    
    /**
     * Este metodo se encarga de inhabilitar el estado de la categoria a partir de un UPDATE.
     * @param idCategoria
     */
    @Modifying
    @Query("UPDATE Categoria c set c.estadoCategoria = 'I' WHERE c.idCategoria = :idCategoria")
    void editarEstadoCategoria(Integer idCategoria);  

    /**
     * Este metodo se encarga de traer una lista de categorias que estan activas.
     * @return Retorna la lista de categorias activas. 
     */
    @Query("SELECT c FROM Categoria c WHERE c.estadoCategoria = 'A'")
    List<Categoria> listarCategoriasActivas();
    
    /**
     * Este metodo se encarga de traer una lista de categorias.
     * @return Retorna la lista de categorias. 
     */
    @Query("SELECT c FROM Categoria c ORDER BY c.idCategoria DESC")
    List<Categoria> listarCategorias();
    
    /**
     * Este metodo se encarga de encontrar la categoria inhabilitada para llenar la tabla bitacora.
     * @param idCategoria
     * @return Retorna un objeto de tipo Categoria.
     */
    @Query("SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria")
    Categoria encontrarCategoriaInhabilitada(Integer idCategoria);
}
