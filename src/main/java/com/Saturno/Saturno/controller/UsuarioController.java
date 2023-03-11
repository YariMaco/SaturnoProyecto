/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.controller;

import com.Saturno.Saturno.entity.Usuario;
import com.Saturno.Saturno.service.ITarjetaService;
import com.Saturno.Saturno.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author menoc
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private ITarjetaService tarjetaService;
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/usuarioN")
    public String registrarse(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "crear";
    }
//
//    @GetMapping("/tarjetaN")
//    public String hacarPago(Model model) {
//        model.addAttribute("Tajett", new Usuario());
//        return "crear";
//    }

    @PostMapping("/save")
    public String guardarPersona(@ModelAttribute Usuario usuario) {
        usuarioService.saveUsuario(usuario);
        return "redirect:/suscrip/plan";
    }

}
