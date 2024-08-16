package presentacion;

import logica.Alien;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public class AlienView {
    private String image1;
    private String image2;

    private Alien alien;
    private int contadorImagen;

    public AlienView(Alien alien) {
        this.alien = alien;
        this.contadorImagen = 0;
    }

    public AlienView(String image1, String image2) {
        this.image1 = image1;
        this.image2 = image2;
    }

    public List<String> getImages() {
        List<String> images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        return images;
    }

    public static AlienView filaImagenesAlien(int fila) {
        switch (fila) {
            case 0:
                return new AlienView("/presentacion/Imagenes/alienBuho1.png", "/presentacion/Imagenes/alienBuho2.png");
            case 1:
            case 2:
                return new AlienView("/presentacion/Imagenes/alienBuldog1.png", "/presentacion/Imagenes/alienBuldog2.png");
            default:
                return new AlienView("/presentacion/Imagenes/alienChita1.png", "/presentacion/Imagenes/alienChita2.png");
        }
    }

    public void dibujarAlien(Graphics g) {
        actualizarImagen();
        String imagenActual = alien.getImagen();
        ImageIcon icono = new ImageIcon(getClass().getResource(imagenActual));
        Image imagen = icono.getImage();
        g.drawImage(imagen, alien.getxPos(), alien.getyPos(), null);
    }

    private void actualizarImagen() {
        if (contadorImagen < 24) {
            contadorImagen++;
        } else if (contadorImagen < 48) {
            contadorImagen++;
        } else {
            contadorImagen = 0;
        }
    }
}