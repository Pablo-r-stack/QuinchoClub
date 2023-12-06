package com.quinchoClub.controladores;
import com.quinchoClub.entidades.Usuario;
import com.quinchoClub.servicios.ResenaServicio;
import com.quinchoClub.servicios.UsuarioServicio;
import java.time.LocalDate;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/resena")
public class ResenaControlador {

    @Autowired
    private ResenaServicio resenaServi;
    @Autowired
    private UsuarioServicio usuarioServi;
    @GetMapping("/cargarResenaPropiedad")
    public String cargarResenaPropiedad(HttpSession session, ModelMap modelo){
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario != null) {
            modelo.put("usuario", usuarioServi.getOne(usuario.getId()));
        }
        return "resenaPropiedad.html";
    }
    @PostMapping("/cargarResenaPropiedad/{id}")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public String cargarResenaPropiedad(@PathVariable String id, ModelMap modelo,@RequestParam String comentario,@RequestParam Integer calificacion,@RequestParam LocalDate fechaComentario) {
        
//        resenaServi.crearResenaPropiedad(id, usuario, comentario, calificacion, fechaComentario);

        return "resenaPropiedad.html";
    }
    @GetMapping("/cargarResenaUsuario/{id}")
    public String cargarResenaUsuario(){
        return "resenasUsuario.html";
    }
//    @PostMapping("/cargarResenaUsuario/{id}")
//    @PreAuthorize("hasRole('ROLE_CLIENTE')")
//    public String cargarResenaUsuario(@PathVariable String autor, ModelMap modelo,@PathVariable String comentario,@PathVariable Integer calificacion,@PathVariable LocalDate fechaComentario) {
//        ResenaUsuario resena = new ResenaUsuario();
//        resena.setAutor(autor);
//        resena.setComentario(comentario);
//        resena.setCalificacion(calificacion);
//        resena.setFechaComentario(fechaComentario);
//        return "resenaUsuario.html";
//    }
    
}
