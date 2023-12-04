/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub.controladores;

import com.quinchoClub.entidades.Propiedad;
import com.quinchoClub.entidades.Usuario;
import com.quinchoClub.servicios.ImagenServicio;
import com.quinchoClub.servicios.PropiedadServicio;
import com.quinchoClub.servicios.UsuarioServicio;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Pablo
 */
@Controller
@RequestMapping("/propiedad")
public class PropiedadControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private PropiedadServicio propiedadServicio;
    @Autowired
    private ImagenServicio ImagenServicio;

    @GetMapping("/lista")
    public String listarPropiedades(ModelMap modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario != null) {
            modelo.put("usuario", usuarioServicio.getOne(usuario.getId()));
        }
        List<Propiedad> propiedades = propiedadServicio.obtenerTodasLasPropiedades();
        modelo.addAttribute("propiedades", propiedades);
        return "listaPropiedades.html";
    }

    @GetMapping("/registrar")
    @PreAuthorize("hasRole('ROLE_PROPIETARIO')")
    public String registrarPropiedad(ModelMap modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario != null) {
            modelo.put("usuario", usuarioServicio.getOne(usuario.getId()));
        }
        return "registrarPropiedad.html";
    }

    @PostMapping("/registrar/{id}")
    @PreAuthorize("hasRole('ROLE_PROPIETARIO')")
    public String cargarPropiedad(@PathVariable String id, @RequestParam String tipo, @RequestParam String detalles,
            @RequestParam String ubicacion, @RequestParam Double tamanio,
            @RequestParam(required = false) boolean wifi, @RequestParam(required = false) boolean pileta,
            @RequestParam(required = false) boolean accesorios, @RequestParam(required = false) boolean cama,
            @RequestParam(required = false) boolean aire, List<MultipartFile> imagenes, @RequestParam(required = false) boolean parrilla,
            @RequestParam Double precioDia) {
        Propiedad propiedad = new Propiedad();
        propiedad.setTipo(tipo);
        propiedad.setDetalles(detalles);
        propiedad.setUbicacion(ubicacion);
        propiedad.setTamanio(tamanio);
        if (!imagenes.isEmpty()) {
            propiedad.setImagenes(ImagenServicio.guardarImagenLista(imagenes));
        }
        //graba la fecha de hoy, debe **pendiente** cambiar atributo a LocalDate
        propiedad.setDisponibilidad(new Date());
        propiedad.setPrecioDia(tamanio);
        propiedad.setWifi(wifi);
        propiedad.setPileta(pileta);
        propiedad.setParrilla(parrilla);
        propiedad.setAccesorios(accesorios);
        propiedad.setCama(cama);
        propiedad.setAire(aire);
        //propiedad.setImagenes(obtenerRutas(imagenes));
        propiedad.setPrecioDia(precioDia);
        Usuario usuario = usuarioServicio.getOne(id);
        List<Propiedad> propiedades = usuario.getPropiedades();
        try {
            propiedadServicio.crearPropiedad(propiedad);
            propiedades.add(propiedad);
            usuario.setPropiedades(propiedades);
            usuarioServicio.guardarUsuarioCompleto(usuario);
            System.out.println("Carga exitosa");
            return "redirect:/usuario/propiedades";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "registrarPropiedad.html";
        }
    }

    @GetMapping("/modificar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROPIETARIO')")
    public String modificarPropiedad(@PathVariable String id, ModelMap modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario != null) {
            modelo.put("usuario", usuarioServicio.getOne(usuario.getId()));
        }
        Propiedad propiedad = propiedadServicio.obtenerPropiedadPorId(id);
        modelo.put("propiedad", propiedad);
        return "modificarPropiedad.html";
    }

    @PostMapping("/modificar/{idPropiedad}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROPIETARIO')")
    public String modificarPropiedad(@PathVariable String idPropiedad, @RequestParam String tipo, @RequestParam String detalles,
            @RequestParam String ubicacion, @RequestParam Double tamanio,
            @RequestParam(required = false) boolean wifi, @RequestParam(required = false) boolean pileta,
            @RequestParam(required = false) boolean accesorios, @RequestParam(required = false) boolean cama,
            @RequestParam(required = false) boolean aire, List<MultipartFile> imagenes, @RequestParam(required = false) boolean parrilla,
            @RequestParam Double precioDia) {
        try {
            Propiedad propiedad = propiedadServicio.obtenerPropiedadPorId(idPropiedad);
            propiedad.setTipo(tipo);
            propiedad.setDetalles(detalles);
            propiedad.setUbicacion(ubicacion);
            propiedad.setTamanio(tamanio);
            propiedad.setImagenes(ImagenServicio.guardarImagenLista(imagenes));
            propiedad.setWifi(wifi);
            propiedad.setPileta(pileta);
            propiedad.setParrilla(parrilla);
            propiedad.setAccesorios(accesorios);
            propiedad.setCama(cama);
            propiedad.setAire(aire);
            propiedad.setPrecioDia(precioDia);
            propiedadServicio.actualizarPropiedad(propiedad);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:/usuario/propiedades";
        }
        return "redirect:/usuario/propiedades";
    }

    @PostMapping("/eliminar/{idPropiedad}/{idUsuario}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROPIETARIO')")
    public String eliminarPropiedad(@PathVariable String idPropiedad, @PathVariable String idUsuario) {
        Usuario usuario = usuarioServicio.getOne(idUsuario);
        List<Propiedad> propiedades = usuario.getPropiedades();
        try {
            Propiedad propiedad = propiedadServicio.obtenerPropiedadPorId(idPropiedad);
            propiedades.remove(propiedad);
            usuario.setPropiedades(propiedades);
            usuarioServicio.guardarUsuarioCompleto(usuario);
            propiedadServicio.eliminarPropiedad(idPropiedad);
            return "redirect:/usuario/propiedades";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:/usuario/propiedades";
        }
    }

    @GetMapping("/publicacion/{id}")
    public String saberMasVista(@PathVariable String id, ModelMap modelo, HttpSession session) {

        //aca insertar lista de roles para modelar el select desde la vista.
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        Usuario vendedor = usuarioServicio.buscarPorPropiedad(id);
        if (usuario != null) {
            modelo.put("usuario", usuarioServicio.getOne(usuario.getId()));
        }
        modelo.put("propiedad", propiedadServicio.obtenerPropiedadPorId(id));
        modelo.addAttribute("vendedor", vendedor);
        return "post.html";
    }

