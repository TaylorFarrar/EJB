/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.ejbs;

import java.sql.SQLException;
import java.util.Collection;
import upsa.ssi.practica.beans.Equipo;
import upsa.ssi.practica.beans.Jugador;
import upsa.ssi.practica.exceptions.EnterpriseAppException;

/**
 *
 * @author Guille
 */
public interface Dao {
    public Collection<Equipo> selectEquipos() throws EnterpriseAppException;
    public Jugador insertJugador(String nombre, String equipos_id, String apellido, String posicion) throws EnterpriseAppException;
    public Equipo selectEquipo(String id) throws EnterpriseAppException;
    public Collection<Jugador> selectJugadores(String id) throws EnterpriseAppException;
}
