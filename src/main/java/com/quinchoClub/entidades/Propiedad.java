
package com.quinchoClub.entidades;

import com.sun.istack.NotNull;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.validation.constraints.Positive;
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
public class Propiedad {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @NotNull
    private String tipo;
    @NotNull
    private String detalles;
    @NotNull
    private String ubicacion;
    @Positive //siempre valores positivos
    private Double tamanio;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date disponibilidad;
    
    private boolean wifi;
    private boolean pileta;
    private boolean parrilla;
    private boolean accesorios;
    private boolean cama;
    private boolean aire;
    
    @ElementCollection
    @CollectionTable(name = "propiedad_imagenes", joinColumns = @JoinColumn(name = "propiedad_id"))
    @Column(name = "imagen")
    private List<String> imagenes;
    
    
}
