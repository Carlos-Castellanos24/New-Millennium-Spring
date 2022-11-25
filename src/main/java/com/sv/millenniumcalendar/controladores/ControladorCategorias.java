package com.sv.millenniumcalendar.controladores;

import com.sv.millenniumcalendar.clases.Categoria;
import com.sv.millenniumcalendar.servicio.CategoriaService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * Esta clase es la encargada de realizar todas las tareas del Backend para la
 * tabla de categorias, especificando a Spring con la anotacion de @Controller que ese sera el controlador que mandara a llamar para hacer la
 * logica, ademas, con la anotacion @SessionAttributes, pasamos como sesion los datos necesarios del administrador.
 */
@Controller
@SessionAttributes({"nombreAdministrador", "idAdministrador", "apellidoAdministrador" ,"correoAdministrador"})
public class ControladorCategorias {

    /**
     * Esta variable categoriaService se utiliza para hacer una inyeccion a
     * la clase CategoriaService y crear un objeto de forma automatica con
     * ayuda del @Autowired.
     */
    @Autowired
    private CategoriaService categoriaService;

    /**
     * El metodo se encarga de listar todas las categorias.
     * @param model
     * @return Retorna a la lista de categorias.
     */
    @GetMapping("/listarCategorias")
    public String listarCategoriaes(Model model) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        var categorias = categoriaService.listarCategorias();
        model.addAttribute("categorias", categorias);
        return "categorias/listadoCategorias";
    }

    /**
     * El metodo se encarga de redirigir al formulario de agregar categoria.
     * @param model
     * @return Retorna al formulario de agregar categoria.
     */
    @GetMapping("/agregarCategoria")
    public String agregarCategoria(Model model) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        return "categorias/agregarCategoria";
    }

    /**
     * El metodo es el encargado de agregar una categoria, a su vez, agrega a la tabla de bitacora.
     * @param categoria
     * @param errores
     * @param model
     * @param tipoRegistro
     * @param accion
     * @param nombreTabla
     * @param nombreCategoria
     * @return retorna a la lista de categorias.
    */
    @PostMapping("/guardarCategoria")
    public String guardarCategoria(@Valid Categoria categoria, Errors errores, Model model,
            @RequestParam("tipoRegistro") String tipoRegistro, @RequestParam("accion") String accion,
            @RequestParam("nombreTabla") String nombreTabla, @RequestParam("nombreCategoria") String nombreCategoria) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        else if(errores.hasErrors()) {
            return "redirect:/agregarCategoria";
        }
        categoriaService.insertarCategoria(categoria);
        categoriaService.agregarBitacoraCategoria(model, tipoRegistro, accion, nombreTabla, nombreCategoria);
        return "redirect:/listarCategorias";
    }

    /**
     * El metodo es el encargado de redirigir al formulario de editar categoria.
     * @param categoria
     * @param model
     * @return retorna al formulario de editar categoria.
     */
    @GetMapping("/editarCategoria/{idCategoria}")
    public String editarCategoria(Categoria categoria, Model model) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        categoria = categoriaService.buscarCategoria(categoria);
        model.addAttribute("categoria", categoria);
        return "categorias/editarCategoria";
    }

    /**
     * El metodo se encarga de editar los cambios seleccionados por el administrador, de la tabla categoria.
     * @param categoria
     * @param errores
     * @param model
     * @param tipoRegistro
     * @param accion
     * @param nombreTabla
     * @param nombreCategoria
     * @return Retorna al listado de categorias.
     */
    @PostMapping("/editarCambiosCategoria")
    public String editarCambiosCategoria(@Valid Categoria categoria, Errors errores, Model model,
            @RequestParam("tipoRegistro") String tipoRegistro, @RequestParam("accion") String accion,
            @RequestParam("nombreTabla") String nombreTabla, @RequestParam("nombreCategoria") String nombreCategoria) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        else if(errores.hasErrors()) {
            return "redirect:/editarCategoria/{idCategoria}";
        }
        categoriaService.insertarCategoria(categoria);
        categoriaService.agregarBitacoraCategoria(model, tipoRegistro, accion, nombreTabla, nombreTabla);
        return "redirect:/listarCategorias";
    }

    /**
     * Este metodo se encarga de inhabilitar una categoria seleccionada.
     * @param categoria
     * @param errores
     * @param idCategoria
     * @param model
     * @return Retorna al listado de categorias.
     */
    @GetMapping("/eliminarCategoria/{idCategoria}")
    public String eliminarCategoria(@Valid Categoria categoria, Errors errores,
            @PathVariable("idCategoria") Integer idCategoria, Model model) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        if (errores.hasErrors()) {
            return "eliminarCategoria";
        }
        categoriaService.actualizarEstadoCategoria(idCategoria);
        var categoriaInhabilitada = categoriaService.encontrarCategoriaInhabilitada(idCategoria);
        categoriaService.agregarBitacoraCategoria(model, "D", "inhabilito", "categoria", categoriaInhabilitada.getNombreCategoria());
        return "redirect:/listarCategorias";
    }
}
