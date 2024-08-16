package presentacion;

import logica.Constantes;
import logica.Escudo;

import javax.swing.*;
import java.awt.*;

public class EscudoView extends JPanel {
    public static final Color NO_COLOR = new Color(0, 0, 0, 0);
    private Escudo escudoLogica;

    public EscudoView(Escudo escudoLogica) {
        this.escudoLogica = escudoLogica;
        this.setPreferredSize(new Dimension(Constantes.ANCHO_ESCUDO, Constantes.ALTO_ESCUDO));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarEscudo(g);
    }

    public void dibujarEscudo(Graphics g) {
        int xPos = escudoLogica.getXPos();
        int yPos = escudoLogica.getYPos();
        int bloqueSize = Constantes.DIMENSIONES_DE_BLOQUE_DE_ESCUDO;

        for (int fila = 0; fila < escudoLogica.getNumeroDeFilasEnEscudo(); fila++) {
            for (int columna = 0; columna < escudoLogica.getNumeroDeColumnasEnEscudo(); columna++) {
                if (escudoLogica.getMatrizEscudo()[fila][columna]) {
                    g.setColor(Color.CYAN);
                } else {
                    g.setColor(NO_COLOR);
                }
                g.fillRect(
                        xPos + bloqueSize * columna,
                        yPos + bloqueSize * fila,
                        bloqueSize,
                        bloqueSize
                );
            }
        }
    }

}
