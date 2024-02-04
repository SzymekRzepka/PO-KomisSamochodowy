import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WszystkieSamochody extends JFrame{
    private JPanel wszystkiesamochody;
    private JButton powrotButton;
    private JTable tabela;
    private BazaPolaczenie bazaPolaczenie;

    public WszystkieSamochody(BazaPolaczenie bazaPolaczenie){
        super("Wszystkie Samochód");
        setContentPane(wszystkiesamochody);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.bazaPolaczenie = bazaPolaczenie;

        wczytajDaneSamochodow();

        powrotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void wczytajDaneSamochodow(){
        Connection connection = bazaPolaczenie.getConnection();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Marka");
        model.addColumn("Model");
        model.addColumn("Rok Produkcji");
        model.addColumn("Pojemność Silnika");
        model.addColumn("Moc Silnika");
        model.addColumn("Cena");
        model.addColumn("Kolor");
        model.addColumn("Rodzaj Paliwa");

        String query = "SELECT * FROM samochody";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getString("id"),
                        resultSet.getString("marka_samochodu"),
                        resultSet.getString("model_samochodu"),
                        resultSet.getString("rok_produkcji"),
                        resultSet.getString("pojemnosc_silnika"),
                        resultSet.getString("moc_silnika"),
                        resultSet.getString("cena"),
                        resultSet.getString("kolor"),
                        resultSet.getString("rodzaj_paliwa")
                };
                model.addRow(row);
            }

            tabela.setModel(model);


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd podczas wczytywania danych", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
