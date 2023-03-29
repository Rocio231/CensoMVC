/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Habitante;
import modelo.consultasOcupacion;
import modelo.consultasHabitanteOcupacion;
import modelo.consultasHabitante;
import modelo.Ocupacion;
import vista.frm_agregarCenso;

public class CtrlOcupacion implements ActionListener {

    private final Ocupacion modeloo;
    private final Habitante modeloh;
    private final consultasOcupacion consultaso;
    private final consultasHabitanteOcupacion consultasho;
    private final frm_agregarCenso vista;

    public CtrlOcupacion(Ocupacion modeloo, Habitante modeloh, consultasOcupacion consultaso, consultasHabitanteOcupacion consultasho, consultasHabitante consultash, frm_agregarCenso vista) {
        this.modeloo = modeloo;
        this.modeloh = modeloh;
        this.consultaso = consultaso;
        this.consultasho = consultasho;
        this.vista = vista;
        vista.cbocupacion.removeAllItems();
        llenarComboBoxOcupaciones();
       

        this.vista.btn_agregarocupacion.addActionListener(this);
        this.vista.btn_quitarocupacion.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Agregar Vivienda");
        vista.setResizable(true);
        vista.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
     
         vista.cbocupacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                String nombreOcupacion = vista.cbocupacion.getSelectedItem().toString();
               
                int idOcupacion = consultaso.obtenerIdOcupacion(nombreOcupacion);
                vista.txtidocupacion.setText(Integer.toString(idOcupacion));
            }
        });
         
         
        if (e.getSource() == vista.btn_agregarocupacion) {

            // Obtener los datos de la vista y guardarlos en el modelo
            modeloo.setIdocupacion(Integer.parseInt(vista.txtidocupacion.getText()));

            modeloh.setIdhabitante(Integer.parseInt(vista.txtidhab.getText()));

            
            if (consultasho.registrarOcupacionHabitante(modeloh, modeloo)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                
                
               

            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
            }

        }
    }

    private void llenarComboBoxOcupaciones() {
        List<String> ocupaciones = consultaso.obtenerNombreOcupacion();
        if (ocupaciones != null) {
            for (String ocupacion : ocupaciones) {
                vista.cbocupacion.addItem(ocupacion);
            }
        }
    }

}
