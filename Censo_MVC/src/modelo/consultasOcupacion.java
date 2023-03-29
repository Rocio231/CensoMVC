/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oscar
 */
public class consultasOcupacion extends Conexion{
    public boolean registrarOcupacion(Ocupacion oc) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "insert into ocupacion (nombre_ocupacion,detalle_ocupacion)values(?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, oc.getNombre_ocupacion());
            ps.setString(2, oc.getDetalle_ocupacion());

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

    }//end registrar ocupacion
    
    public boolean modificarOcupacion(Ocupacion oc) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE ocupacion SET nombre_ocupacion=?,detalle_ocupacion=? WHERE idocupacion=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, oc.getNombre_ocupacion());
            ps.setString(2, oc.getDetalle_ocupacion());
            ps.setInt(3, oc.getIdocupacion());

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

    }//end modificar ocupacion
    
     public boolean eliminarOcupacion(Ocupacion oc) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM ocupacion WHERE idocupacion=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, oc.getIdocupacion());
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

    }//end eliminar ocupacion
      public boolean buscarOcupacion(Ocupacion oc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM ocupacion WHERE idocupacion=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, oc.getIdocupacion());
            rs = ps.executeQuery();

            if (rs.next()) {
                oc.setNombre_ocupacion(rs.getString("nombre_ocupacion"));
                oc.setDetalle_ocupacion(rs.getString("detalle_ocupacion"));
                return true;
            }
            return false;
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

    }//end buscar ocupacion   
      
      public List<String> obtenerNombreOcupacion() {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();
    String SQL = "SELECT nombre_ocupacion FROM ocupacion";
    try {
        ps = con.prepareStatement(SQL);
        rs = ps.executeQuery();

        List<String> municipios = new ArrayList<>();
        while (rs.next()) {
            municipios.add(rs.getString("nombre_ocupacion"));
        }

        return municipios;

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
      
      public int obtenerIdOcupacion(String nombreOcupacion) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();
    String SQL = "SELECT idocupacion FROM ocupacion WHERE nombre_ocupacion = ?";
    try {
        ps = con.prepareStatement(SQL);
        ps.setString(1, nombreOcupacion);
        rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("idocupacion");
        } else {
            return -1;
        }

    } catch (SQLException e) {
        System.err.println(e);
        return -1;
    } finally {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
      

}

