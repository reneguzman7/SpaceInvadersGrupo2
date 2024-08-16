package logica;

public enum AlienTipo {
    ALIEN_BUHO(Constantes.PUNTAJE_ALIEN_3),
    ALIEN_BULDOG(Constantes.PUNTAJE_ALIEN_2),
    ALIEN_CHITA(Constantes.PUNTAJE_ALIEN_CHITA);

    private final int puntaje;

    AlienTipo(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getPuntaje() {
        return puntaje;
    }
}
