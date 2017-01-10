/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.ejbs;

import upsa.ssi.practica.beans.DatosMessageJugador;
import upsa.ssi.practica.beans.Jugador;
import upsa.ssi.practica.exceptions.EnterpriseAppException;

/**
 *
 * @author Guille
 */
public interface JMSLocal {
    public void send(DatosMessageJugador jugador) throws EnterpriseAppException;
}
