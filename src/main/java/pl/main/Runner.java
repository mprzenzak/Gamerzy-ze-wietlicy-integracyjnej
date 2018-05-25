package pl.main;

import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Runner extends Application {

	static MenuState menuState;
	static SubmenuType submenuType;
	static MainMenu mainmenu;
	static PlayMenu playmenu;
	static ShopMenu shopmenu;
	static AchievementsMenu achievementsmenu;
	static HighscoresMenu highscoresmenu;
	static CreditsMenu creditsmenu;
	static Image bgMenuImage;
	HashMap<String, KeyState> keysActive;
	
	static int resolutionWidth;
	static int resolutionHeight;
	static int bgResolutionWidth;
	static int bgResolutionHeight;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// set stage
		resolutionHeight = 900;
		resolutionWidth = 1600;
		bgResolutionHeight = 900;
		bgResolutionWidth = 1600;
		
		Pane bgPane = new Pane();
		Canvas bgCanvas = new Canvas(bgResolutionWidth, bgResolutionHeight);
		GraphicsContext bgGc = bgCanvas.getGraphicsContext2D();
		bgPane.getChildren().add(bgCanvas);
		
		Pane mainmenuPane = new Pane();
		Canvas mainmenuCanvas = new Canvas(resolutionWidth, resolutionHeight);
		GraphicsContext mainmenuGc = mainmenuCanvas.getGraphicsContext2D();
		mainmenuPane.getChildren().add(mainmenuCanvas);
		
		Pane playmenuPane = new Pane();
		Canvas playmenuCanvas = new Canvas(resolutionWidth, resolutionHeight);
		GraphicsContext playmenuGc = playmenuCanvas.getGraphicsContext2D();
		playmenuPane.getChildren().add(playmenuCanvas);
		
		Pane shopmenuPane = new Pane();
		Canvas shopmenuCanvas = new Canvas(resolutionWidth, resolutionHeight);
		GraphicsContext shopmenuGc = shopmenuCanvas.getGraphicsContext2D();
		shopmenuPane.getChildren().add(shopmenuCanvas);
		
		Pane achievementsmenuPane = new Pane();
		Canvas achievementsmenuCanvas = new Canvas(resolutionWidth, resolutionHeight);
		GraphicsContext achievementsmenuGc = achievementsmenuCanvas.getGraphicsContext2D();
		achievementsmenuPane.getChildren().add(achievementsmenuCanvas);
		
		Pane highscoresmenuPane = new Pane();
		Canvas highscoresmenuCanvas = new Canvas(resolutionWidth, resolutionHeight);
		GraphicsContext highscoresmenuGc = highscoresmenuCanvas.getGraphicsContext2D();
		highscoresmenuPane.getChildren().add(highscoresmenuCanvas);
		
		Pane creditsmenuPane = new Pane();
		Canvas creditsmenuCanvas = new Canvas(resolutionWidth, resolutionHeight);
		GraphicsContext creditsmenuGc = creditsmenuCanvas.getGraphicsContext2D();
		creditsmenuPane.getChildren().add(creditsmenuCanvas);
		
		Pane rootPane = new Pane(bgPane, mainmenuPane, playmenuPane, shopmenuPane, achievementsmenuPane, highscoresmenuPane,
				creditsmenuPane); // this is for animated transitions to be implemented soon
		Canvas rootCanvas = new Canvas(resolutionWidth, resolutionHeight);
		GraphicsContext rootGc = rootCanvas.getGraphicsContext2D();
		rootPane.getChildren().add(rootCanvas);
		Scene scene = new Scene(rootPane);

		mainmenuPane.setScaleX(0.5);
		mainmenuPane.setScaleY(0.5);
		stage.setTitle("Jetpack Joe");
		stage.setResizable(false);
//		stage.setHeight(927); // scene size is 1600x900
//		stage.setWidth(1606);
		stage.setScene(scene);

		bgMenuImage = new Image("file:resources\\bg.jpg");
		bgGc.drawImage(bgMenuImage, 0, 0);
		keysActive = new HashMap(); // stores pressed keys

		mainmenu = new MainMenu(mainmenuGc);
		playmenu = new PlayMenu(playmenuGc);
		shopmenu = new ShopMenu(shopmenuGc);
		achievementsmenu = new AchievementsMenu(achievementsmenuGc);
		highscoresmenu = new HighscoresMenu(highscoresmenuGc);
		creditsmenu = new CreditsMenu(creditsmenuGc);

		menuState = MenuState.PREPAREMAINMENU;
		submenuType = SubmenuType.MAIN;

		// main loop
		new AnimationTimer() {

			@Override
			public void handle(long now) {
				update();
			}

		}.start();

		// detects user input
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				String keyData = key.getCode().toString();

				if (!keysActive.containsKey(keyData)) {
					keysActive.put(keyData, KeyState.PRESSED);
				}
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				String keyData = key.getCode().toString();
				keysActive.remove(keyData);
			}
		});

		stage.show();
	}

	private void update() {
		switch (menuState) {
		case PREPAREMAINMENU:
			mainmenu.displayMainMenu();
			menuState = MenuState.MAINMENU;
			break;

		case MAINMENU:
			userSelectMenuOption();
			if (userPressed("ENTER")) {
				menuState = MenuState.PREPARESUBMENU;
				mainmenu.getSelectedSubmenuType();
			}
			break;

		case PREPARESUBMENU:
			prepareChosenSubmenu(); // TODO
			
			break;
			
		case SUBMENU:
			break;
		
		case PREPAREGAMEPLAY:
			break;
			
		case GAMEPLAY:
			break;
		
		case EXIT:
			exit();
		}
	}

	private void exit() {
		// TODO Auto-generated method stub
		
	}

	private void prepareChosenSubmenu() {
		switch (submenuType) {
		case PLAY:
			playmenu.displayPlayMenu();
			break;
		case SHOP:
			shopmenu.displayShopMenu();
			break;
		case ACHIEVEMENTS:
			achievementsmenu.displayAchievementsMenu();
			break;
		case HIGHSCORES:
			highscoresmenu.displayHighscoresMenu();
			break;
		case CREDITS:
			creditsmenu.displayCreditsMenu();
		}
	}

	private boolean userPressed(String button) {
		if (keysActive.containsKey(button)) {
			if (keysActive.get(button) == KeyState.PRESSED) {
				keysActive.put(button, KeyState.HELD);
				return true;
			}
		}
		return false;
	}

	private void userSelectMenuOption() {
		if (userPressed("W") || userPressed("UP")) {
			selectPreviousMenuOption();
		}
		if (userPressed("S") || userPressed("DOWN")) {
			selectNextMenuOption();
		}
	}

	private void selectPreviousMenuOption() {
		switch (submenuType) {
		case MAIN:
			mainmenu.selectPreviousOption();
			break;
		case PLAY:
			playmenu.selectPreviousOption();
			break;
		case SHOP:
			shopmenu.selectPreviousOption();
			break;
		case ACHIEVEMENTS:
			achievementsmenu.selectPreviousOption();
		}
	}

	private void selectNextMenuOption() {
		switch (submenuType) {
		case MAIN:
			mainmenu.selectNextOption();
			break;
		case PLAY:
			playmenu.selectNextOption();
			break;
		case SHOP:
			shopmenu.selectNextOption();
			break;
		case ACHIEVEMENTS:
			achievementsmenu.selectNextOption();
		}

	}

	public enum KeyState {
		PRESSED, HELD
	}

	public enum MenuState {
		PREPAREMAINMENU, MAINMENU, PREPARESUBMENU, SUBMENU, PREPAREGAMEPLAY, GAMEPLAY, EXIT
	}

	public enum SubmenuType {
		MAIN, PLAY, SHOP, ACHIEVEMENTS, HIGHSCORES, CREDITS
	}

}
