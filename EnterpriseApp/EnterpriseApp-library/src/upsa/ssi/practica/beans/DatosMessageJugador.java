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
public class DatosMessageJugador implements Serializable{
    
    private String equipos_id;
    private String nombre;
    private String apellido;
    private String posicion;

    public DatosMessageJugador() {
    }

    
    public DatosMessageJugador(String equipos_id, String nombre, String apellido, String posicion) {
        this.equipos_id = equipos_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.posicion = posicion;
    }

    
    public String getEquipos_id() {
        return equipos_id;
    }

    public void setEquipos_id(String equipos_id) {
        this.equipos_id = equipos_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "DatosMessageJugador{" + "equipos_id=" + equipos_id + ", nombre=" + nombre + ", apellido=" + apellido + ", posicion=" + posicion + '}';
    }
    
    
    
}
