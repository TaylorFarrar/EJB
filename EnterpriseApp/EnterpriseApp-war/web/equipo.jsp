<%-- 
    Document   : equipo
    Created on : 06-may-2016, 14:08:41
    Author     : Guille
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../estilos.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ficha equipo</h1>
        
        
        
        Nombre: ${equipo.nombre}
        <br/>
        Id: ${equipo.id}
        <br/>
        Estadio: ${equipo.estadio}
        <br/>
        <br/>
        <img src="../${equipo.imagen}" alt="${equipo.nombre}"  height="60" width="60"/>
        <br/>
        <br/>
        <br/> 
        <br/>
        
        
        
        <table>
            <thead>
                <tr>
                    
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Posicion</th>
                    
                </tr>
            </thead>


            <tbody>
                <c:forEach var="jugador" items="${jugadores}">    
                    
                    <c:url var="urlJugador" value="/jugador.do">
                            <c:param name="id" value="${jugador.id}"/>
                    </c:url>
                    
                    

                    <tr>
                
                        
                        
                            <td> ${jugador.id} </td>
                            <td><a href="${urlJugador}"> ${jugador.nombre} </a></td>
                            <td> ${jugador.apellido} </td>
                            <td> ${jugador.posicion} </td>
                            
                              
                        
                        </a>
                        
                    </tr>    
                </c:forEach>                   
            </tbody>

        </table>
        
        <br/>
        <br/>
        <br/>
        <br/>
        <a href="index.html">Inicio</a>
               
        
        
    </body>
</html>
