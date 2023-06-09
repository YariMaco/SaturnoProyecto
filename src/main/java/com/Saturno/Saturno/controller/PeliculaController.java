/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.controller;

import com.Saturno.Saturno.entity.Genero;
import com.Saturno.Saturno.entity.Pelicula;
import com.Saturno.Saturno.entity.Suscripcion;
import com.Saturno.Saturno.entity.Usuario;
import com.Saturno.Saturno.service.IGeneroService;
import com.Saturno.Saturno.service.IPeliculaService;
import com.Saturno.Saturno.service.ISuscripcionService;
import com.Saturno.Saturno.service.IUsuarioService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author menoc
 */
@Controller
public class PeliculaController {

    @Autowired
    private IPeliculaService peliculaService;
    @Autowired
    private IGeneroService generoService;
    @Autowired
    private ISuscripcionService suscripcionService;
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/peli")
    public String mostrarPelis(Model model) {
        List<Pelicula> pelicula = peliculaService.getALLPelicula();
        model.addAttribute("pelicula", pelicula);
        List<Genero> listaGeneros = generoService.listGenero();
        model.addAttribute("genero", listaGeneros);
        return "vista";
    }

    @GetMapping("/vistaUsuario")
    public String peliculasUsuario(Model model) {
        List<Pelicula> pelicula = peliculaService.getALLPelicula();
        model.addAttribute("pelicula", pelicula);
        List<Genero> listaGeneros = generoService.listGenero();
        model.addAttribute("genero", listaGeneros);
        return "navbar";
    }

    @GetMapping("/lista")
    public String listaPelis(Model model) {
        List<Pelicula> pelicula = peliculaService.getALLPelicula();
        model.addAttribute("pelicula", pelicula);
        return "peliculas";
    }

    @GetMapping("/verlista/{id}")
    public String verListaPorGenero(@PathVariable("id") long id, Model model) {
        Genero genero = generoService.getGeneroById(id);
        List<Pelicula> peliculasPorGenero = peliculaService.getPeliculasPorGenero(genero);
        model.addAttribute("genero", genero);
        model.addAttribute("pelicula", peliculasPorGenero);
        return "newhtml";
    }

    @GetMapping("/delete/{id}")
    public String eliminarPelicula(@PathVariable("id") Long idPelicula) {
        peliculaService.delete(idPelicula);
        return "redirect:/lista";
    }

    @GetMapping("/peliculaN")
    public String agregarPeli(Model model) {
        List<Genero> listaGeneros = generoService.listGenero();
        model.addAttribute("pelicula", new Pelicula());
        model.addAttribute("generos", listaGeneros);
        return "agregarPeli";
    }

    @GetMapping("/detalles/{idPelicula}")
    public String verMas(@PathVariable("idPelicula") Long idPelicula, Model model) {
        Pelicula pelicula = peliculaService.getPeliculaById(idPelicula);
        model.addAttribute("pelicula", pelicula);
        return "detalles";
    }

    @GetMapping("/guardarPelicula/{id}")
    public String addToFavorites(@PathVariable Long id, Principal principal) {
        Suscripcion usuario = suscripcionService.findByNickname(principal.getName());
        Pelicula pelicula = peliculaService.getPeliculaById(id);
        if (usuario != null && pelicula != null) {
            if (!usuario.getUsuario().getPeliculasFavoritas().contains(pelicula)) {
                usuario.getUsuario().getPeliculasFavoritas().add(pelicula);
                usuarioService.saveUsuario(usuario.getUsuario());
                suscripcionService.saveSuscripcion(usuario);
            }
        }
        return "redirect:/detalles/" + id;
    }

    @GetMapping("/eliminarFavorito/{id}")
    public String eliminarFavorito(@PathVariable Long id, Principal principal) {
        Suscripcion usuario = suscripcionService.findByNickname(principal.getName());
        Pelicula pelicula = peliculaService.getPeliculaById(id);
        if (usuario != null && pelicula != null) {
            usuario.getUsuario().getPeliculasFavoritas().remove(pelicula);
            usuarioService.saveUsuario(usuario.getUsuario());
            suscripcionService.saveSuscripcion(usuario);
        }
        return "redirect:/peliculas/favoritas";
    }

