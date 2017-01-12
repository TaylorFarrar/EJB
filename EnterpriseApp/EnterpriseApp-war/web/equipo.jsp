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
                    <a class="navbar-brand" href="../equipos"><h2 class="display-3">Practica</h2></a>
                </div>


                <div class="float-xs-right">
                    <ul class="nav navbar-nav">


                        <li class="btn">
                            <a href="../equipos/${equipo.id}/download"><button type="button" class="btn btn-primary">
                                    <span></span>Download XML
                                </button>
                            </a>
                        </li>

                        
                        <li class="btn">
                            <a>
                                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModalInsert">
                                    <span></span>Add player
                                </button>
                            </a>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <div class="col-sm-12"><img src="../../images/${equipo.imagen}" alt="${equipo.nombre}"  height="60" width="60"/></div>
                <div class="col-sm-12"><h1 class="display-1">${equipo.nombre}</h1></div>
                <div class="col-sm-12"><h1 class="display-3">Estadio: ${equipo.estadio}</h1></div>
                
            </div>

            <div class="row">

                <table class="table table-hover">
                    <thead class="thead-inverse">
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Posicion</th>
                        </tr>
                    </thead>
                    <tbody>

                            <c:forEach var="jugador" items="${jugadores}">    
                               
                                <tr>
                                    <td> ${jugador.id} </td>
                                    <td> ${jugador.nombre}</td>
                                    <td> ${jugador.apellido} </td>
                                    <td> ${jugador.posicion} </td>
                                </tr>
                                <tr>

                            </c:forEach>                   
                        </tbody>

                    </table>

                    
<!-- Modal -->
        <div class="modal fade" id="myModalInsert" role="dialog">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Nuevo</h4>
                    </div>
                    <div class="modal-body">
                        <form action="../equipos/${equipo.id}" method="POST">
                            <div class="form-group">
                                <label for="formGroupInput">Nombre</label>
                                <input type="text" class="form-control" id="nombre" name="nombre">
                            </div>
                            
                            <input type="hidden" class="form-control" id="equipos_id" name="equipos_id" value="${equipo.id}">
                            
                            <div class="form-group">
                                <label for="formGroupInput2">Apellido</label>
                                <input type="text" class="form-control" id="apellido" name="apellido">
                            </div>
                            <div class="form-group">
                                    <label for="sel1">Posicion</label><br/>
                                    <select class="form-group" id="posicion" name="posicion">
                                        <option  value="Delantero" selected>Delantero</option>
                                        <option  value="Extremo">Extremo</option>
                                        <option  value="Lateral">Lateral</option>
                                        <option  value="Central">Central</option>
                                        <option  value="Medio centro defensivo">Medio centro defensivo</option>
                                        <option  value="Medio centro ofensivo">Medio centro ofensivo</option>
                                        <option  value="Portero">Portero</option>
                                    </select>
                            
                                
                            </div>

                            
                            <button type="submit" class="btn btn-primary">Introducir</button>    
                        </form>


                    </div>



                </div>
            </div>
        </div><!--MODAL-->
                    </body>
                    </html>
