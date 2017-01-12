/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.ejbs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import upsa.ssi.practica.beans.DatosMessageJugador;
import upsa.ssi.practica.beans.Jugador;
import upsa.ssi.practica.exceptions.EnterpriseAppException;

@EJBs({@EJB(name="ejb/dao", beanInterface = DaoRemote.class, lookup = "java:app/EnterpriseApp-ejb/DaoBean!upsa.ssi.practica.ejbs.DaoRemote")
       
                                                                       
      })
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/GuilleQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "jms/GuilleConnectionFactoryTX")
})
public class MessageBean implements MessageListener{
    
    @EJB(name="ejb/dao")
    private DaoRemote dao;
    
    
    
    @Override
    public void onMessage(Message message) 
    {
        try 
        {
                
            System.out.println("----Message-->"+message.getBody(DatosMessageJugador.class));
            Jugador jugador = dao.insertJugador(message.getBody(DatosMessageJugador.class).getNombre(), message.getBody(DatosMessageJugador.class).getEquipos_id(), message.getBody(DatosMessageJugador.class).getApellido(), message.getBody(DatosMessageJugador.class).getPosicion());
      
        } catch (JMSException ex) 
          {
            Logger.getLogger(MessageBean.class.getName()).log(Level.SEVERE, null, ex);
          } catch (EnterpriseAppException ex) {
            Logger.getLogger(MessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
