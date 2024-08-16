package presentacion;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import logica.Entidad;
import logica.NaveEspacial;
import logica.Proyectil;
import logica.SpaceInvaders;

public class ProyectilView extends Proyectil {

    private ImageIcon imageIcon;
    private java.awt.Image img;

    public ProyectilView(SpaceInvaders spaceInvaders, Proyectil proyectilLogica) {
        super(spaceInvaders);
        super.strImg1 = "/presentacion/Imagenes/Proyectil.png";
        super.strImg2 = "";
        super.strImg3 = "";
        this.imageIcon = new ImageIcon(getClass().getResource(super.strImg1));
        this.img = this.imageIcon.getImage();
    }


    @Override
    public void dibujarProyectil(Graphics g) {
        if (this.isEstaDisparando()) {
            g.drawImage(this.img, this.xPos, this.yPos, null);
        }
    }
}


