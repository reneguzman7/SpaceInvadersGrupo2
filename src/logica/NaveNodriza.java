package logica;

import presentacion.Audio;
import presentacion.ManejadorDeEventosRepintar; //Clase que gestiona los eventos de repintado, utilizada para sincronizar animaciones o actualizaciones.

import javax.swing.*;

public class NaveNodriza extends Entidad {

    public final Audio musicaNaveNodriza = new Audio("/presentacion/Sonidos/sonidoNaveNodriza.wav");
    public final Audio musicaNaveNodrizaDestruida = new Audio("/presentacion/Sonidos/sonidoNaveNodrizaDestruida.wav");
    private int contador = 0;

    public NaveNodriza() {
        this.xPos = Constantes.POS_X_INICIAL_NAVE_NODRIZA;
        this.yPos = Constantes.POS_Y_NAVE_NODRIZA;
        this.ancho = Constantes.ANCHO_NAVE_NODRIZA;
        this.alto = Constantes.ALTO_NAVE_NODRIZA;
        this.cambioX = Constantes.ESPACIO_DE_DESPLAZAMIENTO_EN_Y_NAVE_NODRIZA;
        this.cambioY = 0;

        this.strImg1 = "/presentacion/Imagenes/naveNodriza.png";
        this.strImg2 = "/presentacion/Imagenes/naveNodrizaDestruida.png";
        this.strImg3 = "";
        this.imageIcon = new ImageIcon(getClass().getResource(strImg1));
        this.img = this.imageIcon.getImage();
        this.estaVivo = true;

        this.musicaNaveNodriza.play();
        this.musicaNaveNodrizaDestruida.stop();
    }

    public int desplazarNave() {
        this.xPos = (this.estaVivo && ManejadorDeEventosRepintar.contadorDeIteraciones % 2 == 0) ?
                (this.xPos > 0 ? this.xPos - this.cambioX : Constantes.POS_X_INICIAL_NAVE_NODRIZA) : this.xPos;
        return this.xPos;
    }

    public void actualizarEstado() {
        if (!this.estaVivo) {
            actualizarContadorXPosicion();
        }
    }

    private void actualizarContadorXPosicion() {
        contador = (contador < 300) ? contador + 1 : contador;
        if (contador >= 300) {
            restablecerNave();
        }
    }

    private void restablecerNave() {
        this.xPos = Constantes.POS_X_INICIAL_NAVE_NODRIZA;
        this.estaVivo = false;
    }

    public boolean estaVivo() {
        return this.estaVivo;
    }

}