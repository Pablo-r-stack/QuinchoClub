/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quinchoClub.servicios;

import com.quinchoClub.entidades.Propiedad;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * /CRUD
 */

/*
 *
 * @author pavan

se crea los servicios de forma abstracta para ser implementados directamente en la entidad con el CRUD. Asi se puede reaprovechar el codigo
Los servicios adicionales pensé en crear una lista donde cada servicio tiene un valor asociado, pero podemos pensar en ello mas adelante. Por ahora 
solo aparecen como disponibles o no (boolean)
 */
public interface IPropiedadServicio {
    
    public void crearPropiedad(Propiedad nuevaPropiedad);

    public List<Propiedad> obtenerTodasLasPropiedades();

    public Propiedad obtenerPropiedadPorId(String id);

    public void actualizarPropiedad(Propiedad nuevaPropiedad);

    public void eliminarPropiedad(String id);

    public List<Propiedad> buscarPropiedadesPorUbicacion(String ubicacion);

    public List<Propiedad> buscarPropiedadesPorTamanio(Double tamanio);

    public List<Propiedad> filtrarPropiedadesPorServicios(boolean wifi, boolean pileta, boolean parrilla, boolean accesorios, boolean cama, boolean aire);

    boolean verificarDisponibilidad(String id, Date fechaInicio, Date fechaFin);
    
}
