/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.jaxrs.cdi;

import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import upsa.ssi.practica.ejbs.DaoRemote;
import upsa.ssi.practica.ejbs.JMSLocal;

@EJBs({@EJB(name="ejb/dao", beanInterface = DaoRemote.class, lookup = "java:app/EnterpriseApp-ejb/DaoBean!ssi.ejbs.earapp.ejbs.DaoRemote"),
       @EJB(name="ejb/jms", beanInterface = JMSLocal.class,  lookup = "java:app/EARapp-ejb/JMSBean!ssi.ejbs.earapp.ejbs.JMSLocal")
      })



@Stateless
@Local(Logica.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LogicaBean {
    
}
