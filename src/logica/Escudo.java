package logica;

import presentacion.Audio;

public class Escudo extends Entidad {

    private final int numeroDeFilasEnEscudo = Constantes.ALTO_ESCUDO / Constantes.DIMENSIONES_DE_BLOQUE_DE_ESCUDO;
    private final int numeroDeColumnasEnEscudo = Constantes.ANCHO_ESCUDO / Constantes.DIMENSIONES_DE_BLOQUE_DE_ESCUDO;
    private final boolean[][] matrizEscudo = new boolean[numeroDeFilasEnEscudo][numeroDeColumnasEnEscudo];

    public Escudo(int xPos) {
        this.xPos = xPos;
        this.yPos = Constantes.POSICION_Y_ESCUDO;
        this.crearEscudo();
    }

    private void crearEscudo() {
        for (int fila = 0; fila < numeroDeFilasEnEscudo; fila++) {
            for (int columna = 0; columna < numeroDeColumnasEnEscudo; columna++) {
                matrizEscudo[fila][columna] = true;
            }
        }

        vaciarBloques(0, 6, 0, 2);
        vaciarBloques(2, 4, 0, 4);
        vaciarBloques(4, 6, 0, 2);
        vaciarBloques(18, numeroDeFilasEnEscudo, 10, numeroDeColumnasEnEscudo - 10);
        vaciarBloques(16, 18, 12, numeroDeColumnasEnEscudo - 12);
        vaciarBloques(14, 16, 14, numeroDeColumnasEnEscudo - 14);
    }

    private void vaciarBloques(int filaInicio, int filaFin, int columnaInicio, int columnaFin) {
        for (int fila = filaInicio; fila < filaFin; fila++) {
            for (int columna = columnaInicio; columna < columnaFin; columna++) {
                matrizEscudo[fila][columna] = false;
                matrizEscudo[fila][numeroDeColumnasEnEscudo - columna - 1] = false;
            }
        }
    }

    public int determinarColumnaDondeImpactoMisilEnElEscudo(int posicionXProyectil) {
        return (posicionXProyectil - this.xPos) / Constantes.DIMENSIONES_DE_BLOQUE_DE_ESCUDO;
    }

    public int encontrarFilaDondeSeEncuentraElBloque(int columna) {
        int fila = numeroDeFilasEnEscudo - 1;
        while (fila >= 0 && !matrizEscudo[fila][columna]) {
            fila--;
        }
        return fila;
    }

    private void eliminarBloques(int fila, int columna) {
        for (int contador = 0; contador < 6; contador++) {
            if (fila - contador >= 0) {
                matrizEscudo[fila - contador][columna] = false;
                if (columna < numeroDeColumnasEnEscudo - 1) {
                    matrizEscudo[fila - contador][columna + 1] = false;
                }
            }
        }
    }

    public void romperBloques(int posicionXProyectil) {
        Audio.playSound("/presentacion/Sonidos/sonidoImactoDisparoContraEscudo.wav");
        int columna = determinarColumnaDondeImpactoMisilEnElEscudo(posicionXProyectil);
        eliminarBloques(encontrarFilaDondeSeEncuentraElBloque(columna), columna);
    }

    public int encontrarBloqueSuperior(int columna) {
        int fila = 0;
        if (columna != -1) {
            while (fila < numeroDeFilasEnEscudo && !matrizEscudo[fila][columna]) {
                fila++;
            }
        }
        return fila;
    }

    private void eliminarBloquesDesdeArribaEnLaMatrizBooleanDelEscudo(int fila, int columna) {
        for (int contador = 0; contador < 6; contador++) {
            if (fila + contador < numeroDeFilasEnEscudo && columna != -1) {
                matrizEscudo[fila + contador][columna] = false;
                if (columna < numeroDeColumnasEnEscudo - 1) {
                    matrizEscudo[fila + contador][columna + 1] = false;
                }
            }
        }
    }

    public void romperBloquesDesdeArriba(int posXDisparoAlien) {
        Audio.playSound("/presentacion/Sonidos/sonidoImactoDisparoContraEscudo.wav");
        int columna = determinarColumnaDondeImpactoMisilEnElEscudo(posXDisparoAlien);
        eliminarBloquesDesdeArribaEnLaMatrizBooleanDelEscudo(encontrarBloqueSuperior(columna), columna);
    }

    public int getNumeroDeFilasEnEscudo() {
        return numeroDeFilasEnEscudo;
    }

    public int getNumeroDeColumnasEnEscudo() {
        return numeroDeColumnasEnEscudo;
    }

    public boolean[][] getMatrizEscudo() {
        return matrizEscudo;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
