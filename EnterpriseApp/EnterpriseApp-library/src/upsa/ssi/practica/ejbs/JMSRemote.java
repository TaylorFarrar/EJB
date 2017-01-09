/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.ejbs;

import upsa.ssi.practica.exceptions.EnterpriseAppException;




public interface JMSRemote
{
    public void send(String message) throws EnterpriseAppException;
}
