package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.Facilitador;
import java.util.List;
import org.springframework.ui.Model;

public interface FacilitadorService {
    
    /**
     * Este metodo trae un listado de facilitadores de manera descendente.
     * @return Retorna una lista de tipo Facilitador
     */
    public List<Facilitador> listarFacilitadores();
    
    /**
     * Este metodo inserta una facilitador.
     * @param facilitador
     */
    public void insertarFacilitador(Facilitador facilitador);
    
    /**
     * Este metodo permite eliminar una facilitador.
     * @param facilitador
     */
    public void eliminarFacilitador(Facilitador facilitador);
    
    /**
     * Este metodo permite buscar un facilitador.
     * @param facilitador
     * @return Retorna un objeto de tipo Facilitador
     */
    public Facilitador buscarFacilitador(Facilitador facilitador);
    
    /**
     * Este metodo permite actualizar el estado del facilitador.
     * @param idFacilitador
     */
    public void actualizarEstadoFacilitador(Integer idFacilitador);
    
    /**
     * Este metodo trae un listado de los facilitadores activas.
     * @return Retorna una lista de tipo Facilitador
     */
    public List<Facilitador> listarFacilitadoresActivos();
    
    /**
     * Este metodo nos ayuda a encontrar un facilitador que ha sido deshabilitado.
     * @param idFacilitador
     * @return Retorna un objeto de tipo Facilitador
     */
    public Facilitador encontrarFacilitadorInhabilitado(Integer idFacilitador);
    
    /**
     * Este metodo nos ayuda a poder ingresar los datos de la tabla bitacora.
     * @param model
     * @param tipoRegistro
     * @param accion
     * @param nombreTabla
     * @param nombreFacilitador
     */
    public void agregarBitacoraFacilitador(Model model, String tipoRegistro, String accion, String nombreTabla, String nombreFacilitador);   
}
