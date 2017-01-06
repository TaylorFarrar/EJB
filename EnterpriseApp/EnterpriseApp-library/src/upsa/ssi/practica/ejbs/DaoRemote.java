/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.ejbs;

import java.util.Collection;
import upsa.ssi.practica.beans.Equipo;
import upsa.ssi.practica.exceptions.EnterpriseAppException;

/**
 *
 * @author Guille
 */
public interface DaoRemote {
     
    public Collection<Equipo> selectEquipos() throws EnterpriseAppException; 

}
