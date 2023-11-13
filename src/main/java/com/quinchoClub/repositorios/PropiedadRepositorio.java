/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quinchoClub.repositorios;

import com.quinchoClub.entidades.Propiedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavan
 */

@Repository
public interface PropiedadRepositorio extends JpaRepository<Propiedad, String> {
    List<Propiedad> findByUbicacion(String ubicacion);
    List<Propiedad> findByTamanio(Double tamanio);

    /**
     *
     * @param wifi
     * @param pileta
     * @param parrilla
     * @param accesorios
     * @param cama
     * @param aire
     * @return
     */
    List<Propiedad> findByWifiAndPiletaAndParrillaAndAccesoriosAndCamaAndAire(boolean wifi, boolean pileta, boolean parrilla, boolean accesorios, boolean cama, boolean aire);
}
