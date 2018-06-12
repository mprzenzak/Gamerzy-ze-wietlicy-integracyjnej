package pl.gameplayOneFile;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pl.main.Runner.KeyState;
import pl.main.values.GroupPanesAndGcSet;

public class Gameplay {

	private GameState gameState;
	private LevelStage levelStage;
	private PlayerState player1State;
	private PlayerState player2State;
	private Player player1;
	private Player player2;
	
	private EnemiesMap enemiesMap;
	static ArrayList<Bullet> bullets;

	GraphicsContext bgGc;
	GraphicsContext valuesGc;
	private Pane gamePane;
	
	private int money;
	private int numberOfPlayers;
	private boolean isMultiplayer;
	private String mode;

	private int points;
	private int bgXPosition;
	private int bgXPositionChangeSpeed;
	private int floorBorder, ceilingBorder, rightWallBorder;
	
	private boolean hasWon;

	public Gameplay(int money, String mode, int numberOfPlayers) { // TODO Auto-generated constructor stub
		this.money = money;
		this.numberOfPlayers = numberOfPlayers;
		this.mode = mode;
		this.points = 0;
		
		floorBorder = 1080 - 50;
		ceilingBorder = 70;
		rightWallBorder = 1920/3;
		
		bgXPosition = 0;
		bgXPositionChangeSpeed = 5;

		player1State = PlayerState.NORMAL;
		player2State = PlayerState.NORMAL;
		levelStage = LevelStage.PREPARE1;
		gameState = GameState.START;
	}

	public void initContent(GroupPanesAndGcSet gpgc) {
		bgGc = gpgc.getGc("gameBg");
		valuesGc = gpgc.getGc("gameMoney");
		gamePane = gpgc.getPane("game");
		
		valuesGc.setFill(Color.WHITE);
		valuesGc.setFont(Font.font("Consolas", 45));

		paintScene();
		updateMoneyCounter();
		updatePointsCounter();

		Image player1img = new Image("file:resources\\player1.png");
		ImageView ivPlayer1 = new ImageView(player1img);
		gamePane.getChildren().add(ivPlayer1);
		
		player1 = new Player(400, ivPlayer1);

		if (numberOfPlayers == 2) {
			Image player2img = new Image("file:resources\\player2.png");
			ImageView ivPlayer2 = new ImageView(player2img);
			gamePane.getChildren().add(ivPlayer2);

			player2 = new Player(700, ivPlayer2);
			
			isMultiplayer = true;
		}

		enemiesMap = new EnemiesMap();
		bullets = new ArrayList<>();
	}

	private void paintScene() { // TODO Auto-generated method stub
		Image bgImage = new Image("file:resources\\gameBg.jpg");
		bgGc.drawImage(bgImage, 0, 0);
		bgGc.drawImage(bgImage, 1920, 0);

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

	public void play(HashMap<String, KeyState> keysActive) { // TODO Auto-generated method stub
		switch (gameState) {
		case START: //
			playersEnter();
			break;

		case PLAYING: //
			updateBg();
			updatePlayers(keysActive);
			updateEntities();
			updateBullets();
			break;

		case PAUSE: //
			break;

		case KILLED: //
			hasWon = false;
			break;

		case REVIVESCREEN: //
			break;

		case REVIVED: //
			break;

		case GAMEOVER: //

		}
	}

	private void updateBullets() {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).move();
			
			if(bullets.get(i).getRightBorder() < 0) {
				bullets.get(i).disappear();
				bullets.remove(i);
			}
		}
	}

	private void updateEntities() {
		switch (levelStage) {
		case PREPARE1:
			enemiesMap.spawnWave(1, gamePane);
			levelStage = LevelStage.LV1;
			break;
			
		case LV1:
			enemiesMap.move();
			
			if(enemiesMap.checkIfCleared() == true) {
				levelStage = LevelStage.PREPARE2;
			}
			break;
			
		case PREPARE2:
			enemiesMap.spawnWave(2, gamePane);
			levelStage = LevelStage.LV2;
			break;
			
		case LV2:
			enemiesMap.move();
			
			if(enemiesMap.checkIfCleared() == true) {
				levelStage = LevelStage.PREPARE3;
			}
			break;
			
		case PREPARE3:
			enemiesMap.spawnWave(3, gamePane);
			levelStage = LevelStage.LV3;
			break;
			
		case LV3:
			enemiesMap.move();
			
			if(enemiesMap.checkIfCleared() == true) {
				levelStage = LevelStage.PREPARE4;
			}
			break;
			
		case PREPARE4:
			enemiesMap.spawnWave(4, gamePane);
			levelStage = LevelStage.LV4;
			break;
			
		case LV4:
			enemiesMap.move();
			
			if(enemiesMap.checkIfCleared() == true) {
				levelStage = LevelStage.PREPARE5;
			}
			break;
			
		case PREPARE5:
			enemiesMap.spawnWave(5, gamePane);
			levelStage = LevelStage.LV5;
			break;
			
		case LV5:
			enemiesMap.move();
			
			if(enemiesMap.checkIfCleared() == true) {
				if (mode != "levels") {
					levelStage = LevelStage.PREPARE1;
					
				} else {
					hasWon = true;
					gameState = GameState.GAMEOVER;
				}
			}
		}
	}

	private void updatePlayers(HashMap<String, KeyState> keysActive) {
		if (keysActive.containsKey("W")) { //TODO change so it uses keys from options
			player1.moveUp(ceilingBorder);
		}
		
		if (keysActive.containsKey("S")) {
			player1.moveDown(floorBorder);
		}
		
		if (keysActive.containsKey("D")) {
			player1.moveRight(rightWallBorder);
		}
		
		if (keysActive.containsKey("A")) {
			player1.moveLeft(0);
		}
		
		if (isMultiplayer) {
			if (keysActive.containsKey("UP")) { //TODO change so it uses keys from options
				player2.moveUp(ceilingBorder);
			}
			
			if (keysActive.containsKey("DOWN")) {
				player2.moveDown(floorBorder);
			}
			
			if (keysActive.containsKey("RIGHT")) {
				player2.moveRight(rightWallBorder);
			}
			
			if (keysActive.containsKey("LEFT")) {
				player2.moveLeft(0);
			}
		}
		
		
//		if (player1State == PlayerState.HIT) {
//			player1.getHit();
//		}
	}

	private void updateBg() {
		bgXPosition -= bgXPositionChangeSpeed;

		bgGc.getCanvas().setTranslateX(bgXPosition);

		if (bgXPosition < -1920) {
			bgXPosition = 0;
		}
	}

	public void playersEnter() {

		boolean animation1Finished = player1.enter();
		boolean animation2Finished;
		
		if (isMultiplayer) {
			animation2Finished = player2.enter();
		} else {
			animation2Finished = true;
		}
		
		if (animation1Finished && animation2Finished) {
			gameState = GameState.PLAYING;
		}
	}

	private enum GameState {
		START, PLAYING, PAUSE, KILLED, REVIVESCREEN, REVIVED, GAMEOVER
	}
	
	private enum PlayerState {
		NORMAL, HIT, DEAD
	}
	
	private enum LevelStage {
		PREPARE1, LV1, PREPARE2, LV2, PREPARE3, LV3, PREPARE4, LV4, PREPARE5, LV5
	}

}