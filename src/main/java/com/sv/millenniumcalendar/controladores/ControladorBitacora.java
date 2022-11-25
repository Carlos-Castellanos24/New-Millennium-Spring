package com.sv.millenniumcalendar.controladores;

import com.sv.millenniumcalendar.servicio.BitacoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Esta clase es la encargada de realizar todas las tareas del Backend para la
 * tabla de bitacora, especificando a Spring con la anotacion de @Controller que ese sera el controlador que mandara a llamar para hacer la
 * logica, ademas, con la anotacion @SessionAttributes, pasamos como sesion los datos necesarios del administrador.
 */
@Controller
@SessionAttributes({"nombreAdministrador", "idAdministrador", "apellidoAdministrador" ,"correoAdministrador"})
public class ControladorBitacora {
    
    /**
     * Esta variable bitacoraService se utiliza para hacer una inyeccion a
     * la clase BitacoraService y crear un objeto de forma automatica con
     * ayuda del @Autowired.
     */
    @Autowired
    private BitacoraService bitacoraService;
    
    /**
     * El metodo se encarga de listar todos los movimientos que hay en la aplicacion, ya sea un insert, update o delete.
     * @param model
     * @return Retorna a la lista de bitacoras.
     */
    @GetMapping("/listarBitacoras")
    public String listarBitacoras(Model model){
        if (model.getAttribute("nombreAdministrador") == null) {
            return "redirect:/404";
        }
        var bitacoras = bitacoraService.listarBitacoras();
        model.addAttribute("bitacoras", bitacoras);
        return "bitacoras/listarBitacoras";
    }
}
