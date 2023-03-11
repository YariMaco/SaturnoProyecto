/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.controller;

import com.Saturno.Saturno.entity.Plan;
import com.Saturno.Saturno.entity.Suscripcion;
import com.Saturno.Saturno.entity.Tarjeta;
import com.Saturno.Saturno.entity.Usuario;
import com.Saturno.Saturno.service.IPlanService;
import com.Saturno.Saturno.service.ISuscripcionService;
import com.Saturno.Saturno.service.ITarjetaService;
import com.Saturno.Saturno.service.IUsuarioService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author menoc
 */
@Controller

public class SuscripcionController {
@Autowired
    private IPlanService planService;
    @Autowired
    private ISuscripcionService suscripcionService;
    @Autowired
    private ITarjetaService tarjetaService;
    @Autowired
    private IUsuarioService usuarioService;

     @GetMapping("/usuarioN")
    public String registrarse(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "crearU";
    }
    @PostMapping("/saveU")
    public String guardarU(@ModelAttribute Usuario usuario) {
        usuarioService.saveUsuario(usuario);
        return "redirect:/graciasRe";
    }
    @GetMapping("/graciasRe")
    public String showGracias() {
        return "susHecha";
    }

    @GetMapping("/plan")
    public String elegirPlan(Model model) {
        List<Plan> listaPlan = planService.listPlan();
        model.addAttribute("planes", listaPlan);
        return "redirect:/tarjeta";
    }
      @GetMapping("/tarjeta")
    public String meterTarjeta(Model model) {
         model.addAttribute("tarjeta", new Tarjeta());
        return "addCard";

    }
         @PostMapping("/saveT")
    public String guardarU(@ModelAttribute Tarjeta tarjeta) {
        tarjetaService.saveTarjeta(tarjeta);
        return "redirect:/graciasSus";
    }
    //Falta guardar suscripcion


}
