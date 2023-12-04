/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub.servicios;

import com.quinchoClub.entidades.Propiedad;
import com.quinchoClub.repositorios.PropiedadRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pavan
 */
@Service
@Transactional
public class PropiedadServicio implements IPropiedadServicio {
    
    @Autowired
    private PropiedadRepositorio pr;
    
    @Override
    public void crearPropiedad(Propiedad nuevaPropiedad){
       validarPropiedad(nuevaPropiedad); // Método para validar los datos
        pr.save(nuevaPropiedad);
    }

    @Override
    public List<Propiedad> obtenerTodasLasPropiedades() {
        return pr.findAll();
    }

    @Override
    public Propiedad obtenerPropiedadPorId(String id) {
        return pr.findById(id).orElse(null);
    }

    @Override
    public void actualizarPropiedad(Propiedad nuevaPropiedad) {
        pr.save(nuevaPropiedad);
    }

    @Override
    public void eliminarPropiedad(String id) {
        pr.deleteById(id);
    }

    @Override
    public List<Propiedad> buscarPropiedadesPorUbicacion(String ubicacion) {
        return pr.findByUbicacion(ubicacion);
    }

    @Override
    public List<Propiedad> buscarPropiedadesPorTamanio(Double tamanio) {
         return pr.findByTamanio(tamanio);
    }
    
       public List<Propiedad> buscarPropiedades(String tipo, String ubicacion, Double precioDia, boolean wifi, boolean pileta, boolean parrilla) {
        // Supongamos que tienes una lista de todas las propiedades disponibles
        List<Propiedad> todasLasPropiedades = pr.findAll();

        // Creamos una lista para almacenar las propiedades que cumplen con los criterios de búsqueda
        List<Propiedad> propiedadesFiltradas = new ArrayList<>();

        // Iteramos sobre todas las propiedades para aplicar los filtros
        for (Propiedad propiedad : todasLasPropiedades) {
            // Verificamos los criterios de búsqueda
            if ((tipo == null || tipo.equals(propiedad.getTipo()))
                    && (ubicacion == null || ubicacion.equals(propiedad.getUbicacion()))
                    && (precioDia == null || precioDia >= propiedad.getPrecioDia())
                    && (!wifi || propiedad.isWifi())
                    && (!pileta || propiedad.isPileta())
                    && (!parrilla || propiedad.isParrilla())) {
                // Si la propiedad cumple con todos los criterios, la agregamos a la lista filtrada
                propiedadesFiltradas.add(propiedad);
            }
        }
        return propiedadesFiltradas;
    }

    @Override
    public List<Propiedad> filtrarPropiedadesPorServicios(boolean wifi, boolean pileta, boolean parrilla, boolean accesorios, boolean cama, boolean aire) {
        return pr.findByWifiAndPiletaAndParrillaAndAccesoriosAndCamaAndAire(wifi, pileta, parrilla, accesorios, cama, aire);
    }

    @Override
    public boolean verificarDisponibilidad(String id, Date fechaInicio, Date fechaFin) {
         // Verificar que las fechas sean válidas
        if (fechaInicio == null || fechaFin == null || fechaInicio.after(fechaFin)) {
            throw new IllegalArgumentException("Fechas de reserva no válidas");
        }

        // Obtener la propiedad por ID
        Propiedad propiedad = pr.findById(id).orElse(null);

        // Verificar la disponibilidad - Eso podemos implementar despues porque tendriamos que crear la clase "reserva"
        /*
        if (propiedad != null) {
            List<Reserva> reservas = propiedad.getReservas();
            for (Reserva reserva : reservas) {
                if (reserva.getFechaInicio().before(fechaFin) && reserva.getFechaFin().after(fechaInicio)) {
                    // Existe una reserva que se superpone con el rango de fechas proporcionado
                    return false;
                }
            }
            // No hay reservas que se superpongan, la propiedad está disponible
            return true;
        }
        */
        // La propiedad con el ID dado no existe
        throw new IllegalArgumentException("No se encontró la propiedad con ID: " + id);
    }
    //Metodo para validar que los datos no sean nulos y que el tamanio del terreno sea un numero positivo
    private void validarPropiedad(Propiedad propiedad) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Propiedad>> violations = validator.validate(propiedad);

    if (!violations.isEmpty()) {
        throw new IllegalArgumentException("Para registrar una propiedad debes tener minimo: tipo, detalles y ubicación. Además de eso, el tamaño debe ser un valor positivo.");
    }
    }
}

