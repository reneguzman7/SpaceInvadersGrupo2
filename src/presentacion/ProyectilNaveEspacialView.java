package presentacion;

import logica.Proyectil;
import logica.SpaceInvaders;

import java.awt.*;
public class ProyectilNaveEspacialView extends Proyectil {

    public ProyectilNaveEspacialView(SpaceInvaders spaceInvaders, int xPos, int yPos, boolean estaDisparando) {
        super(spaceInvaders);
        this.xPos = xPos;
        this.yPos = yPos;
        this.setEstaDisparando(estaDisparando);
    }

    @Override
    public void dibujarProyectil(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(this.xPos, this.yPos, this.ancho, this.alto);
    }
}
