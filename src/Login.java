import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    private JPanel principal;
    private JTextField usuario;
    private JTextField clave;
    private JButton validarButton;

    public Login() {

        validarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usuario.getText();
                String pass = clave.getText();

                try {
                    Connection con = Conexion.getConexion();
                    String query = "SELECT * FROM USUARIO WHERE username = ? AND password = ?";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, user);
                    stmt.setString(2, pass);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + user);
                        JFrame ventanaLogin = (JFrame) SwingUtilities.getWindowAncestor(validarButton);
                        ventanaLogin.dispose();

                        JFrame frame = new JFrame("Registrar Pacientes");
                        frame.setContentPane(new RegistrarPacientes().getPanel());
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        frame.pack();
                        frame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o clave incorrectos");
                    }
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getPanel() {
        return principal;
    }
}

