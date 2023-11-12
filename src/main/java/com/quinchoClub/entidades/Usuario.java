
package com.quinchoClub.entidades;

import java.util.Date;
import javax.persistence.Entity;
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
    protected String id;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String password;
    protected String password2;

   // @Enumerated(EnumType.STRING)
   // protected Rol rol;
    protected Integer dni;

    @Temporal(TemporalType.DATE)
    protected Date fechadenacimiento;
    protected Integer telefono;
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date fechadealta;

}
