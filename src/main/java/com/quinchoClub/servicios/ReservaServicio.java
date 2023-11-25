package com.quinchoClub.servicios;

import com.quinchoClub.entidades.Propiedad;
import com.quinchoClub.entidades.Reserva;
import com.quinchoClub.entidades.Usuario;
import com.quinchoClub.excepciones.MiException;
import com.quinchoClub.repositorios.ReservaRepositorio;
import java.time.LocalDate;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServicio {
    @Autowired
    private ReservaRepositorio rr;
    
    @Transactional
    public void crearReserva( Usuario cliente,Propiedad propiedad,LocalDate fechaInicio,LocalDate fechaFin,String precioTotal)throws MiException{
        validar(cliente,propiedad,fechaInicio,fechaFin,precioTotal);
//        if (rr.buscarporReserva(cliente)!= null) {
//            throw new MiException("Cliente no encontrado");
//        }
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setPropiedad(propiedad);
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);
        reserva.setPrecioTotal(precioTotal);
        rr.save(reserva);
    }
    private void validar(Usuario cliente,Propiedad propiedad,LocalDate fechaInicio,LocalDate fechaFin,String precioTotal)throws MiException{
        if (cliente == null) {
            throw new MiException("El nombre 'cliente' no puede ser nulo");
        }
        if ( propiedad == null) {
            throw new MiException("La propiedad no puede ser nula");
        }
        if (fechaInicio == null) {
            throw new MiException("La fecha de inicio no puede estar nula");
        }
        if (fechaFin == null) {
            throw new MiException("La fecha de fin no puede estar nula");
        }
        if (precioTotal.isEmpty() || precioTotal == null) {
            throw new MiException("-");
        }
    }
    public void borrarReserva(Long id) throws MiException {
        if (id==null|| id.equals("")) {
            throw new MiException("El id proporcionado es nulo");
        } else {
            Optional<Reserva> respuesta = rr.findById(id);
            if (respuesta.isPresent()) {
                Reserva reserva = respuesta.get();
                rr.delete(reserva);
            }
        }
    }
}
