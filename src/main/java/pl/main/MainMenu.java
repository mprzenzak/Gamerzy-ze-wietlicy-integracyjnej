package pl.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MainMenu {

	private GraphicsContext gc;
	private Image bg;
	private Image playButton, playButtonPressed, playButtonSelected;
	private Image shopButton, shopButtonPressed, shopButtonSelected;
	private Image achievementsButton, achievementsButtonPressed, achievementsButtonSelected;
	private Image highscoresButton, highscoresButtonPressed, highscoresButtonSelected;
	private Image creditsButton, creditsButtonPressed, creditsButtonSelected;
	private Image exitButton, exitButtonPressed, exitButtonSelected;

	private int playX, playY;
	private int shopX, shopY;
	private int achievementsX, achievementsY;
	private int highscoresX, highscoresY;
	private int creditsX, creditsY;
	private int exitX, exitY;

	private ButtonSelected buttonSelected;

	public MainMenu(GraphicsContext gc) {
		this.gc = gc;

		this.bg = Runner.bgMenuImage;

		this.playButton = new Image("file:resources\\play_button.png");
		this.playButtonPressed = new Image("file:resources\\play_button_pressed.png");
		this.playButtonSelected = new Image("file:resources\\play_button_selected.png");
		this.playX = 700;
		this.playY = 575;

		this.shopButton = new Image("file:resources\\shop_button.png");
		this.shopButtonPressed = new Image("file:resources\\shop_button_pressed.png");
		this.shopButtonSelected = new Image("file:resources\\shop_button_selected.png");
		this.shopX = 700;
		this.shopY = 640;

		this.achievementsButton = new Image("file:resources\\achievements_button.png");
		this.achievementsButtonPressed = new Image("file:resources\\achievements_button_pressed.png");
		this.achievementsButtonSelected = new Image("file:resources\\achievements_button_selected.png");
		this.achievementsX = 700;
		this.achievementsY = 705;

		this.highscoresButton = new Image("file:resources\\highscores_button.png");
		this.highscoresButtonPressed = new Image("file:resources\\highscores_button_pressed.png");
		this.highscoresButtonSelected = new Image("file:resources\\highscores_button_selected.png");
		this.highscoresX = 700;
		this.highscoresY = 770;

		this.creditsButton = new Image("file:resources\\credits_button.png");
		this.creditsButtonPressed = new Image("file:resources\\credits_button_pressed.png");
		this.creditsButtonSelected = new Image("file:resources\\credits_button_selected.png");
		this.creditsX = 1385;
		this.creditsY = 835;

		this.exitButton = new Image("file:resources\\exit_button.png");
		this.exitButtonPressed = new Image("file:resources\\exit_button_pressed.png");
		this.exitButtonSelected = new Image("file:resources\\exit_button_selected.png");
		this.exitX = 15;
		this.exitY = 835;
	}

	private enum ButtonSelected {
		PLAY, SHOP, ACHIEVEMENTS, HIGHSCORES, CREDITS, EXIT
	}
	
	public void getSelectedSubmenuType() {
		switch(this.buttonSelected) {
		case PLAY:
			Runner.submenuType = Runner.SubmenuType.PLAY;
			break;
		case SHOP:
			Runner.submenuType = Runner.SubmenuType.SHOP;
			break;
		case ACHIEVEMENTS:
			Runner.submenuType = Runner.SubmenuType.ACHIEVEMENTS;
			break;
		case HIGHSCORES:
			Runner.submenuType = Runner.SubmenuType.HIGHSCORES;
			break;
		case CREDITS:
			Runner.submenuType = Runner.SubmenuType.CREDITS;
			break;
		case EXIT:
			Runner.menuState = Runner.MenuState.EXIT;
		}
	}
	
	public void displayMainMenu() {
		gc.drawImage(bg, 0, 0);
		gc.drawImage(playButtonSelected, playX, playY);
		gc.drawImage(shopButton, shopX, shopY);
		gc.drawImage(achievementsButton, achievementsX, achievementsY);
		gc.drawImage(highscoresButton, highscoresX, highscoresY);
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

		case CREDITS:
			gc.drawImage(creditsButton, creditsX, creditsY);
			gc.drawImage(highscoresButtonSelected, highscoresX, highscoresY);
			this.buttonSelected = ButtonSelected.HIGHSCORES;
			break;

		case EXIT:
			gc.drawImage(exitButton, exitX, exitY);
			gc.drawImage(creditsButtonSelected, creditsX, creditsY);
			this.buttonSelected = ButtonSelected.CREDITS;
		}		
	}
}
