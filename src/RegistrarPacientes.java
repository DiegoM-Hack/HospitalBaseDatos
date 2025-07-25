import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistrarPacientes {
    private JTextField historialClinico;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField telefono;
    private JTextField edad;
    private JTextField descripcion;
    private JButton busquedaButton;
    private JButton regresarButton;
    private JButton registrarButton;
    private JPanel principal;
    private JTextField cedula;

    public RegistrarPacientes() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ced = cedula.getText().trim();  // eliminamos espacios

                // Validar que no esté vacía
                if (ced.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese la cédula del paciente", "Cédula requerida", JOptionPane.WARNING_MESSAGE);
                    return; // Detener el proceso de guardado
                }

                try {
                    Connection con = Conexion.getConexion();
                    String sql = "INSERT INTO PACIENTE (cedula, n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setString(1, ced);
                    stmt.setInt(2, Integer.parseInt(historialClinico.getText()));
                    stmt.setString(3, nombre.getText());
                    stmt.setString(4, apellido.getText());
                    stmt.setString(5, telefono.getText());
                    stmt.setInt(6, Integer.parseInt(edad.getText()));
                    stmt.setString(7, descripcion.getText());

                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Paciente registrado correctamente");
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar paciente");
                }
            }
        });
        busquedaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la ventana actual
                JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(busquedaButton);

                // Cerrar la ventana actual
                ventanaActual.dispose();

                // Abrir nueva ventana (Busqueda)
                JFrame nuevaVentana = new JFrame("Buscar Pacientes");
                nuevaVentana.setContentPane(new Busqueda().getPanel());
                nuevaVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                nuevaVentana.pack();
                nuevaVentana.setVisible(true);
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(regresarButton);
                ventanaActual.dispose();

                JFrame nuevaVentana = new JFrame("Login");
                nuevaVentana.setContentPane(new Login().getPanel());
                nuevaVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                nuevaVentana.pack();
                nuevaVentana.setLocationRelativeTo(null);
                nuevaVentana.setVisible(true);
            }
        });
    }
    public JPanel getPanel() {
        return principal;
    }
}
