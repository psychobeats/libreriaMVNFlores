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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
      @GetMapping("/modificarAu")
    public String modificarAu(RedirectAttributes redirectAttributes,@RequestParam String idA, ModelMap model){

        Autor autor = autorServicio.buscarPorId(idA);
        try {
            
            model.put("autor", autor);
            return "modificarAu.html";
            
        } catch (Exception e) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, e);
            return "modificarAu.html";
        }
        
        
        
    }
   

        
        
    @PostMapping("/modificarAu")
    public String modificarAutor(RedirectAttributes redirectAttributes, @RequestParam String idA, @RequestParam String nombre, ModelMap model){

            Autor autor = autorServicio.buscarPorId(idA);
        try {
            
            model.put("nombre", autor.getNombre());
            autorServicio.modificarAutor(idA, nombre);
            return "modificarAu.html";
            
        } catch (Exception ex) {
            Logger.getLogger(portalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "modificarAu.html";
        }
        
        
    }
    
//    @PostMapping("/edicionAutor")
//    public String editamosAutor(RedirectAttributes redirectAttributes, ModelMap modelo, String id, @RequestParam(required = false) String nombreAutor) {
//        Autor autor = autorServicio.buscarPorId(id);
//        try {
//            modelo.put("nombreAutor", autor.getNombre());
//            autorServicio.modificar(id, nombreAutor, id);
//        } catch (ErrorServicio ex) {
//            modelo.put("tipo", autor);
//            modelo.put("error", ex.getMessage());
//            redirectAttributes.addAttribute("id", id);
//            redirectAttributes.addFlashAttribute("error", ex.getMessage());
//            return "redirect:/autor/{id}";
//        }
//        modelo.put("mensaje", "Autor modificado con éxito");
//        return "index.html";
//    }

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