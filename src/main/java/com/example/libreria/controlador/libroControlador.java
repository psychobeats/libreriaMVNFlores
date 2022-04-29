/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libreria.controlador;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.errores.ErrorServicio;
import com.example.libreria.repositorios.autorRepositorio;
import com.example.libreria.repositorios.editorialRepositorio;
import com.example.libreria.servicios.AutorServicio;
import com.example.libreria.servicios.EditorialServicio;
import com.example.libreria.servicios.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Asus
 */
@Controller
@RequestMapping("/")
public class libroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;
    @Autowired
    private autorRepositorio AutorRepositorio;
    @Autowired
    private editorialRepositorio EditorialRepositorio;
   


    
    @GetMapping("/libros")
    public String agregar(ModelMap vista) throws ErrorServicio {
        List<Autor> autores = autorServicio.listarTodos();
        vista.addAttribute("autores", autores);
        List<Editorial> editoriales = editorialServicio.listarTodos();
        vista.addAttribute("editoriales", editoriales);
        return "libros.html";
      
    }


    @PostMapping("/libros")
    public String agregar(ModelMap model, @RequestParam String isbn, @RequestParam String titulo, @RequestParam(value = "anio", required=false) Integer anio, @RequestParam Integer ejemplares, @RequestParam Autor autor, @RequestParam Editorial editorial) throws ErrorServicio {
        try {
           libroServicio.crearLibro(isbn, titulo, anio, ejemplares, Boolean.TRUE, autor, editorial); 
            
           return "libros.html";
           
        } catch (Exception e) {
        model.put("error", e.getMessage());
        model.put("isbn", isbn);
        model.put("titulo", titulo);
        model.put("anio", anio);
        model.put("ejemplares", ejemplares);
        model.put("alta", Boolean.TRUE);
        model.put("autor", autor);
        model.put("editorial", editorial);
        
        return "libros.html";
        }
        
        
        
        
        
        

    }

    
    @PostMapping("/modificar")
    public String modificar(@RequestParam String id, @RequestParam String isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares) {

        try {
            libroServicio.modificarLibro(id, isbn, titulo, anio, ejemplares, ejemplares, ejemplares, Boolean.TRUE, null, null);
            return "libros.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "libros.html";
        }

    }

    @PostMapping("/listarTodo")
    public String listarTodo() {

        try {
            libroServicio.listarTodos();
            return "libros.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "libros.html";
        }

    }

    @PostMapping("/darBaja")
    public String darBaja(@RequestParam String id) {

        try {
            libroServicio.darBajaLibro(id);
            return "libros.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "libros.html";
        }

    }

    @PostMapping("/darAlta")
    public String darAlta(@RequestParam String id) {

        try {
            libroServicio.darBajaLibro(id);
            return "libros.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "libros.html";
        }

    }
}
