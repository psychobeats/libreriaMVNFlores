/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libreria.servicios;

import com.example.libreria.entidades.Foto;
import com.example.libreria.errores.ErrorServicio;
import com.example.libreria.repositorios.fotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Asus
 */
@Service
public class FotoServicio {
    
    @Autowired
    private fotoRepositorio FotoRepositorio;
    
    public Foto guardar (MultipartFile archivo) throws ErrorServicio{
        
        
        
        if (archivo != null) {
            
            try{
            
            Foto foto = new Foto();
            foto.setMime(archivo.getContentType());
            foto.setNombre(archivo.getName());
            foto.setContenido(archivo.getBytes());
            
            return FotoRepositorio.save(foto);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
    
}
