/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.quinchoClub.controladores;

import com.quinchoClub.servicios.ResenaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Gabi
 */
@Controller
@RequestMapping("/resena")
public class ResenaControlador {
    @Autowired
    private ResenaServicio rs;
    @GetMapping("/ver")
    public String verResena(){
        return null;
    }
}
