package com.quinchoClub.repositorios;

import com.quinchoClub.entidades.Reserva;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> desarrollotin
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface ReservaRepositorio extends JpaRepository<Reserva, String> { 
=======
public interface ReservaRepositorio extends JpaRepository<Reserva, String> {

>>>>>>> desarrollotin
    @Query("SELECT u FROM Reserva u WHERE u.id = :id")
    public Reserva buscarporReserva(@Param("id") String id);

    @Query("SELECT r FROM Reserva r WHERE r.propiedad.id = :id")
    public List<Reserva> buscarPorPropiedad(@Param("id") String id);


//    public Object buscarporReserva(Usuario cliente);
//
//    public Object buscarporReserva(Long id);
}
