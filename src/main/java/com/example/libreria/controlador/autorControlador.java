/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libreria.controlador;

import com.example.libreria.entidades.Autor;
import com.example.libreria.errores.ErrorServicio;
import com.example.libreria.servicios.AutorServicio;
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
public class autorControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    

    
    @GetMapping("/autores")
    public String autores(){
        return "autores.html";
    }
    
    @PostMapping("/agregarAu")
    public String agregarAu(@RequestParam String nombre){
//        System.out.println("Nombre: " + nombre);
        
        try {
            autorServicio.crearAutor(nombre);
            return "autores.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autores.html";
        }
        
        
    }
    @PostMapping("/modificarAu")
    public String modificarAu(@RequestParam String id, @RequestParam String nombre){

        try {
            autorServicio.modificarAutor(id, nombre);
            return "autores.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autores.html";
        }
        
        
    }
    @PostMapping("/listarAu")
    public String listarAu(ModelMap model) throws ErrorServicio{

        List<Autor> autores = autorServicio.listarTodos();
        model.addAttribute("autores", autores);
        return "autores.html";
  
    }
    @PostMapping("/darBajaAu")
    public String darBajaAu(@RequestParam String id){

        try {
            autorServicio.darBajaAutor(id);
            return "autores.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autores.html";
        }
        
        
    }
    @PostMapping("/darAltaAu")
    public String darAltaAu(@RequestParam String id){

        try {
            autorServicio.darAltaAutor(id);
            return "autores.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autores.html";
        }
        
        
    }
    
}
