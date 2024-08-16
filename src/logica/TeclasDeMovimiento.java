package logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import presentacion.Audio;
import presentacion.ProyectilNaveEspacialView;
import presentacion.VentanaMenu;

public class TeclasDeMovimiento implements KeyListener {

    private static final int TIEMPO_ENTRE_DISPAROS_MS = 750;
    private Timer timer;
    private boolean puedeDisparar;

    public TeclasDeMovimiento() {
        puedeDisparar = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        SpaceInvaders logic = VentanaMenu.spaceInvaders;

        if (logic.getNaveEspacial().isEstaVivo()) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                logic.getNaveEspacial().setCambioX(Constantes.ESPACIO_DESPLAZAMIENTO_X_NAVE_ESPACIAL);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) { //Si se presiona la tecla izquierda (←)
                logic.getNaveEspacial().setCambioX(-Constantes.ESPACIO_DESPLAZAMIENTO_X_NAVE_ESPACIAL);
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (puedeDisparar) {
                    Audio.playSound("/presentacion/Sonidos/sonidoProyectil.wav");

                    ProyectilNaveEspacialView proyectil = (ProyectilNaveEspacialView) logic.getProyectil();
                    if (proyectil == null) {
                        proyectil = new ProyectilNaveEspacialView(VentanaMenu.spaceInvaders,
                                logic.getNaveEspacial().getxPos() + Constantes.ANCHO_NAVE / 2 - 1,
                                Constantes.Y_POSICION_INICIAL_NAVE - Constantes.ALTO_PROYECTIL,
                                true
                        );
                        logic.setProyectil(proyectil);
                    } else {
                        proyectil.setxPos(logic.getNaveEspacial().getxPos() + Constantes.ANCHO_NAVE / 2 - 1);
                        proyectil.setyPos(Constantes.Y_POSICION_INICIAL_NAVE - Constantes.ALTO_PROYECTIL);
                        proyectil.setEstaDisparando(true);
                    }
                    puedeDisparar = false;
                    timer = new Timer(TIEMPO_ENTRE_DISPAROS_MS, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            puedeDisparar = true;
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        SpaceInvaders logic = VentanaMenu.spaceInvaders;
        if (logic != null) {
            logic.getNaveEspacial().setCambioX(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}