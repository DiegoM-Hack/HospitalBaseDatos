import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Busqueda {
    private JPanel principal;
    private JTextField cedula;
    private JButton loginButton;
    private JButton registroButton;
    private JButton buscarButton;

    public Busqueda() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedulaBuscar = cedula.getText();
                try {
                    Connection con = Conexion.getConexion();
                    String sql = "SELECT * FROM PACIENTE WHERE cedula = ?";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setString(1, cedulaBuscar);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        String datos = "Nombre: " + rs.getString("nombre") +
                                "\nApellido: " + rs.getString("apellido") +
                                "\nTeléfono: " + rs.getString("telefono") +
                                "\nEdad: " + rs.getInt("edad") +
                                "\nEnfermedad: " + rs.getString("descripcion_enfermedad");
                        JOptionPane.showMessageDialog(null, datos);
                    } else {
                        JOptionPane.showMessageDialog(null, "Paciente no encontrado");
                    }
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        registroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public JPanel getPanel() {
        return principal;
    }
}
