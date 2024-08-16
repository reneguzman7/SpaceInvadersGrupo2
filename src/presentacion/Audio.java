package presentacion;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.util.ArrayList;
import java.util.List;

public class Audio {

	private Clip clip;
	private List<String> alienSounds;

	public Audio(String sonido) {
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(sonido));
			clip = AudioSystem.getClip();
			clip.open(audio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Audio(){
		alienSounds = new ArrayList<>();
		alienSounds.add("/Presentacion/Sonidos/sonidoAlien1.wav");
		alienSounds.add("/Presentacion/Sonidos/sonidoAlien2.wav");
		alienSounds.add("/Presentacion/Sonidos/sonidoAlien3.wav");
		alienSounds.add("/Presentacion/Sonidos/sonidoAlien4.wav");
	}

	public void reproducirAlienSonido(int soundIndex) {
		if (soundIndex < 0 || soundIndex >= alienSounds.size()) {
			throw new IllegalArgumentException("Invalid sound index");
		}
		try {
			Audio.playSound(alienSounds.get(soundIndex));
		} catch (Exception e) {
			System.err.println("Error playing Alien sound: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void play() {
		clip.start();
	}

	public void stop() {
		clip.stop();
	}

	public static void playSound(String sonido) {
		Audio s = new Audio(sonido);
		s.play();
	}
}