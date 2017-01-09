/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.ejbs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import upsa.ssi.practica.exceptions.EnterpriseAppException;
import upsa.ssi.practica.exceptions.RollbackEARappException;

@Resource(name = "jms/queue", type = Queue.class, lookup = "jms/GuilleQueue")

@Stateless
@Local(JMSLocal.class)
@Remote(JMSRemote.class)
public class JMSBean implements JMSRemote, JMSLocal{
    
    @Resource(name = "jms/queue")
    private Queue queue;
    
    @Inject
    @JMSConnectionFactory("jms/ssiConnectionFactoryTX")
    private JMSContext jmsContext;
    
    @Resource
    private SessionContext sessionContext;

    @Override
    public void send(String message) throws EnterpriseAppException 
    {        
       jmsContext.createProducer().send(queue, message);
       
        String posicion =  message ;
        if (posicion.equals("Delantero"))
        {
            Logger.getGlobal().log(Level.INFO, "Hacemos rollback");
            throw new RollbackEARappException();
        }
       
       
       
       
    }
    
}
