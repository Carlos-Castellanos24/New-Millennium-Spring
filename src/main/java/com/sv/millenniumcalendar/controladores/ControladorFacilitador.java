package com.sv.millenniumcalendar.controladores;

import com.sv.millenniumcalendar.clases.*;
import com.sv.millenniumcalendar.servicio.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * Esta clase es la encargada de realizar todas las tareas del Backend para la
 * tabla de facilitadores, especificando a Spring con la anotacion de @Controller que ese sera el controlador que mandara a llamar para hacer la
 * logica, ademas, con la anotacion @SessionAttributes, pasamos como sesion los datos necesarios del administrador.
 */
@Controller
@SessionAttributes({"nombreAdministrador", "idAdministrador", "apellidoAdministrador" ,"correoAdministrador"})
public class ControladorFacilitador {

    /**
     * Esta variable facilitadorService se utiliza para hacer una inyeccion a
     * la clase FacilitadorService y crear un objeto de forma automatica con
     * ayuda del @Autowired.
     */
    @Autowired
    private FacilitadorService facilitadorService;

    /**
     * El metodo se encarga de listar todos los facilitadores.
     * @param model
     * @return Retorna a la lista de facilitadores.
     */
    @GetMapping("/listarFacilitadores")
    public String listarFacilitadores(Model model) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        var facilitadores = facilitadorService.listarFacilitadores();
        model.addAttribute("facilitadores", facilitadores);
        return "facilitadores/listadoFacilitadores";
    }

    /**
     * El metodo se encarga de agregar un facilitador.
     * @param model
     * @return Retorna al formulario de facilitadores.
     */
    @GetMapping("/agregarFacilitador")
    public String agregarFacilitador(Model model) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        return "facilitadores/agregarFacilitador";
    }

    /**
     * Este metodo se encarga de guardar todos los datos seleccionados por el administrador.
     * @param facilitador
     * @param errores
     * @param model
     * @param tipoRegistro
     * @param accion
     * @param nombreTabla
     * @param nombreFacilitador
     * @return Retorna a la lista de facilitadores
     */
    @PostMapping("/guardarFacilitador")
    public String guardarFacilitador(@Valid Facilitador facilitador, Errors errores, Model model,
            @RequestParam("tipoRegistro") String tipoRegistro, @RequestParam("accion") String accion,
            @RequestParam("nombreTabla") String nombreTabla, @RequestParam("nombreFacilitador") String nombreFacilitador) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        if (errores.hasErrors()) {
            return "redirect:/agregarFacilitador";
        }
        facilitadorService.agregarBitacoraFacilitador(model, tipoRegistro, accion, nombreTabla, nombreFacilitador);
        facilitadorService.insertarFacilitador(facilitador);
        return "redirect:/listarFacilitadores";
    }

    /**
     * Este metodo se encarga de redirigir al administrador al formulario de editar administrador.
     * @param facilitador
     * @param model
     * @return Retorna al formulario de editar facilitador.
     */
    @GetMapping("/editarFacilitador/{idFacilitador}")
    public String editarFacilitador(Facilitador facilitador, Model model) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        facilitador = facilitadorService.buscarFacilitador(facilitador);
        model.addAttribute("facilitador", facilitador);
        return "facilitadores/editarFacilitador";
    }

    /**
     * Este metodo se encarga de guardar todos los datos cambiados del facilitador por el administrador.
     * @param facilitador
     * @param errores
     * @param model
     * @param tipoRegistro
     * @param accion
     * @param nombreTabla
     * @param nombreFacilitador
     * @return Retorna a la lista de facilitadores
     */
    @PostMapping("/editarCambiosFacilitador")
    public String editarCambiosFacilitador(@Valid Facilitador facilitador, Errors errores, Model model,
            @RequestParam("tipoRegistro") String tipoRegistro, @RequestParam("accion") String accion,
            @RequestParam("nombreTabla") String nombreTabla, @RequestParam("nombreFacilitador") String nombreFacilitador) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        if (errores.hasErrors()) {
            return "redirect:/editarFacilitador/{idFacilitador}";
        }
        facilitadorService.insertarFacilitador(facilitador);
        facilitadorService.agregarBitacoraFacilitador(model, tipoRegistro, accion, nombreTabla, nombreFacilitador);
        return "redirect:/listarFacilitadores";
    }

    /**
     * El metodo se encarga de inhabilitar un facilitador, llenando de forma manual la tabla de bitacora, ya que no hay formulario
     * para inhabilitar un facilitador.
     * @param facilitador
     * @param errores
     * @param idFacilitador
     * @param model
     * @return Retorna al listado de facilitadores
     */
    @GetMapping("/eliminarFacilitador/{idFacilitador}")
    public String eliminarFacilitador(@Valid Facilitador facilitador, Errors errores,
            @PathVariable("idFacilitador") Integer idFacilitador, Model model) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        if (errores.hasErrors()) {
            return "eliminarFacilitador";
        }

        facilitadorService.actualizarEstadoFacilitador(idFacilitador);
        var facilitadorInhabilitado = facilitadorService.encontrarFacilitadorInhabilitado(idFacilitador);
        facilitadorService.agregarBitacoraFacilitador(model, "D", "inhabilito", "facilitador", facilitadorInhabilitado.getNombreFacilitador());
        return "redirect:/listarFacilitadores";
    }
}
