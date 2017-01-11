/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.jaxrs.cdi;

import java.util.Collection;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import upsa.ssi.practica.beans.DatosMessageJugador;
import upsa.ssi.practica.beans.Equipo;
import upsa.ssi.practica.beans.Jugador;
import upsa.ssi.practica.ejbs.DaoRemote;
import upsa.ssi.practica.ejbs.JMSLocal;
import upsa.ssi.practica.exceptions.EnterpriseAppException;

@EJBs({@EJB(name="ejb/dao", beanInterface = DaoRemote.class, lookup = "java:app/EnterpriseApp-ejb/DaoBean!upsa.ssi.practica.ejbs.DaoRemote"),
       @EJB(name="ejb/jms", beanInterface = JMSLocal.class,  lookup = "java:app/EnterpriseApp-ejb/JMSBean!upsa.ssi.practica.ejbs.JMSLocal")
                                                                       
      })



@Stateless
@Local(Logica.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LogicaBean implements Logica{
    
    @EJB(name="ejb/dao")
    private DaoRemote dao;
    
    @EJB(name="ejb/jms")
    private JMSLocal jms;
    
    @Resource
    private SessionContext sessionContext;
    
    @Produces
    @Named("equipos")   
    @RequestScoped
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Equipo> getEquipos() throws EnterpriseAppException
    {
        return dao.selectEquipos();
    }
    
    
//    @Produces
//    @Named("equipo")   
//    @RequestScoped
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Equipo getEquipo(String id) throws EnterpriseAppException
    {
        return dao.selectEquipo(id);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertJugador(String nombre, String equipos_id, String apellido, String posicion) throws EnterpriseAppException
    {
        
        
        DatosMessageJugador dmj = new DatosMessageJugador();
        dmj.setEquipos_id(equipos_id);
        dmj.setNombre(nombre);
        dmj.setApellido(apellido);
        dmj.setPosicion(posicion);

        System.out.println(dmj.toString());
        
        jms.send(   dmj   );
        
    }

    
    
}
