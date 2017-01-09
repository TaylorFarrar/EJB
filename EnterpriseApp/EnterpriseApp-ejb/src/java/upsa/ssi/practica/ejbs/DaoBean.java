/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsa.ssi.practica.ejbs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Collection;
import java.util.LinkedList;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import upsa.ssi.practica.beans.Equipo;
import upsa.ssi.practica.beans.Jugador;
import upsa.ssi.practica.exceptions.EnterpriseAppException;
import upsa.ssi.practica.exceptions.SQLEARappException;

@Resource(name = "jdbc/database", type = DataSource.class, lookup = "jdbc/GuilleResourceTX")


@Stateless
@Local(Dao.class)
@Remote(DaoRemote.class)
public class DaoBean implements DaoRemote, Dao{

    @Resource(name="jdbc/database")
    private DataSource dataSource;
    
    @Override
    public Collection<Equipo> selectEquipos() throws EnterpriseAppException{
        String consulta = "SELECT E.ID, E.NOMBRE, E.ESTADIO, E.IMG FROM EQUIPOS E";
        Collection<Equipo> equipos = new LinkedList<>();
        
        try(
            
                Connection connection = dataSource.getConnection();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(consulta)
            )
        {
        
                if(rs.next()){
                    do{
                        
                        Equipo equipo = new Equipo();
                        equipo.setId(rs.getString(1));
                        equipo.setNombre(rs.getString(2));
                        equipo.setEstadio(rs.getString(3));
                        equipo.setImagen(rs.getString(4));
                        
                        equipos.add(equipo);
                        
                        
                    }while(rs.next());
                }
        } catch (SQLException sqlException) { throw new SQLEARappException( sqlException ); }
        return equipos;
    }

    @Override
    public Jugador insertJugador(String nombre, String equipos_id, String apellido, String posicion) throws EnterpriseAppException {
        String consulta = "{CALL INSERT INTO JUGADORES(     ID,                EQUIPOS_ID,  NOMBRE, apellido,  POSICION) "
                + "                             VALUES(SEQ_JUGADORES.NEXTVAL,       ?,          ?,      ?,          ?)    "
                + "                             RETURNING ID INTO ?}";
        
        try(
                Connection connection = dataSource.getConnection();
                CallableStatement cs = connection.prepareCall(consulta);
                
           )
        {
            cs.setString(1,equipos_id);
            cs.setString(2,nombre);
            cs.setString(3,apellido);
            cs.setString(4,posicion);
            
            cs.registerOutParameter(5, Types.VARCHAR);
            
            cs.execute();
            
            Jugador jugador = new Jugador();
            jugador.setId(cs.getString(5));
            jugador.setEquipos_id(equipos_id);
            jugador.setNombre(nombre);
            jugador.setApellido(apellido);
            jugador.setPosicion(posicion);
            
            return jugador;
            
            
        } catch (SQLException sqlException) { throw new SQLEARappException( sqlException ); }
        
    }
    
}
