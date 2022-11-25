package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.Administrador;
import java.util.List;
import org.springframework.ui.Model;

public interface AdministradorService {
    
    /**
     * Este metodo ayuda a listar todos los administradores de manera descendente.
     * @return Retorna una lista de tipo Administrador.
     */
    public List<Administrador> listarAdministradores();   
    
    /**
     * Este metodo nos permite insertar un administrador por medio de un objeto.
     * @param administrador
     */
    public void insertarAdministrador(Administrador administrador);
    
    /**
     * Este metodo permite eliminar un administrador.
     * @param administrador
     */
    public void eliminarAdministrador(Administrador administrador);
    
    /**
     * Este metodo nos ayuda a buscar un administrador por medio del id.
     * @param administrador
     * @return Retorna un objeto de tipo Administrador
     */
    public Administrador buscarAdministrador(Administrador administrador);
    
    /**
     * Este metodo nos permite actualizar el estado del administrador.
     * @param idAministrador
     */
    public void actualizarEstadoAdministrador(Integer idAministrador);
     
    /**
     * Este metodo busca a un administrador por medio del id.
     * @param idAdministrador
     * @return Retorna un objeto de tipo Administrador
     */
    public Administrador buscarAdministradorId(Integer idAdministrador);
    
    /**
     * Este metodo nos ayuda a poder ingresar los datos de la tabla bitacora.
     * @param model
     * @param tipoRegistro
     * @param accion
     * @param nombreTabla
     * @param nombreAdministrador
     */
    public void agregarBitacoraAdministrador(Model model, String tipoRegistro, String accion, String nombreTabla, String nombreAdministrador);

    /**
     * Este metodo ayuda a editar todos los datos del administrador, cuando editamos los datos del Login.
     * @param nombreAdministrador
     * @param apellidoAdministrador
     * @param estadoAdministrador
     * @param idAdministrador
     */
    public void editarAdministrador(String nombreAdministrador, String apellidoAdministrador, String estadoAdministrador, Integer idAdministrador);
}
