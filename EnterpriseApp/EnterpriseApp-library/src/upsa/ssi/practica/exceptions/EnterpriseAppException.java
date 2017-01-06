/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.exceptions;

/**
 *
 * @author Guille
 */
public class EnterpriseAppException extends Exception{

    public EnterpriseAppException() {
    }

    public EnterpriseAppException(String message) {
        super(message);
    }

    public EnterpriseAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnterpriseAppException(Throwable cause) {
        super(cause);
    }

    public EnterpriseAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
    
    
}
