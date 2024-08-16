package logica;

import javax.swing.ImageIcon;

public class Alien extends Entidad {

    private int contadorImagen;
    private AlienTipo tipo;

    public Alien(int xPos, int yPos, String strImg1, String strImg2, AlienTipo tipo) {
        super.xPos = xPos;
        super.yPos = yPos;
        super.ancho = Constantes.ANCHO_ALIEN;
        super.alto = Constantes.ALTURA_ALIEN;
        super.cambioX = 0;
        super.cambioY = 0;
        super.estaVivo = true;
        super.strImg1 = strImg1;
        super.strImg2 = strImg2;
        super.strImg3 = "/presentacion/Imagenes/alienDestruido.png";
        super.imageIcon = new ImageIcon(getClass().getResource(super.strImg1));
        super.img = this.imageIcon.getImage();
        this.tipo = tipo;
    }


    public String getImagen() {
        contadorImagen++;
        return estaVivo ? getImagenEntidadViva() : strImg3;
    }

    private String getImagenEntidadViva() {
        return (contadorImagen / 24) % 2 == 0 ? strImg1 : strImg2;
    }

    public int calcularPuntaje() {
        return tipo.getPuntaje();
    }
}
