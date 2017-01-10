/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.jaxrs;

import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import upsa.ssi.practica.beans.Equipo;
import upsa.ssi.practica.beans.JugadorForm;
import upsa.ssi.practica.exceptions.EnterpriseAppException;
import upsa.ssi.practica.jaxrs.cdi.Logica;

/**
 * REST Web Service
 *
 * @author Guille
 */
@Path("equipos")
@RequestScoped
public class EquiposResource {

    @EJB 
    private Logica logica;
    
    @Inject
    private Collection<Equipo> equipo;
    
    @Context
    private HttpServletRequest request;
    
    @Context 
    private HttpServletResponse response;
    
    @Context
    private UriInfo uriInfo;
    

    
   
    @GET
    @Produces(MediaType.TEXT_HTML)
    public void getHtml() throws ServletException, IOException {
        request.getRequestDispatcher("/equipos.jsp").forward(request, response);
    }

    
    @POST
    @Consumes( MediaType.APPLICATION_FORM_URLENCODED )
    public Response insertProducto(@BeanParam JugadorForm jugadorForm) throws EnterpriseAppException
    {
        
        logica.insertJugador( jugadorForm.getNombre(), jugadorForm.getEquipos_id(), jugadorForm.getApellido(), jugadorForm.getPosicion());
        return Response.created(uriInfo.getAbsolutePath() ).build();
    }
}
