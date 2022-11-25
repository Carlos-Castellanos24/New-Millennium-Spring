package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.Actividad;
import java.util.List;
import org.springframework.ui.Model;

public interface ActividadService {
    
    /**
     * Este metodo trae un listado de actividades de manera descendente.
     * @return Retorna una lista de tipo Actividad
     */
    public List<Actividad> listarActividades();
    
    /**
     * Este metodo trae un listado de las actividades activas.
     * @return Retorna una lista de tipo Actividad
     */   
    public List<Actividad> listarActividadesActivas();
    
    /**
     * Este metodo inserta una actividad.
     * @param actividad
     */
    public void insertarActividad(Actividad actividad);
    
    /**
     * Este metodo permite eliminar una actividad.
     * @param actividad
     */
    public void eliminarActividad(Actividad actividad);
    
    /**
     * Este metodo permite buscar una actividad completa.
     * @param actividad
     * @return Retorna un objeto de tipo Actividad
     */
    public Actividad buscarActividad(Actividad actividad);   
    
    /**
     * Este metodo nos ayuda a encontrar una actividad que ha sido deshabilitada.
     * @param idActividad
     * @return Retorna un objeto de tipo Actividad
     */
    public Actividad encontrarActividadInhabilitada(Integer idActividad);
    
    /**
     * Este metodo permite actualizar el estado de la actividad.
     * @param idActividad
     */
    public void actualizarEstadoActividad(Integer idActividad);      
    
    /**
     * Este metodo nos ayuda a poder ingresar los datos de la tabla bitacora.
     * @param model
     * @param tipoRegistro
     * @param accion
     * @param nombreTabla
     * @param nombreActividad
     */
    public void agregarBitacoraActividad(Model model, String tipoRegistro, String accion, String nombreTabla, String nombreActividad);

    /**
     * Este metodo permite buscar una actividad por medio del id.
     * @param idActividad
     * @return Retorna un objeto de tipo Actividad
     */
    public Actividad buscarActividadPorId(Integer idActividad);
}
