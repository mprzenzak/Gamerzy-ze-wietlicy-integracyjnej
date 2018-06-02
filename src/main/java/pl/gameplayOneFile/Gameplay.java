package pl.gameplayOneFile;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.main.values.GroupPanesAndGcSet;

public class Gameplay {
	
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<Node> blocks = new ArrayList<Node>();
	public GraphicsContext gc;
	private Pane appRoot = new Pane();
	public int levelWidth = 720;
	ImageView ivPlayer;
	
	private GameState gameState;
	private Player player1;
	private Player player2;
	
	GraphicsContext bgGc;
	GraphicsContext valuesGc;
	private int money;
	private int numberOfPlayers;
	private String mode;
	
	private int points;

	public Gameplay(int money, String mode, int numberOfPlayers) { // TODO Auto-generated constructor stub
		this.money = money;
		this.numberOfPlayers = numberOfPlayers;
		this.mode = mode;
		this.points = 0;
	}

	public void initContent(GroupPanesAndGcSet gpgc) {
		bgGc = gpgc.getGc("gameBg");
		valuesGc = gpgc.getGc("gameMoney");
		
		valuesGc.setFill(Color.WHITE);
		valuesGc.setFont(Font.font("Consolas", 45));
		
		paintScene();
		updateMoneyCounter();
		updatePointsCounter();
		
		player1 = new Player();
		if (numberOfPlayers == 2) {
			player2 = new Player();
		}
	}

	private void paintScene() { // TODO Auto-generated method stub
		Image bgImage = new Image("file:resources\\gameBg.jpg");
		bgGc.drawImage(bgImage, 0, 0);
		
		Image coin = new Image("file:resources\\coin.png");
		valuesGc.drawImage(coin, 1700, 15);
	}

	private void updateMoneyCounter() { 
		valuesGc.clearRect(1760, 0, 200, 200);
		
		String moneyString = Integer.toString(money);
		while (moneyString.length() < 5) {
			moneyString = "0" + moneyString;
		}
		valuesGc.fillText(moneyString, 1760, 55);		
	}
	
	private void updatePointsCounter() {
		valuesGc.clearRect(15, 0, 800, 200);
		
		String pointsString = Integer.toString(points);
		while (pointsString.length() < 10) {
			pointsString = "0" + pointsString;
		}
		if (mode == "levels") {
			pointsString = "Points: " + pointsString;
		} else {
			pointsString = "Meters: " + pointsString;
		}
		
		valuesGc.fillText(pointsString, 15, 55);		
	}
	
	public void play() { // TODO Auto-generated method stub
		switch (gameState) {
		case START: //
			playersEnter();
			break;
			
		case PLAYING: //
			updateBg();
			break;
			
		case PAUSE: //
			break;
			
		case KILLED: //
			break;
			
		case REVIVESCREEN: //
			break;
			
		case REVIVED: //
			break;
			
		case GAMEOVER: //
			
		}
	}
	
	private void updateBg() {
		// TODO Auto-generated method stub
		
	}
	
	public void playersEnter() {
		if(numberOfPlayers == 1) {
			player1.enter(500);
		} else {
			player1.enter(400);
			player2.enter(600);
		}
	}

	private enum GameState {
		START, PLAYING, PAUSE, KILLED, REVIVESCREEN, REVIVED, GAMEOVER
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Player
	public static Point2D playerGoDown = new Point2D(0, 0);
	public static Point2D playerGoRight = new Point2D(0, 0);
	// final Point2D playerPosition = new Point2D(0, 0);
	// player.moveTo(playerPosition);
	public boolean canJump = true;
	int playerX;
	int playerY;
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

	///////////////////////////////////////////////////////////////////////////////////////
	// Position
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
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// public playerPosition translate(int x, int y) {
	// return new playerPosition(playerX, playerY);
	// }

	private void movePlayerRight() {
		playerGoRight = playerGoRight.add(10, 0);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Gameloop
	int frameRate;
	boolean running;
	boolean paused;
	static boolean keyPressed;
	float interval;

	public void GameLoop(final GraphicsContext context) {
//		this.context = context;
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
	public void update() {
		if (isPressed(KeyCode.W) && ivPlayer.getTranslateY() >= 0) {
			jumpPlayer();
		}

		if (playerGoDown.getY() < 10) {
			playerGoDown = playerGoDown.add(0, 1);
		}
		movePlayerY((int) playerGoDown.getY());
	}

	public void start(Stage primaryStage) throws Exception {
		ImageView ivPlayer = new ImageView(new Image("https://files.gamebanana.com/img/ico/sprays/5376ef308bef4.png"));
		ivPlayer.setFitWidth(50);
		ivPlayer.setFitHeight(50);
		// ivPlayer.relocate(0, 240);
		ivPlayer.relocate(playerX, 240);
		appRoot.getChildren().addAll(ivPlayer);		
	}

}