//    public List<String> obtenerRutas(List<MultipartFile> imagenes){
//        List<String> rutasObtenidas = new ArrayList();
//        if (!imagenes.isEmpty()) {
//            for (MultipartFile imagen : imagenes) {
//                String rutaImagen = "";
//                String nombreArchivo = UUID.randomUUID().toString()+ "_" + imagen.getOriginalFilename() + ".jpg";
//                rutaImagen = "/img/prop/" + nombreArchivo;
//                File directorioDestino = new File("src/main/resources/static/img/prop");
//                if(!directorioDestino.exists()){
//                    directorioDestino.mkdirs();
//                }
//                Path rutaDestino = Paths.get("src/main/resources/static/img/prop", nombreArchivo);
//                try {
//                    Files.write(rutaDestino, imagen.getBytes(), StandardOpenOption.CREATE);
//                } catch (IOException ex) {
//                    Logger.getLogger(PropiedadControlador.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                rutasObtenidas.add(rutaImagen);
//            }
//        }
//        return rutasObtenidas;
//    }
    
    
         @PostMapping("/buscarPropiedades")
    public List<Propiedad> buscarPropiedades(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String ubicacion,
            @RequestParam(required = false) double precioDia,
            @RequestParam(required = false, defaultValue = "false") boolean wifi,
            @RequestParam(required = false, defaultValue = "false") boolean pileta,
            @RequestParam(required = false, defaultValue = "false") boolean parrilla
    ) {
        // Llamar al servicio para obtener las propiedades filtradas
        return propiedadServicio.buscarPropiedades(tipo, ubicacion, precioDia, wifi, pileta, parrilla);
    }
}


