/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.controller;

import com.Saturno.Saturno.entity.Plan;
import com.Saturno.Saturno.entity.Suscripcion;
import com.Saturno.Saturno.service.IPlanService;
import com.Saturno.Saturno.service.ISuscripcionService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author menoc
 */
@Controller
@RequestMapping("/suscrip")
public class SuscripcionController {

    @Autowired
    private IPlanService planService;
    @Autowired
    private ISuscripcionService suscripcionService;

    @GetMapping("/plan")
    public String elegirPlan(Model model) {
        List<Plan> listaPlan = planService.listPlan();
        model.addAttribute("planes", listaPlan);
        return "redirect:/usuario/tarjeta";
    }

    @PostMapping("/suscripciones")
    public String crearSuscripcion(@RequestParam("fechaInicio") Date currentDate,
            @RequestParam("fechaFinal") Date newDate) {
        Suscripcion suscripcion = new Suscripcion();
        currentDate = new Date(System.currentTimeMillis());        
        LocalDate localDate = currentDate.toLocalDate();
        int monthsToAdd = suscripcion.getPlan().getDuracion();        
        LocalDate newLocalDate = localDate.plusMonths(monthsToAdd);
        newDate = Date.valueOf(newLocalDate);
        suscripcion.setFechaInicio(currentDate);
        suscripcion.setFechaFinal(newDate);
        return "redirect:/products";
    }

}
