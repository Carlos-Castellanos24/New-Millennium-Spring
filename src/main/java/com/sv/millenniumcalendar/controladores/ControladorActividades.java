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
 * Esta clase es la encargada de realizar todas las tareas del Backend para la tabla de actividades, especificando a Spring
 * con la anotacion de @Controller que ese sera el controlador que mandara a llamar para hacer la logica, ademas,
 * con la anotacion @SessionAttributes, pasamos como sesion los datos necesarios del administrador.
 */
@Controller
@SessionAttributes({"nombreAdministrador", "idAdministrador", "apellidoAdministrador" ,"correoAdministrador"})
public class ControladorActividades {

    /**
     * Esta variable actividadService se utiliza para hacer una inyeccion a la clase ActividadService y crear un objeto de forma automatica
     * con ayuda del @Autowired.
     */
    @Autowired
    private ActividadService actividadService;

    /**
     * Esta variable categoriaService se utiliza para hacer una inyeccion a la clase CategoriaService y crear un objeto de forma automatica
     * con ayuda del @Autowired.
     */
    @Autowired
    private CategoriaService categoriaService;

    /**
     * Esta variable facilitadorService se utiliza para hacer una inyeccion a la clase FacilitadorService y crear un objeto de forma automatica
     * con ayuda del @Autowired.
     */
    @Autowired
    private FacilitadorService facilitadorService;
    
    /**
     * Esta variable administradorService se utiliza para hacer una inyeccion a la clase AdministradorService y crear un objeto de forma automatica
     * con ayuda del @Autowired.
     */
    @Autowired
    private AdministradorService administradorService;
    
    /**
     * Esta variable adminActService se utiliza para hacer una inyeccion a la clase AdministradorActividadService y crear un objeto de forma automatica
     * con ayuda del @Autowired.
     */
    @Autowired
    private AdministradorActividadService adminActService;
    
    /**
     * El metodo es el encargado de redirigir al administrador, a la lista de actividades.
     * @param model     
     * @return Retorna la lista de actividades para poder observarlas en el HTML. 
     */
    @GetMapping("/listarActividades")
    public String listarActividades(Model model) {

        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        var actividades = actividadService.listarActividades();
        model.addAttribute("actividades", actividades);
        return "actividades/listadoActividades";
    }

