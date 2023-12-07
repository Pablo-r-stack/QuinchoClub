/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quinchoClub.entidades;

import com.quinchoClub.enumeraciones.Estado;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;
    
    private LocalDate fechaInicio;
   
    private LocalDate fechaFin;
    
    private Double precioTotal;
    
    @Enumerated(EnumType.STRING)
    private Estado estado;
    
    
}
