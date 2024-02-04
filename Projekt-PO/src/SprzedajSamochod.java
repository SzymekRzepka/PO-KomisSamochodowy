import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SprzedajSamochod extends JFrame{
    private JTextField idField;
    private JButton zatwierdzButton;
    private JButton powrotButton;
    private JPanel sprzedajsamochod;
    private BazaPolaczenie bazaPolaczenie;

    public SprzedajSamochod(BazaPolaczenie bazaPolaczenie){
        super("Sprzedaj Samochód");
        setContentPane(sprzedajsamochod);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.bazaPolaczenie = bazaPolaczenie;

        zatwierdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zatwierdzDane();
            }
        });

        powrotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void zatwierdzDane(){
        String id = idField.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Wprowadź ID samochodu", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int idSamochodu = Integer.parseInt(id);
            usunZBazy(idSamochodu);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Wprowadź poprawne ID samochodu", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void usunZBazy(int idSamochodu) {
        Connection connection = bazaPolaczenie.getConnection();
        String query = "DELETE FROM samochody WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idSamochodu);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Samochód sprzedany. Usunięto z bazy danych", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Nie znaleziono samochodu o podanym ID", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd podczas usuwania z bazy danych", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
