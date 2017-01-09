/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.beans;

import java.io.Serializable;

/**
 *
 * @author Guille
 */
public class Equipo implements Serializable{
   

    private String id;
    private String nombre;
    private String estadio;
    private String imagen;

    public Equipo() {
    }

    public Equipo(String id, String nombre, String estadio, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.estadio = estadio;
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }
    
    
    
}    

