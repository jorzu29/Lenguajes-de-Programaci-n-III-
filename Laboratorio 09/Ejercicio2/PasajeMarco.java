package Paquete;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

public class PasajeMarco extends JFrame {

    // Componentes de la GUI según el módulo
    private final JLabel lblNombre, lblDocumento, lblFecha, lblOrigen, lblDestino, lblPiso, lblServicio, lblOpcionales;
    private final JTextField txtNombre, txtDocumento, txtFecha;
    private final JCheckBox chkAudifonos, chkManta, chkRevistas;
    private final JRadioButton rbtnPiso1, rbtnPiso2;
    private final ButtonGroup grupoPisos;
    private final JComboBox<String> cboOrigen, cboDestino;
    private final JList<String> lstServicio;
    private final JButton btnProcesar, btnLimpiar;

    public PasajeMarco() {
        super("Sistema de Compra de Pasajes - Transporte Terrestre");
        setLayout(new FlowLayout());

        // 1. Etiquetas (JLabel) para rotular la información
        lblNombre = new JLabel("Nombre Completo:");
        txtNombre = new JTextField(18);
        add(lblNombre);
        add(txtNombre);

        lblDocumento = new JLabel("Documento de Identidad:");
        txtDocumento = new JTextField(12);
        add(lblDocumento);
        add(txtDocumento);

        lblFecha = new JLabel("Fecha de Viaje (DD/MM/AAAA):");
        txtFecha = new JTextField(10);
        add(lblFecha);
        add(txtFecha);

        // 2. Cuadros combinados (JComboBox) para Origen y Destino
        lblOrigen = new JLabel("Lugar de Origen:");
        String[] origenes = {"Lima", "Arequipa", "Cusco", "Tacna", "Trujillo"};
        cboOrigen = new JComboBox<>(origenes);
        add(lblOrigen);
        add(cboOrigen);

        lblDestino = new JLabel("Lugar de Destino:");
        String[] destinos = {"Cusco", "Arequipa", "Lima", "Tacna", "Trujillo"};
        cboDestino = new JComboBox<>(destinos);
        add(lblDestino);
        add(cboDestino);

        // 3. Botones de Opción (JRadioButton) para el piso del bus
        lblPiso = new JLabel("Ubicación en el Bus:");
        rbtnPiso1 = new JRadioButton("1er Piso", true);
        rbtnPiso2 = new JRadioButton("2do Piso", false);
        grupoPisos = new ButtonGroup();
        grupoPisos.add(rbtnPiso1);
        grupoPisos.add(rbtnPiso2);
        add(lblPiso);
        add(rbtnPiso1);
        add(rbtnPiso2);

        // 4. Lista (JList) para elegir la calidad de servicio
        lblServicio = new JLabel("Calidad de Servicio:");
        String[] servicios = {"Económico", "Standard", "VIP"};
        lstServicio = new JList<>(servicios);
        lstServicio.setVisibleRowCount(3);
        add(lblServicio);
        add(new JScrollPane(lstServicio));

        // 5. Casillas de Verificación (JCheckBox) para servicios opcionales
        lblOpcionales = new JLabel("Servicios Opcionales:");
        chkAudifonos = new JCheckBox("Audífonos");
        chkManta = new JCheckBox("Manta");
        chkRevistas = new JCheckBox("Revistas");
        add(lblOpcionales);
        add(chkAudifonos);
        add(chkManta);
        add(chkRevistas);

        // 6. Botones de Comando (JButton) para procesar y limpiar
        btnProcesar = new JButton("Mostrar Resumen");
        btnLimpiar = new JButton("Reiniciar");
        add(btnProcesar);
        add(btnLimpiar);

        // Registro de manejadores de eventos usando clases internas o ActionListener
        ManejadorEventos manejador = new ManejadorEventos();
        btnProcesar.addActionListener(manejador);
        btnLimpiar.addActionListener(manejador);
    }

    // Clase interna privada para el manejo de eventos de los botones
    private class ManejadorEventos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == btnProcesar) {
                // Validación básica de campos de texto
                String nombre = txtNombre.getText().trim();
                String documento = txtDocumento.getText().trim();
                String fecha = txtFecha.getText().trim();

                if (nombre.isEmpty() || documento.isEmpty() || fecha.isEmpty()) {
                    JOptionPane.showMessageDialog(PasajeMarco.this,
                            "Debe completar los campos de texto obligatorios.",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Recopilación de datos de los componentes
                String origen = (String) cboOrigen.getSelectedItem();
                String destino = (String) cboDestino.getSelectedItem();
                String piso = rbtnPiso1.isSelected() ? "1er Piso" : "2do Piso";
                String calidad = lstServicio.getSelectedValue();
                if (calidad == null) calidad = "No seleccionado";

                StringBuilder opcionales = new StringBuilder();
                if (chkAudifonos.isSelected()) opcionales.append("- Audífonos\n");
                if (chkManta.isSelected()) opcionales.append("- Manta\n");
                if (chkRevistas.isSelected()) opcionales.append("- Revistas\n");
                if (opcionales.length() == 0) opcionales.append("Ninguno");

                // Construcción del resumen para mostrar en un cuadro de diálogo (JOptionPane)
                String resumen = String.format(
                        "--- RESUMEN DE COMPRA DE PASAJE ---\n" +
                        "Pasajero: %s\n" +
                        "Documento: %s\n" +
                        "Fecha de Viaje: %s\n" +
                        "Ruta: %s -> %s\n" +
                        "Ubicación: %s\n" +
                        "Servicio: %s\n\n" +
                        "Opcionales:\n%s",
                        nombre, documento, fecha, origen, destino, piso, calidad, opcionales.toString()
                );

                JOptionPane.showMessageDialog(PasajeMarco.this,
                        resumen,
                        "Resumen de Datos del Pasajero",
                        JOptionPane.INFORMATION_MESSAGE);

            } else if (evento.getSource() == btnLimpiar) {
                // Blanquear / reiniciar todos los componentes de la GUI
                txtNombre.setText("");
                txtDocumento.setText("");
                txtFecha.setText("");
                cboOrigen.setSelectedIndex(0);
                cboDestino.setSelectedIndex(0);
                rbtnPiso1.setSelected(true);
                lstServicio.clearSelection();
                chkAudifonos.setSelected(false);
                chkManta.setSelected(false);
                chkRevistas.setSelected(false);
            }
        }
    }
}
