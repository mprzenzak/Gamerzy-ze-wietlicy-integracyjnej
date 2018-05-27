package pl.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MainMenu {

	private GraphicsContext gc;
	private Image playButton, playButtonPressed, playButtonSelected;
	private Image shopButton, shopButtonPressed, shopButtonSelected;
	private Image achievementsButton, achievementsButtonPressed, achievementsButtonSelected;
	private Image highscoresButton, highscoresButtonPressed, highscoresButtonSelected;
	private Image optionsButton, optionsButtonPressed, optionsButtonSelected;
	private Image creditsButton, creditsButtonPressed, creditsButtonSelected;
	private Image exitButton, exitButtonPressed, exitButtonSelected;

	private int playX, playY;
	private int shopX, shopY;
	private int achievementsX, achievementsY;
	private int highscoresX, highscoresY;
	private int optionsX, optionsY;
	private int creditsX, creditsY;
	private int exitX, exitY;

	private ButtonSelected buttonSelected;

	public MainMenu(GraphicsContext gc) {
		this.gc = gc;

		this.playButton = new Image("file:resources\\play_button.png");
		this.playButtonPressed = new Image("file:resources\\play_button_pressed.png");
		this.playButtonSelected = new Image("file:resources\\play_button_selected.png");
		this.playX = 1920/2-200/2;
		this.playY = 1080-7*65;

		this.shopButton = new Image("file:resources\\shop_button.png");
		this.shopButtonPressed = new Image("file:resources\\shop_button_pressed.png");
		this.shopButtonSelected = new Image("file:resources\\shop_button_selected.png");
		this.shopX = 1920/2-200/2;
		this.shopY = 1080-6*65;

		this.achievementsButton = new Image("file:resources\\achievements_button.png");
		this.achievementsButtonPressed = new Image("file:resources\\achievements_button_pressed.png");
		this.achievementsButtonSelected = new Image("file:resources\\achievements_button_selected.png");
		this.achievementsX = 1920/2-200/2;
		this.achievementsY = 1080-5*65;

		this.highscoresButton = new Image("file:resources\\highscores_button.png");
		this.highscoresButtonPressed = new Image("file:resources\\highscores_button_pressed.png");
		this.highscoresButtonSelected = new Image("file:resources\\highscores_button_selected.png");
		this.highscoresX = 1920/2-200/2;
		this.highscoresY = 1080-4*65;
		
		this.optionsButton = new Image("file:resources\\options_button.png");
		this.optionsButtonPressed = new Image("file:resources\\options_button_pressed.png");
		this.optionsButtonSelected = new Image("file:resources\\options_button_selected.png");
		this.optionsX = 1920/2-200/2;
		this.optionsY = 1080-3*65;

		this.creditsButton = new Image("file:resources\\credits_button.png");
		this.creditsButtonPressed = new Image("file:resources\\credits_button_pressed.png");
		this.creditsButtonSelected = new Image("file:resources\\credits_button_selected.png");
		this.creditsX = 1920/2-200/2;
		this.creditsY = 1080-2*65;

		this.exitButton = new Image("file:resources\\exit_button.png");
		this.exitButtonPressed = new Image("file:resources\\exit_button_pressed.png");
		this.exitButtonSelected = new Image("file:resources\\exit_button_selected.png");
		this.exitX = 1920/2-200/2;
		this.exitY = 1080-65;
	}

	private enum ButtonSelected {
		PLAY, SHOP, ACHIEVEMENTS, HIGHSCORES, OPTIONS, CREDITS, EXIT
	}
	
	public GraphicsContext getGc() {
		return gc;
	}

	public void getSelectedSubmenuType() {
		switch(this.buttonSelected) {
		case PLAY:
			gc.drawImage(playButtonPressed, playX, playY);
			Runner.submenuType = Runner.SubmenuType.PLAY;
			break;
			
		case SHOP:
			gc.drawImage(shopButtonPressed, shopX, shopY);
			Runner.submenuType = Runner.SubmenuType.SHOP;
			break;
			
		case ACHIEVEMENTS:
			gc.drawImage(achievementsButtonPressed, achievementsX, achievementsY);
			Runner.submenuType = Runner.SubmenuType.ACHIEVEMENTS;
			break;
			
		case HIGHSCORES:
			gc.drawImage(highscoresButtonPressed, highscoresX, highscoresY);
			Runner.submenuType = Runner.SubmenuType.HIGHSCORES;
			break;
			
		case OPTIONS:
			gc.drawImage(optionsButtonPressed, optionsX, optionsY);
			Runner.submenuType = Runner.SubmenuType.OPTIONS;
			break;
			
		case CREDITS:
			gc.drawImage(creditsButtonPressed, creditsX, creditsY);
			Runner.submenuType = Runner.SubmenuType.CREDITS;
			break;
			
		case EXIT:
			gc.drawImage(exitButtonPressed, exitX, exitY);
			Runner.menuState = Runner.MenuState.EXIT;
		}
	}
	
	public void displayMainMenu() {
		gc.drawImage(playButtonSelected, playX, playY);
		gc.drawImage(shopButton, shopX, shopY);
		gc.drawImage(achievementsButton, achievementsX, achievementsY);
		gc.drawImage(highscoresButton, highscoresX, highscoresY);
		gc.drawImage(optionsButton, optionsX, optionsY);
		gc.drawImage(creditsButton, creditsX, creditsY);
		gc.drawImage(exitButton, exitX, exitY);

		this.buttonSelected = ButtonSelected.PLAY;
	}

	public void selectNextOption() {
		switch (this.buttonSelected) {
		case PLAY:
			gc.drawImage(playButton, playX, playY);
			gc.drawImage(shopButtonSelected, shopX, shopY);
			this.buttonSelected = ButtonSelected.SHOP;
			break;

		case SHOP:
			gc.drawImage(shopButton, shopX, shopY);
			gc.drawImage(achievementsButtonSelected, achievementsX, achievementsY);
			this.buttonSelected = ButtonSelected.ACHIEVEMENTS;
			break;

		case ACHIEVEMENTS:
			gc.drawImage(achievementsButton, achievementsX, achievementsY);
			gc.drawImage(highscoresButtonSelected, highscoresX, highscoresY);
			this.buttonSelected = ButtonSelected.HIGHSCORES;
			break;

		case HIGHSCORES:
			gc.drawImage(highscoresButton, highscoresX, highscoresY);
			gc.drawImage(optionsButtonSelected, optionsX, optionsY);
			this.buttonSelected = ButtonSelected.OPTIONS;
			break;

		case OPTIONS:
			gc.drawImage(optionsButton, optionsX, optionsY);
			gc.drawImage(creditsButtonSelected, creditsX, creditsY);
			this.buttonSelected = ButtonSelected.CREDITS;
			break;
			
		case CREDITS:
			gc.drawImage(creditsButton, creditsX, creditsY);
			gc.drawImage(exitButtonSelected, exitX, exitY);
			this.buttonSelected = ButtonSelected.EXIT;
			break;

		case EXIT:
			gc.drawImage(exitButton, exitX, exitY);
			gc.drawImage(playButtonSelected, playX, playY);
			this.buttonSelected = ButtonSelected.PLAY;
		}
	}

	public void selectPreviousOption() {
		switch (this.buttonSelected) {
		case PLAY:
			gc.drawImage(playButton, playX, playY);
			gc.drawImage(exitButtonSelected, exitX, exitY);
			this.buttonSelected = ButtonSelected.EXIT;
			break;

		case SHOP:
			gc.drawImage(shopButton, shopX, shopY);
			gc.drawImage(playButtonSelected, playX, playY);
			this.buttonSelected = ButtonSelected.PLAY;
			break;

		case ACHIEVEMENTS:
			gc.drawImage(achievementsButton, achievementsX, achievementsY);
			gc.drawImage(shopButtonSelected, shopX, shopY);
			this.buttonSelected = ButtonSelected.SHOP;
			break;

		case HIGHSCORES:
			gc.drawImage(highscoresButton, highscoresX, highscoresY);
			gc.drawImage(achievementsButtonSelected, achievementsX, achievementsY);
			this.buttonSelected = ButtonSelected.ACHIEVEMENTS;
			break;
			
		case OPTIONS:
			gc.drawImage(optionsButton, optionsX, optionsY);
			gc.drawImage(highscoresButtonSelected, highscoresX, highscoresY);
			this.buttonSelected = ButtonSelected.HIGHSCORES;
			break;

		case CREDITS:
			gc.drawImage(creditsButton, creditsX, creditsY);
			gc.drawImage(optionsButtonSelected, optionsX, optionsY);
			this.buttonSelected = ButtonSelected.OPTIONS;
			break;

		case EXIT:
			gc.drawImage(exitButton, exitX, exitY);
			gc.drawImage(creditsButtonSelected, creditsX, creditsY);
			this.buttonSelected = ButtonSelected.CREDITS;
		}		
	}
}
