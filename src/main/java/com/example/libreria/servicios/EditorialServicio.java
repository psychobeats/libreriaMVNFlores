/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libreria.servicios;

import com.example.libreria.entidades.Editorial;
import com.example.libreria.errores.ErrorServicio;
import com.example.libreria.repositorios.editorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Asus
 */
@Service
public class EditorialServicio {

    @Autowired
    private editorialRepositorio EditorialRepositorio;

    public void crearEditorial(String nombre) throws ErrorServicio {

        validarEditorial(nombre);
        Editorial chequeado = EditorialRepositorio.buscarPorNombre(nombre);

        if (chequeado == null) {
            Editorial editorial = new Editorial();
            editorial.setNombre(nombre);
            editorial.setAlta(true);

            EditorialRepositorio.save(editorial);
        } else {
            throw new ErrorServicio("La editorial ya existe en la lista: " + chequeado.getNombre());
        }

    }

    public void validarEditorial(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío");
        }

    }

    public void modificarEditorial(String id, String nombre) throws ErrorServicio {

        Optional<Editorial> editorialABuscar = EditorialRepositorio.findById(id);

        if (editorialABuscar.isPresent()) {
            Editorial editorial = editorialABuscar.get();
            editorial.setNombre(nombre);

            EditorialRepositorio.save(editorial);

        } else {

            throw new ErrorServicio("La Editorial no ha sido encontrado");

        }

    }

    public void darBajaEditorial(String id) throws ErrorServicio {

        Optional<Editorial> editorialABuscar = EditorialRepositorio.findById(id);

        if (editorialABuscar.isPresent()) {
            Editorial editorial = editorialABuscar.get();
            editorial.setAlta(false);

            EditorialRepositorio.save(editorial);

        } else {

            throw new ErrorServicio("La Editorial no ha sido encontrado");

        }

    }

    public void darAltaEditorial(String id) throws ErrorServicio {

        Optional<Editorial> editorialABuscar = EditorialRepositorio.findById(id);

        if (editorialABuscar.isPresent()) {
            Editorial editorial = editorialABuscar.get();
            editorial.setAlta(true);

            EditorialRepositorio.save(editorial);

        } else {

            throw new ErrorServicio("La Editorial no ha sido encontrado");

        }

    }
    
    @Transactional(readOnly = true)
    public List<Editorial> listarTodos() throws ErrorServicio {

        if (EditorialRepositorio.findAll().isEmpty()) {
            throw new ErrorServicio("No hay editoriales para mostrar.");
        } else {
            return (List <Editorial>)EditorialRepositorio.findAll();
        }

    }

}
