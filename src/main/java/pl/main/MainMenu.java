package pl.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MainMenu {

	private GraphicsContext gc;
	
	private Image button, button_selected, button_pressed;
	private int buttonWidth, buttonHeight;
	
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

		this.playX = 1920/2-200/2;
		this.playY = 1080-7*65;

		this.shopX = 1920/2-200/2;
		this.shopY = 1080-6*65;

		this.achievementsX = 1920/2-200/2;
		this.achievementsY = 1080-5*65;

		this.highscoresX = 1920/2-200/2;
		this.highscoresY = 1080-4*65;
		
		this.optionsX = 1920/2-200/2;
		this.optionsY = 1080-3*65;

		this.creditsX = 1920/2-200/2;
		this.creditsY = 1080-2*65;

		this.exitX = 1920/2-200/2;
		this.exitY = 1080-65;
		
		this.button = new Image("file:resources\\button.png");
		this.button_selected = new Image("file:resources\\buttonSelected.png");
		this.button_pressed = new Image("file:resources\\buttonPressed.png");
		this.buttonWidth = 200;
		this.buttonHeight = 50;
	}

	private enum ButtonSelected {
		PLAY, SHOP, ACHIEVEMENTS, HIGHSCORES, OPTIONS, CREDITS, EXIT
	}
	
	public GraphicsContext getGc() {
		return gc;
	}

	public void getSelectedOption() {
		switch(this.buttonSelected) {
		case PLAY:
			drawButton(button_pressed, "play", playX, playY, buttonHeight, buttonWidth);
			Runner.submenuType = Runner.SubmenuType.PLAY;
			Runner.menuState = Runner.MenuState.PREPARESUBMENU;
			break;
			
		case SHOP:
			drawButton(button_pressed, "shop", shopX, shopY, buttonHeight, buttonWidth);
			Runner.submenuType = Runner.SubmenuType.SHOP;
			Runner.menuState = Runner.MenuState.PREPARESUBMENU;
			break;
			
		case ACHIEVEMENTS:
			drawButton(button_pressed, "achievements", achievementsX, achievementsY, buttonHeight, buttonWidth);
			Runner.submenuType = Runner.SubmenuType.ACHIEVEMENTS;
			Runner.menuState = Runner.MenuState.PREPARESUBMENU;
			break;
			
		case HIGHSCORES:
			drawButton(button_pressed, "highscores", highscoresX, highscoresY, buttonHeight, buttonWidth);
			Runner.submenuType = Runner.SubmenuType.HIGHSCORES;
			Runner.menuState = Runner.MenuState.PREPARESUBMENU;
			break;
			
		case OPTIONS:
			drawButton(button_pressed, "options", optionsX, optionsY, buttonHeight, buttonWidth);
			Runner.submenuType = Runner.SubmenuType.OPTIONS;
			Runner.menuState = Runner.MenuState.PREPARESUBMENU;
			break;
			
		case CREDITS:
			drawButton(button_pressed, "credits", creditsX, creditsY, buttonHeight, buttonWidth);
			Runner.submenuType = Runner.SubmenuType.CREDITS;
			Runner.menuState = Runner.MenuState.PREPARESUBMENU;
			break;
			
		case EXIT:
			drawButton(button_pressed, "exit", exitX, exitY, buttonHeight, buttonWidth);
			Runner.menuState = Runner.MenuState.EXIT;
		}
	}
	
	public boolean displayMainMenu(boolean gameJustStarted) {
		if(!gameJustStarted) {
			gc.getCanvas().setTranslateX(1920);
		}

		drawButton(button_selected, "play", playX, playY, buttonHeight, buttonWidth);
		drawButton(button, "shop", shopX, shopY, buttonHeight, buttonWidth);
		drawButton(button, "achievements", achievementsX, achievementsY, buttonHeight, buttonWidth);
		drawButton(button, "highscores", highscoresX, highscoresY, buttonHeight, buttonWidth);
		drawButton(button, "options", optionsX, optionsY, buttonHeight, buttonWidth);
		drawButton(button, "credits", creditsX, creditsY, buttonHeight, buttonWidth);
		drawButton(button, "exit", exitX, exitY, buttonHeight, buttonWidth);

		this.buttonSelected = ButtonSelected.PLAY;
		
		return false;
	}

	private void drawButton(Image img, String string, int x, int y, int buttonH, int buttonW) { 
		gc.drawImage(img, x, y);
		
		Text text = new Text(string);
		text.setFont(new Font("Consolas", 20));
		double textH = text.getLayoutBounds().getHeight();
		double textW = text.getLayoutBounds().getWidth();
		
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("Consolas", 20));
		gc.fillText(string, x + buttonW/2 - textW/2, y + buttonH/2 + textH/2 - 4);
		
	}

	public void selectNextOption() {
		switch (this.buttonSelected) {
		case PLAY:
			drawButton(button, "play", playX, playY, buttonHeight, buttonWidth);
			drawButton(button_selected, "shop", shopX, shopY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.SHOP;
			break;

		case SHOP:
			drawButton(button, "shop", shopX, shopY, buttonHeight, buttonWidth);
			drawButton(button_selected, "achievements", achievementsX, achievementsY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.ACHIEVEMENTS;
			break;

		case ACHIEVEMENTS:
			drawButton(button, "achievements", achievementsX, achievementsY, buttonHeight, buttonWidth);
			drawButton(button_selected, "highscores", highscoresX, highscoresY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.HIGHSCORES;
			break;

		case HIGHSCORES:
			drawButton(button, "highscores", highscoresX, highscoresY, buttonHeight, buttonWidth);
			drawButton(button_selected, "options", optionsX, optionsY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.OPTIONS;
			break;

		case OPTIONS:
			drawButton(button, "options", optionsX, optionsY, buttonHeight, buttonWidth);
			drawButton(button_selected, "credits", creditsX, creditsY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.CREDITS;
			break;
			
		case CREDITS:
			drawButton(button, "credits", creditsX, creditsY, buttonHeight, buttonWidth);
			drawButton(button_selected, "exit", exitX, exitY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.EXIT;
			break;

		case EXIT:
			drawButton(button, "exit", exitX, exitY, buttonHeight, buttonWidth);
			drawButton(button_selected, "play", playX, playY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.PLAY;
		}
	}

	public void selectPreviousOption() {
		switch (this.buttonSelected) {
		case PLAY:
			drawButton(button, "play", playX, playY, buttonHeight, buttonWidth);
			drawButton(button_selected, "exit", exitX, exitY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.EXIT;
			break;

		case SHOP:
			drawButton(button, "shop", shopX, shopY, buttonHeight, buttonWidth);
			drawButton(button_selected, "play", playX, playY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.PLAY;
			break;

		case ACHIEVEMENTS:
			drawButton(button, "achievements", achievementsX, achievementsY, buttonHeight, buttonWidth);
			drawButton(button_selected, "shop", shopX, shopY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.SHOP;
			break;

		case HIGHSCORES:
			drawButton(button, "highscores", highscoresX, highscoresY, buttonHeight, buttonWidth);
			drawButton(button_selected, "achievements", achievementsX, achievementsY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.ACHIEVEMENTS;
			break;
			
		case OPTIONS:
			drawButton(button, "options", optionsX, optionsY, buttonHeight, buttonWidth);
			drawButton(button_selected, "highscores", highscoresX, highscoresY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.HIGHSCORES;
			break;

		case CREDITS:
			drawButton(button, "credits", creditsX, creditsY, buttonHeight, buttonWidth);
			drawButton(button_selected, "options", optionsX, optionsY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.OPTIONS;
			break;

		case EXIT:
			drawButton(button, "exit", exitX, exitY, buttonHeight, buttonWidth);
			drawButton(button_selected, "credits", creditsX, creditsY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.CREDITS;
		}		
	}
}
