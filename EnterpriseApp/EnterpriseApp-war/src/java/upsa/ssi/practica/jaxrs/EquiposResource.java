/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.jaxrs;

import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.EJBs;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import upsa.ssi.practica.beans.Equipo;
import upsa.ssi.practica.beans.Jugador;
import upsa.ssi.practica.beans.JugadorForm;
import upsa.ssi.practica.ejbs.DaoRemote;
import upsa.ssi.practica.ejbs.JMSLocal;
import upsa.ssi.practica.exceptions.EnterpriseAppException;
import upsa.ssi.practica.jaxrs.cdi.Logica;

@EJBs({@EJB(name="ejb/dao", beanInterface = DaoRemote.class, lookup = "java:app/EnterpriseApp-ejb/DaoBean!upsa.ssi.practica.ejbs.DaoRemote")
      
        })

@Path("equipos")
@RequestScoped
public class EquiposResource {

    @EJB 
    private Logica logica;
    
    @EJB(name="ejb/dao")
    private DaoRemote dao;
    
    
    @Context
    private HttpServletRequest request;
    
    @Context 
    private HttpServletResponse response;
    
    @Context
    private UriInfo uriInfo;
    

    
   
    @GET
    @Produces(MediaType.TEXT_HTML)
    public void getHtml() throws ServletException, IOException, EnterpriseAppException {
        
        Collection<Equipo> equipos = dao.selectEquipos();
        request.setAttribute("equipos", equipos);
        request.getRequestDispatcher("/equipos.jsp").forward(request, response);
    }

    
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("{id}")
    public void getEquipo(@PathParam("id") String id) throws ServletException, IOException, EnterpriseAppException {
        
        Equipo equipo = dao.selectEquipo(id);
        Collection<Jugador> jugadores = dao.selectJugadores(id);
        request.setAttribute("jugadores", jugadores);
        request.setAttribute("equipo", equipo);
        
        request.getRequestDispatcher("/equipo.jsp").forward(request, response);
    }
    
    @POST
    @Consumes( MediaType.APPLICATION_FORM_URLENCODED )
    public Response insertJugador(@BeanParam JugadorForm jugadorForm) throws EnterpriseAppException
    {
        
        logica.insertJugador( jugadorForm.getNombre(), jugadorForm.getEquipos_id(), jugadorForm.getApellido(), jugadorForm.getPosicion());
        return Response.created(uriInfo.getAbsolutePath() ).build();
    }
}
