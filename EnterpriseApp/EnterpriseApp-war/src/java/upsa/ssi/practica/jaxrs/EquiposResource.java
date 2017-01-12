/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.jaxrs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
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
import javax.enterprise.deploy.spi.exceptions.ConfigurationException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
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
    public void getHtml() throws ServletException, IOException, EnterpriseAppException {
        
        //Collection<Equipo> equipos = dao.selectEquipos();
        //request.setAttribute("equipos", equipos);
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
    @Path("{id}")
    @Consumes( MediaType.APPLICATION_FORM_URLENCODED )
    public Response insertJugador(@BeanParam JugadorForm jugadorForm, @PathParam("id") String id) throws EnterpriseAppException, ServletException, IOException
    {
        logica.insertJugador( jugadorForm.getNombre(), jugadorForm.getEquipos_id(), jugadorForm.getApellido(), jugadorForm.getPosicion());
        
        Equipo equipo = dao.selectEquipo(id);
        Collection<Jugador> jugadores = dao.selectJugadores(id);
        request.setAttribute("jugadores", jugadores);
        request.setAttribute("equipo", equipo);
        request.getRequestDispatcher("/redirectJugadorOK.jsp").forward(request, response);
        
        
        return Response.created(uriInfo.getAbsolutePath() ).build();
    }
    
    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("{id}/download")
    public void descargarEquipo(@PathParam("id") String id) throws ConfigurationException, EnterpriseAppException, SAXException, IOException, TransformerConfigurationException 
    {
        
        Equipo equipo = dao.selectEquipo(id);
        Collection<Jugador> jugadores = dao.selectJugadores(id);
        SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        TransformerHandler transformerHandler = saxTransformerFactory.newTransformerHandler();
        AttributesImpl attrs = new AttributesImpl();
        Transformer transformer = transformerHandler.getTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.VERSION,  "1.0");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");        
        transformer.setOutputProperty(OutputKeys.MEDIA_TYPE, "text/xml");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult( writer );        
        transformerHandler.setResult( result );
        transformerHandler.startDocument();
        //Info del equipo
        attrs.addAttribute("", "", "id", "ID", equipo.getId());
        transformerHandler.startElement("", "", "equipo", attrs);
        attrs.clear();
        transformerHandler.startElement("", "", "nombreEquipo", attrs);
        transformerHandler.characters(equipo.getNombre().toCharArray(), 0, equipo.getNombre().length());
        transformerHandler.endElement("", "", "nombreEquipo");
        transformerHandler.startElement("", "", "rutaImg", attrs);
        transformerHandler.characters(equipo.getImagen().toCharArray(), 0, equipo.getImagen().length());
        transformerHandler.endElement("", "", "rutaImg");
        transformerHandler.startElement("", "", "estadio", attrs);
        transformerHandler.characters(equipo.getEstadio().toCharArray(), 0, equipo.getEstadio().length());
        transformerHandler.endElement("", "", "estadio");
        //Info de los jugadores
        transformerHandler.startElement("", "", "jugadores", attrs);
        for(Jugador j : jugadores)
        {
            
            //System.out.println("--------gsgsdgsdgdsds>>>"+j.toString());
            attrs.addAttribute("", "", "id", "ID", j.getId());
            transformerHandler.startElement("", "", "jugador", attrs);
            
            attrs.clear();
            
            
            transformerHandler.startElement("", "", "nombre", attrs);
            transformerHandler.characters(j.getNombre().toCharArray(), 0, j.getNombre().length());
            transformerHandler.endElement("", "", "nombre");
            
            
            transformerHandler.startElement("", "", "apellido", attrs);        
            transformerHandler.characters(j.getApellido().toCharArray(), 0, j.getApellido().length());
            transformerHandler.endElement("", "", "apellido");
            
            
            transformerHandler.startElement("", "", "equipoId", attrs);
            transformerHandler.characters(j.getEquipos_id().toCharArray(), 0, j.getEquipos_id().length());
            transformerHandler.endElement("", "", "equipoId");
            
            transformerHandler.startElement("", "", "posicion", attrs);
            transformerHandler.characters(j.getPosicion().toCharArray(), 0, j.getPosicion().length());
            transformerHandler.endElement("", "", "posicion");
            
            
            
            transformerHandler.endElement("", "", "jugador");
        }
        transformerHandler.endElement("", "", "juagdores");
        transformerHandler.endElement("", "", "equipo");
        transformerHandler.endDocument();
        writer.flush();
        writer.close();
        String cadenaXML = writer.toString();
        System.out.println(cadenaXML);
        String home = System.getProperty("user.home");
        File file = new File(home+"/Downloads/" + equipo.getNombre() + ".xml");
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(file));
        bw.write(cadenaXML);
        bw.close();
        //request.getRequestDispatcher("/equipos.jsp").forward(request, response);
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("/download") 
    public void descargarEquipos() throws SQLException, TransformerConfigurationException, SAXException, IOException, ServletException, EnterpriseAppException
    {

          
          
          Collection<Equipo> equipos = dao.selectEquipos();
          
          
         
        SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        TransformerHandler transformerHandler = saxTransformerFactory.newTransformerHandler();
        AttributesImpl attrs = new AttributesImpl();
        
        Transformer transformer = transformerHandler.getTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.VERSION,  "1.0");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");        
        transformer.setOutputProperty(OutputKeys.MEDIA_TYPE, "text/xml");
        
        
        
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult( writer );        
        transformerHandler.setResult( result );
        
        
        
        transformerHandler.startDocument();  
  
            //Info de los jugadores
            transformerHandler.startElement("", "", "equipos", attrs);

            
            for(Equipo e : equipos)
            {
                attrs.addAttribute("", "", "id", "ID", e.getId());
                transformerHandler.startElement("", "", "equipo", attrs);
                
                attrs.clear();
                   transformerHandler.startElement("", "", "nombre", attrs);
                   transformerHandler.characters(e.getNombre().toCharArray(), 0, e.getNombre().length());
                   transformerHandler.endElement("", "", "nombre");
                
               
                   
                   
                   transformerHandler.startElement("", "", "imagenRuta", attrs);
                   transformerHandler.characters(e.getImagen().toCharArray(), 0, e.getImagen().length());
                   transformerHandler.endElement("", "", "imagenRuta");
                   
                   transformerHandler.startElement("", "", "estadio", attrs);
                   transformerHandler.characters(e.getEstadio().toCharArray(), 0, e.getEstadio().length());
                   transformerHandler.endElement("", "", "estadio");
                   
                   
                   
                   //Plantilla del equipo
                   transformerHandler.startElement("", "", "juagdores", attrs);
                   Collection<Jugador> jugadoresCurrEquipo = dao.selectJugadores(e.getId());
                   for(Jugador j :jugadoresCurrEquipo)
                   {
                   attrs.addAttribute("", "", "id", "ID", j.getId());
                transformerHandler.startElement("", "", "jugador", attrs);
                
                attrs.clear();
                
                
                   transformerHandler.startElement("", "", "nombre", attrs);
                   transformerHandler.characters(j.getNombre().toCharArray(), 0, j.getNombre().length());
                   transformerHandler.endElement("", "", "nombre");
                
                   
                   transformerHandler.startElement("", "", "apellido", attrs);
                   transformerHandler.characters(j.getApellido().toCharArray(), 0, j.getApellido().length());
                   transformerHandler.endElement("", "", "apellido");
                   
                   
                   transformerHandler.startElement("", "", "equipoId", attrs);
                   transformerHandler.characters(j.getEquipos_id().toCharArray(), 0, j.getEquipos_id().length());
                   transformerHandler.endElement("", "", "equipoId");
                   
                   transformerHandler.startElement("", "", "posicion", attrs);
                   transformerHandler.characters(j.getPosicion().toCharArray(), 0, j.getPosicion().length());
                   transformerHandler.endElement("", "", "posicion");
                   
                   
                   
                   transformerHandler.endElement("", "", "jugador");
                   }
                   transformerHandler.endElement("", "", "jugadores");
                   
                   
                   
                   
                   transformerHandler.endElement("", "", "equipo");
            }
                
                  transformerHandler.endElement("", "", "equipos");
        transformerHandler.endDocument();
                
        writer.flush();
        writer.close();
        String cadenaXML = writer.toString();
        System.out.println(cadenaXML);
          
        
        String home = System.getProperty("user.home");
        File file = new File(home+"/Downloads/lista_equipos.xml");
        
        BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(cadenaXML);
        bw.close();
        
        //request.getRequestDispatcher("/redirectEquipos.jsp").forward(request, response);
    }
}
