package presentacion;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import logica.NaveEspacial;

public class NaveEspacialView {

    private NaveEspacial naveEspacial;
    private Image img;
    private ImageIcon imageIcon;
    private int contador = 0;

    public NaveEspacialView(NaveEspacial naveEspacial) {
        this.naveEspacial = naveEspacial;
        cargarImagenes();
    }

    private void cargarImagenes() {
        imageIcon = new ImageIcon(getClass().getResource("/presentacion/Imagenes/naveEspacial.png"));
        img = imageIcon.getImage();
    }

    public void dibujarNave(Graphics g) {
        if (!naveEspacial.estaVivo()) {
            destruirNave();
        }
        g.drawImage(img, naveEspacial.desplazarNave(), naveEspacial.getyPos(), null);
    }

    private void destruirNave() {
        if (contador < 300) {
            if (ManejadorDeEventosRepintar.contadorDeIteraciones % 2 == 0) {
                imageIcon = new ImageIcon(getClass().getResource("/presentacion/Imagenes/naveEspacialDestruida.png"));
            } else {
                imageIcon = new ImageIcon(getClass().getResource("/presentacion/Imagenes/naveEspacialDestruida2.png"));
            }
            img = imageIcon.getImage();
            contador++;
        }
    }
}