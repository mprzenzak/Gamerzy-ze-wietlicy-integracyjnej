package pl.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.main.values.PaneCanvasGcSet;
import pl.main.values.RootPaneAndGcSet;
import pl.main.values.ScreenAndPaneDimensions;

public class Runner extends Application {

	static MenuState menuState;
	static SubmenuType submenuType;

	MainMenu mainMenu;
	PlayMenu playMenu;
	ShopMenu shopMenu;
	AchievementsMenu achievementsMenu;
	HighscoresMenu highscoresMenu;
	OptionsMenu optionsMenu;
	CreditsMenu creditsMenu;

	HashMap<String, KeyState> keysActive;
	
	int exitAnimationPosition;
	int enterAnimationPosition;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// set stage
		RootPaneAndGcSet rpgc = setPanes();
		Scene scene = setStage(stage, rpgc);
		createMenus(rpgc);
		setMenuBg(rpgc);

		keysActive = new HashMap(); // stores pressed keys
		exitAnimationPosition = 0;
		enterAnimationPosition = 1920;

		// main loop
		new AnimationTimer() {

			@Override
			public void handle(long now) {
				update();
				
				if(keysActive.containsKey("ESCAPE")) {
					exit();
				} //usun pozniej
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

	private void createMenus(RootPaneAndGcSet rpgc) {
		mainMenu = new MainMenu(rpgc.getMainMenuGc());
		playMenu = new PlayMenu(rpgc.getPlayMenuGc());
		shopMenu = new ShopMenu(rpgc.getShopMenuGc());
		achievementsMenu = new AchievementsMenu(rpgc.getAchievementsMenuGc());
		highscoresMenu = new HighscoresMenu(rpgc.getHighscoresMenuGc());
		optionsMenu = new OptionsMenu(rpgc.getOptionsMenuGc());
		creditsMenu = new CreditsMenu(rpgc.getCreditsMenuGc());

		menuState = MenuState.PREPAREMAINMENU;
		submenuType = SubmenuType.MAIN;

	}

	private void setMenuBg(RootPaneAndGcSet rpgc) {
		Image bgMenuImage = new Image("file:resources\\bg.jpg");
		rpgc.getBgGc().drawImage(bgMenuImage, 0, 0);
	}

	private Scene setStage(Stage stage, RootPaneAndGcSet rpgc) {
		stage.setTitle("Jetpack Joe");
		stage.setFullScreen(true);
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		Scene scene = new Scene(rpgc.getRootPane());
		stage.setScene(scene);

		return scene;
	}

	private RootPaneAndGcSet setPanes() {
		ScreenAndPaneDimensions dimensions = getDimensions();

		PaneCanvasGcSet pcgBg = setPaneComponents(dimensions);
		scaleCanvas(pcgBg.getCanvas(), dimensions);

		PaneCanvasGcSet pcgMainMenu = setPaneComponents(dimensions);
		scaleCanvas(pcgMainMenu.getCanvas(), dimensions);

		PaneCanvasGcSet pcgPlayMenu = setPaneComponents(dimensions);
		scaleCanvas(pcgPlayMenu.getCanvas(), dimensions);

		PaneCanvasGcSet pcgShopMenu = setPaneComponents(dimensions);
		scaleCanvas(pcgShopMenu.getCanvas(), dimensions);

		PaneCanvasGcSet pcgAchievementsMenu = setPaneComponents(dimensions);
		scaleCanvas(pcgAchievementsMenu.getCanvas(), dimensions);

		PaneCanvasGcSet pcgHighscoresMenu = setPaneComponents(dimensions);
		scaleCanvas(pcgHighscoresMenu.getCanvas(), dimensions);

		PaneCanvasGcSet pcgOptionsMenu = setPaneComponents(dimensions);
		scaleCanvas(pcgOptionsMenu.getCanvas(), dimensions);

		PaneCanvasGcSet pcgCreditsMenu = setPaneComponents(dimensions);
		scaleCanvas(pcgCreditsMenu.getCanvas(), dimensions);

		Pane rootPane = new Pane(pcgBg.getPane(), pcgMainMenu.getPane(), pcgPlayMenu.getPane(), pcgShopMenu.getPane(),
				pcgAchievementsMenu.getPane(), pcgHighscoresMenu.getPane(), pcgOptionsMenu.getPane(),
				pcgCreditsMenu.getPane()); // this is for animated transitions to be implemented soon
		Canvas rootCanvas = new Canvas(dimensions.getPaneWidth(), dimensions.getPaneHeight());
		GraphicsContext rootGc = rootCanvas.getGraphicsContext2D();
		rootPane.getChildren().add(rootCanvas);
		scaleCanvas(rootCanvas, dimensions);

		RootPaneAndGcSet rpgc = new RootPaneAndGcSet(rootPane, pcgBg.getGc(), pcgMainMenu.getGc(), pcgPlayMenu.getGc(),
				pcgShopMenu.getGc(), pcgAchievementsMenu.getGc(), pcgHighscoresMenu.getGc(), pcgOptionsMenu.getGc(),
				pcgCreditsMenu.getGc(), rootGc);

		return rpgc;
	}

	
	private PaneCanvasGcSet setPaneComponents(ScreenAndPaneDimensions dim) {
		Pane pane = new Pane();
		Canvas canvas = new Canvas(dim.getPaneWidth(), dim.getPaneHeight());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		pane.getChildren().add(canvas);

		return new PaneCanvasGcSet(pane, canvas, gc);
	}

	
	private void scaleCanvas(Canvas canvas, ScreenAndPaneDimensions dim) {
		canvas.setScaleX(dim.getScreenWidth() / dim.getPaneWidth());
		canvas.setScaleY(dim.getScreenHeight() / dim.getPaneHeight());
		canvas.setTranslateX(0 - Math.abs(dim.getScreenWidth() - dim.getPaneWidth()) / 2);
		canvas.setTranslateY(0 - Math.abs(dim.getScreenHeight() - dim.getPaneHeight()) / 2);
	}

	
	private ScreenAndPaneDimensions getDimensions() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double paneHeight = 1080;
		double paneWidth = 1920;
		double screenHeight = screenSize.getHeight();
		double screenWidth = screenSize.getWidth();

		return new ScreenAndPaneDimensions(paneHeight, paneWidth, screenHeight, screenWidth);
	}

	
	private void update() {
		switch (menuState) {
		case PREPAREMAINMENU: //TODO
			mainMenu.displayMainMenu();
			menuState = MenuState.MAINMENU;
			break;

		case MAINMENU:
			userSelectMenuOption();
			if (userPressed("ENTER")) {
				mainMenu.getSelectedSubmenuType();
				prepareChosenSubmenu();
				menuState = MenuState.PREPARESUBMENU;
			}
			break;

		case PREPARESUBMENU:
			if(exitAnimationPosition >= -1920) {
				menuExitAnimation(mainMenu.getGc().getCanvas());
				menuEnterAnimation();
				enterAnimationPosition -= 20;
			} else {
				exitAnimationPosition = 0;
				enterAnimationPosition = 1920;
				menuState = MenuState.SUBMENU;
			}
			break;

		case SUBMENU: //TODO
			userSelectMenuOption();
			if (userPressed("ENTER")) {
				getSelectedOption();
				menuState = MenuState.PREPAREGAMEPLAY;
			}
			break;

		case PREPAREGAMEPLAY:
			//-----------------------------------------------------------------------------------------------------------TU PRZYGOTOWANIE ROZGRYWKI
			break;

		case GAMEPLAY:
			//-----------------------------------------------------------------------------------------------------------TU PETLA SAMEJ ROZGRYWKI
			break;

		case EXIT:
			exit();
		}
	}

	private void getSelectedOption() { // TODO Auto-generated method stub
		switch (submenuType) {
		case PLAY:
//			playMenu.getSelectedOption();
			break;
		case SHOP:

			break;
		case ACHIEVEMENTS:

			break;
		case HIGHSCORES:

			break;
		case OPTIONS:

			break;
		case CREDITS:

		}		
	}

	private void menuEnterAnimation() {
		switch (submenuType) {
		case MAIN:
			mainMenu.getGc().getCanvas().setTranslateX(enterAnimationPosition);
			break;
		case PLAY:
			playMenu.getGc().getCanvas().setTranslateX(enterAnimationPosition);
			break;
		case SHOP:
			shopMenu.getGc().getCanvas().setTranslateX(enterAnimationPosition);
			break;
		case ACHIEVEMENTS:
			achievementsMenu.getGc().getCanvas().setTranslateX(enterAnimationPosition);
			break;
		case HIGHSCORES:
			highscoresMenu.getGc().getCanvas().setTranslateX(enterAnimationPosition);
			break;
		case OPTIONS:
			optionsMenu.getGc().getCanvas().setTranslateX(enterAnimationPosition);
			break;
		case CREDITS:
			creditsMenu.getGc().getCanvas().setTranslateX(enterAnimationPosition);
		}		
	}

	private void menuExitAnimation(Canvas canvas) {
			canvas.setTranslateX(exitAnimationPosition);
			exitAnimationPosition -= 20;
		// TODO Auto-generated method stub
		
	}

	private void exit() {
		System.exit(0); // clean up before!
	}

	private void prepareChosenSubmenu() {
		switch (submenuType) {
		case PLAY:
			playMenu.displayPlayMenu();
			break;
		case SHOP:
			shopMenu.displayShopMenu();
			break;
		case ACHIEVEMENTS:
			achievementsMenu.displayAchievementsMenu();
			break;
		case HIGHSCORES:
			highscoresMenu.displayHighscoresMenu();
			break;
		case OPTIONS:
			optionsMenu.displayOptionsMenu();
			break;
		case CREDITS:
			creditsMenu.displayCreditsMenu();
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
			mainMenu.selectPreviousOption();
			break;
		case PLAY:
			playMenu.selectPreviousOption();
			break;
		case SHOP:
			shopMenu.selectPreviousOption();
			break;
		case ACHIEVEMENTS:
			achievementsMenu.selectPreviousOption();
			break;
		case OPTIONS:
			optionsMenu.selectPreviousOption();
		}
	}

	private void selectNextMenuOption() {
		switch (submenuType) {
		case MAIN:
			mainMenu.selectNextOption();
			break;
		case PLAY:
			playMenu.selectNextOption();
			break;
		case SHOP:
			shopMenu.selectNextOption();
			break;
		case ACHIEVEMENTS:
			achievementsMenu.selectNextOption();
			break;
		case OPTIONS:
			optionsMenu.selectNextOption();
		}

	}

	public enum KeyState {
		PRESSED, HELD
	}

	public enum MenuState {
		PREPAREMAINMENU, MAINMENU, PREPARESUBMENU, SUBMENU, PREPAREGAMEPLAY, GAMEPLAY, EXIT
	}
	

	public enum SubmenuType {
		MAIN, PLAY, SHOP, ACHIEVEMENTS, HIGHSCORES, OPTIONS, CREDITS
	}

}