    @GetMapping("/eliminarHistorial/{id}")
    public String eliminarHistorial(@PathVariable Long id, Principal principal) {
        Suscripcion usuario = suscripcionService.findByNickname(principal.getName());
        Pelicula pelicula = peliculaService.getPeliculaById(id);
        if (usuario != null && pelicula != null) {
            usuario.getUsuario().getHistorial().remove(pelicula);
            usuarioService.saveUsuario(usuario.getUsuario());
            suscripcionService.saveSuscripcion(usuario);
        }
        return "redirect:/peliculas/historial";
    }

    @GetMapping("/reproducirPelicula/{id}")
    public String reproducirVideo(@PathVariable Long id, Model model, Principal principal) {
        Pelicula pelicula = peliculaService.getPeliculaById(id);
        Suscripcion usuario = suscripcionService.findByNickname(principal.getName());
        model.addAttribute("pelicula", pelicula);
        if (usuario != null && pelicula != null) {
            usuario.getUsuario().getHistorial().remove(pelicula);
            usuario.getUsuario().getHistorial().add(pelicula);

            usuarioService.saveUsuario(usuario.getUsuario());
            suscripcionService.saveSuscripcion(usuario);
        }
        return "reproductor";
    }

    @GetMapping("/peliculas/favoritas")
    public String mostrarGuardados(Principal principal, Model model) {
        Suscripcion suscripcion = suscripcionService.findByNickname(principal.getName());
        Usuario usuario = suscripcion.getUsuario();
        List<Pelicula> peliculasFavoritas = usuario.getPeliculasFavoritas();
        model.addAttribute("pelicula", peliculasFavoritas);
        model.addAttribute("titulo", "Mis Guardados");
        return "guardarP";
    }

    @GetMapping("/peliculas/historial")
    public String mostrarHistorial(Principal principal, Model model) {
        Suscripcion suscripcion = suscripcionService.findByNickname(principal.getName());
        Usuario usuario = suscripcion.getUsuario();
        List<Pelicula> historial = usuario.getHistorial();
        model.addAttribute("historial", historial);
        model.addAttribute("titulo", "Historial");
        return "historial";
    }

    @PostMapping("/guardarPelicula")
    public String guardarPelicula(@ModelAttribute Pelicula pelicula,
            @RequestParam("imagen") MultipartFile multipartFile) throws IOException {

        Pelicula peliculaExistente = peliculaService.getPeliculaById(pelicula.getId());
        if (!multipartFile.isEmpty()) {
            try {
                byte[] bytes = multipartFile.getBytes();
                java.nio.file.Path path = Paths.get("src/main/resources/static/images1/" + multipartFile.getOriginalFilename());
                Files.write(path, bytes);
                pelicula.setRutaimagen("/images1/" + multipartFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            pelicula.setRutaimagen(peliculaExistente.getRutaimagen());
        }
        peliculaService.savePelicula(pelicula);
        return "redirect:/peli";
    }

//    @PostMapping("/guardarPelicula")
//    public String guardarPelicula(@ModelAttribute Pelicula pelicula,
//            @RequestParam("imagen") MultipartFile multipartFile) throws IOException {
//
//        if (!multipartFile.isEmpty()) {
//            try {
//                byte[] bytes = multipartFile.getBytes();
//                java.nio.file.Path path = Paths.get("src/main/resources/static/images1/" + multipartFile.getOriginalFilename());
//                Files.write(path, bytes);
//                pelicula.setRutaimagen("/images1/" + multipartFile.getOriginalFilename());
//                peliculaService.savePelicula(pelicula);
//                return "redirect:/peli";
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            peliculaService.savePelicula(pelicula);
//        }
//        return "redirect:/peli";
//    }
    @GetMapping("/editPelicula/{id}")//Edit Completo
    public String editarPelicula(@PathVariable("id") Long idPelicula, Model model) {
        Pelicula pelicula = peliculaService.getPeliculaById(idPelicula);
        List<Genero> listaGeneros = generoService.listGenero();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("generos", listaGeneros);
        return "agregarPeli";
    }

}
