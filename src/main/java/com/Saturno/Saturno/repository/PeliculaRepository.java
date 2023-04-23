

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Saturno.Saturno.repository;

import com.Saturno.Saturno.entity.Genero;
import com.Saturno.Saturno.entity.Pelicula;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository  extends CrudRepository<Pelicula,Long>{
    List<Pelicula> findByGeneros(Genero genero);
 
}
