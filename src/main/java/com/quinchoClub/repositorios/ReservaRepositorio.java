package com.quinchoClub.repositorios;

import com.quinchoClub.entidades.Reserva;
import com.quinchoClub.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> { 
    @Query("SELECT u FROM Reserva u WHERE u.id = :id")
    public Reserva buscarporReserva(@Param("id") String id );

    public Object buscarporReserva(Usuario cliente);

    public Object buscarporReserva(Long id);
}
