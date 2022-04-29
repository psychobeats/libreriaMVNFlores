/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libreria.servicios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.entidades.Libro;
import com.example.libreria.errores.ErrorServicio;
import com.example.libreria.repositorios.autorRepositorio;
import com.example.libreria.repositorios.editorialRepositorio;
import com.example.libreria.repositorios.libroRepositorio;
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
public class LibroServicio {

    @Autowired
    private libroRepositorio LibroRepositorio;

    @Autowired
    private autorRepositorio AutorRepositorio;

    @Autowired
    private editorialRepositorio EditorialRepositorio;

    @Transactional(propagation = Propagation.NESTED)
    public void crearLibro(String isbn, String titulo, Integer anio, Integer ejemplares, Boolean alta, Autor autor, Editorial editorial) throws ErrorServicio {

        validarLibro(isbn, titulo, anio, ejemplares);

        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
        libro.setAlta(true);
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        LibroRepositorio.save(libro);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void modificarLibro(String id, String isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestante, Boolean alta, Autor autor, Editorial editorial) throws ErrorServicio {

        Optional<Libro> libroABuscar = LibroRepositorio.findById(id);

        if (libroABuscar.isPresent()) {
            Libro libro = libroABuscar.get();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
            libro.setAlta(alta);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

    }
    }
    
    

    @Transactional(propagation = Propagation.NESTED)
    public void darBajaLibro(String id) throws ErrorServicio {

        Optional<Libro> libroABuscar = LibroRepositorio.findById(id);

        if (libroABuscar.isPresent()) {
            Libro libro = libroABuscar.get();
            libro.setAlta(false);

            LibroRepositorio.save(libro);

        } else {

            throw new ErrorServicio("El Libro no ha sido encontrado");

        }

    }

    @Transactional(propagation = Propagation.NESTED)
    public void darAltaaLibro(String id) throws ErrorServicio {

        Optional<Libro> libroABuscar = LibroRepositorio.findById(id);

        if (libroABuscar.isPresent()) {
            Libro libro = libroABuscar.get();
            libro.setAlta(true);

            LibroRepositorio.save(libro);

        } else {

            throw new ErrorServicio("El Libro no ha sido encontrado");

        }
    }

    @Transactional(readOnly = true)
    public List<Libro> listarTodos() throws ErrorServicio {

        if (LibroRepositorio.findAll().isEmpty()) {
            throw new ErrorServicio("No hay libros para mostrar.");
        } else {
            return LibroRepositorio.findAll();
        }

    }

    public void validarLibro(String isbn, String titulo, Integer anio, Integer ejemplares) throws ErrorServicio {

        if (isbn == null || isbn.length() > 13) {
            throw new ErrorServicio("El ISBN no puede tener mas de 13 dígitos");
        }

        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El Título no puede estar vacío");
        }

        if (anio == null || anio.toString().length() > 4) {
            throw new ErrorServicio("El año no puede tener mas de 4 dígitos");
        }
        if (anio == null || ejemplares.toString().isEmpty()) {
            throw new ErrorServicio("La cantidad de ejemplares no puede ser 0 o null");
        }

//        if (autor == null) {
//            throw new ErrorServicio("El Autor no puede estar vacío");
//        }
//
//        if (editorial == null) {
//            throw new ErrorServicio("La Editorial no puede estar vacío");
//        }

    }

}
