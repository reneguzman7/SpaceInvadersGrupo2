package logica;

import presentacion.AlienView;
import presentacion.Audio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ColmenaDeAliens {

    private boolean estaYendoHaciaLaDerecha;
    private boolean esPrimeraImagenDelAlien;
    public int velocidad;
    private int[] matrizAliensMuertos = {-1, -1};
    private Random random = new Random();
    private int numeroTotalAliens;
    private int contadorDeNumeroDeSonidosAliens = 0;
    private SpaceInvaders spaceInvaders;

    public int numFilasColmena;
    public int numColumnasColmena;
    private Alien[][] colmenaDeAliens;

    public ColmenaDeAliens(SpaceInvaders spaceInvaders, int numFilas, int numColumnas) {
        this.spaceInvaders = spaceInvaders;
        this.numFilasColmena = numFilas;
        this.numColumnasColmena = numColumnas;
        this.colmenaDeAliens = new Alien[numFilasColmena][numColumnasColmena];
        this.crearColmenaDeAliens();
        this.estaYendoHaciaLaDerecha = true;
        this.esPrimeraImagenDelAlien = true;
        this.velocidad = Constantes.VELOCIDAD_ALIENIGENAS;
        this.numeroTotalAliens = numFilas * numColumnas;
    }

    public int getNumFilasColmena() {
        return numFilasColmena;
    }
    public int getNumColumnasColmena() {
        return numColumnasColmena;
    }

    private void crearColmenaDeAliens() {

        for (int columna = 0; columna < getNumColumnasColmena(); columna++) {
            for (int fila = 0; fila < getNumFilasColmena(); fila++) {
                AlienTipo tipo = determinarTipoDeAlienPorFila(fila);
                ArrayList<String> imagenes = (ArrayList<String>) obtenerImagenesPorFila(fila);

                this.colmenaDeAliens[fila][columna] = new Alien(
                        Constantes.POSICION_INICIAL_EN_X_ALIENIGENAS + (Constantes.ANCHO_ALIEN + Constantes.ESPACIO_HORIZONTAL_ENTRE_COLUMNAS_ALIENIGENAS) * columna,
                        Constantes.ALTURA_INICIAL_ALIENIGENA + Constantes.ESPACIO_VERTICAL_ENTRE_FILAS_ALIENIGENAS * fila,
                        imagenes.get(0),
                        imagenes.get(1),
                        tipo
                );
            }
        }
    }


    private AlienTipo determinarTipoDeAlienPorFila(int fila) {
        switch (fila) {
            case 0:
                return AlienTipo.ALIEN_BUHO;
            case 1:
            case 2:
                return AlienTipo.ALIEN_BULDOG;
            default:
                return AlienTipo.ALIEN_CHITA;
        }
    }

    private List<String> obtenerImagenesPorFila(int fila) {
        AlienView imageSet = AlienView.filaImagenesAlien(fila);
        return imageSet.getImages();
    }

    public void desplazarAliens() {
        if (this.matrizAliensMuertos[0] != -1) {
            eliminarAlienMuerto(matrizAliensMuertos);
            matrizAliensMuertos[0] = -1;
        }
        moverAliensEnDireccion();
        emitirSonidoAlien();
        this.contadorDeNumeroDeSonidosAliens++;
        this.esPrimeraImagenDelAlien = !this.esPrimeraImagenDelAlien;
        cambiarDireccionYBajarColmenaSiEsNecesario();
    }

    private void moverAliensEnDireccion() {
        int cambioEnX = this.estaYendoHaciaLaDerecha ? Constantes.CAMBIO_EN_X_DX_ALIEN : -Constantes.CAMBIO_EN_X_DX_ALIEN;
        moverAliens(cambioEnX);
    }

    private void moverAliens(int cambioEnX) {
        for (int columna = 0; columna < getNumColumnasColmena(); columna++) {
            for (int fila = 0; fila < getNumFilasColmena(); fila++) {
                if (this.colmenaDeAliens[fila][columna] != null) {
                    this.colmenaDeAliens[fila][columna].setxPos(this.colmenaDeAliens[fila][columna].getxPos() + cambioEnX);
                }
            }
        }
    }

    private void cambiarDireccionYBajarColmenaSiEsNecesario() {
        if (estaTocandoBorde()) {
            moverColmenaHaciaAbajo();
            this.estaYendoHaciaLaDerecha = !this.estaYendoHaciaLaDerecha;
            aumentarVelocidad();
        }
    }

    private boolean estaTocandoBorde() {
        return estaTocandoBordeDerecho() || estaTocandoBordeIzquierdo();
    }

    private boolean estaTocandoBordeIzquierdo() {
        for (Alien[] filaAliens : this.colmenaDeAliens) {
            for (Alien alien : filaAliens) {
                if (alien != null && alien.getxPos() < Constantes.MARGEN_VENTANA) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean estaTocandoBordeDerecho() {
        for (Alien[] filaAliens : this.colmenaDeAliens) {
            for (Alien alien : filaAliens) {
                if (alien != null && alien.getxPos() > Constantes.ANCHO_VENTANA - Constantes.ANCHO_ALIEN - Constantes.CAMBIO_EN_X_DX_ALIEN - Constantes.MARGEN_VENTANA) {
                    return true;
                }
            }
        }
        return false;
    }


    //Mueve toda la colmena de aliens hacia abajo.
    private void moverColmenaHaciaAbajo() {
        for (Alien[] filaAliens : this.colmenaDeAliens) {
            for (Alien alien : filaAliens) {
                if (alien != null) {
                    alien.setyPos(alien.getyPos() + Constantes.CAMBIO_EN_Y_DY_ALIEN);
                }
            }
        }
    }

    private void aumentarVelocidad() {
        if (this.velocidad < 9) {
            this.velocidad++;
        }
    }

    private void eliminarAlienMuerto(int[] matrizAliensMuertos) {
        this.colmenaDeAliens[matrizAliensMuertos[0]][matrizAliensMuertos[1]] = null;
        this.numeroTotalAliens--;
    }

    public void proyectilImpactaNave(Proyectil proyectil) {
        for (int columna = 0; columna < getNumColumnasColmena(); columna++) {
            for (int fila = 0; fila < getNumFilasColmena(); fila++) {
                Alien alien = this.colmenaDeAliens[fila][columna];
                if (alien != null && proyectil.destruirAlien(alien)) {
                    alien.estaVivo = false;
                    proyectil.yPos = -1;
                    registrarAlienMuerto(fila, columna);
                    spaceInvaders.setScore(alien.calcularPuntaje());
                    return;
                }
            }
        }
    }

    private void registrarAlienMuerto(int fila, int columna) {
        this.matrizAliensMuertos[0] = fila;
        this.matrizAliensMuertos[1] = columna;
    }

    public int[] elegirAlienParaDisparar() {
        int[] posicionAlienQueVaADisparar = {-1, -1};
        if (this.numeroTotalAliens != 0) {
            do {
                int columna = random.nextInt(getNumColumnasColmena());
                for (int fila = getNumFilasColmena() - 1; fila >= 0; fila--) {
                    Alien alien = this.colmenaDeAliens[fila][columna];
                    if (alien != null) {
                        posicionAlienQueVaADisparar[0] = alien.getxPos();
                        posicionAlienQueVaADisparar[1] = alien.getyPos();
                        break;
                    }
                }
            } while (posicionAlienQueVaADisparar[0] == -1);
        }
        return posicionAlienQueVaADisparar;
    }

    public int getNumeroTotalAliens() {
        return numeroTotalAliens;
    }

    public int obtenerPosicionAlienigenaMasBajo() {
        int posicionBajaFinal = 0;
        for (int columna = 1; columna < getNumColumnasColmena(); columna++) {
            for (int fila = getNumFilasColmena() - 1; fila >= 0; fila--) {
                Alien alien = this.colmenaDeAliens[fila][columna];
                if (alien != null) {
                    posicionBajaFinal = Math.max(posicionBajaFinal, alien.getyPos());
                }
            }
        }
        return posicionBajaFinal;
    }

    private void emitirSonidoAlien() {
        try {
            int contador = this.contadorDeNumeroDeSonidosAliens % 4;
            Audio alienSound = new Audio();
            alienSound.reproducirAlienSonido(contador);
        } catch (Exception e) {
            System.err.println("Error reproduciendo sonido de Alien: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Alien[][] getColmenaDeAliens() {
        return this.colmenaDeAliens;
    }


}
