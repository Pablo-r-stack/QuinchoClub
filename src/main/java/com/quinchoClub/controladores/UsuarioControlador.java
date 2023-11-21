/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub.controladores;

import com.quinchoClub.entidades.Usuario;
import com.quinchoClub.enumeraciones.Rol;
import com.quinchoClub.excepciones.MiException;
import com.quinchoClub.servicios.UsuarioServicio;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/registrar")
    public String registroUsuario() {
        return "register.html";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email,
            @RequestParam String password, @RequestParam String password2, @RequestParam Integer dni, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaDeNacimiento,
            @RequestParam Integer telefono, @RequestParam(required = false) boolean propietario, ModelMap modelo) {
        try {
            usuarioServicio.crearUsuario(nombre, apellido, email, password, password2, dni, fechaDeNacimiento, telefono, propietario);
            return "redirect:/";
        } catch (MiException ex) {
            System.out.println(ex.getMessage());
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            return "register.html";
        }
    }

    @GetMapping("/login")
    public String loginUsuario(@RequestParam(required = false) String error, ModelMap modelo) {
        if(error != null){
            modelo.put("error", "usuario o contraseña invalidos");
        }
        return "login.html";
    }

    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listarUsuarios(ModelMap modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if(usuario != null){
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
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaDeNacimiento, Integer telefono,ModelMap modelo) {
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
            return "redirect:/usuario/lista";
        }
    }
}
