/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libreria.repositorios;

import com.example.libreria.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Asus
 */

@Repository
public interface libroRepositorio extends JpaRepository<Libro,String> {
    
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public Libro buscarPorTitulo(@Param("titulo") String nombre);
    
    @Query("SELECT l FROM Libro l WHERE l.anio = :anio")
    public Libro buscarPorAnio(@Param("anio") String anio);
   
    @Query("SELECT l FROM Libro l WHERE l.isbn = :isbn")
    public Libro buscarPorIsbn(@Param("isbn") String isbn);
    
}
