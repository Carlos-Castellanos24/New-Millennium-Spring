package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.Categoria;
import java.util.List;
import org.springframework.ui.Model;

public interface CategoriaService {
    
    /**
     * Este metodo trae un listado de categorias de manera descendente.
     * @return Retorna una lista de tipo Categoria
     */
    public List<Categoria> listarCategorias();
    
    /**
     * Este metodo inserta una categoria.
     * @param categoria
     */
    public void insertarCategoria(Categoria categoria);
    
    /**
     * Este metodo permite eliminar una categoria.
     * @param categoria
     */
    public void eliminarCategoria(Categoria categoria);
    
    /**
     * Este metodo permite buscar una categoria completa.
     * @param categoria
     * @return Retorna un objeto de tipo Actividad
     */
    public Categoria buscarCategoria(Categoria categoria);
    
    /**
     * Este metodo permite actualizar el estado de la categoria.
     * @param idCategoria
     */
    public void actualizarEstadoCategoria(Integer idCategoria);
    
    /**
     * Este metodo trae un listado de las categorias activas.
     * @return Retorna una lista de tipo Categoria
     */ 
    public List<Categoria> listarCategoriasActivas();
    
    /**
     * Este metodo nos ayuda a poder ingresar los datos de la tabla bitacora.
     * @param model
     * @param tipoRegistro
     * @param accion
     * @param nombreTabla
     * @param nombreCategoria
     */
    public void agregarBitacoraCategoria(Model model, String tipoRegistro, String accion, String nombreTabla, String nombreCategoria);

    /**
     * Este metodo nos ayuda a encontrar una categoria que ha sido deshabilitada.
     * @param idCategoria
     * @return Retorna un objeto de tipo Categoria
     */
    public Categoria encontrarCategoriaInhabilitada(Integer idCategoria);
}
