package logica;

import presentacion.Audio;

import java.awt.*;

public abstract class Proyectil extends Entidad {
    private boolean estaDisparando = false;
    private final SpaceInvaders spaceInvaders;

    public Proyectil(SpaceInvaders spaceInvaders) {
        this.spaceInvaders = spaceInvaders;
        this.ancho = Constantes.ANCHO_PROYECTIL;
        this.alto = Constantes.ALTO_PROYECTIL;
        this.cambioX = 0;
        this.cambioY = Constantes.UNIDADES_DE_DESPLAZAMIENTOS;
        this.estaDisparando = false;
    }

    public boolean isEstaDisparando() {
        return estaDisparando;
    }

    public void setEstaDisparando(boolean estaDisparando) {
        this.estaDisparando = estaDisparando;
    }

    public int desplazarProyectil() {
        if (estaDisparando) {
            if (yPos > 0) {
                yPos -= Constantes.UNIDADES_DE_DESPLAZAMIENTOS;
            } else {
                estaDisparando = false;
            }
        }
        return yPos;
    }

    public boolean destruirAlien(Alien alien) {
        boolean impacto = isImpacto(alien.getyPos(), alien.getAlto(), alien.getxPos(), alien.getAncho());
        if (impacto) {
            Audio.playSound("/presentacion/Sonidos/sonidoDestruccionDeAlien.wav");
        }
        return impacto;
    }

    private boolean isImpacto(int alien, int alien1, int alien2, int alien3) {
        return yPos < alien + alien1 &&
                yPos + alto > alien &&
                xPos + ancho > alien2 &&
                xPos < alien2 + alien3;
    }

    private boolean estaElProyectilALaAlturaDeEscudo() {
        return yPos < Constantes.POSICION_Y_ESCUDO + Constantes.ALTO_ESCUDO &&
                yPos + alto > Constantes.POSICION_Y_ESCUDO;
    }

    private int numeroEscudoEncontrado() {
        for (int columna = 0; columna < spaceInvaders.getNumeroDeEscudos(); columna++) {
            int posXEscudoInicio = Constantes.MARGEN_VENTANA + Constantes.POSICION_X_PRIMER_ESCUDO + columna * (Constantes.ANCHO_ESCUDO + Constantes.ESPACIO_DE_SEPARACION_ENTRE_ESCUDOS); //Calcula la posición inicial (X) del escudo en la columna actual.
            int posXEscudoFin = posXEscudoInicio + Constantes.ANCHO_ESCUDO;

            if (xPos + ancho > posXEscudoInicio && xPos < posXEscudoFin) {
                return columna;
            }
        }
        return -1;
    }

    private int obtenerAbscisaDeImpactoProyectilConEscudo(Escudo escudo) {
        if (xPos + ancho > escudo.getxPos() && xPos < escudo.getxPos() + Constantes.ANCHO_ESCUDO) {
            return xPos;
        }
        return -1;
    }

    public int[] detectarEscudoDeImpacto() {
        int[] matrizNumEscudoYPosX = {-1, -1};
        if (estaElProyectilALaAlturaDeEscudo()) {
            int numeroEscudo = numeroEscudoEncontrado();
            if (numeroEscudo != -1) {
                matrizNumEscudoYPosX[0] = numeroEscudo;
                matrizNumEscudoYPosX[1] = obtenerAbscisaDeImpactoProyectilConEscudo(
                        spaceInvaders.getArregloEscudosLogica()[numeroEscudo]);
            }
        }
        return matrizNumEscudoYPosX;
    }

    public void proyectilDestruyeEscudo(Escudo[] arregloEscudos) {
        int[] impacto = detectarEscudoDeImpacto();
        if (impacto[0] != -1) { //Si hubo un impacto
            Escudo escudoImpactado = arregloEscudos[impacto[0]];
            int columnaImpacto = escudoImpactado.determinarColumnaDondeImpactoMisilEnElEscudo(impacto[1]);
            if (escudoImpactado.encontrarFilaDondeSeEncuentraElBloque(columnaImpacto) != -1) {
                escudoImpactado.romperBloques(impacto[1]);
                yPos = -1;
            }
        }
    }

    public boolean proyectilImpactoNaveNodriza(NaveNodriza naveNodriza) {
        boolean impacto = (yPos < naveNodriza.getyPos() + naveNodriza.getAlto() && yPos + alto > naveNodriza.getyPos() &&
                xPos + ancho > naveNodriza.getxPos() && xPos < naveNodriza.getxPos() + naveNodriza.getAncho());

        if (impacto) {
            estaDisparando = false;
        }
        return impacto;
    }

    public abstract void dibujarProyectil(Graphics g);

}
