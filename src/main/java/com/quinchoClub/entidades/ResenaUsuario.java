package com.quinchoClub.entidades;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResenaUsuario extends Resena {

    @ManyToOne
    private Usuario usuario;
    
}
