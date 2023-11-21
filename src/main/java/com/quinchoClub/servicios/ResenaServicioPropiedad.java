package com.quinchoClub.servicios;

import com.quinchoClub.repositorios.ResenaPropiedadRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResenaServicioPropiedad {
    @Autowired
    private ResenaPropiedadRepositorio rp;
  
   @Transactional
   public void crearReseña(String propiedad){
       
   }   
}
