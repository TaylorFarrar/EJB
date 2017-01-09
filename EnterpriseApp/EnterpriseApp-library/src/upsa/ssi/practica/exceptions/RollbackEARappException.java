/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class RollbackEARappException extends EnterpriseAppException
{
    
}
