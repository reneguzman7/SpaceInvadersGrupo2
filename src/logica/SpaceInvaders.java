package logica;

import java.util.ArrayList;
import java.util.List;

import presentacion.ManejadorDeEventosRepintar;

public class SpaceInvaders {

    private final NaveEspacial naveEspacial;
    private ColmenaDeAliens colmenaDeAliens;
    private Proyectil proyectil;
    private NaveNodriza naveNodriza;
    private final Escudo[] escudos;
    private int score = 0;
    private final List<DisparoAlien> disparosAliens;
    public final int numeroDeEscudos = 5;

    public SpaceInvaders() {
        naveEspacial = new NaveEspacial();
        colmenaDeAliens = new ColmenaDeAliens(this,5,5);
        proyectil = null;
        naveNodriza = null;

        escudos = new Escudo[numeroDeEscudos];
        for (int i = 0; i < numeroDeEscudos; i++) {
            escudos[i] = new Escudo(Constantes.MARGEN_VENTANA + Constantes.POSICION_X_PRIMER_ESCUDO + i * (Constantes.ANCHO_ESCUDO + Constantes.ESPACIO_DE_SEPARACION_ENTRE_ESCUDOS));
        }

        disparosAliens = new ArrayList<>();
    }

    public int getNumeroDeEscudos() {
        return numeroDeEscudos;
    }

    public NaveEspacial getNaveEspacial() {
        return naveEspacial;
    }

    public ColmenaDeAliens getColmenaDeAliens() {
        return colmenaDeAliens;
    }

    public Proyectil getProyectil() {
        return proyectil;
    }

    public NaveNodriza getNaveNodriza() {
        return naveNodriza;
    }

    public Escudo getEscudo(int index) {
        return escudos[index];
    }

    public Escudo[] getEscudos() {
        return escudos;
    }

    public int getScore() {
        return score;
    }

    public Escudo[] getArregloEscudosLogica() {
        return escudos;
    }

    public void setProyectil(Proyectil proyectil) {
        this.proyectil = proyectil;
    }

    public void setColmenaDeAliens(ColmenaDeAliens nuevaColmena) {
        this.colmenaDeAliens = nuevaColmena;
    }

    public void setNaveNodriza(NaveNodriza naveNodriza) {
        this.naveNodriza = naveNodriza;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void manejarDisparosAliens() {
        agregarDisparosAliens();
        List<DisparoAlien> disparosEliminar = new ArrayList<>();
        for (DisparoAlien disparo : disparosAliens) {
            procesarDisparoAlien(disparo, disparosEliminar);
        }
        disparosAliens.removeAll(disparosEliminar);
    }

    private void agregarDisparosAliens() {
        if (ManejadorDeEventosRepintar.contadorDeIteraciones % 500 == 0 ||
                ManejadorDeEventosRepintar.contadorDeIteraciones % 750 == 0 ||
                ManejadorDeEventosRepintar.contadorDeIteraciones % 900 == 0) {
            disparosAliens.add(new DisparoAlien(colmenaDeAliens.elegirAlienParaDisparar(), this));
        }
    }

    private void procesarDisparoAlien(DisparoAlien disparo, List<DisparoAlien> disparosEliminar) {
        if (disparo != null) {
            disparo.desplazarProyectil();
            disparo.detectarImpactoConEscudo(escudos);
            if (disparo.detectarImpactoNave(naveEspacial)) {
                naveEspacial.setEstaVivo(false);
            }
            if (!disparo.isEstaDisparando()) {
                disparosEliminar.add(disparo);
            }
        }
    }

}
