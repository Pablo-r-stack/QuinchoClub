package com.quinchoClub.servicios;

import com.quinchoClub.entidades.Propiedad;
import com.quinchoClub.entidades.Reserva;
import com.quinchoClub.entidades.Usuario;
import com.quinchoClub.excepciones.MiException;
import com.quinchoClub.repositorios.ReservaRepositorio;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepositorio rr;

    @Transactional
    public void crearReserva(Usuario cliente, Propiedad propiedad, LocalDate fechaInicio, LocalDate fechaFin, Double precioDia) throws MiException {
        validar(cliente, propiedad, fechaInicio, fechaFin, precioDia);
//        if (rr.buscarporReserva(cliente)!= null) {
//            throw new MiException("Cliente no encontrado");
//        }
        double precioFinal = calcularPrecio(fechaInicio, fechaFin, precioDia);
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setPropiedad(propiedad);
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);
        reserva.setPrecioTotal(precioFinal);
        //reserva.toString();
        rr.save(reserva);
    }

    private void validar(Usuario cliente, Propiedad propiedad, LocalDate fechaInicio, LocalDate fechaFin, Double precioDia) throws MiException {
        if (cliente == null) {
            throw new MiException("El nombre 'cliente' no puede ser nulo");
        }
        if (propiedad == null) {
            throw new MiException("La propiedad no puede ser nula");
        }
        if (fechaInicio == null) {
            throw new MiException("La fecha de inicio no puede estar nula");
        }
        if (fechaFin == null) {
            throw new MiException("La fecha de fin no puede estar nula");
        }
        if (precioDia.toString().isEmpty() || precioDia == null) {
            throw new MiException("-");
        }
    }

    public void borrarReserva(Long id) throws MiException {
        if (id == null || id.equals("")) {
            throw new MiException("El id proporcionado es nulo");
        } else {
            Optional<Reserva> respuesta = rr.findById(id);
            if (respuesta.isPresent()) {
                Reserva reserva = respuesta.get();
                rr.delete(reserva);
            }
        }
    }

    private Double calcularPrecio(LocalDate fechaInicio, LocalDate fechaFin, Double precioDia) {
        double precioFinal;
        Period period = Period.between(fechaInicio, fechaFin);
        double diasDeDiferencia = period.getDays();
        precioFinal=precioDia*diasDeDiferencia;
        return precioFinal;
    }
}
