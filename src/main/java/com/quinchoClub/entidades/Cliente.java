
package com.quinchoClub.entidades;

import java.time.LocalDate;

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
public class Cliente extends Usuario {

    private List<Propiedad> propiedades;
    private LocalDate calendario;

}

