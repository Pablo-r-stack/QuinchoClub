
package com.quinchoClub.entidades;

import com.quinchoClub.enumeraciones.Rol;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Tincho
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Integer dni;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Temporal(TemporalType.DATE)
    private Date fechadenacimiento;
    private Integer telefono;

}