    /**
     * El metodo es el encargado de redirigir al administrador al formulario de agregar actividad.
     * @param model     
     * @return Retorna al formulario de agregar una actividad, listando las categorias activas y los facilitadores activos.     
     */
    @GetMapping("/agregarActividad")
    public String agregarActividad(Model model) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        this.listarCategoriasActivas(model);
        this.listarFacilitadoresActivos(model);
        return "actividades/agregarActividad";
    }

    /**
     * El metodo es el encargado de guardar una actividad, pero a su vez, se hace una consulta para
     * obtener el id del administrador, para poder agregar a la tabla de Administrador Actividad, asi mismo,
     * añade los datos necesarios a la tabla de bitacora, por eso se utilizan los @RequestParam.  
     * @param actividad     
     * @param errores     
     * @param model     
     * @param tipoRegistro     
     * @param accion     
     * @param nombreTabla     
     * @param nombreActividad  
     * @return Retorna a la lista de actividades.     
     */
    @PostMapping("/guardarActividad")
    public String guardarActividad(@Valid Actividad actividad, Errors errores, Model model,
            @RequestParam("tipoRegistro") String tipoRegistro, @RequestParam("accion") String accion,
            @RequestParam("nombreTabla") String nombreTabla, @RequestParam("nombreActividad") String nombreActividad) {

        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        else if (errores.hasErrors()) {
            return "redirect:/agregarActividad";
        }
        
        if (actividad != null && model.getAttribute("idAdministrador") != null) {           
            actividadService.insertarActividad(actividad);
            var administrador = administradorService.buscarAdministradorId((Integer) model.getAttribute("idAdministrador"));
            adminActService.insertarAdministradorActividad(administrador, actividad);
        }       
        
        actividadService.agregarBitacoraActividad(model, tipoRegistro, accion, nombreTabla, nombreActividad);
        return "redirect:/listarActividades";
    }

    /**
     * El metodo es el encargado de editar una actividad, a su vez, lista todas las categorias y facilitadores
     * que estan activos para poder ser añadidos nuevamente.
     * @param actividad
     * @param model
     * @return Retorna al formulario de editar actividad.    
     */
    @GetMapping("/editarActividad/{idActividad}")
    public String editarActividad(Actividad actividad, Model model) {

        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        this.listarCategoriasActivas(model);
        this.listarFacilitadoresActivos(model);

        actividad = actividadService.buscarActividad(actividad);
        model.addAttribute("actividad", actividad);
        return "actividades/editarActividad";
    }

    /**
     * El metodo se encarga de editar los cambios seleccionados por el administrador, a si mismo,
     * añade los datos necesarios a la tabla de bitacora. Utilizando @RequestParam.
     * @param actividad
     * @param errores
     * @param model
     * @param tipoRegistro
     * @param accion
     * @param nombreTabla
     * @param nombreActividad
     * @return Retorna a la lista de actividades.
     */
    @PostMapping("/editarCambiosActividad")
    public String editarCambiosActividad(@Valid Actividad actividad, Errors errores, Model model,
            @RequestParam("tipoRegistro") String tipoRegistro, @RequestParam("accion") String accion,
            @RequestParam("nombreTabla") String nombreTabla, @RequestParam("nombreActividad") String nombreActividad) {

        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        else if(errores.hasErrors()) {
            return "redirect:/editarActividad/{idActividad}";
        }
        
        actividadService.insertarActividad(actividad);
        actividadService.agregarBitacoraActividad(model, tipoRegistro, accion, nombreTabla, nombreActividad);
        return "redirect:/listarActividades";
    }

    /**
     * El metodo es el encargado de inhabilitar una actividad, añadiendo de forma manual, los parametros
     * para la tabla de bitacora, ya que no hay formulario para inhabilitar la actividad.
     * @param actividad
     * @param errores
     * @param idActividad
     * @param model
     * @return Retorna a la lista de actividades.
     */
    @GetMapping("/eliminarActividad/{idActividad}")
    public String eliminarActividad(@Valid Actividad actividad, Errors errores,
            @PathVariable("idActividad") Integer idActividad, Model model) {

        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        else if(errores.hasErrors()) {
            return "eliminarActividad";
        }
        actividadService.actualizarEstadoActividad(idActividad);
        var actividadInhabilitada = actividadService.encontrarActividadInhabilitada(idActividad);
        actividadService.agregarBitacoraActividad(model, "D", "inhabilito", "actividad", actividadInhabilitada.getNombreActividad());
        return "redirect:/listarActividades";
    }
    
    /**
     * El metodo es el encargado de mostrar al administrador, todos los datos relacionados con la tabla de
     * actividad.
     * @param idActividad
     * @param model
     * @return Retorna al detalle de actividades.
     */
    @GetMapping("/detallesActividad/{idActividad}")
    public String detallesActividad(@PathVariable("idActividad") Integer idActividad, Model model) {

        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }        

        this.detallesActividades(idActividad, model);
        return "actividades/detallesActividades";
    }

    /**
     * El metodo se encarga de listar todas las categorias activas para poder mostrarla en los formularios
     * de agregar actividad o editar actividad.
     * @param model     
     */
    public void listarCategoriasActivas(Model model) {
        var categoriasActivas = categoriaService.listarCategoriasActivas();
        model.addAttribute("categoriasActivas", categoriasActivas);
    }

    /**
     * El metodo se encarga de listar todos los facilitadores activos para poder mostrarlos en los formularios
     * de agregar actividad o editar actividad.
     * @param model
     */
    public void listarFacilitadoresActivos(Model model) {
        var facilitadoresActivos = facilitadorService.listarFacilitadoresActivos();
        model.addAttribute("facilitadoresActivos", facilitadoresActivos);
    }
    
    /**
     * Este metodo se encarga de obtener todos los datos de la actividad, por medio de su id obtenemos cada uno de ellos
     * y los añadimos a un modelo.
     * @param idActividad
     * @param model
     */
    public void detallesActividades(Integer idActividad, Model model){
        var actividadEncontrada = actividadService.buscarActividadPorId(idActividad);
        model.addAttribute("idActividad", actividadEncontrada.getIdActividad());
        model.addAttribute("nombreActividad", actividadEncontrada.getNombreActividad());
        model.addAttribute("idCategoria", actividadEncontrada.getCategoria().getIdCategoria());
        model.addAttribute("nombreCategoria", actividadEncontrada.getCategoria().getNombreCategoria());
        model.addAttribute("idFacilitador", actividadEncontrada.getFacilitador().getIdFacilitador());
        model.addAttribute("nombreFacilitador", actividadEncontrada.getFacilitador().getNombreFacilitador());
        model.addAttribute("fechaInicio", actividadEncontrada.getFechaInicio());
        model.addAttribute("fechaFinal", actividadEncontrada.getFechaFinal());
        model.addAttribute("diasSemana", actividadEncontrada.getDiasSemana());
        model.addAttribute("horario", actividadEncontrada.getHorasDias());
        model.addAttribute("descripcion", actividadEncontrada.getDescripcion());
        model.addAttribute("estadoActividad", actividadEncontrada.getEstadoActividad());
    }
}
