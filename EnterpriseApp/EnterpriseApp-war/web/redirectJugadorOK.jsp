<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="urlJugador" value="../equipos/${equipo.id}">
    
</c:url>
<c:redirect url="${urlJugador}"/>
