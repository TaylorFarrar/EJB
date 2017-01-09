/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.beans;

import javax.ws.rs.FormParam;

/**
 *
 * @author Guille
 */
public class JugadorForm {
    
    
    @FormParam("id")
    private String id;

    @FormParam("equipos_id")
    private String equipos_id;

    @FormParam("nombre")
    private String nombre;

    @FormParam("apellido")
    private String apellido;
    
    @FormParam("posicion")
    private String posicion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
    
}
