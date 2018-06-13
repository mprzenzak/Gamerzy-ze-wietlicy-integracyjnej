package pl.gameplayOneFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import pl.gameplayOneFile.Gameplay.PlayerState;
import pl.main.Runner.KeyState;
import pl.main.values.GroupPanesAndGcSet;

public class Gameplay {

	private GameState gameState;
	private static PlayerState player1State;
	private static PlayerState player2State;
	private Player player1;
	private Player player2;

	private EnemiesMap enemiesMap;
	static ArrayList<Bullet> bullets;
	static ArrayList<Bullet> playerBullets;

	GraphicsContext bgGc;
	GraphicsContext valuesGc;
	private Pane gamePane;

	private int money;
	private int numberOfPlayers;
	private boolean isMultiplayer;
	private String mode;
	private int currentLevel;

	private int points;
	private int bgXPosition;
	private int bgXPositionChangeSpeed;
	private int floorBorder, ceilingBorder, rightWallBorder;

	private boolean hasWon;

	public Gameplay(int money, String mode, int numberOfPlayers) {
		this.money = money;
		this.numberOfPlayers = numberOfPlayers;
		this.mode = mode;

		this.points = 0;

		floorBorder = 1080 - 50;
		ceilingBorder = 70;
		rightWallBorder = 1920 / 3;

		bgXPosition = 0;
		bgXPositionChangeSpeed = 5;

		currentLevel = 0;

		player1State = PlayerState.NORMAL;
		player2State = PlayerState.NORMAL;
		gameState = GameState.START;
	}

	public void initContent(GroupPanesAndGcSet gpgc) {
		bgGc = gpgc.getGc("gameBg");
		valuesGc = gpgc.getGc("gameMoney");
		gamePane = gpgc.getPane("game");

		valuesGc.setFill(Color.WHITE);
		valuesGc.setFont(Font.font("Consolas", 45));

		paintScene();
		updateMoney();
		updatePoints();

		player1 = new Player(gamePane, 400, new Image("file:resources\\player1.png"));
		updateHearts(player1);

		if (numberOfPlayers == 2) {
			player2 = new Player(gamePane, 700, new Image("file:resources\\player2.png"));
			updateHearts(player2);

			isMultiplayer = true;
		}

		enemiesMap = new EnemiesMap();
		bullets = new ArrayList<>();
		playerBullets = new ArrayList<>();
	}

	private void updateHearts(Player player) {
		Image heart1 = new Image("file:resources\\heart.png");
		Image heart2 = new Image("file:resources\\heart.png");
		Image heart3 = new Image("file:resources\\heart.png");
		int y = 1080 - 65;
		int x1, x2, x3;

		if (player == player1) {
			x1 = 15;
			x2 = 15 + 65;
			x3 = 15 + 2 * 65;
		} else {
			x1 = 1920 - 3 * 65;
			x2 = 1920 - 2 * 65;
			x3 = 1920 - 65;
		}

		switch (player.getHealth()) {
		case 0:
			heart1 = new Image("file:resources\\emptyHeart.png");
		case 1:
			heart2 = new Image("file:resources\\emptyHeart.png");
		case 2:
			heart3 = new Image("file:resources\\emptyHeart.png");
		}

		valuesGc.clearRect(x1, y, 180, 50);
		valuesGc.drawImage(heart1, x1, y);
		valuesGc.drawImage(heart2, x2, y);
		valuesGc.drawImage(heart3, x3, y);
	}

	private void paintScene() {
		Image bgImage = new Image("file:resources\\gameBg.jpg");
		bgGc.drawImage(bgImage, 0, 0);
		bgGc.drawImage(bgImage, 1920, 0);

		Image coin = new Image("file:resources\\coin.png");
		valuesGc.drawImage(coin, 1700, 15);
	}

	private void updateMoney() {
		valuesGc.clearRect(1760, 0, 200, 200);

		String moneyString = Integer.toString(money);
		while (moneyString.length() < 5) {
			moneyString = "0" + moneyString;
		}
		valuesGc.fillText(moneyString, 1760, 55);
	}

