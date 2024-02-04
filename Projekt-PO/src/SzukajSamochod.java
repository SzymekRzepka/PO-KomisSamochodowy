import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SzukajSamochod extends JFrame{
    private JPanel szukajsamochod;
    private JButton powrotButton;
    private JButton szukajButton;
    private JTextField pojemnoscSilnikaField1;
    private JTextField rokProdukcjiField2;
    private JTextField cenaField3;
    private JTable tabela;
    private BazaPolaczenie bazaPolaczenie;

    public SzukajSamochod(BazaPolaczenie bazaPolaczenie){
        super("Szukaj Samochodu");
        setContentPane(szukajsamochod);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.bazaPolaczenie = bazaPolaczenie;

        szukajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                szukajDane();
            }
        });

        powrotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void szukajDane(){
        String pojemnoscSilnikaStr = pojemnoscSilnikaField1.getText();
        String rokProdukcjiStr = rokProdukcjiField2.getText();
        String cenaStr = cenaField3.getText();

        try {
            int pojemnoscSilnika = Integer.parseInt(pojemnoscSilnikaStr);
            int rokProdukcji = Integer.parseInt(rokProdukcjiStr);
            int cena = Integer.parseInt(cenaStr);

            Connection connection = bazaPolaczenie.getConnection();
            String query = "SELECT * FROM samochody WHERE pojemnosc_silnika >= ? AND rok_produkcji >= ? AND cena <= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pojemnoscSilnika);
            preparedStatement.setInt(2, rokProdukcji);
            preparedStatement.setInt(3, cena);

            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Marka");
            model.addColumn("Model");
            model.addColumn("Rok produkcji");
            model.addColumn("Pojemność silnika");
            model.addColumn("Moc silnika");
            model.addColumn("Cena");
            model.addColumn("Kolor");
            model.addColumn("Rodzaj paliwa");

            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("id"),
                        resultSet.getString("marka_samochodu"),
                        resultSet.getString("model_samochodu"),
                        resultSet.getInt("rok_produkcji"),
                        resultSet.getInt("pojemnosc_silnika"),
                        resultSet.getInt("moc_silnika"),
                        resultSet.getInt("cena"),
                        resultSet.getString("kolor"),
                        resultSet.getString("rodzaj_paliwa"),
                };
                model.addRow(row);
            }

            tabela.setModel(model);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Wprowadź poprawne liczby", "Błąd", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd podczas wyszukiwania w bazie danych", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
