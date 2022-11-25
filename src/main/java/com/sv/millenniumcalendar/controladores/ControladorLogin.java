package com.sv.millenniumcalendar.controladores;

import com.sv.millenniumcalendar.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.DefaultSessionAttributeStore;
import org.springframework.web.context.request.WebRequest;

/**
 * Esta clase se encarga de hacer la logica del BackEnd para el login del administrador, validando el login y validando sesiones.
 */
@Controller
@SessionAttributes({"nombreAdministrador", "idAdministrador", "apellidoAdministrador" ,"correoAdministrador"})
public class ControladorLogin {

    /**
     * Esta variable loginService se utiliza para hacer una inyeccion a
     * la clase LoginService y crear un objeto de forma automatica con
     * ayuda del @Autowired.
     */
    @Autowired
    private LoginService loginService;

    /**
     * Esta variable administradorService se utiliza para hacer una inyeccion a
     * la clase AdministradorService y crear un objeto de forma automatica con
     * ayuda del @Autowired.
     */
    @Autowired
    private AdministradorService administradorService;

    /**
     * Este metodo se encarga de validar el login, tomando la contraseña encriptada y validandola con el
     * metodo matches, a su misma vez, se le añaden los atributos de sesion del administrador.
     * @param correo
     * @param clave
     * @param model
     * @return Retorna al index administracion.
     */
    @PostMapping("/validarLogin")
    public String validarLogin(String correo, String clave, Model model) {
        
        var login = loginService.validarLogin(correo);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        if (login != null && encoder.matches(clave, login.getClave())) {

            var administrador = administradorService.buscarAdministradorId(login.getAdministrador().getIdAdministrador());

            if (administrador != null && administrador.getEstadoAdministrador().equals("A")) {

                model.addAttribute("nombreAdministrador", administrador.getNombreAdministrador());
                model.addAttribute("idAdministrador", administrador.getIdAdministrador());
                model.addAttribute("apellidoAdministrador", administrador.getApellidoAdministrador());
                model.addAttribute("correoAdministrador", login.getCorreo());
                
                return "redirect:/indexAdministracion";
            }
        }
        return "redirect:/loginAdministracion";
    }

    /**
     * Este metodo se encarga de redirigir al index administracion.
     * @param model
     * @return Retorna al index administracion.
     */
    @GetMapping("/indexAdministracion")
    public String indexAdministracion(Model model) {
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        model.getAttribute("nombreAdministrador");
        return "administracion/indexAdministracion";
    }

    /**
     * Este metodo se encarga de borrar todas las variables de sesion activas por el administrador.
     * @param request
     * @param status
     * @param model
     * @return Retorna al login administracion.
     */
    @GetMapping("/cerrarSesion")
    public String cerrarSesion(WebRequest request, DefaultSessionAttributeStore status, ModelMap model) {
        
        if (model.getAttribute("nombreAdministrador") != null || model.getAttribute("idAdministrador") != null || 
                model.getAttribute("apellidoAdministrador") != null || model.getAttribute("correoAdministrador") != null) {
            
            model.remove("nombreAdministrador");
            model.remove("idAdministrador");
            model.remove("apellidoAdministrador");
            model.remove("correoAdministrador");
            
            status.cleanupAttribute(request, "nombreAdministrador");
            status.cleanupAttribute(request, "idAdministrador");
            status.cleanupAttribute(request, "apellidoAdministrador");
            status.cleanupAttribute(request, "correoAdministrador");
            
            return "redirect:/loginAdministracion";
        }
        return "redirect:/404";
    }
}
