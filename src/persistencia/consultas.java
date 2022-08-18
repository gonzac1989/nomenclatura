/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author e274263
 */
public class consultas {

    public ArrayList<String> devuelve_listado_departamentos() throws Exception {
        Connection c;
        conexion conex = conexion.getInstancia();
        c = conex.crearconexion();
        ArrayList<String> listado = null;

        String cellValue_id;
        String cellValue_departamento;
        String cellValue_codigo;
        String cellValue_localidad;
        String cellValue_codigo_red;
        String cellValue_ip;
        String cellValue_obs;

        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from codigos");
            while (res.next()) {
                if (listado == null) {
                    listado = new ArrayList<>();
                    listado.add("");
                }
                cellValue_id = chequear_string_null(res.getString("id"));
                cellValue_departamento = chequear_string_null(res.getString("departamento"));
                cellValue_codigo = chequear_string_null(res.getString("cod_dpto"));
                cellValue_localidad = chequear_string_null(res.getString("localidad"));
                cellValue_codigo_red = chequear_string_null(res.getString("cod_red"));
                cellValue_ip = chequear_string_null(res.getString("ip_desde"));
                cellValue_obs = chequear_string_null(res.getString("observaciones"));

                listado.add(convertir_texto_a_numero(cellValue_id) + "::" + cellValue_departamento + "::" + cellValue_localidad + "::" + cellValue_codigo + "::" + cellValue_codigo_red + "::" + cellValue_ip + devuelve_observacion(cellValue_obs));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return listado;
    }

    public ArrayList<String> devuelve_listado_tipo_equipo() throws Exception {
        Connection c;
        conexion conex = conexion.getInstancia();
        c = conex.crearconexion();
        ArrayList<String> listado = null;

        String nombre;
        String codigo;

        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from tipo_equipo");
            while (res.next()) {
                if (listado == null) {
                    listado = new ArrayList<>();
                    listado.add("");
                }
                nombre = chequear_string_null(res.getString("nombre"));
                codigo = chequear_string_null(res.getString("codigo"));

                listado.add((nombre) + "::" + codigo);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return listado;
    }

    public ArrayList<String> devuelve_listado_nomenclaturas_pc(String ubicacion, String red, String tipo_dispositivo) throws Exception {
        Connection c;
        conexion conex = conexion.getInstancia();
        c = conex.crearconexion();
        ArrayList<String> listado = null;

        String nomenclatura;

        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            /*
            System.out.println(ubicacion);
            System.out.println(red);
            System.out.println(tipo_dispositivo);
            System.out.println("---");
             */
            res = st.executeQuery("SELECT NUEVO_NOMBRE FROM pc WHERE `COD. DPTO`='" + ubicacion + "' AND `CODIGO RED`='" + red + "' AND SUBSTR(NUEVO_NOMBRE, 4,1)='" + tipo_dispositivo + "'\n"
                    + "ORDER BY NUEVO_NOMBRE asc");
            while (res.next()) {
                if (listado == null) {
                    listado = new ArrayList<>();
                }
                nomenclatura = chequear_string_null(res.getString("NUEVO_NOMBRE"));
                listado.add(nomenclatura);
                //System.out.println(nomenclatura);
            }
            /*
            if (listado != null) {
                System.out.println("total: " + listado.size());
            }
             */
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return listado;
    }
    
    public ArrayList<String> devuelve_listado_nomenclaturas_raspberry(String ubicacion, String red, String tipo_dispositivo) throws Exception {
        Connection c;
        conexion conex = conexion.getInstancia();
        c = conex.crearconexion();
        ArrayList<String> listado = null;

        String nomenclatura;

        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            /*
            System.out.println(ubicacion);
            System.out.println(red);
            System.out.println(tipo_dispositivo);
            System.out.println("---");
            */
            res = st.executeQuery("SELECT NOMBRE FROM raspberry WHERE `COD. DPTO`='" + ubicacion + "' AND `CODIGO RED`='" + red + "' AND SUBSTR(NOMBRE, 4,1)='" + tipo_dispositivo + "'\n"
                    + "ORDER BY NOMBRE asc");
            while (res.next()) {
                if (listado == null) {
                    listado = new ArrayList<>();
                }
                nomenclatura = chequear_string_null(res.getString("NOMBRE"));
                listado.add(nomenclatura);
                //System.out.println(nomenclatura);
            }
            /*
            if (listado != null) {
                System.out.println("total: " + listado.size());
            }
             */
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return listado;
    }
    
    public ArrayList<String> devuelve_listado_nomenclaturas_print_servers(String ubicacion, String red, String tipo_dispositivo) throws Exception {
        Connection c;
        conexion conex = conexion.getInstancia();
        c = conex.crearconexion();
        ArrayList<String> listado = null;

        String nomenclatura;

        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();

            res = st.executeQuery("SELECT Nomenclatura FROM print_servers WHERE `COD. DPTO`='" + ubicacion + "' AND `CODIGO RED`='" + red + "' AND SUBSTR(Nomenclatura, 4,1)='" + tipo_dispositivo + "'\n"
                    + "ORDER BY Nomenclatura asc");
            while (res.next()) {
                if (listado == null) {
                    listado = new ArrayList<>();
                }
                nomenclatura = chequear_string_null(res.getString("Nomenclatura"));
                listado.add(nomenclatura);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return listado;
    }
    
    public ArrayList<String> devuelve_listado_nomenclaturas_serv_impresion(String ubicacion, String red, String tipo_dispositivo) throws Exception {
        Connection c;
        conexion conex = conexion.getInstancia();
        c = conex.crearconexion();
        ArrayList<String> listado = null;

        String nomenclatura;

        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();

            res = st.executeQuery("SELECT NUEVO_NOMBRE FROM serv_impresion WHERE `COD. DPTO`='" + ubicacion + "' AND `CODIGO RED`='" + red + "' AND SUBSTR(NUEVO_NOMBRE, 4,1)='" + tipo_dispositivo + "'\n"
                    + "ORDER BY NUEVO_NOMBRE asc");
            while (res.next()) {
                if (listado == null) {
                    listado = new ArrayList<>();
                }
                nomenclatura = chequear_string_null(res.getString("NUEVO_NOMBRE"));
                listado.add(nomenclatura);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return listado;
    }

    public ArrayList<String> devuelve_listado_nomenclaturas_notebooks(String ubicacion, String red, String tipo_dispositivo) throws Exception {
        Connection c;
        conexion conex = conexion.getInstancia();
        c = conex.crearconexion();
        ArrayList<String> listado = null;

        String nomenclatura;

        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();

            res = st.executeQuery("SELECT NUEVO_NOMBRE FROM notebook WHERE `COD. DPTO`='" + ubicacion + "' AND `CODIGO RED`='" + red + "' AND SUBSTR(NUEVO_NOMBRE, 4,1)='" + tipo_dispositivo + "'\n"
                    + "ORDER BY NUEVO_NOMBRE asc");
            while (res.next()) {
                if (listado == null) {
                    listado = new ArrayList<>();
                }
                nomenclatura = chequear_string_null(res.getString("NUEVO_NOMBRE"));
                listado.add(nomenclatura);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return listado;
    }
    
    String devuelve_observacion(String texto) {
        if (!texto.equals("")) {
            return "::" + texto;
        }
        return "";
    }

    String chequear_string_null(Object texto) throws Exception {

        if (texto == null) {
            return "";
        }
        return texto.toString();
    }

    Integer convertir_texto_a_numero(String texto) {
        if (texto.isEmpty() || texto.equals("")) {
            return -1;
        }
        boolean isNumeric = texto.matches("[+-]?\\d*(\\.\\d+)?");
        if (!isNumeric) {
            return -1;
        }
        //Float id=Float.parseFloat(texto);
        int i = new Double(texto).intValue();

        return i;
    }
}
