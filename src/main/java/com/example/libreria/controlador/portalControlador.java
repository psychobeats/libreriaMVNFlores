/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libreria.controlador;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author Asus
 */
@Controller
@RequestMapping("/")
public class portalControlador {

    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    @GetMapping("/logIn")
    public String LogIn(){
        return "logIn.html";
    }
    @GetMapping("/singUp")
    public String SingUp(){
        return "singUp.html";
    }
    
    @PostMapping("/ingresar")
    public String ingresar(){
        return "logIn.html";
    }
    @PostMapping("/registrar")
    public String registrar(){
        return "singUp.html";
    }
    

}
    

