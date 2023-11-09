/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quinchoClub.entidades;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Tincho
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Propietario extends Usuario {

    private List<Propiedad> propiedades;
    private Calendar calendario;
;
}
