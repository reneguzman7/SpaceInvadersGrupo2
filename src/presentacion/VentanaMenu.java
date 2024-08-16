package presentacion;

import logica.Constantes;
import logica.SpaceInvaders;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {

    public static SpaceInvaders spaceInvaders;
    public static GameViewManager gameViewManager;
    public static JFrame ventana;
    public static boolean juego = true;

    public VentanaMenu() {
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon backgroundImageIcon = new ImageIcon(VentanaMenu.class.getResource("/presentacion/Imagenes/polinvaders-fondo4.png"));

        if (backgroundImageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("La imagen de fondo no se pudo cargar");
            return;
        }

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight()));

        JLabel imageLabel = new JLabel(backgroundImageIcon);
        imageLabel.setBounds(0, 0, backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight());
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        ImageIcon titleImageIcon = new ImageIcon(VentanaMenu.class.getResource("/presentacion/Imagenes/logo-polinvaders.png"));

        JLabel titleLabel = new JLabel(titleImageIcon);

        int titleWidth = titleImageIcon.getIconWidth();
        int titleHeight = titleImageIcon.getIconHeight();
        int titleX = (backgroundImageIcon.getIconWidth() - titleWidth) / 2;
        int titleY = 50;

        titleLabel.setBounds(titleX, titleY, titleWidth, titleHeight);
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);

        ImageIcon playButtonIcon = new ImageIcon(VentanaMenu.class.getResource("/presentacion/Imagenes/Boton_Play.png"));

        JButton playButton = new JButton(playButtonIcon);

        int buttonWidth = 300;
        int buttonHeight = 100;
        int buttonX = (backgroundImageIcon.getIconWidth() - buttonWidth) / 2;
        int buttonY = backgroundImageIcon.getIconHeight() - buttonHeight - 80;

        playButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        layeredPane.add(playButton, JLayeredPane.PALETTE_LAYER);

        playButton.addActionListener(e -> {
            ejecutarPrograma();
            frame.dispose();
        });

        frame.add(layeredPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void ejecutarPrograma() {
        if (ventana != null) {
            ventana.dispose();
        }

        ventana = new JFrame("Juego PoliSpace Invaders");
        ventana.setSize(Constantes.ANCHO_VENTANA, Constantes.ALTURA_VENTANA);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setAlwaysOnTop(true);

        spaceInvaders = new SpaceInvaders();
        gameViewManager = new GameViewManager(spaceInvaders, ventana);

        ventana.setContentPane(gameViewManager);
        ventana.setVisible(true);
    }

    public static void resetearJuego() {
        juego = false;
        ventana.dispose();
        spaceInvaders = null;
        gameViewManager = null;
        juego = true;
        new VentanaMenu();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaMenu::new);
    }
}
