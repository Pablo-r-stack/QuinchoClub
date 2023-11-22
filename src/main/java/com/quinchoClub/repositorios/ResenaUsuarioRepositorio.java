package com.quinchoClub.repositorios;

import com.quinchoClub.entidades.ResenaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenaUsuarioRepositorio extends JpaRepository<ResenaUsuario, String> {

    @Query("SELECT u FROM ResenaUsuario u WHERE u.usuario = :usuario")
    public ResenaUsuario buscarResenaAUsuario(@Param("usuario") String usuario);
}
