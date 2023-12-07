/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub.controladores;

import com.quinchoClub.entidades.Propiedad;
import com.quinchoClub.entidades.ResenaUsuario;
import com.quinchoClub.entidades.Usuario;
import com.quinchoClub.excepciones.MiException;
import com.quinchoClub.servicios.ImagenServicio;
import com.quinchoClub.servicios.UsuarioServicio;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ImagenServicio imagenServicio;

    @GetMapping("/registrar")
    public String registroUsuario() {
        return "register.html";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email,
            @RequestParam String password, @RequestParam String password2, @RequestParam Integer dni, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaDeNacimiento,
            @RequestParam Integer telefono, @RequestParam(required = false) boolean propietario, ModelMap modelo, RedirectAttributes redirectAttributes) {
        try {
            usuarioServicio.crearUsuario(nombre, apellido, email, password, password2, dni, fechaDeNacimiento, telefono, propietario);
            redirectAttributes.addFlashAttribute("mensaje", "Registo completado con Exito");
            return "redirect:/";
        } catch (MiException ex) {
            System.out.println(ex.getMessage());
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            return "redirect:/usuario/registrar";
        }
    }

    @GetMapping("/login")
    public String loginUsuario(@RequestParam(required = false) String error, HttpSession session, ModelMap modelo, RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario != null) {
            modelo.put("usuario", usuarioServicio.getOne(usuario.getId()));
        }
        if (error != null) {
            modelo.put("error", "usuario o contraseña invalidos");
        }
        return "login.html";
    }

    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listarUsuarios(ModelMap modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario != null) {
            modelo.put("usuario", usuarioServicio.getOne(usuario.getId()));
        }
        List<Usuario> listaUsuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("listaUsuarios", listaUsuarios);
        return "listaUsuarios.html";
    }

    @GetMapping("/modificar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String modificarUsuarioVista(@PathVariable String id, ModelMap modelo) {
        //aca insertar lista de roles para modelar el select desde la vista.
        modelo.put("usuario", usuarioServicio.getOne(id));
        return "modificarUsuario.html";
    }

    @PostMapping("/modificar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String modificarUsuario(@PathVariable String id, String nombre, String apellido, String email,
            @RequestParam String rol, Integer dni,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaDeNacimiento, Integer telefono, ModelMap modelo) {
        try {
            usuarioServicio.actualizar(id, nombre, apellido, email, rol, dni, fechaDeNacimiento, telefono);
            System.out.println("Actualizado con exito");
            return "redirect:../lista";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            modelo.put("error", ex.getMessage());
            return "modificarUsuario.html";
        }
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id) {
        try {
            usuarioServicio.borrarUsuario(id);
            System.out.println("Borrado con Exito");
            return "redirect:/usuario/lista";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect: /usuario/lista";
        }
    }

    @GetMapping("/propiedades")
    @PreAuthorize("hasRole('ROLE_PROPIETARIO')")
    public String propiedadesUsuario(HttpSession session, ModelMap modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario != null) {
            Usuario propietario = usuarioServicio.getOne(usuario.getId());
            modelo.put("usuario", usuarioServicio.getOne(usuario.getId()));
            List<Propiedad> propiedades = propietario.getPropiedades();
            System.out.println(propiedades);
            modelo.addAttribute("propiedades", propiedades);
        }
        return "propiedadesUsuario.html";
    }

    @GetMapping("/perfil/{id}")
    public String perfilUsuario(@PathVariable String id, HttpSession session, ModelMap modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario != null) {
            modelo.put("usuario", usuarioServicio.getOne(usuario.getId()));
        }
        return "perfilUsuario.html";
    }
    
    @GetMapping("/verPerfil/{id}")
    public String verPerfil(@PathVariable String id, HttpSession session, ModelMap modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario != null) {
            modelo.put("usuario", usuarioServicio.getOne(usuario.getId()));
        }
        Usuario user = usuarioServicio.getOne(id);
        modelo.put("user", user);
        List<ResenaUsuario> resenas= user.getResenaDeUsario();
        modelo.put("resenas", resenas);
        return "vistaPerfil.html";
    }

    @PostMapping("/foto/{id}")
    public String cargarFoto(@PathVariable String id, @RequestParam("imagen") MultipartFile archivo) {
        try {
            if (id != null && archivo != null && !archivo.isEmpty()) {
                Usuario usuario = usuarioServicio.getOne(id);
                usuario.setImagen(imagenServicio.guardarImagen(archivo));
                usuarioServicio.guardarUsuarioCompleto(usuario);

                // Redirige a la página del perfil u otra página relevante
                return "redirect:/";
            }
        } catch (Exception e) {
            // Maneja la excepción (puede loguear el error, mostrar un mensaje de error, etc.)
            e.printStackTrace();

        }
        return "redirect:/";
    }

    @GetMapping("/cambiarContrasenaForm")
    public String mostrarFormularioCambioContrasena(HttpSession session, ModelMap modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario != null) {
            modelo.put("usuario", usuarioServicio.getOne(usuario.getId()));
        }
        return "modificarContrasena.html"; // Nombre de la vista del formulario
    }

    @PostMapping("/cambiarContrasena/{id}")
    public String procesarCambioContrasena(@PathVariable String id,
            @RequestParam String contrasenaActual,
            @RequestParam String nuevaContrasena,
            @RequestParam String confirmarNuevaContrasena,
            Model model) {
        boolean cambioExitoso = usuarioServicio.cambiarContrasena(
                id, contrasenaActual, nuevaContrasena, confirmarNuevaContrasena);

        if (cambioExitoso) {
            model.addAttribute("exito", "BUEEEENA WACHIN, ÉXITO!! MUCHAAAAACHOOOOO");
            return "redirect:/"; // Redirigir a la página de éxito
        } else {
            model.addAttribute("error", "NOOOO HERMANO, NO FUNCIONÓ");
            return "error"; // Redirigir al formulario con un mensaje de error
        }
    }

    @GetMapping("/exito")
    public String mostrarExitoCambioContrasena() {
        return "exito";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable String id, HttpSession session, ModelMap modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario != null) {
            modelo.put("usuario", usuarioServicio.getOne(usuario.getId()));
        }

        return "editarPerfil.html";
    }

    @PostMapping("/editar/{id}")
    public String actualizarPerfil(@PathVariable String id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam Integer dni,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaDeNacimiento, @RequestParam Integer telefono) {
        // Update profile info and change password
        usuarioServicio.actualizarPerfil(id, nombre, apellido, email, dni, fechaDeNacimiento, telefono);

        return "redirect:/"; // Redirect with a success message
    }
}
