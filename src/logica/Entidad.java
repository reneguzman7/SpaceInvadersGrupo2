package logica;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Entidad {
	protected int ancho, alto, xPos, yPos, cambioX, cambioY;
	protected boolean estaVivo;
	protected String strImg1, strImg2, strImg3;
	protected ImageIcon imageIcon;
	protected Image img;

	public Entidad() {
	}

	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getCambioX() {
		return cambioX;
	}
	public void setCambioX(int cambioX) {
		this.cambioX = cambioX;
	}

	public boolean isEstaVivo() {
		return estaVivo;
	}
	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}

	public String getStrImg1() {
		return strImg1;
	}
	public String getStrImg2() {
		return strImg2;
	}

	public void moverEntidadFueraPantalla() {
		this.yPos = 700;
	}


}
