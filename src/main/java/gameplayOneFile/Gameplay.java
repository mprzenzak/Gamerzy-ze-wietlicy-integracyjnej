package gameplayOneFile;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import pl.main.LevelData;

public class Gameplay extends Application {
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<Node> blocks = new ArrayList<Node>();
	public GraphicsContext gc;
	private Image playerImg;
	private Pane appRoot = new Pane();
	public int levelWidth = 720;
	ImageView ivPlayer;
	private Node player;

	// public void initContent() {
	// appRoot.setStyle("-fx-background-color: #F0591E");
	// }
	private void initContent() {
		Rectangle background = new Rectangle(720, 480);
		appRoot.setStyle("-fx-background-color: #F0591E");
		//player = createEntity(0, 350, 40, 40, Color.YELLOW);
	}

//	private Node createEntity(int x, int y, int w, int h, Color color) {
//		Rectangle entity = new Rectangle(w, h);
//		entity.setTranslateX(x);
//		entity.setTranslateY(y);
//		entity.setFill(color);
//		appRoot.getChildren().add(entity);
//		return entity;
//
//	}

	private GraphicsContext context;
	///////////////////////////////////////////////////////////////////////////////////////////
	// Player
	public static Point2D playerGoDown = new Point2D(0, 0);
	public static Point2D playerGoRight = new Point2D(0, 0);
	// final Point2D playerPosition = new Point2D(0, 0);
	// player.moveTo(playerPosition);
	public boolean canJump = true;
	static int playerX;
	static int playerY;
	private int x;
	private int y;

