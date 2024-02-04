import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AktualizujSamochod extends JFrame{
    private JPanel aktualizujsamochod;
    private JButton zatwierdzButton;
    private JButton powrotButton;
    private JTextField idField;
    private JButton sprawdzIDButton;
    private JTextField markaField1;
    private JTextField modelField1;
    private JTextField rokProdukcjiField1;
    private JTextField pojemnoscSilnikaField1;
    private JTextField mocSilnikaField1;
    private JTextField cenaField1;
    private JTextField kolorField1;
    private JTextField rodzajPaliwaField1;
    private BazaPolaczenie bazaPolaczenie;

    public AktualizujSamochod(BazaPolaczenie bazaPolaczenie){
        super("Aktualizuj Samochód");
        setContentPane(aktualizujsamochod);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.bazaPolaczenie = bazaPolaczenie;


        sprawdzIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();

                if (czyIstniejeSamochod(id)) {
                    markaField1.setEnabled(true);
                    modelField1.setEnabled(true);
                    rokProdukcjiField1.setEnabled(true);
                    pojemnoscSilnikaField1.setEnabled(true);
                    mocSilnikaField1.setEnabled(true);
                    cenaField1.setEnabled(true);
                    kolorField1.setEnabled(true);
                    rodzajPaliwaField1.setEnabled(true);
                    JOptionPane.showMessageDialog(AktualizujSamochod.this, "Kontynuuj wypełnianie danych", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(AktualizujSamochod.this, "Samochód o podanym ID nie istnieje", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

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

    private boolean czyIstniejeSamochod(String id) {
        Connection connection = bazaPolaczenie.getConnection();
        String query = "SELECT * FROM samochody WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void zatwierdzDane() {
        String id = idField.getText();
        String marka = markaField1.getText();
        String model = modelField1.getText();
        String rokProdukcji = rokProdukcjiField1.getText();
        String pojemnoscSilnika = pojemnoscSilnikaField1.getText();
        String mocSilnika = mocSilnikaField1.getText();
        String cena = cenaField1.getText();
        String kolor = kolorField1.getText();
        String rodzajPaliwa = rodzajPaliwaField1.getText();

        aktualizujDaneSamochodu(id, marka, model, rokProdukcji, pojemnoscSilnika, mocSilnika, cena, kolor, rodzajPaliwa);
    }

    private void aktualizujDaneSamochodu(String id, String marka, String model, String rokProdukcji,
                                         String pojemnoscSilnika, String mocSilnika, String cena, String kolor, String rodzajPaliwa) {
        Connection connection = bazaPolaczenie.getConnection();
        String query = "UPDATE samochody SET marka_samochodu=?, model_samochodu=?, rok_produkcji=?, pojemnosc_silnika=?, moc_silnika=?, cena=?, kolor=?, rodzaj_paliwa=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, marka);
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, rokProdukcji);
            preparedStatement.setString(4, pojemnoscSilnika);
            preparedStatement.setString(5, mocSilnika);
            preparedStatement.setString(6, cena);
            preparedStatement.setString(7, kolor);
            preparedStatement.setString(8, rodzajPaliwa);
            preparedStatement.setString(9, id);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(AktualizujSamochod.this, "Zaktualizowano dane samochodu", "Informacja", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(AktualizujSamochod.this, "Błąd podczas aktualizacji danych", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
