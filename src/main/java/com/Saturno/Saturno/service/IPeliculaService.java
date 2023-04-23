/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Saturno.Saturno.service;

import com.Saturno.Saturno.entity.Genero;
import com.Saturno.Saturno.entity.Pelicula;
import java.util.List;

/**
 *
 * @author menoc
 */
public interface IPeliculaService {
    public List<Pelicula> getALLPelicula();
    public Pelicula getPeliculaById(long id);
    public void savePelicula(Pelicula pelicula);
    public void delete(long id); 
    public List<Pelicula> getPeliculasPorGenero(Genero genero);
    
}
