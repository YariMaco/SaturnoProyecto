/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.controller;

import com.Saturno.Saturno.entity.Genero;
import com.Saturno.Saturno.entity.Pelicula;
import com.Saturno.Saturno.service.IGeneroService;
import com.Saturno.Saturno.service.IPeliculaService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    @GetMapping("/peli")
    public String mostrarPelis(Model model) {
        List<Pelicula> pelicula = peliculaService.getALLPelicula();
        model.addAttribute("pelicula", pelicula);
        return "vista";
    }
     @GetMapping("/vistaUsuario")
    public String peliculasUsuario(Model model) {
        List<Pelicula> pelicula = peliculaService.getALLPelicula();
        model.addAttribute("pelicula", pelicula);
        return "navbar";
    }
    @GetMapping("/lista")
    public String listaPelis(Model model) {
        List<Pelicula> pelicula = peliculaService.getALLPelicula();
        model.addAttribute("pelicula", pelicula);
        return "peliculas";
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


@PostMapping("/guardarPelicula")
public String guardarPelicula(
        @RequestParam("titulo") String titulo,
        @RequestParam("duracion") String duracion,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("rutaimagen") MultipartFile rutaimagen,
        @RequestParam("director") String director,
        @RequestParam("actores") String actores,
        @RequestParam("score") Integer score,
        @RequestParam("ano") Integer ano,
        @RequestParam("generos") List<Long> generos) {

    if (!rutaimagen.isEmpty()) {
        try {
         
            byte[] bytes = rutaimagen.getBytes();
            java.nio.file.Path path = Paths.get("src/main/resources/static/images1/" + rutaimagen.getOriginalFilename());
            Files.write(path, bytes);
            Pelicula pelicula = new Pelicula();
            pelicula.setTitulo(titulo);
            pelicula.setDuracion(duracion);
            pelicula.setDescripcion(descripcion);
            pelicula.setRutaimagen("/images1/" + rutaimagen.getOriginalFilename());
            pelicula.setDirector(director);
            pelicula.setActores(actores);
            pelicula.setScore(score);
            pelicula.setAno(ano);
            pelicula.setGeneros(generoService.getGenerosById(generos));
            peliculaService.savePelicula(pelicula);

            return "redirect:/peli";
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
    }
    return "redirect:/peli";
}




}
