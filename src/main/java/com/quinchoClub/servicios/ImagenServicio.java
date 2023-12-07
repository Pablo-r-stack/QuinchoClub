/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub.servicios;

import com.quinchoClub.entidades.Imagen;
import com.quinchoClub.repositorios.ImagenRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Pablo
 */
@Service
public class ImagenServicio implements IImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;

    @Override
    public Imagen guardarImagen(MultipartFile archivo) {
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                String nombreUnico = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
                imagen.setNombre(nombreUnico);
                imagen.setContenido(archivo.getBytes());
                return imagenRepositorio.save(imagen);
            } catch (Exception e) {
                //manejar el error
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Imagen actualizar(MultipartFile archivo, String idImagen) {
        if (archivo != null) {
            try {

                Imagen imagen = new Imagen();

                if (idImagen != null) {
                    Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);

                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }
                }
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepositorio.save(imagen);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;

    }

    @Override
    public List<Imagen> listarImagen() {
        return imagenRepositorio.findAll();
    }

    @Override
    public void borrarImagen(String idImagen) {
        if (idImagen != null && !idImagen.isEmpty()) {
            imagenRepositorio.deleteById(idImagen);
            System.out.println("Imagen borrada correctamente");
        }
    }

    @Override
    public List<Imagen> guardarImagenLista(List<MultipartFile> archivos) {
        List<Imagen> imagenes = new ArrayList();
        if (archivos != null && !archivos.isEmpty()) {
            try {
                for (MultipartFile archivo : archivos) {
                    if(!archivo.getContentType().contentEquals("application/octet-stream")){
                    Imagen imagen = new Imagen();
                    imagen.setMime(archivo.getContentType());
                    String nombreUnico = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
                    imagen.setNombre(nombreUnico);
                    imagen.setContenido(archivo.getBytes());
                    imagenRepositorio.save(imagen);
                    imagenes.add(imagen);
                    }
                }
            } catch (IOException e) {
                // Manejar la excepción de lectura de bytes
                e.printStackTrace();
            }
        }
        return imagenes;
    }

    @Override
    public Imagen getOne(String id) {
        return imagenRepositorio.findById(id).orElse(null);
    }

}
