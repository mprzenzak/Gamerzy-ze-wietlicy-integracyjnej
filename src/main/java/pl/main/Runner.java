package pl.main;

import java.util.ArrayList;
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

public class Runner extends Application{

	static MenuState menuState;
	static SubmenuType submenuType;
	static MainMenu menu;
	HashMap<String, KeyState> keysActive;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//set stage
		Pane rootPane = new Pane();
		Scene scene = new Scene(rootPane);
		Canvas canvas = new Canvas(1600, 900);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		stage.setTitle("Jetpack Joe");
		stage.setResizable(false);
		stage.setHeight(927); //scene size is 1600x900
		stage.setWidth(1606);	
		stage.setScene(scene);
		
		rootPane.getChildren().addAll(canvas);
		
		
		keysActive = new HashMap(); //stores pressed keys
		menu = new MainMenu(gc);
		menuState = MenuState.PREPAREMENU;
		submenuType = SubmenuType.MAIN;
		//main loop
		new AnimationTimer() {

			@Override
			public void handle(long now) {
				update();
			}
			
		}.start();
		
		//detects user input
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
		case PREPAREMENU:
			menu.displayMainMenu();
			menuState = MenuState.MAINMENU;
			break;
			
		case MAINMENU:
			userSelectMenuOption();
			if (userPressed("ENTER")) {
			//	menuState = MenuState.SUBMENUS; //TODO
			}
			break;
			
		case SUBMENUS: //TODO
			
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
			
			break;
		case PLAY: //TODO
		}
	}

	private void selectNextMenuOption() {
		switch (submenuType) {
		case MAIN:
			menu.selectNextOption();
			break;
		case PLAY: //TODO
		}
		
	}
	public enum KeyState {
		PRESSED, HELD
	}

	public enum MenuState {
		PREPAREMENU, MAINMENU, SUBMENUS, GAMEPLAY, EXIT
	}
	
	public enum SubmenuType {
		MAIN, PLAY, SHOP, ACHIEVEMENTS, HIGHSCORES, CREDITS
	}
	

}
