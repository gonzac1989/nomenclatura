/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author e274263
 */
public class jp_ingreso extends javax.swing.JPanel {

    ArrayList<String> listado_departamentos = new ArrayList<>();
    ArrayList<String> listado_tipo_dispositivos = new ArrayList<>();
    /**
     * Creates new form jp_ingreso
     */
    public jp_ingreso() {
        initComponents();
        cargar_departamentos();
        cargar_tipo_dispositivos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmb_departamento = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmb_tipo = new javax.swing.JComboBox<>();

        setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("UBICACION:");

        jButton1.setText("GENERAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("TIPO DE DISPOSITIVO:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmb_departamento, 0, 448, Short.MAX_VALUE)
                            .addComponent(cmb_tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmb_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println(devuelve_codigo_dispositivo((String) cmb_tipo.getSelectedItem()));

    }//GEN-LAST:event_jButton1ActionPerformed

    void cargar_departamentos() {
        InputStream excelStream = null;
        listado_departamentos = null;
        listado_departamentos = new ArrayList<>();

        try {
            Boolean ya_agrego_campo_vacio = false;
            excelStream = new FileInputStream(new File("Administracion IP.xls"));
            // High level representation of a workbook.
            // Representación del más alto nivel de la hoja excel.
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
            // We chose the sheet is passed as parameter. 
            // Elegimos la hoja que se pasa por parámetro.
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);//reemplazar por hoja 0, que tiene codigos deptos e ip, etc
            // An object that allows us to read a row of the excel sheet, and extract from it the cell contents.
            // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
            HSSFRow hssfRow;
            // Initialize the object to read the value of the cell 
            // Inicializo el objeto que leerá el valor de la celda
            HSSFCell cell;
            // I get the number of rows occupied on the sheet
            // Obtengo el número de filas ocupadas en la hoja
            int rows = hssfSheet.getLastRowNum();
            // I get the number of columns occupied on the sheet
            // Obtengo el número de columnas ocupadas en la hoja
            int cols = 0;
            // A string used to store the reading cell
            // Cadena que usamos para almacenar la lectura de la celda
            String cellValue_id = "";
            String cellValue_departamento = "";
            String cellValue_codigo = "";
            String cellValue_localidad = "";
            String cellValue_codigo_red = "";
            String cellValue_ip = "";
            // For this example we'll loop through the rows getting the data we want
            // Para este ejemplo vamos a recorrer las filas obteniendo los datos que queremos            
            for (int fila = 0; fila <= rows; fila++) {
                hssfRow = hssfSheet.getRow(fila);
                if (hssfRow == null) {
                    break;
                } else {
                    //for (short c = 0; c < (cols = hssfRow.getLastCellNum()); c++) {
                    if (fila == 0) {
                        //PARA QUE SALTEE LA PRIMER FILA QUE TIENE EL TITULO
                        if (!ya_agrego_campo_vacio) {
                            cmb_departamento.addItem("");
                            ya_agrego_campo_vacio = true;
                        }
                        continue;
                    }
                    cellValue_id = chequear_string_null(hssfRow.getCell(0));
                    cellValue_departamento = chequear_string_null(hssfRow.getCell(1));
                    cellValue_localidad = chequear_string_null(hssfRow.getCell(2));
                    cellValue_codigo = chequear_string_null(hssfRow.getCell(3));
                    cellValue_codigo_red = chequear_string_null(hssfRow.getCell(4));
                    cellValue_ip = chequear_string_null(hssfRow.getCell(5));
                    //}
                    if (fila > 0) {
                        if (convertir_texto_a_numero(cellValue_id) > 0) {
                            cmb_departamento.addItem(cellValue_departamento + "::" + cellValue_localidad);
                            listado_departamentos.add(convertir_texto_a_numero(cellValue_id) + "::" + cellValue_departamento + "::" + cellValue_localidad + "::" + cellValue_codigo + "::" + cellValue_codigo_red + "::" + cellValue_ip);
                        }
                    }
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
        } catch (IOException ex) {
            System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
        } catch (Exception ex) {
            Logger.getLogger(jp_ingreso.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                System.out.println("Error in file processing after close it (Error al procesar el fichero después de cerrarlo): " + ex);
            }
        }
    }
    
    void cargar_tipo_dispositivos() {
        InputStream excelStream = null;
        listado_tipo_dispositivos = null;
        listado_tipo_dispositivos = new ArrayList<>();

        try {
            Boolean ya_agrego_campo_vacio = false;
            excelStream = new FileInputStream(new File("Administracion IP.xls"));
            // High level representation of a workbook.
            // Representación del más alto nivel de la hoja excel.
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
            // We chose the sheet is passed as parameter. 
            // Elegimos la hoja que se pasa por parámetro.
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(7);//reemplazar por hoja 0, que tiene codigos deptos e ip, etc
            // An object that allows us to read a row of the excel sheet, and extract from it the cell contents.
            // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
            HSSFRow hssfRow;
            // Initialize the object to read the value of the cell 
            // Inicializo el objeto que leerá el valor de la celda
            HSSFCell cell;
            // I get the number of rows occupied on the sheet
            // Obtengo el número de filas ocupadas en la hoja
            int rows = hssfSheet.getLastRowNum();
            // I get the number of columns occupied on the sheet
            // Obtengo el número de columnas ocupadas en la hoja
            int cols = 0;
            // A string used to store the reading cell
            // Cadena que usamos para almacenar la lectura de la celda
            String cellValue_nombre = "";
            String cellValue_codigo = "";
            // For this example we'll loop through the rows getting the data we want
            // Para este ejemplo vamos a recorrer las filas obteniendo los datos que queremos            
            for (int fila = 0; fila <= rows; fila++) {
                hssfRow = hssfSheet.getRow(fila);
                if (hssfRow == null) {
                    break;
                } else {
                    //for (short c = 0; c < (cols = hssfRow.getLastCellNum()); c++) {
                    if (fila == 0) {
                        //PARA QUE SALTEE LA PRIMER FILA QUE TIENE EL TITULO
                        if (!ya_agrego_campo_vacio) {
                            cmb_tipo.addItem("");
                            ya_agrego_campo_vacio = true;
                        }
                        continue;
                    }
                    cellValue_nombre = chequear_string_null(hssfRow.getCell(0));
                    cellValue_codigo = chequear_string_null(hssfRow.getCell(1));                    
                    //}
                    if (fila > 0) {
                        if (convertir_texto_a_numero(cellValue_codigo) >= 0) {
                            cmb_tipo.addItem(cellValue_nombre);
                            listado_tipo_dispositivos.add(convertir_texto_a_numero(cellValue_codigo) + "::" + cellValue_nombre);
                        }
                    }
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
        } catch (IOException ex) {
            System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
        } catch (Exception ex) {
            Logger.getLogger(jp_ingreso.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                System.out.println("Error in file processing after close it (Error al procesar el fichero después de cerrarlo): " + ex);
            }
        }
    }

    Integer convertir_texto_a_numero(String texto) {
        if (texto.isEmpty() || texto.equals("")) {
            return -1;
        }
        boolean isNumeric =  texto.matches("[+-]?\\d*(\\.\\d+)?");
        if(!isNumeric){
            return -1;
        }
        //Float id=Float.parseFloat(texto);
        int i = new Double(texto).intValue();

        return i;
    }

    String chequear_string_null(Object texto) throws Exception {

        if (texto == null) {
            return "";
        }
        return texto.toString();
    }

    String devuelve_codigo_departamento(String departamento) {
        String[] cortarString_seleccion = departamento.split("::");
        for (int i = 0; i < listado_departamentos.size(); i++) {
            String[] cortarString_listado = listado_departamentos.get(i).split("::");
            if (cortarString_listado[1].equals(cortarString_seleccion[0])) {
                return cortarString_listado[3];
            }
        }
        return "";
    }
    
    String devuelve_codigo_dispositivo(String dispositivo) {
        //String[] cortarString_seleccion = dispositivo.split("::");
        for (int i = 0; i < listado_tipo_dispositivos.size(); i++) {
            String[] cortarString_listado = listado_tipo_dispositivos.get(i).split("::");
            if (cortarString_listado[1].equals(dispositivo)) {
                return cortarString_listado[0];
            }
        }
        return "";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_departamento;
    private javax.swing.JComboBox<String> cmb_tipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
