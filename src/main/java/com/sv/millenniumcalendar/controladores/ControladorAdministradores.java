package com.sv.millenniumcalendar.controladores;

import com.sv.millenniumcalendar.clases.Administrador;
import com.sv.millenniumcalendar.clases.Login;
import com.sv.millenniumcalendar.servicio.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * Esta clase es la encargada de realizar todas las tareas del Backend para la
 * tabla de administradores, especificando a Spring con la anotacion de @Controller que ese sera el controlador que mandara a llamar para hacer la
 * logica, ademas, con la anotacion @SessionAttributes, pasamos como sesion los datos necesarios del administrador.
 */
@Controller
@SessionAttributes({"nombreAdministrador", "idAdministrador", "apellidoAdministrador", "correoAdministrador"})
public class ControladorAdministradores {

    /**
     * Esta variable administradorService se utiliza para hacer una inyeccion a
     * la clase AdministradorService y crear un objeto de forma automatica con
     * ayuda del @Autowired.
     */
    @Autowired
    private AdministradorService administradorService;

    /**
     * Esta variable loginService se utiliza para hacer una inyeccion a la clase
     * LoginService y crear un objeto de forma automatica con ayuda del
     *
     * @Autowired.
     */
    @Autowired
    private LoginService loginService;

    /**
     * El metodo es el encargado de redirigir al administrador, a la lista de
     * administradores.
     *
     * @param model
     * @return Retorna la lista de administradores.
     */
    @GetMapping("/listarAdministradores")
    public String listarAdministradores(Model model) {

        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        var administradores = administradorService.listarAdministradores();
        model.addAttribute("administradores", administradores);
        return "administradores/listadoAdministradores";
    }

    /**
     * El metodo es el encargado de redirigir al administrador al formulario de
     * agregar administrador.
     *
     * @param model
     * @return Retorna al formulario de agregar un administrador.
     */
    @GetMapping("/agregarAdministrador")
    public String agregarAdministrador(Model model) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        return "administradores/agregarAdministrador";
    }

    /**
     * El metodo es el encargado de guardar un administrador, pero a su vez, se
     * hace una consulta para agregar informacion a la tabla de Login asi mismo,
     * añade los datos necesarios a la tabla de bitacora, por eso se utilizan
     * los @RequestParam.
     *
     * @param administrador
     * @param errores
     * @param model
     * @param tipoRegistro
     * @param accion
     * @param nombreTabla
     * @param nombreAdministrador
     * @param estado
     * @param correo
     * @param clave
     * @return Retorna a la lista de administradores.
     */
    @PostMapping("/guardarAdministrador")
    public String guardarAdministrador(@Valid Administrador administrador, Errors errores, Model model,
            @RequestParam("tipoRegistro") String tipoRegistro, @RequestParam("accion") String accion,
            @RequestParam("nombreTabla") String nombreTabla, @RequestParam("nombreAdministrador") String nombreAdministrador,
            @RequestParam("estadoAdministrador") String estado, @RequestParam("correo") String correo,
            @RequestParam("clave") String clave) {

        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        } else if (errores.hasErrors()) {
            return "redirect:/agregarAdministrador";
        }

        if (administrador != null) {
            administradorService.insertarAdministrador(administrador);
            loginService.insertarLoginAdmin(administrador, correo, clave, estado);
        }

        if (nombreAdministrador != null && tipoRegistro != null && nombreTabla != null && accion != null) {
            administradorService.agregarBitacoraAdministrador(model, tipoRegistro, accion, nombreTabla, nombreAdministrador);
        }
        return "redirect:/listarAdministradores";
    }

    /**
     * El metodo se encarga de editar al administrador seleccionado, enviando
     * como parametro el objeto Login obtenido.
     *
     * @param administrador
     * @param model
     * @param login
     * @return Reorna al formulario de editar administrador.
     */
    @GetMapping("/editarAdministrador/{idAdministrador}")
    public String editarAdministrador(Administrador administrador, Model model, Login login) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }

        if (administrador != null && login != null) {

            administrador = administradorService.buscarAdministrador(administrador);
            model.addAttribute("administrador", administrador);

            login = loginService.buscarLoginAdministrador(administrador.getIdAdministrador());
            model.addAttribute("login", login);
        }

        return "administradores/editarAdministrador";
    }

    /**
     * @param administrador
     * @param errores
     * @param model
     * @param tipoRegistro
     * @param accion
     * @param nombreTabla
     * @param nombreAdministrador
     * @param apellidoAdministrador
     * @param correo
     * @param estado
     * @param idLogin
     * @param clave
     * @return
     */
    @PostMapping("/editarCambiosAdministrador")
    public String editarCambiosAdministrador(@Valid Administrador administrador, Errors errores, Model model,
            @RequestParam("tipoRegistro") String tipoRegistro, @RequestParam("accion") String accion,
            @RequestParam("nombreTabla") String nombreTabla, @RequestParam("nombreAdministrador") String nombreAdministrador,
            @RequestParam("apellidoAdministrador") String apellidoAdministrador, @RequestParam("correo") String correo, @RequestParam("estadoAdministrador") String estado,
            @RequestParam(name = "clave", required = false) String clave, @RequestParam("idLogin") Integer idLogin) {

        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        } else if (errores.hasErrors()) {
            return "redirect:/editarAdministrador/{idAdministrador}";
        }

        if (administrador != null) {
            administradorService.insertarAdministrador(administrador);
        } else if (correo != null && estado != null && idLogin != null) {
            loginService.editarLoginAdministrador(correo, estado, idLogin);
        }
        if (nombreAdministrador != null && tipoRegistro != null && nombreTabla != null && accion != null) {
            administradorService.agregarBitacoraAdministrador(model, tipoRegistro, accion, nombreTabla, nombreAdministrador);
        }
        return "redirect:/listarAdministradores";
    }

    /**
     * Este metodo se encarga de inhabilitar al administrador seleccionado, y a su vez, hace una consulta para 
     * inhabilitar el login a la misma vez.
     * @param administrador
     * @param errores
     * @param idAdministrador
     * @param model
     * @return Retorna al listado de administradores.
     */
    @GetMapping("/eliminarAdministrador/{idAdministrador}")
    public String eliminarAdministrador(@Valid Administrador administrador, Errors errores,
            @PathVariable("idAdministrador") Integer idAdministrador, Model model) {

        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        } else if (errores.hasErrors()) {
            return "eliminarFacilitador";
        }

        if (idAdministrador != null) {

            administradorService.actualizarEstadoAdministrador(idAdministrador);
            var administradorInhabilitado = administradorService.buscarAdministradorId(idAdministrador);

            loginService.inhabilitarLoginAdmin(idAdministrador);
            administradorService.agregarBitacoraAdministrador(model, "D", "inhabilito", "administrador", administradorInhabilitado.getNombreAdministrador());
        }

        return "redirect:/listarAdministradores";
    }
}
