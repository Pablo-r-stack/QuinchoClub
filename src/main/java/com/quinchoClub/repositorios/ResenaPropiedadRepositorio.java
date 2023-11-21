package com.quinchoClub.repositorios;

import com.quinchoClub.entidades.ResenaPropiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenaPropiedadRepositorio  extends JpaRepository<ResenaPropiedad, String> {
    @Query("SELECT u FROM ResenaPropiedad u WHERE u.propiedad = :propiedad")
    public ResenaPropiedad buscarResenaAPropiedad(@Param("propiedad") String propiedad);
}

