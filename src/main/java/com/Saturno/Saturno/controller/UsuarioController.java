/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.controller;

import com.Saturno.Saturno.entity.Plan;
import com.Saturno.Saturno.entity.Suscripcion;
import com.Saturno.Saturno.entity.Tarjeta;
import com.Saturno.Saturno.service.IPlanService;
import com.Saturno.Saturno.service.ISuscripcionService;
import com.Saturno.Saturno.service.ITarjetaService;
import com.Saturno.Saturno.service.IUsuarioService;
import com.Saturno.Saturno.service.Userprincipal;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    @Autowired
    private IPlanService planService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private ISuscripcionService suscripcionService;
    @Autowired
    private ITarjetaService tarjetaService;

    @GetMapping("/cuenta")
    public String showMiCuenta() {
        return "miCuenta";
    }

    @GetMapping("/cambiosRealizados")
    public String showCambios() {
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
    
    @GetMapping("/cuenta/cambioT2")
    public String showCambioTarjeta() {
        return "cambioTarjeta";
    }
    
    @GetMapping("/cuenta/cambioP")
    public String showCambioPlan(Model model) {
        List<Plan> listaPlan = planService.listPlan();
        model.addAttribute("planes", listaPlan);
        return "cambioPlan";
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

        if (!encoder.matches(confimarContrasenaActual, usuario.getContrasena()) || !consulta.equals(confirma)) {
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

    @GetMapping("/cuenta/cambioPla")
    public String showCambioPlan(@RequestParam("idPlan") Long idPlan, Principal principal,
           @AuthenticationPrincipal Userprincipal loggeado) {

        Suscripcion usuario = suscripcionService.findByNickname(principal.getName());
        Plan plan = planService.getPlanById(idPlan);


        usuario.setPlan(plan);
        loggeado.setPlan(usuario.getPlan());
        
        usuarioService.saveUsuario(usuario.getUsuario());
        suscripcionService.saveSuscripcion(usuario);

        return "redirect:/cambiosRealizados";
    }

    @GetMapping("/cuenta/cambioTarje")
    public String showCambioTarjeta(@RequestParam("nombret") String nombret, @RequestParam("numerot") int numerot,
            @RequestParam("fechaex") String fechaex, @RequestParam("codse") int codse, Principal principal,
            @AuthenticationPrincipal Userprincipal loggeado){
        
        Suscripcion usuario = suscripcionService.findByNickname(principal.getName());
        
        usuario.getTarjeta().setNombret(nombret);
        usuario.getTarjeta().setNumerot(numerot);        
        usuario.getTarjeta().setFechaex(fechaex);
        usuario.getTarjeta().setCodse(codse);
        
        usuarioService.saveUsuario(usuario.getUsuario());
        suscripcionService.saveSuscripcion(usuario);
        
        return "redirect:/cambiosRealizados";
    }
}
