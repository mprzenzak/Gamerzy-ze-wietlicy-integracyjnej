package pl.gameplay;

import java.awt.image.BufferedImage;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Player {

	// private Player player;
	// private Position playerPosition;
	// public static final Color COLOR = Color.YELLOW;
	// private static final int PLAYER_HEIGHT = 80;
	// private static final int PLAYER_WIDTH = 80;
	// private Point2D playerGoDown = new Point2D(0, 0);
	// private Point2D playerGoRight = new Point2D(0, 0);
	// private boolean canJump = true;

	// private BufferedImage playerImage;
	public static Point2D playerGoDown = new Point2D(0, 0);
	public static Point2D playerGoRight = new Point2D(0, 0);
	public boolean canJump = true;;
	static int playerX;
	static int playerY;
	ImageView imageView;
	int width = 100;
	int height = 100;

	// the maze
	// public Player(BufferedImage playerImage, int playerX, int playerY) {
	// this.playerImage = playerImage;
	// this.playerX = playerX;
	// this.playerY = playerY;
	// }
	public Player(ImageView imageView) {
		this.imageView = imageView;
		this.imageView.setViewport(new Rectangle2D(playerX, playerY, width, height));
		// getChildren().addAll(imageView);
	}

	// public BufferedImage getPlayerImage() {
	// return playerImage;
	// }

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

//	public static int getTranslateY() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	private void jumpPlayer() {
		if (canJump) {
			playerGoDown = playerGoDown.add(0, -10);
			canJump = false;
		}
	}

	private Point2D movePlayerX(int value) {
		return playerGoRight = playerGoRight.add(0, -10);
	}

	private Point2D PlayerGoRight() {
		playerGoRight = playerGoRight.add(0, -10);
		return playerGoRight;
	}

	private void movePlayerY(int value) {
		boolean movingDown = value > 0;

			Player.setTranslateY(Player.getTranslateY() + (movingDown ? 1 : -1));
		}

	public static int getTranslateX() {
		return playerX;
	}
	
	public void setTranslateX(int playerX) {
		this.playerX = playerX;
	}
	
	public static int getTranslateY() {
		return playerY;
	}
	
	public static void setTranslateY(int playerY) {
		Player.playerY = playerY;
	}
}
