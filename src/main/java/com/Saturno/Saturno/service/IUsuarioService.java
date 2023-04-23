/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Saturno.Saturno.service;

import com.Saturno.Saturno.entity.Pelicula;
import com.Saturno.Saturno.entity.Usuario;
import java.util.List;

/**
 *
 * @author menoc
 */
public interface IUsuarioService {
    public List<Usuario> getAllUsuario(); //No se va a usar, un usuario no puede ver otros
    //si el admin quiere verlos tiene que ser desde la suscripcion
    public Usuario getUsuarioById(long id);
    public void saveUsuario(Usuario usuario);   
    boolean verificarCorreoExistente(String email);
    public void actualizarPeliculasFavoritas(Usuario usuario, List<Pelicula> peliculasFavoritas);

}
