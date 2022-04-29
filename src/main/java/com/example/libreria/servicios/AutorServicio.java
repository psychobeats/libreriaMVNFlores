/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libreria.servicios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.errores.ErrorServicio;
import com.example.libreria.repositorios.autorRepositorio;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Asus
 */
@Service
public class AutorServicio {

    @Autowired
    private autorRepositorio AutorRepositorio;

    @Transactional(propagation = Propagation.NESTED)
    public void crearAutor(String nombre) throws ErrorServicio {

        validarAutor(nombre);
        Autor chequeado = AutorRepositorio.buscarPorNombre(nombre);

        if (chequeado == null) {
            Autor autor = new Autor();
            autor.setNombre(nombre);
            autor.setAlta(true);

            AutorRepositorio.save(autor);

        } else {
            throw new ErrorServicio("El autor ya existe en la lista: " + chequeado.getNombre());
        }

        

    }

    public void validarAutor(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío");
        }

    }

    @Transactional(propagation = Propagation.NESTED)
    public void modificarAutor(String id, String nombre) throws ErrorServicio {

        Optional<Autor> autorABuscar = AutorRepositorio.findById(id);

        if (autorABuscar.isPresent()) {
            Autor autor = autorABuscar.get();
            autor.setNombre(nombre);

            AutorRepositorio.save(autor);

        } else {

            throw new ErrorServicio("El autor no ha sido encontrado");

        }

    }

    @Transactional(propagation = Propagation.NESTED)
    public void darBajaAutor(String id) throws ErrorServicio {

        Optional<Autor> autorABuscar = AutorRepositorio.findById(id);

        if (autorABuscar.isPresent()) {
            Autor autor = autorABuscar.get();
            autor.setAlta(false);

            AutorRepositorio.save(autor);

        } else {

            throw new ErrorServicio("El autor no ha sido encontrado");

        }

    }

    @Transactional(propagation = Propagation.NESTED)
    public void darAltaAutor(String id) throws ErrorServicio {

        Optional<Autor> autorABuscar = AutorRepositorio.findById(id);

        if (autorABuscar.isPresent()) {
            Autor autor = autorABuscar.get();
            autor.setAlta(true);

            AutorRepositorio.save(autor);

        } else {

            throw new ErrorServicio("El autor no ha sido encontrado");

        }

    }
    
    @Transactional(readOnly = true)
    public List<Autor> listarTodos() throws ErrorServicio {

        if (AutorRepositorio.findAll().isEmpty()) {
            throw new ErrorServicio("No hay autores para mostrar.");
        } else {
            return AutorRepositorio.findAll();
        }

    }

}
