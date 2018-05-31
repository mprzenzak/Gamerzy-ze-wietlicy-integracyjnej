package pl.main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {

	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<Node> blocks = new ArrayList<Node>();
	private Pane appRoot = new Pane();

	private Pane gameRoot = new Pane();
	private Pane uiRoot = new Pane();

	private Node player;
	private Point2D playerGoDown = new Point2D(0, 0);
	private Point2D playerGoRight = new Point2D(0, 0);
	private boolean canJump = true;
	private int levelWidth;

	private void initContent() {
		Rectangle background = new Rectangle(720, 480);
		// BackgroundFill(Color.WHITE);

		levelWidth = LevelData.LEVEL1[0].length();

		for (int i = 0; i < LevelData.LEVEL1.length; i++) {
			String map = LevelData.LEVEL1[i] + LevelData.LEVEL2[i] + LevelData.LEVEL1[i] + LevelData.LEVEL2[i]
					+ LevelData.LEVEL1[i] + LevelData.LEVEL2[i];

			String line = map;
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
				case '0':
					break;
				case '1':
					Node block = createEntity(j * 30, i * 30, 30, 30, Color.ORANGE);
					blocks.add(block);
					break;
				}
			}
		}
		player = createEntity(0, 350, 40, 40, Color.YELLOW);
		// a ????????????
		player.translateXProperty().addListener((a, old, newValue) -> {
			int offset = newValue.intValue();
			// if (offset > 360 && offset < levelWidth - 360) {
			gameRoot.setLayoutX(-(offset - 360));
			// }
		});
		appRoot.getChildren().addAll(background, gameRoot, uiRoot);
	}

	private void update() {
		if (isPressed(KeyCode.W) && player.getTranslateY() >= 0) {
			jumpPlayer();
		}
		if (playerGoDown.getY() < 10) {
			playerGoDown = playerGoDown.add(0, 1);
		}
		movePlayerY((int) playerGoDown.getY());

		if (player.getTranslateX() <= levelWidth) {
			// movePlayerX(5);
			movePlayerRight();
		}

		if (playerGoRight.getX() < 0) {
			playerGoRight = playerGoRight.add(0, 1);
		}
		movePlayerX((int) playerGoRight.getX());
	}

	private void movePlayerX(int value) {
		boolean movingRight = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node block : blocks) {
				if (player.getBoundsInParent().intersects(block.getBoundsInParent())) {
					if (movingRight) {
						if (player.getTranslateX() + 40 == block.getTranslateX()) {
							return;
						}
					} else {
						if (player.getTranslateX() == block.getTranslateX() + 60) {
							return;
						}
					}
				}
			}
			player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
		}
	}

	private void movePlayerY(int value) {
		boolean movingDown = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node block : blocks) {
				if (player.getBoundsInParent().intersects(block.getBoundsInParent())) {
					if (movingDown) {
						if (player.getTranslateY() + 40 == block.getTranslateY()) {
							canJump = true;
							return;
						}
					} else {
						if (player.getTranslateY() == block.getTranslateY() + 60) {
							return;
						}
					}
				}
			}
			player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
		}
	}

	private void jumpPlayer() {
		if (canJump) {
			playerGoDown = playerGoDown.add(0, -10);
			canJump = false;
		}
	}

	private void movePlayerRight() {
		playerGoRight = playerGoRight.add(10, 0);
	}

	private Node createEntity(int x, int y, int w, int h, Color color) {
		Rectangle entity = new Rectangle(w, h);
		entity.setTranslateX(x);
		entity.setTranslateY(y);
		entity.setFill(color);
		gameRoot.getChildren().add(entity);
		return entity;

	}

	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initContent();
		Scene scene = new Scene(appRoot);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
		primaryStage.setTitle("Jetpack gameplay");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		timer.start();
	}

	public static void main(String[] args) {

		launch(args);
	}
}
