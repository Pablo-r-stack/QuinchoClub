/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub.controladores;

import com.quinchoClub.entidades.Propiedad;
import com.quinchoClub.servicios.PropiedadServicio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Pablo
 */
@Controller
@RequestMapping("/propiedad")
public class PropiedadControlador {
    @Autowired
    private PropiedadServicio propiedadServicio;
    @GetMapping("/lista")
    public String listarPropiedades(ModelMap modelo){
        List<Propiedad> propiedades = propiedadServicio.obtenerTodasLasPropiedades();
        modelo.addAttribute("propiedades", propiedades);
        return "listaPropiedades.html";
    }
    @GetMapping("/registrar")
    public String registrarPropiedad(){
        return "registrarPropiedad.html";
    }
    @PostMapping("/registrar")
    public String cargarPropiedad(@RequestParam String tipo, @RequestParam String detalles,
            @RequestParam String ubicacion, @RequestParam Double tamanio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date disponibilidad,
            @RequestParam boolean wifi, @RequestParam boolean pileta, @RequestParam boolean accesorios, @RequestParam boolean cama, @RequestParam boolean aire){
        Propiedad propiedad = new Propiedad();
        propiedad.setTipo(tipo);
        propiedad.setDetalles(detalles);
        propiedad.setUbicacion(ubicacion);
        propiedad.setTamanio(tamanio);
        propiedad.setDisponibilidad(disponibilidad);
        propiedad.setWifi(wifi);
        propiedad.setPileta(pileta);
        propiedad.setAccesorios(accesorios);
        propiedad.setCama(cama);
        propiedad.setAire(aire);
        try {
        propiedadServicio.crearPropiedad(propiedad);
            System.out.println("Carga exitosa");
            return "redirect:/propiedad/lista";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "registrarPropiedad.html";
        }
    }
    @GetMapping("/modificar/{id}")
    public String modificarPropiedad(@PathVariable String id, ModelMap modelo){
        Propiedad propiedad = propiedadServicio.obtenerPropiedadPorId(id);
        modelo.put("propiedad", propiedad);
        return "modificarPropiedad.html";
    }
    @PutMapping("/modificar")
    public String modificarPropiedad(@PathVariable String id){
        return null;
    }
    @PostMapping("/eliminar/{id}")
    public String eliminarPropiedad(@PathVariable String id){
        try {
            propiedadServicio.eliminarPropiedad(id);
            return "redirect:/propiedad/lista";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:/propiedad/lista";
        }
    }
}
