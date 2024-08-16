package presentacion;

import logica.ColmenaDeAliens;
import logica.Alien;

import java.awt.Graphics;

public class ColmenaDeAliensView {

    private ColmenaDeAliens colmenaDeAliens;

    public ColmenaDeAliensView(ColmenaDeAliens colmenaDeAliens) {
        this.colmenaDeAliens = colmenaDeAliens;
    }

    public void dibujarColmenaDeAliens(Graphics g) {
        if (ManejadorDeEventosRepintar.contadorDeIteraciones % (colmenaDeAliens.velocidad + 10) == 0) {
            colmenaDeAliens.desplazarAliens();
        }

        for (int columna = 0; columna < colmenaDeAliens.getNumColumnasColmena(); columna++) {
            for (int fila = 0; fila < colmenaDeAliens.getNumFilasColmena(); fila++) {
                Alien alien = colmenaDeAliens.getColmenaDeAliens()[fila][columna];
                if (alien != null) {
                    AlienView alienP = new AlienView(alien);
                    alienP.dibujarAlien(g);
                }
            }
        }
    }

}