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
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String registrarse(Model model, HttpSession session) {
        Usuario usuario =  new Usuario();
        model.addAttribute("usuario",usuario);
        String email = (String) session.getAttribute("email");
        model.addAttribute("email", email);
        usuario.setEmail(email);
        model.addAttribute("suscripcion", new Suscripcion());
        return "crearU";
    }

    @PostMapping("/continuaR")
    public String siguientePagina(@RequestParam("email") String email, HttpSession session) {
        session.setAttribute("email", email);
        return "redirect:/usuarioN";
    }

    @PostMapping("/suscripciones")
    public String crearSuscripcion(@ModelAttribute("usuario") Usuario usuario,
            @ModelAttribute("suscripcion") Suscripcion suscripcion,
            @RequestParam("email") String email) {
        usuario.setEmail(email);
        usuarioService.saveUsuario(usuario);
        suscripcion.setUsuario(usuario);
        suscripcionService.saveSuscripcion(suscripcion);
        long idsuscripcion = suscripcion.getId();
        return "redirect:/graciasRe/" + idsuscripcion;
    }

    @GetMapping("/graciasRe/{idsuscripcion}")
    public String agradecimiento(@PathVariable("idsuscripcion") Long idsuscripcion, Model model) {
        model.addAttribute("idsuscripcion", idsuscripcion);
        return "graciasRe";
    }

    @GetMapping("/graciasRe/plan/{id}")
    public String elegirPlan(@PathVariable("id") Long idsuscripcion, Model model) {
        Suscripcion suscripcion = suscripcionService.getSuscirpcionById(idsuscripcion);
        List<Plan> listaPlan = planService.listPlan();
        model.addAttribute("suscripcion", suscripcion);
        model.addAttribute("planes", listaPlan);
        return "planes";
    }

    @PostMapping("/guardarPlan")
    public String guardarPlan(@ModelAttribute("suscripcion") Suscripcion suscripcion, @RequestParam("planId") Long planId) {
        Plan plan = planService.getPlanById(planId);
        suscripcion.setPlan(plan);
        suscripcionService.saveSuscripcion(suscripcion);
        long idsuscripcion = suscripcion.getId();
        return "redirect:/home/" + idsuscripcion;
    }

    @GetMapping("/pagoPlan/{idSuscripcion}/{idPlan}")
    public String pagoPlan(@PathVariable("idSuscripcion") Long idSuscripcion, @PathVariable("idPlan") Long idPlan, Model model) {
        Plan plan = planService.getPlanById(idPlan);
        Suscripcion suscripcion = suscripcionService.getSuscirpcionById(idSuscripcion);
        model.addAttribute("idSuscripcion", idSuscripcion);
        model.addAttribute("idPlan", idPlan);
        model.addAttribute("plan", plan);
        model.addAttribute("tarjeta", new Tarjeta());
        model.addAttribute("usuario", suscripcion.getUsuario());
        return "addCard";
    }

    @PostMapping("/saveT/{idSuscripcion}/{idPlan}")
    public String guardarT(@ModelAttribute Tarjeta tarjeta, @PathVariable("idSuscripcion") Long idSuscripcion,
            @PathVariable("idPlan") Long idPlan, Model model) {
        Plan plan = planService.getPlanById(idPlan);
        Suscripcion suscripcion = suscripcionService.getSuscirpcionById(idSuscripcion);
        Date  empieza = new Date(System.currentTimeMillis());
        suscripcion.setFechainicio(empieza);
        int meses = plan.getDuracion();
        LocalDate localDate = empieza.toLocalDate();
        LocalDate newLocalDate = localDate.plusMonths(meses);
        Date finaliza = Date.valueOf(newLocalDate);
        suscripcion.setFechafinal(finaliza);
        suscripcion.setPlan(plan);
        Usuario usuario = suscripcion.getUsuario();
        tarjetaService.saveTarjeta(tarjeta);
        suscripcion.setTarjeta(tarjeta);
        suscripcionService.saveSuscripcion(suscripcion);
        model.addAttribute("usuario", usuario);
        return "redirect:/graciasSus";
    }
    @GetMapping("/graciasSus")
    public String suscripcionExitosa() {
        return "graciasSus";
    }
    
    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

    
    @GetMapping("/terminosYcondiciones")
    public String mostrarTerminos() {
        return "terminosYcondiciones";
    }
    
    @GetMapping("/cuenta")
    public String showMiCuenta() {
        return "miCuenta";
    }
    
    @GetMapping("/cuenta/cambioC")
    public String showCambioContrasena() {
        return "cambioContrasena";
    }
    
    @GetMapping("/cuenta/cambioT")
    public String showCambioTelefono() {
        return "cambioTelefono";
    }
    @GetMapping("/iniSesion")
    public String showIniSesion(){
        return "inicioSesion";
    }

}
