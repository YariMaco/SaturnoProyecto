/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.controller;

import com.Saturno.Saturno.entity.Suscripcion;
import com.Saturno.Saturno.service.ISuscripcionService;
import com.Saturno.Saturno.service.IUsuarioService;
import com.Saturno.Saturno.service.Userprincipal;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private ISuscripcionService suscripcionService;

    @GetMapping("/cuenta")
    public String showMiCuenta() {
        return "miCuenta";
    }
    
    @GetMapping("/cambiosRealizados")
    public String showCambios(){
        return "cambiosExitosos";
    }

    @GetMapping("/cuenta/cambioC")
    public String showCambioContrasena() {
        return "cambioContrasena";
    }

    @GetMapping("/cuenta/cambioT")
    public String showCambioTelefono() {
        return "cambioTelefono";
    }

    @GetMapping("/cuenta/cambioTele")
    public String showCambioTelefono(@RequestParam("consulta") int consulta, Principal principal,
            @AuthenticationPrincipal Userprincipal loggeado) {
        Suscripcion usuario = suscripcionService.findByNickname(principal.getName());

        usuario.getUsuario().setTelefono(consulta);
        loggeado.setTelefono(usuario.getUsuario().getTelefono());

        usuarioService.saveUsuario(usuario.getUsuario());
        suscripcionService.saveSuscripcion(usuario);

        return "redirect:/cambiosRealizados";
    }

    @GetMapping("/cuenta/cambioContra")
    public String showCambioContrasena(@RequestParam("consulta") String consulta, @RequestParam("confirma") String confirma,
            @RequestParam("confimarContrasenaActual") String confimarContrasenaActual, String contrasenaActual, 
            Model model, Principal principal, @AuthenticationPrincipal Userprincipal loggeado) {

        Suscripcion usuario = suscripcionService.findByNickname(principal.getName());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        if(!encoder.matches(confimarContrasenaActual, usuario.getContrasena()) || !consulta.equals(confirma)){
            model.addAttribute("error", "*Las contraseñas no coinciden*");
            return "cambioContrasena";
        }
        
        String nuevaContrasenaEncriptada = new BCryptPasswordEncoder().encode(consulta);

            usuario.setContrasena(nuevaContrasenaEncriptada);
            loggeado.setPassword(usuario.getContrasena());

            usuarioService.saveUsuario(usuario.getUsuario());
            suscripcionService.saveSuscripcion(usuario);

            return "redirect:/cambiosRealizados";
    }

}
