/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libreria.controlador;

import com.example.libreria.entidades.Editorial;
import com.example.libreria.errores.ErrorServicio;
import com.example.libreria.servicios.EditorialServicio;
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
public class editorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/editoriales")
    public String editoriales() {
        return "editoriales.html";
    }

    @PostMapping("/agregarEd")
    public String agregarEd(@RequestParam String nombre) {
//        System.out.println("Nombre: " + nombre);
        try {
            editorialServicio.crearEditorial(nombre);
            return "editoriales.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editoriales.html";
        }

    }

    @PostMapping("/modificarEd")
    public String modificarEd(@RequestParam String id, @RequestParam String nombre) {

        try {
            editorialServicio.modificarEditorial(id, nombre);
            return "editoriales.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editoriales.html";
        }

    }

    @PostMapping("/listarEd")
    public String listarEd(ModelMap model) throws ErrorServicio {

        List<Editorial> editoriales = editorialServicio.listarTodos();
        model.addAttribute("editoriales", editoriales);
        return "editoriales.html";

    }


    @PostMapping("/darBajaEd")
    public String darBajaEd(@RequestParam String id) {

        try {
            editorialServicio.darBajaEditorial(id);
            return "editoriales.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editoriales.html";
        }
    }

    @PostMapping("/darAltaEd")
    public String darAltaEd(@RequestParam String id) {

        try {
            editorialServicio.darAltaEditorial(id);
            return "editoriales.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editoriales.html";
        }

    }
}
