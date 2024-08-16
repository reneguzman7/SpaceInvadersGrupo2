package logica;

public class NaveEspacial extends Entidad {
    private int contador = 0;

    public NaveEspacial() {
        this.xPos = Constantes.X_POSICION_INICIAL_NAVE;
        this.yPos = Constantes.Y_POSICION_INICIAL_NAVE;
        this.ancho = Constantes.ANCHO_NAVE;
        this.alto = Constantes.ALTO_NAVE;
        this.cambioX = 0;
        this.cambioY = 0;
        this.estaVivo = true;
    }

    public int desplazarNave() {
        if (this.cambioX < 0 && this.xPos > Constantes.LIMITE_IZQUIERDO_NAVE) {
            this.xPos += this.cambioX;
        } else if (this.cambioX > 0 && this.xPos + this.cambioX < Constantes.LIMITE_DERECHO_NAVE) {
            this.xPos += this.cambioX;
        }
        return this.xPos;
    }

    public void destruirNave() {
        if (contador < 300) {
            contador++;
        }
    }

    public boolean estaVivo() {
        return this.estaVivo;
    }

    public void setEstaVivo(boolean estado) {
        this.estaVivo = estado;
    }
}
