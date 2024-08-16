package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import logica.*;

import static presentacion.VentanaMenu.spaceInvaders;

public class GameViewManager extends JPanel {

    private SpaceInvaders logic;
    private NaveEspacialView naveEspacialP;
    private ColmenaDeAliensView colmenaDeAliensP;
    private presentacion.EscudoView[] arregloEscudosPresentacion = new presentacion.EscudoView[spaceInvaders.getNumeroDeEscudos()];
    private NaveNodrizaView naveNodrizaP;
    private JFrame ventana;
    private boolean ventanaCerrada;
    private ManejadorDeEventosRepintar manejadorDeEventosRepintar;
    private Thread hiloRepintado;

    private Font fuentePuntuacion = new Font("Arial", Font.PLAIN, 20);
    private Font fuenteTexto = new Font("Arial", Font.PLAIN, 40);
    private Image fondo;

    public GameViewManager(SpaceInvaders logic, JFrame ventana) {
        super();
        this.logic = logic;
        this.ventana = ventana;
        this.ventanaCerrada = false;

        naveEspacialP = new NaveEspacialView(logic.getNaveEspacial());
        colmenaDeAliensP = new ColmenaDeAliensView(logic.getColmenaDeAliens());

        for (int columna = 0; columna < spaceInvaders.getNumeroDeEscudos(); columna++) {
            arregloEscudosPresentacion[columna] = new presentacion.EscudoView(logic.getEscudo(columna));
        }

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new TeclasDeMovimiento());

        manejadorDeEventosRepintar = new ManejadorDeEventosRepintar();
        hiloRepintado = new Thread(manejadorDeEventosRepintar);
        hiloRepintado.start();

        fondo = new ImageIcon(getClass().getResource("/presentacion/Imagenes/Fondo_Esp.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (fondo != null) {
            g2.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }

        g2.setColor(Color.cyan);
        g2.fillRect(30, 530, 535, 5);

        g.setFont(fuentePuntuacion);
        g.drawString("SCORE : " + logic.getScore(), 400, 25);

        if (ManejadorDeEventosRepintar.contadorDeIteraciones < 500) {
            g.setFont(fuenteTexto);
            g.drawString("Bienvenido !", 180, 100);
        }

        if (!logic.getNaveEspacial().isEstaVivo() && !ventanaCerrada) {
            g.setFont(fuenteTexto);
            g.drawString("TOP DOWN GANA", 120, 80);
            detenerJuego();
        }

        naveEspacialP.dibujarNave(g2);

        colmenaDeAliensP.dibujarColmenaDeAliens(g2);

        if (logic.getProyectil() != null && logic.getProyectil().isEstaDisparando()) {
            logic.getProyectil().dibujarProyectil(g2);
            logic.getProyectil().desplazarProyectil();
        }

        if (logic.getProyectil() != null) {
            logic.getColmenaDeAliens().proyectilImpactaNave(logic.getProyectil());
            logic.getProyectil().proyectilDestruyeEscudo(logic.getEscudos());
        }


        for (presentacion.EscudoView escudoP : arregloEscudosPresentacion) {
            escudoP.dibujarEscudo(g2);
        }

        manejarDisparosAliens(g2);

        if (ManejadorDeEventosRepintar.contadorDeIteraciones % 2500 == 0) {
            NaveNodriza naveNodriza = new NaveNodriza();
            naveNodrizaP = new NaveNodrizaView(naveNodriza);
            logic.setNaveNodriza(naveNodriza);
        }

        // Verificar y dibujar nave nodriza
        if (logic.getNaveNodriza() != null) {
            NaveNodriza naveNodriza = logic.getNaveNodriza();
            if (naveNodriza.getxPos() > 0) {
                if (logic.getProyectil() != null && logic.getProyectil().proyectilImpactoNaveNodriza(naveNodriza)) {
                    if (naveNodriza.getCambioX() != 0) {
                        logic.setScore(Constantes.PUNTAJE_NAVE_NODRIZA);
                    }
                    naveNodriza.setCambioX(0);
                    naveNodriza.setEstaVivo(false);
                    naveNodriza.musicaNaveNodriza.stop();
                    naveNodriza.musicaNaveNodrizaDestruida.play();
                }
                naveNodrizaP.dibujarNave(g2);
            } else {
                logic.setNaveNodriza(null);
                naveNodrizaP = null;
            }
        }

        if (logic.getColmenaDeAliens().getNumeroTotalAliens() == 0) {
            ColmenaDeAliens nuevaColmena = new ColmenaDeAliens(this.logic, logic.getColmenaDeAliens().getNumFilasColmena(), logic.getColmenaDeAliens().getNumColumnasColmena());
            logic.setColmenaDeAliens(nuevaColmena);
            colmenaDeAliensP = new ColmenaDeAliensView(nuevaColmena);
        }

        if (logic.getColmenaDeAliens().obtenerPosicionAlienigenaMasBajo() > Constantes.Y_POSICION_INICIAL_NAVE) {
            logic.getNaveEspacial().destruirNave();
        }
    }

    private List<DisparoAlien> disparosAliens = new ArrayList<>();

    private void manejarDisparosAliens(Graphics g) {
        if (ManejadorDeEventosRepintar.contadorDeIteraciones % 500 == 0) {
            DisparoAlien disparo = new DisparoAlien(logic.getColmenaDeAliens().elegirAlienParaDisparar(), this.logic);
            disparosAliens.add(disparo);
        }
        if (ManejadorDeEventosRepintar.contadorDeIteraciones % 750 == 0) {
            DisparoAlien disparo = new DisparoAlien(logic.getColmenaDeAliens().elegirAlienParaDisparar(), this.logic);
            disparosAliens.add(disparo);
        }
        if (ManejadorDeEventosRepintar.contadorDeIteraciones % 900 == 0) {
            DisparoAlien disparo = new DisparoAlien(logic.getColmenaDeAliens().elegirAlienParaDisparar(), this.logic);
            disparosAliens.add(disparo);
        }

        List<DisparoAlien> disparosEliminar = new ArrayList<>();
        for (DisparoAlien disparo : disparosAliens) {
            if (disparo != null) {
                disparo.desplazarProyectil();
                disparo.dibujarProyectil(g);
                disparo.detectarImpactoConEscudo(logic.getEscudos());
                if (disparo.detectarImpactoNave(logic.getNaveEspacial())) {
                    logic.getNaveEspacial().setEstaVivo(false);
                }
                if (!disparo.isEstaDisparando()) {
                    disparosEliminar.add(disparo);
                }
            }
        }
        disparosAliens.removeAll(disparosEliminar);
    }

    private void detenerJuego() {
        ventanaCerrada = true;
        manejadorDeEventosRepintar.detener();

        new Thread(() -> {
            try {
                // Esperar 3 segundos
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(() -> {
                ventana.dispose();
                VentanaMenu.resetearJuego();
            });
        }).start();
    }
}