	private void updatePoints() {
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
			updateEnemies();
			updateBullets();
			
			if (mode == "infinity") {
				points++;
				updatePoints();
			}
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

			if (bullets.get(i).getRightBorder() < 0 || bullets.get(i).getLeftBorder() > 1920) {
				bullets.get(i).disappear();
				bullets.remove(i);
			}
		}

		for (int i = 0; i < playerBullets.size(); i++) {
			playerBullets.get(i).move();

			if (playerBullets.get(i).getLeftBorder() > 1920) {
				playerBullets.get(i).disappear();
				playerBullets.remove(i);
			}
		}
	}

	private void updateEnemies() {
		final int maxLevel = 5;

		if (enemiesMap.checkIfClear()) {
			if (currentLevel < maxLevel) {
				currentLevel++;
				enemiesMap.spawnWave(currentLevel, gamePane);

			} else {
				if (mode == "infinity") {
					currentLevel = 0;

				} else {
					hasWon = true;
					gameState = GameState.GAMEOVER;
				}
			}
		}

		enemiesMap.move();

		Iterator<Enemy> iterEnemy = enemiesMap.getEnemies().iterator();
		
		while (iterEnemy.hasNext()) {
			enemyCollisions(iterEnemy);
		}
	}

	private void enemyCollisions(Iterator<Enemy> iterEnemy) {
		Enemy enemy = iterEnemy.next();
		
		Rectangle e = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
		
		Iterator<Bullet> iterBullet = playerBullets.iterator();
		
		while (iterBullet.hasNext()) {
			Bullet bullet = iterBullet.next();
			if (e.intersects(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight())) {
				enemy.getHit();
				bullet.disappear();
				iterBullet.remove();
			}
		}
		
		if (enemy.getHealth() == 0) {
			enemy.disappear();
			iterEnemy.remove();
			
			if (mode == "levels") {
				points += enemy.getPoints();
				updatePoints();
			}
		}
	}

	private void updatePlayers(HashMap<String, KeyState> keysActive) {
		if (player1State != PlayerState.DEAD) {
			if (keysActive.containsKey("W")) { // TODO change so it uses keys from options
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

			if (keysActive.containsKey("SPACE")) {
				player1.shoot();
			} else {
				player1.resetTimer(); // bad idea, resets every cycle unnecessarily
			}

			playerCollisions(player1);
		}

		if (isMultiplayer) {
			if (player2State != PlayerState.DEAD) {
				if (keysActive.containsKey("UP")) { // TODO change so it uses keys from options
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

				if (keysActive.containsKey("ENTER")) {
					player2.shoot();
				} else {
					player2.resetTimer(); //
				}

				playerCollisions(player2);
			}

			if (player1State == PlayerState.DEAD && player2State == PlayerState.DEAD) {
				gameState = GameState.KILLED;
			}

		} else if (player1State == PlayerState.DEAD) {
			gameState = GameState.KILLED;
		}

	}

	private void playerCollisions(Player player) {
		PlayerState playerState;
		if (player == player1) {
			playerState = player1State;
		} else {
			playerState = player2State;
		}

		if (playerState == PlayerState.NORMAL && hasPlayerCollided(player)) {
			playerState = player.getHit();
			updateHearts(player);
		}

		if (playerState == PlayerState.HIT && player.getHasCooldownEnded()) {
			playerState = PlayerState.NORMAL;
		}

		if (player == player1) {
			player1State = playerState;
		} else {
			player2State = playerState;
		}
	}

	private boolean hasPlayerCollided(Player player) {
		Rectangle p = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());

		for (Bullet bullet : bullets) {
			if (p.intersects(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight())) {
				if (player == player1) {
					if (player1State == PlayerState.NORMAL) {
						bullet.disappear();
						bullets.remove(bullet);
					}
				} else {
					if (player2State == PlayerState.NORMAL) {
						bullet.disappear();
						bullets.remove(bullet);
					}
				}

				return true;
			}
		}

		for (Enemy enemy : enemiesMap.getEnemies()) {
			if (p.intersects(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())) {
				return true;
			}
		}
		return false;
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

	public enum PlayerState {
		NORMAL, HIT, DEAD
	}

}