	private static Point2D origin = Point2D.ZERO;

	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}
	// void Player() {
	// final Point2D playerPosition = new Point2D(0, 0);
	// //Player.moveTo(playerPosition);
	//
	// }

	// void Player(BufferedImage playerImg, int playerX, int playerY) {
	// final Point2D playerPosition = new Point2D(0, 0);
	// this.ivPlayer = ivPlayer;
	// this.playerX = playerX;
	// this.playerY = playerY;
	// }

	static void moveBy(final Point2D p) {
		origin.add(p);
	}

	static void moveTo(final Point2D p) {
		final Point2D dif = p.subtract(origin);
		moveBy(dif);
	}

	// private void setPlayerImg() {
	// Image playerImg = new Image("/Jetpack/resources/playerImage.png");
	// //Image playerImg = new Image("file:resources\\play_button.png");
	// //Image playerImg = new Image("playerImage.png");
	// ImageView ivPlayer = new ImageView();
	// ivPlayer.setImage(playerImg);
	// ivPlayer.setFitWidth(100);
	// HBox box = new HBox();
	// box.getChildren().add(ivPlayer);
	// appRoot.getChildren().add(box);
	// };

	// public void Player(BufferedImage playerImg, int playerX, int playerY) {
	// this.ivPlayer = ivPlayer;
	// this.playerX = playerX;
	// this.playerY = playerY;
	// }

	// void Player(){
	// final Point2D playerPosition = new Point2D(0, 0);

	// private void createPlayer() {
	// // Player player = new Player(player, 48, 716);
	// return;
	// }

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	// public void moveX(int x) {
	// boolean right = x > 0 ? true : false;
	// for (int i = 0; i < Math.abs(x); i++) {
	// if (right)
	// this.setTranslateX(this.getTranslateX());
	// }
	// }
	// Main.root.getChildren().remove(removeRect);

	// public static boolean canJump() {
	// // TODO Auto-generated method stub
	// return canJump();
	// }

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

	// private void movePlayerY(int value) {
	// boolean movingDown = value > 0;
	//
	// // ivPlayer.setTranslateY(ivPlayer.getTranslateY() + (movingDown ? 1 : -1));
	// }
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void movePlayerY(int value) {
		boolean movingDown = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {

			if (movingDown) {
				// if (ivPlayer.getTranslateY() + 40 == block.getTranslateY()) {
				// canJump = true;
				// return;
			}
			// } else {
			// if (ivPlayer.getTranslateY() == block.getTranslateY() + 60) {
			// return;
			// }
			// }

			// ivPlayer.setTranslateY(ivPlayer.getTranslateY() + (movingDown ? 1 : -1));
		}
	}

	public static int getTranslateX() {
		return playerX;
	}

	public void setTranslateX(int playerX) {
		Gameplay.playerX = playerX;
	}

	public static int getTranslateY() {
		return playerY;
	}

	public void setTranslateY(int playerY) {
		Gameplay.playerY = playerY;
	}

	///////////////////////////////////////////////////////////////////////////////////////
	// Position
	///////////////////////////////////////////////////////////////////////////////////////
	public int playerPosition(int playerX, int playerY) {
		this.x = playerX;
		this.y = playerY;
		return playerPosition(playerY, playerY);
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
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
	// public playerPosition translate(int x, int y) {
	// return new playerPosition(playerX, playerY);
	// }

	private void movePlayerRight() {
		playerGoRight = playerGoRight.add(10, 0);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// position end
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Gameloop
	int frameRate;
	boolean running;
	boolean paused;
	static boolean keyPressed;
	float interval;

	public void GameLoop(final GraphicsContext context) {
		this.context = context;
		frameRate = 60;
		interval = 1000.0f / frameRate;
		// interval = 1000 / frameRate;
		running = true;
		paused = false;
		keyPressed = false;
	}

	public void run() {
		while (running && !paused) {
			float time = System.currentTimeMillis();
			keyPressed = false;
			time = System.currentTimeMillis() - time;
		}
	}

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

	//////////////////////////////////////////////////////////////////////////////////////////////
	// Gameloop end
	// public static void main(String[] args) {
	// launch(args);
	// }

	// public void keyPressed(KeyEvent ke) {
	// if (ke.getKeyCode() == KeyEvent.VK_UP) {
	// // jumpPlayer();
	// }
	// // if (isPressed(KeyCode.W) && player.getTranslateY() >= 0) {
	// // jumpPlayer();
	// // }
	// }

	// @Override
	// public void start(Stage primaryStage) throws Exception {
	// initContent();
	// Scene scene = new Scene(appRoot);
	// // scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
	// // scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
	// //primaryStage.setTitle("Jetpack gameplay");
	// primaryStage.setScene(scene);
	// primaryStage.show();
	// primaryStage.setResizable(false);
	// AnimationTimer timer = new AnimationTimer() {
	// @Override
	// public void handle(long now) {
	// update();
	// }
	// };
	// timer.start();
	// }

	// public static void main(String[] args) {
	//
	// launch(args);
	// }
	
	//TODO ivPlayer (imageView) unacceptable here
	public void update() {
		if (isPressed(KeyCode.W) && ivPlayer.getTranslateY() >= 0) {
			System.out.println("test");
			jumpPlayer();
		}
		// if(isPressed(KeyEvent.VK_UP)&& ivPlayer.getTranslateY()>=0) {
		// jumpPlayer();
		// }
		if (playerGoDown.getY() < 10) {
			playerGoDown = playerGoDown.add(0, 1);
		}
		movePlayerY((int) playerGoDown.getY());
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		initContent();
		ImageView ivPlayer = new ImageView(new Image("https://files.gamebanana.com/img/ico/sprays/5376ef308bef4.png"));
		ivPlayer.setFitWidth(50);
		ivPlayer.setFitHeight(50);
		// ivPlayer.relocate(0, 240);
		ivPlayer.relocate(playerX, 240);
		appRoot.getChildren().addAll(ivPlayer);
		appRoot.setMinWidth(720);
		appRoot.setMinHeight(480);
		Scene scene = new Scene(appRoot);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setTitle("Jetpack gameplay");
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		timer.start();
	}
}