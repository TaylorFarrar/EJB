<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-light bg-faded">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="../api/equipos"><h2 class="display-3">Practica</h2></a>
                </div>


                <div class="float-xs-right">
                    <ul class="nav navbar-nav">


                        <li class="btn">
                            <a href="../api/equipos/download"><button type="button" class="btn btn-primary">
                                    <span></span>Download XML
                                </button>
                            </a>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <h1 class="display-4">Consulta de equipos</h1>
            </div>
                    <div class="row">
                         
                        <table class="table table-hover">
                            <thead class="thead-inverse">
                                <tr>
                                    <th>Identificador</th>
                                    <th>Nombre</th>
                                    <th>Estadio</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="equipo" items="${equipos}">    

                                    <c:url var="urlEquipo" value="equipos/${equipo.id}"></c:url>

                                 <tr>

                                            <!--<td><img src="../${equipo.imagen}" alt="${equipo.nombre}"></td>-->
                                            <td>${equipo.id}</td>
                                            <td>${equipo.nombre}</td>
                                            <td>${equipo.estadio}</td>
                                            
                                            <td><a href="${urlEquipo}"><button  name = "btnDetalles" type="button" class="btn btn-success">
                                                        <span></span>Ver detalles
                                                    </button>
                                                </a></td>
                                           


                                        </tr>
                                              
                                    
                                   
                                </c:forEach>
                                </tbody>

                            </table>              

                        </div>
                    </div>

                   

              
            </div>



            <c:forEach var="equipo" items="${equipos}">    

                <c:url var="urlEquipo" value="/equipo.do">
                    <c:param name="id" value="${equipo.id}"/>
                </c:url>

                <a href="${urlEquipo}"> 
                    <div>





                    </div> 
                </a>    
            </c:forEach>

        </body>
    </html>
