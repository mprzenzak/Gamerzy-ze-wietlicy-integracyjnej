package pl.gameplayNotUsed;

import javafx.scene.canvas.GraphicsContext;

public class GameLoop {
	int frameRate;
	boolean running;
	boolean paused;
	static boolean keyPressed;
	final GraphicsContext context;
	float interval;

	public GameLoop(final GraphicsContext context) {
		this.context = context;
		frameRate = 60;
		interval = 1000.0f / frameRate;
		running = true;
		paused = false;
		keyPressed = false;
	}

	// public void run() {
	// while (running && !paused) {
	// float time = System.currentTimeMillis();
	// keyPressed = false;
	//
	// }
	// }

	public void stop() {
		running = false;
	}

	public static boolean keyPressed() {
		return keyPressed = true;
	}

	public void resume() {
		paused = false;
	}

	public void pause() {
		paused = true;
	}

	public boolean isPaused() {
		return paused;
	}

	public int getFrameRate() {
		return frameRate;
	}

	public void setFrameRate(int frameRate) {
		this.frameRate = frameRate;
	}
}