package presentacion;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import logica.NaveNodriza;

public class NaveNodrizaView {

    private NaveNodriza naveNodriza;
    private ImageIcon imageIcon;
    private java.awt.Image img;

    public NaveNodrizaView(NaveNodriza naveNodriza) {
        this.naveNodriza = naveNodriza;
        this.imageIcon = new ImageIcon(getClass().getResource(naveNodriza.getStrImg1()));
        this.img = this.imageIcon.getImage();
    }

    public void dibujarNave(Graphics g) {
        if (naveNodriza.isEstaVivo() == false) {
            naveNodriza.actualizarEstado();
            actualizarImagen();
        }
        g.drawImage(this.img, naveNodriza.desplazarNave(), naveNodriza.getyPos(), null);
    }

    public void actualizarImagen() {
        if (!naveNodriza.estaVivo()) {
            this.imageIcon = new ImageIcon(getClass().getResource(naveNodriza.getStrImg2()));
            this.img = this.imageIcon.getImage();
        }
    }
}