/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author Usuario
 */
public class conexion {

    private static conexion instancia = null;

    public Connection crearconexion() throws Exception {
        Connection conex;
        String driver;
        String url;
        String usr;
        String pwd;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("configuracion.properties"));
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            usr = prop.getProperty("usr");
            pwd = prop.getProperty("pwd");
            Class.forName(driver);
            conex = DriverManager.getConnection(url, usr, pwd);
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        return conex;
    }

    private conexion() {
    }

    public synchronized static conexion getInstancia() {
        if (instancia == null) {
            instancia = new conexion();
        }

        return instancia;
    }
}
