import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KupSamochod extends JFrame{
    private JTextField markaField;
    private JTextField modelField;
    private JTextField rokProdukcjiField;
    private JTextField pojemnoscSilnikaField;
    private JTextField mocSilnikaField;
    private JTextField cenaField;
    private JTextField kolorField;
    private JTextField rodzajPaliwaField;
    private JButton zatwierdzButton;
    private JPanel kupsamochod;
    private JButton powrotButton;
    private BazaPolaczenie bazaPolaczenie;

    public KupSamochod(BazaPolaczenie bazaPolaczenie){
        super("Kup Samochód");
        setContentPane(kupsamochod);
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

    private void zatwierdzDane() {
        String marka = markaField.getText();
        String model = modelField.getText();
        String rokProdukcji = rokProdukcjiField.getText();
        String pojemnoscSilnika = pojemnoscSilnikaField.getText();
        String mocSilnika = mocSilnikaField.getText();
        String cena = cenaField.getText();
        String kolor = kolorField.getText();
        String rodzajPaliwa = rodzajPaliwaField.getText();

        if (czyPolaWypelnione(marka, model, rokProdukcji, pojemnoscSilnika, mocSilnika, cena, kolor, rodzajPaliwa)) {
            zapiszDoBazy(marka, model, rokProdukcji, pojemnoscSilnika, mocSilnika, cena, kolor, rodzajPaliwa);
        } else {
            JOptionPane.showMessageDialog(this, "Wypełnij wszystkie pola przed zapisaniem do bazy danych", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean czyPolaWypelnione(String... pola) {
        for (String pole : pola) {
            if (pole.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void zapiszDoBazy(String marka, String model, String rokProdukcji, String pojemnoscSilnika, String mocSilnika, String cena, String kolor, String rodzajPaliwa) {
        Connection connection = bazaPolaczenie.getConnection();
        String query = "INSERT INTO samochody (marka_samochodu, model_samochodu, rok_produkcji, pojemnosc_silnika, moc_silnika, cena, kolor, rodzaj_paliwa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, marka);
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, rokProdukcji);
            preparedStatement.setString(4, pojemnoscSilnika);
            preparedStatement.setString(5, mocSilnika);
            preparedStatement.setString(6, cena);
            preparedStatement.setString(7, kolor);
            preparedStatement.setString(8, rodzajPaliwa);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Dodano samochód do bazy danych", "Informacja", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd podczas zapisu do bazy danych", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
