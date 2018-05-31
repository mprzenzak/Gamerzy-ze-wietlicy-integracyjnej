package gameplayOneFile;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import pl.gameplay.GameLoop;
import pl.gameplay.Player;
import pl.gameplay.Position;
import pl.main.values.RootPaneAndGcSet;

public class Gameplay extends Application {

	private Pane appRoot = new Pane();
	Rectangle background = new Rectangle(720, 480);
	// private static final int width = 1280;
	// private static final int height = 720;

	private GameLoop loop;
	private GraphicsContext context;
	///////////////////////////////////////////////////////////////////////////////////////////
	// Player
	public static Point2D playerGoDown = new Point2D(0, 0);
	public static Point2D playerGoRight = new Point2D(0, 0);
	public boolean canJump = true;;
	static int playerX;
	static int playerY;
	// ImageView imageView;
	int playerImageWidth = 100;
	int playerImageHeight = 100;
	private int x;
	private int y;

	private void setPlayerImg() {
		Image playerImg = new Image("/Jetpack/resources/playerImage.png");
		ImageView ivPlayer = new ImageView();
		ivPlayer.setImage(playerImg);
	};

	// the maze
	// public Player(BufferedImage playerImage, int playerX, int playerY) {
	// this.playerImage = playerImage;
	// this.playerX = playerX;
	// this.playerY = playerY;
	// }}

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

	public void setTranslateY(int playerY) {
		this.playerY = playerY;
	}

	///////////////////////////////////////////////////////////////////////////////////////
	// Position
	///////////////////////////////////////////////////////////////////////////////////////
	void Position(final int x, final int y) {
	        this.x = x;
	        this.y = y;
	    }

	// Position(int PlayerX, int PlayerY) {
	// this.PlayerX = playerX;
	// this.PlayerY = playerY;
	// }
	// Position(int PlayerX, int PlayerY) {
	// this.playerX = playerX;
	// this.PlayerY = playerY;
	// }

	// PlayerX = playerX;
	// PlayerY = playerY;

	public Position translate(int x, int y) {
		return new Position(playerX, playerY);
	}

	private void update() {
		if (GameLoop.keyPressed() && Player.getTranslateY() >= 0) {
			jumpPlayer();
		}
		if (playerGoDown.getY() < 10) {
			Player.playerGoDown = playerGoDown.add(0, 1);
		}
		movePlayerY((int) playerGoDown.getY());

		if (Player.getTranslateX() <= 720) {
			// movePlayerX(5);
			movePlayerRight();
		}

		if (playerGoRight.getX() < 0) {
			playerGoRight = playerGoRight.add(0, 1);
		}
		movePlayerX((int) playerGoRight.getX());
	}

	private void movePlayerRight() {
		playerGoRight = playerGoRight.add(10, 0);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// position end
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

}
