import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Pub pub = new Pub(false);

        JFrame window = new JFrame("Menu");
        window.setSize(1080, 720);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("FILE");
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        JMenuItem openOption = new JMenuItem("Open");
        JMenuItem saveOption = new JMenuItem("Save as");
        fileMenu.add(openOption);
        fileMenu.add(saveOption);

        window.add(menuBar);

        window.setVisible(true);
    }
}
