<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilos.css">
        <title>JSP Page</title>
    </head>
    <body>

        <h1>Equipos</h1>

        
                <table>
            <thead>
                <tr>
                    
                    <th>Equipo</th>
                    <th>Escudo</th>
                    
                </tr>
            </thead>


            <tbody>
                <c:forEach var="equipo" items="${equipos}">    
                    
                    <c:url var="urlEquipo" value="/equipo.do">
                            <c:param name="id" value="${equipo.id}"/>
                    </c:url>

                      <tr>
                          
                              
                            <td><a href="${urlEquipo}"> ${equipo.nombre}</a></td>
                            <td> <img src="${equipo.imagen}" alt="${equipo.nombre}"  height="42" width="42"/> </td>
                           
                           
                      </tr>  
                    
                </c:forEach>
            </tbody>
        </table>

            
                <c:forEach var="equipo" items="${equipos}">    
                    
                    <c:url var="urlEquipo" value="/equipo.do">
                            <c:param name="id" value="${equipo.id}"/>
                    </c:url>

                    <a href="${urlEquipo}"> 
                        <div>
                        
                                
                              
                                
                        
                        </div> 
                    </a>    
                </c:forEach>
        
        <br/>
        <br/>
        <br/>
        <br/>
        <a href="index.html">Inicio</a>
           
    </body>
</html>
