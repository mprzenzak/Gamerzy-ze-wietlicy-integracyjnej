package pl.gameplayNotUsed;

import javafx.scene.image.ImageView;

public class Position extends Player {
	private final int x;
	private final int y;

	Position(final int x, final int y, ImageView imageView) {
		super(imageView);
		this.x = x;
		this.y = y;
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public Position translate(int x, int y) {
		return new Position(playerX, playerY, imageView);
	}

	private void update() {
		if (GameLoop.keyPressed() && this.getTranslateY() >= 0) {
			jumpPlayer();
		}
		if (playerGoDown.getY() < 10) {
			Player.playerGoDown = playerGoDown.add(0, 1);
		}
		movePlayerY((int) playerGoDown.getY());

		if (this.getTranslateX() <= width) {
			// movePlayerX(5);
			movePlayerRight();
		}

		if (playerGoRight.getX() < 0) {
			playerGoRight = playerGoRight.add(0, 1);
		}
		movePlayerX((int) playerGoRight.getX());
	}

	public void jumpPlayer() {
		if (Player.canJump()) {
			Player.playerGoDown = playerGoDown.add(0, -10);
			// canJump = false;
		}
	}

	private void movePlayerRight() {
		playerGoRight = playerGoRight.add(10, 0);
	}
}