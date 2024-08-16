package logica;

public final class Constantes {
	
	/************************************* Ventana *************************************/
	public static final int ANCHO_VENTANA = 600;
	public static final int ALTURA_VENTANA = 600;
	public static final int MARGEN_VENTANA = 50;
	
	/************************************* NaveEspacial *************************************/
	public static final int ANCHO_NAVE = 32;
	public static final int ALTO_NAVE = 24;

	public final static int X_POSICION_INICIAL_NAVE = (ANCHO_VENTANA - ANCHO_NAVE)/ 2;
	public final static int Y_POSICION_INICIAL_NAVE = 490;

	public final static int ESPACIO_DESPLAZAMIENTO_X_NAVE_ESPACIAL = 1;

	public final static int LIMITE_IZQUIERDO_NAVE = 60;
	public final static int LIMITE_DERECHO_NAVE = 500;
	
	/************************************* Alien ***************************************/
	public static final int ANCHO_ALIEN = 30;
	public static final int ALTURA_ALIEN = 30;
	public final static int ALTURA_INICIAL_ALIENIGENA = 120;
	public final static int POSICION_INICIAL_EN_X_ALIENIGENAS = 29 + MARGEN_VENTANA;
	public final static int ESPACIO_VERTICAL_ENTRE_FILAS_ALIENIGENAS = 40;
	public final static int ESPACIO_HORIZONTAL_ENTRE_COLUMNAS_ALIENIGENAS = 10;

	public final static int CAMBIO_EN_X_DX_ALIEN = 2;
	public final static int CAMBIO_EN_Y_DY_ALIEN = 20;
	public final static int VELOCIDAD_ALIENIGENAS = 3;


	/************************************ Proyectil **********************************/
	public static final int ANCHO_PROYECTIL = 3;
	public static final int ALTO_PROYECTIL = 13;

	public final static int UNIDADES_DE_DESPLAZAMIENTOS = 4;
	
	/************************************* Escudos *************************************/
	public static final int DIMENSIONES_DE_BLOQUE_DE_ESCUDO = 2;

	public static final int ANCHO_ESCUDO = 72;
	public static final int ALTO_ESCUDO = 54;

	public final static int POSICION_Y_ESCUDO = 400;
	public final static int POSICION_X_PRIMER_ESCUDO = 20;
	public final static int ESPACIO_DE_SEPARACION_ENTRE_ESCUDOS = 20; //42
	
	/************************************ Disparo Alienigena ************************************/

	public static final int ANCHO_DISPARO_ALIEN = 5;
	public static final int ALTO_DISPARO_ALIEN = 15;
	
	public final static int ESPACIO_DE_DESPLAZAMIENTO_EN_Y_DISPARO_ALIEN = 3;

	/************************************* NaveNodriza *************************************/

	public static final int ANCHO_NAVE_NODRIZA = 42;
	public static final int ALTO_NAVE_NODRIZA = 22;

	public final static int POS_X_INICIAL_NAVE_NODRIZA = ANCHO_VENTANA;
	public final static int POS_Y_NAVE_NODRIZA = 50;

	public final static int ESPACIO_DE_DESPLAZAMIENTO_EN_Y_NAVE_NODRIZA = 1;
	
	/************************************* Puntos *************************************/
	public static final int PUNTAJE_ALIEN_3 = 50;
	public static final int PUNTAJE_ALIEN_2 = 40;
	public static final int PUNTAJE_ALIEN_CHITA = 20;
	public static final int PUNTAJE_NAVE_NODRIZA = 100;
}


