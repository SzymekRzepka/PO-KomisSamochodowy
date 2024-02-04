import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StronaGlowna extends JFrame{
    private JLabel zdjecie;
    private JPanel naglowek;
    private JButton kupButton;
    private JButton sprzedajButton;
    private JButton aktualizujButton;
    private JButton wszystkieButton;
    private JButton szukajButton;
    private JButton zakonczButton;
    private JPanel stronaGlowna;

    public StronaGlowna(){
        super("Strona Główna");
        setContentPane(stronaGlowna);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        zakonczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        kupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BazaPolaczenie bazaPolaczenie = new BazaPolaczenie();
                KupSamochod kupSamochod = new KupSamochod(bazaPolaczenie);
                kupSamochod.setVisible(true);
            }
        });

        sprzedajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BazaPolaczenie bazaPolaczenie = new BazaPolaczenie();
                SprzedajSamochod sprzedajSamochod = new SprzedajSamochod(bazaPolaczenie);
                sprzedajSamochod.setVisible(true);
            }
        });

        aktualizujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BazaPolaczenie bazaPolaczenie = new BazaPolaczenie();
                AktualizujSamochod aktualizujSamochod = new AktualizujSamochod(bazaPolaczenie);
                aktualizujSamochod.setVisible(true);
            }
        });

        wszystkieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BazaPolaczenie bazaPolaczenie = new BazaPolaczenie();
                WszystkieSamochody wszystkieSamochody = new WszystkieSamochody(bazaPolaczenie);
                wszystkieSamochody.setVisible(true);
            }
        });

        szukajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BazaPolaczenie bazaPolaczenie = new BazaPolaczenie();
                SzukajSamochod szukajSamochod = new SzukajSamochod(bazaPolaczenie);
                szukajSamochod.setVisible(true);
            }
        });
    }
}
