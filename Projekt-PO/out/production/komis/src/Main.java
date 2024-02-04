import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StronaGlowna stronaGlowna = new StronaGlowna();
            stronaGlowna.setVisible(true);
        });
    }
}