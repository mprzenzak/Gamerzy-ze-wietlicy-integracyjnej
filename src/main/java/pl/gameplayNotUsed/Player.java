package pl.gameplayNotUsed;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Player {

	// private Player player;
	// private Position playerPosition;
	// public static final Color COLOR = Color.YELLOW;
	// private static final int PLAYER_HEIGHT = 80;
	// private static final int PLAYER_WIDTH = 80;
	// private Point2D playerGoDown = new Point2D(0, 0);
	// private Point2D playerGoRight = new Point2D(0, 0);

	public static Point2D playerGoDown = new Point2D(0, 0);
	public static Point2D playerGoRight = new Point2D(0, 0);
	public boolean canJump = true;
	int playerX;
	int playerY;
	ImageView imageView;
	int width = 100;
	int height = 100;

	// the maze
	public Player(ImageView imageView) {
		this.imageView = imageView;
		this.imageView.setViewport(new Rectangle2D(playerX, playerY, width, height));
		// getChildren().addAll(imageView);
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public void moveX(int x) {
		boolean right = x > 0 ? true : false;
		for (int i = 0; i < Math.abs(x); i++) {
			if (right)
				this.setTranslateX(this.getTranslateX());
		}
	}
	// Main.root.getChildren().remove(removeRect);

	public static boolean canJump() {
		// TODO Auto-generated method stub
		return canJump();
	}

	// public static int getTranslateY() {
	// // TODO Auto-generated method stub
	// return 0;
	// }

	public void jumpPlayer() {
		if (canJump) {
			playerGoDown = playerGoDown.add(0, -10);
			canJump = false;
		}
	}

	public Point2D movePlayerX(int value) {
		return playerGoRight = playerGoRight.add(0, -10);
	}

	public Point2D PlayerGoRight() {
		playerGoRight = playerGoRight.add(0, -10);
		return playerGoRight;
	}

	public void movePlayerY(int value) {
		boolean movingDown = value > 0;

		this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
	}

	public int getTranslateX() {
		return playerX;
	}

	public void setTranslateX(int playerX) {
		this.playerX = playerX;
	}

	public int getTranslateY() {
		return playerY;
	}

	public void setTranslateY(int playerY) {
		this.playerY = playerY;
	}
}
