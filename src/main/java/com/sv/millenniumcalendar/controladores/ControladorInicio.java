package com.sv.millenniumcalendar.controladores;

import com.sv.millenniumcalendar.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Esta clase se encarga de redirigir al cliente a todo lo que tiene disponible en la pagina web, especificando a Spring con @Controller, 
 * que este sera el controlador que hara los procesos logicos para el cliente.
 */
@Controller
public class ControladorInicio {
    
    /**
     * Esta variable actividadService se utiliza para hacer una inyeccion a
     * la clase ActividadService y crear un objeto de forma automatica con
     * ayuda del @Autowired.
     */
    @Autowired
    private ActividadService actividadService;
    
    /**
     * Esta variable categoriaService se utiliza para hacer una inyeccion a
     * la clase CategoriaService y crear un objeto de forma automatica con
     * ayuda del @Autowired.
     */
    @Autowired
    private CategoriaService categoriaService;
    
    /**
     * Esta variable facilitadorService se utiliza para hacer una inyeccion a
     * la clase FacilitadorService y crear un objeto de forma automatica con
     * ayuda del @Autowired.
     */
    @Autowired
    private FacilitadorService facilitadorService;
    
    
    /**
     * Este metodo se encarga de redirigir al cliente de forma automatica al index.
     * @return Retorna al index.
     */
    @GetMapping("/")
    public String inicio(){
        return "redirect:/index";
    }
    
    /**
     * Este metodo se encarga de rederigir al cliente a la pagina 404, en algun dado caso entre a una pagina sin permisos accesores.
     * @return Retorna a la pagina de error 404.
     */
    @GetMapping("/404")
    public String paginaError(){
        return "404";
    }
    
    /**
     * Este metodo se encarga de redirigir al cliente al index con las actividades activas listadas.
     * @param model
     * @return Retorna al index.
     */
    @GetMapping("/index")
    public String index(Model model){
        this.listarActividadesActivas(model);
        return "index";
    }
    
    /**
     * Este metodo se encarga de redirigir al cliente a la pagina con las categorias activas listadas.
     * @param model
     * @return Retorna a la pagina de categorias.
     */
    @GetMapping("/categorias")
    public String categorias(Model model){
        this.listarCategoriasActivas(model);
        return "categorias";
    }
    
    /**
     * Este metodo se encarga de redirigir al cliente a la pagina con los facilitadores activos listadas.
     * @param model
     * @return Retorna a la pagina de facilitadores.
     */
    @GetMapping("/facilitadores")
    public String facilitadores(Model model){
        this.listarFacilitadoresActivos(model);
        return "facilitadores";
    }
    
    /**
     * Este metodo se encarga de redirigir al cliente o administrador al login para acceder al index administracion.
     * @return Retorna al login administracion.
     */
    @GetMapping("/loginAdministracion")
    public String loginAdministracion(){
        return "loginAdministracion";
    }    
    
    /**
     * El metodo se encarga de listar todas las categorias activas para poder mostrarla en los formularios.
     * @param model     
     */
    public void listarCategoriasActivas(Model model){
        var categoriasActivas = categoriaService.listarCategoriasActivas();
        model.addAttribute("categoriasActivas", categoriasActivas); 
    }
    
    /**
     * El metodo se encarga de listar todos los facilitadores activos para poder mostrarlos.
     * @param model
     */
    public void listarFacilitadoresActivos(Model model){
        var facilitadoresActivos = facilitadorService.listarFacilitadoresActivos();
        model.addAttribute("facilitadoresActivos", facilitadoresActivos);       
    }
    
    /**
     * El metodo se encarga de listar todas las actividades activas para poder mostrarlos en los formularios.
     * @param model
     */
    public void listarActividadesActivas(Model model){
        var actividadesActivas = actividadService.listarActividadesActivas();
        model.addAttribute("actividadesActivas", actividadesActivas);
    }  
}
