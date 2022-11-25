package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.Bitacora;
import java.util.List;

public interface BitacoraService {
    
    /**
     * Este metodo lista todas las bitacoras que existen.
     * @return Retorna una lista de tipo Bitacora
     */
    public List<Bitacora> listarBitacoras();
    
    /**
     * Este metodo nos ayuda a insertar una bitacora por medio de un objeto de tipo Bitacora.
     * @param bitacora
     */
    public void insertarBitacora(Bitacora bitacora);
    
    /**
     * Este metodo nos permite eliminar una bitacora.
     * @param bitacora
     */
    public void eliminarBitacora(Bitacora bitacora);
    
    /**
     * Este metodo nos ayuda a buscar una bitacora por medio de un objeto de tipo Bitacora.
     * @param bitacora
     * @return Retorna un objeto de tipo Bitacora
     */
    public Bitacora buscarBitacora(Bitacora bitacora);
    
    /**
     * Este metodo nos ayuda a poder agregar una descripcion a la tabla bitacora.
     * @param nombreAdministrador
     * @param accion
     * @param tabla
     * @param dato
     * @return Retorna una descripcion para la tabla bitacora.
     */
    public String descripcionBitacora(String nombreAdministrador, String accion, String tabla, String dato);
}
