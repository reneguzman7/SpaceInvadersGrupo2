package logica;

import presentacion.Audio;
import presentacion.ManejadorDeEventosRepintar;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;


public class DisparoAlien extends Proyectil {

	private final Random random = new Random();
	private final SpaceInvaders spaceInvaders;

	public DisparoAlien(int[] matrizPosicionALien, SpaceInvaders spaceInvaders) {
		super(spaceInvaders);
		this.spaceInvaders = spaceInvaders;

		this.xPos = matrizPosicionALien[0] + Constantes.ANCHO_ALIEN / 2 - 1;
		this.yPos = matrizPosicionALien[1] + Constantes.ALTURA_ALIEN;
		this.ancho = Constantes.ANCHO_DISPARO_ALIEN;
		this.alto = Constantes.ALTO_DISPARO_ALIEN;
		this.cambioX = 0;
		this.cambioY = Constantes.ESPACIO_DE_DESPLAZAMIENTO_EN_Y_DISPARO_ALIEN;

		this.strImg1 = "/presentacion/Imagenes/disparoAlien.png";
		this.strImg2 = "/presentacion/Imagenes/disparoAlien2.png";
		this.strImg3 = "";

		this.imageIcon = new ImageIcon(getClass().getResource(random.nextInt(2) == 0 ? strImg1 : strImg2));
		this.img = this.imageIcon.getImage();
		this.setEstaDisparando(true);
	}

	@Override
	public int desplazarProyectil() {
		this.yPos = (ManejadorDeEventosRepintar.contadorDeIteraciones % 4 == 0 && this.yPos < 600) ? this.yPos + Constantes.ESPACIO_DE_DESPLAZAMIENTO_EN_Y_DISPARO_ALIEN : this.yPos;
		return yPos;
	}

	@Override
	public void dibujarProyectil(Graphics g) {
		desplazarProyectil();
		g.drawImage(this.img, this.xPos, this.yPos, null);
	}

	public int[] obtenerImpactoConEscudo() {
		int[] resultado = {-1, -1};

		if (estaElDisparoALaAlturaDeLosEscudos()) {
			resultado[0] = determinarEscudoDeImpacto();

			if (resultado[0] != -1) {
				resultado[1] = obtenerAbscisaDeImpactoConEscudo(spaceInvaders.getArregloEscudosLogica()[resultado[0]]);
			}
		}
		return resultado;
	}

	public void detectarImpactoConEscudo(Escudo[] arregloEscudos) {
		int[] resultado = obtenerImpactoConEscudo();

		if (resultado[0] != -1) {
			int filaBloque = arregloEscudos[resultado[0]]
					.encontrarBloqueSuperior(arregloEscudos[resultado[0]]
							.determinarColumnaDondeImpactoMisilEnElEscudo(resultado[1]));

			if (filaBloque != -1 && filaBloque != 27) {
				arregloEscudos[resultado[0]].romperBloquesDesdeArriba(resultado[1]);
				moverEntidadFueraPantalla();
			}
		}
	}

	public static boolean detectarImpacto(Entidad entity1, Entidad entity2) {
		return entity1.getyPos() < entity2.getyPos() + entity2.getAlto() &&
				entity1.getyPos() + entity1.getAlto() > entity2.getyPos() &&
				entity1.getxPos() + entity1.getAncho() > entity2.getxPos() &&
				entity1.getxPos() < entity2.getxPos() + entity2.getAncho();
	}

	public static void manejarImpacto(Entidad projectil, NaveEspacial naveEspacial) {
		if (detectarImpacto(projectil, naveEspacial)) {
			projectil.moverEntidadFueraPantalla();
			Audio.playSound("/presentacion/Sonidos/sonidoDestruccionNave.wav");
		}
	}

	public boolean detectarImpactoNave(NaveEspacial naveEspacial) {
		if (detectarImpacto(this, naveEspacial)) {
			manejarImpacto(this, naveEspacial);
			return true;
		}
		return false;
	}

	private boolean estaElDisparoALaAlturaDeLosEscudos() {
		return this.yPos < Constantes.POSICION_Y_ESCUDO + Constantes.ALTO_ESCUDO &&
				this.yPos + this.alto > Constantes.POSICION_Y_ESCUDO;
	}

	private int determinarEscudoDeImpacto() {
		for (int columna = 0; columna < spaceInvaders.getNumeroDeEscudos(); columna++) {
			if (this.xPos + this.ancho - 1 > calcularPosicionXEscudo(columna) &&
					this.xPos + 1 < calcularPosicionXEscudo(columna) + Constantes.ANCHO_ESCUDO) {
				return columna;
			}
		}
		return -1;
	}

	private int calcularPosicionXEscudo(int columna) {
		return Constantes.MARGEN_VENTANA + Constantes.POSICION_X_PRIMER_ESCUDO + columna * (Constantes.ANCHO_ESCUDO + Constantes.ESPACIO_DE_SEPARACION_ENTRE_ESCUDOS);
	}

	// Calcula la posición X exacta del impacto con un escudo.
	private int obtenerAbscisaDeImpactoConEscudo(Escudo escudo) {
		return (this.xPos + this.ancho > escudo.getxPos() &&
				this.xPos < escudo.getxPos() + Constantes.ANCHO_ESCUDO)?
				this.xPos : -1;
	}


}
