/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.service;

import com.Saturno.Saturno.entity.Pelicula;
import com.Saturno.Saturno.entity.Plan;
import com.Saturno.Saturno.entity.Suscripcion;
import com.Saturno.Saturno.entity.Tarjeta;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author menoc
 */
public class Userprincipal implements UserDetails {

    private Suscripcion suscripcion;

    public Userprincipal(Suscripcion suscipcion) {
        this.suscripcion = suscipcion;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        this.suscripcion.getUsuario().getPermissionList().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });

        this.suscripcion.getUsuario().getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorities.add(authority);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.suscripcion.getContrasena();
    }

    @Override
    public String getUsername() {
        return this.suscripcion.getNickname();
    }

    public int getTelefono() {
        return this.suscripcion.getUsuario().getTelefono();
    }

    public String getNombre() {
        return this.suscripcion.getUsuario().getNombre() + " " + this.suscripcion.getUsuario().getApellido1();
    }

    public String getApellido() {
        return this.suscripcion.getUsuario().getApellido1();
    }

    public String getNombrePlan() {
        return this.suscripcion.getPlan().getNombre();
    }

    public Plan getPlan() {
        return this.suscripcion.getPlan();
    }

    public Tarjeta getTarjeta() {
        return this.suscripcion.getTarjeta();
    }

    public void setPlan(Plan plan) {
        this.suscripcion.setPlan(plan);
    }

    public void setTajeta(Tarjeta tarjeta) {
        this.suscripcion.setTarjeta(tarjeta);
    }

    public void setTelefono(int telefono) {
        this.suscripcion.getUsuario().setTelefono(telefono);
    }


    public void setPassword(String password) {
        this.suscripcion.setContrasena(password);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.suscripcion != null && this.suscripcion.getActive() == 1;
    }

}
