/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub.servicios;

import com.quinchoClub.repositorios.ResenaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabi
 */
@Service
public class ResenaServicio {

    @Autowired
    private ResenaRepositorio  rr;
    //CRUD DE RESENAS.
}
