/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quinchoClub.entidades;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author rocio
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reserva{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;
    @ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double precioTotal;
    
    
}
