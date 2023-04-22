/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author johan
 */

@Entity
@Table(name = "generos")
public class Genero implements Serializable{
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
      private long id;
    private String nombre;
    
    @ManyToMany(mappedBy = "generos")
      private List<Pelicula> movies = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pelicula> getMovies() {
        return movies;
    }

    public void setMovies(List<Pelicula> movies) {
        this.movies = movies;
    }
    

    
}
