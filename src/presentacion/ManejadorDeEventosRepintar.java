package presentacion;

public class ManejadorDeEventosRepintar implements Runnable {

	/**** Variables ****/
	private final int intervaloDeActualizacion = 5;
	public static int contadorDeIteraciones = 0;
	private volatile boolean corriendo = true;

	/**** Métodos ****/
	public void detener() {
		corriendo = false;
	}

	@Override
	public void run() {
		while (corriendo && VentanaMenu.juego) {
			contadorDeIteraciones++;
			GameViewManager gameViewManager = VentanaMenu.gameViewManager;
			if (gameViewManager != null) {
				gameViewManager.repaint();
			}

			try {
				Thread.sleep(intervaloDeActualizacion);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
