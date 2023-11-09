
package com.quinchoClub.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Propiedad {
    @Id
    private String id;
    private String tipo;
    private String detalles;
    
    
    
     
}
