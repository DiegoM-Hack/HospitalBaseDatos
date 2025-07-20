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

    public RegistrarPacientes() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = Conexion.getConexion();
                    String sql = "INSERT INTO PACIENTE (cedula, n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setString(1, historialClinico.getText()); // reemplaza con un campo de cédula si lo agregas
                    stmt.setInt(2, Integer.parseInt(historialClinico.getText()));
                    stmt.setString(3, nombre.getText());
                    stmt.setString(4, apellido.getText());
                    stmt.setString(5, telefono.getText());
                    stmt.setInt(6, Integer.parseInt(edad.getText()));
                    stmt.setString(7, descripcion.getText());

                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Paciente registrado");
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar paciente");
                }
            }
        });
    }
    public JPanel getPanel() {
        return principal;
    }
}
