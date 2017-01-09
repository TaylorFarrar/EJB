/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.ejbs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/GuilleQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "jms/GuilleConnectionFactoryTX")
})
public class MessageBean implements MessageListener{
    @Override
    public void onMessage(Message message) 
    {
        try 
        {
            String text = message.getBody(String.class);
            Logger.getLogger(MessageBean.class.getName()).log(Level.INFO, String.format("Mensaje procesado: [%s]", text));
            
            
        } catch (JMSException ex) 
          {
            Logger.getLogger(MessageBean.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}
