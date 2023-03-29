/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Habitante;
import modelo.Ocupacion;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import vista.frmMenuOp;
import vista.frm_agregarCenso;
import modelo.Vivienda;
import modelo.consultasHabitante;
import modelo.consultasHabitanteOcupacion;
import modelo.consultasOcupacion;
import modelo.consultasVivienda;

public class CtrlMenu implements ActionListener {

    private final frmMenuOp vistam;
    private final frm_agregarCenso vistaAgregar;

    public CtrlMenu(frmMenuOp vistam) {
        this.vistam = vistam;
        this.vistaAgregar = new frm_agregarCenso(); //se crea una instancia del formulario "frmAgregar"
        this.vistam.btn_agregarCenso.addActionListener(this);

        this.vistam.btn_dashboard.addActionListener(this);
    }

    public void iniciar() {
        vistam.setTitle("Menu de Opciones");
        vistam.setLocationRelativeTo(null);
        vistam.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistam.btn_agregarCenso) { //se verifica si el evento proviene del botón "btn_agregarCenso"

            
            frm_agregarCenso frmAgregar = new frm_agregarCenso();
            
            CtrlVivienda ctrlvivienda = new CtrlVivienda(new Vivienda(), new consultasVivienda(), frmAgregar);
            CtrlHabitante ctrlhabitante = new CtrlHabitante(new Habitante(), new consultasHabitante(), frmAgregar);
            CtrlOcupacion ctrlocupacion = new CtrlOcupacion(new Ocupacion(), new Habitante(), new consultasOcupacion(), new consultasHabitanteOcupacion(), new consultasHabitante(),  frmAgregar);

            ctrlvivienda.iniciar();
            ctrlhabitante.iniciar();
            ctrlocupacion.iniciar();

        }
    }

}
