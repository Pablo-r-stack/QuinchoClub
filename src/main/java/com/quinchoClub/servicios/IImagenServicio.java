/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quinchoClub.servicios;

import com.quinchoClub.entidades.Imagen;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Pablo
 */
public interface IImagenServicio {
 public Imagen guardarImagen(MultipartFile archivo);
 public Imagen actualizar(MultipartFile archivo, String idImagen);
 public List<Imagen> listarImagen();
 public void borrarImagen(String idImagen);
 public List<Imagen> guardarImagenLista(List<MultipartFile> archivos);
 public Imagen getOne(String id);
}
