
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class consultasHabitanteOcupacion extends Conexion {
     public boolean registrarOcupacionHabitante(Habitante hab, Ocupacion ocupacion) {
    PreparedStatement ps = null;
    Connection con = getConexion();
    String sql = "INSERT INTO habitante_ocupacion (habitante_idhabitante, ocupacion_idocupacion) VALUES (?, ?)";

    try {
        ps = con.prepareStatement(sql);
        ps.setInt(1, hab.getIdhabitante());
        ps.setInt(2, ocupacion.getIdocupacion());

        ps.execute();
        return true;
    } catch (SQLException e) {
        System.err.println(e);
        return false;
    } finally {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
     public List<Integer> obtenerIdOcupacionPorIdHabitante(int idHabitante) {
    List<Integer> idOcupacionList = new ArrayList<>();
    PreparedStatement ps = null;
    Connection con = getConexion();
    String sql = "SELECT ocupacion_idocupacion FROM habitante_ocupacion WHERE habitante_idhabitante = ?";

    try {
        ps = con.prepareStatement(sql);
        ps.setInt(1, idHabitante);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int idOcupacion = rs.getInt("ocupacion_idocupacion");
            idOcupacionList.add(idOcupacion);
        }

        return idOcupacionList;
    } catch (SQLException e) {
        System.err.println(e);
        return null;
    } finally {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

     

}
