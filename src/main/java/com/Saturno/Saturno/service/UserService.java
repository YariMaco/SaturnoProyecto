 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.service;

import com.Saturno.Saturno.entity.Suscripcion;
import com.Saturno.Saturno.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author menoc
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private ISuscripcionService suscripcionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Suscripcion persona = this.suscripcionService.findByNickname(username);
        Userprincipal userPrincipal = new Userprincipal(persona);
        return userPrincipal;

    }
}

