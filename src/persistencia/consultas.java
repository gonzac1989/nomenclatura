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
                cellValue_codigo_red = chequear_string_null(res.getString("codigo red"));
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
