package com.quinchoClub.servicios;

import com.quinchoClub.entidades.Propiedad;
import com.quinchoClub.entidades.Reserva;
import com.quinchoClub.entidades.Usuario;
import com.quinchoClub.enumeraciones.Estado;
import com.quinchoClub.enumeraciones.Rol;
import com.quinchoClub.excepciones.MiException;
import com.quinchoClub.repositorios.ReservaRepositorio;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
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
        double precioFinal = calcularPrecio(fechaInicio, fechaFin, precioDia);
        Reserva reserva = new Reserva();
        reserva.setUsuario(cliente);
        reserva.setPropiedad(propiedad);
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);
        reserva.setPrecioTotal(precioFinal);
        reserva.setEstado(Estado.PENDIENTE);

        rr.save(reserva);
    }

    @Transactional
    public Reserva bucarReservaId(String id) throws MiException {
        rr.buscarporReserva(id);
        Optional<Reserva> respuesta = rr.findById(id);
        if (respuesta.isPresent()) {
            Reserva reserva = respuesta.get();
            return reserva;
        } else {
            throw new MiException("No se encuentra la reserva");
        }
    }

    @Transactional
    public List<Reserva> listaReserva(Usuario usuario) throws MiException {

        switch (usuario.getRol()) {
            case CLIENTE:
                return rr.buscarPorUsuario(usuario.getId());
            case PROPIETARIO:
                
                List<Propiedad> propiedades = usuario.getPropiedades();
                List<Reserva> listaReserva = new ArrayList<>(propiedades.size());

                for (Propiedad propiedad : propiedades) {
                    listaReserva.addAll(rr.buscarPorPropiedad(propiedad.getId()));
                }
                listaReserva.toString();
                return listaReserva;
            default:
                throw new MiException("El id proporcionado es nulo");
        }

    }

    @Transactional
    public void borrarReserva(String idReserva, Usuario usuario) throws MiException {
        if (idReserva == null || idReserva.equals("")) {
            throw new MiException("El id proporcionado es nulo");
        } else {
            Reserva reserva = bucarReservaId(idReserva);
            if (usuario.getRol().equals(Rol.CLIENTE) && reserva.getEstado().equals(Estado.PENDIENTE)) {
                rr.delete(reserva);
            } else if (usuario.getRol().equals(Rol.PROPIETARIO) || usuario.getRol().equals(Rol.ADMIN)) {
                rr.delete(reserva);
            }
        }
    }

    @Transactional
    public void cambiarEstado(String idReserva, String estado) throws MiException {
        if (idReserva == null || idReserva.equals("")) {
            throw new MiException("El id proporcionado es nulo");
        } else {
            Reserva reserva = bucarReservaId(idReserva);
            if (estado.equals("CONFIRMADA")) {
                reserva.setEstado(Estado.CONFIRMADA);
                rr.save(reserva);
            } else if (estado.equals("COMPLETADA")) {
                reserva.setEstado(Estado.COMPLETADA);
                rr.save(reserva);
            }

        }

    }
    //    private void reservaCompletada(LocalDate fechaFin, String id) throws MiException {
    //        if (id == null || id.equals("")) {
    //            throw new MiException("El id proporcionado es nulo");
    //        } else {
    //            Reserva reserva = bucarReservaId(id);
    //            if (fechaFin == LocalDate.now()) {
    //
    //                reserva.setEstado(Estado.COMPLETADA);
    //                rr.save(reserva);
    //            }
    //        }
    //    }

    private Double calcularPrecio(LocalDate fechaInicio, LocalDate fechaFin, Double precioDia) {
        double precioFinal;
        Period period = Period.between(fechaInicio, fechaFin);
        double diasDeDiferencia = period.getDays();
        precioFinal = precioDia * diasDeDiferencia;
        return precioFinal;
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
}
