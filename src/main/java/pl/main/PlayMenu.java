package pl.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PlayMenu {

	private GraphicsContext gc;
	
	private Image button, button_selected, button_pressed;
	private int buttonWidth, buttonHeight;
	
	private Image numberOfPlayersButton_singlePressed, numberOfPlayersButton_multiPressed,
			numberOfPlayersButton_singleSelected, numberOfPlayersButton_singleSelected_multiPressed,
			numberOfPlayersButton_multiSelected, numberOfPlayersButton_multiSelected_singlePressed;
	private Image modeButton_levelsPressed, modeButton_infinityPressed, modeButton_levelsSelected,
			modeButton_levelsSelected_infinityPressed, modeButton_infinitySelected,
			modeButton_infinitySelected_levelsPressed;
	
	private Image goalsBox;
	private Image descriptionBox;
	
	private int backX, backY;
	private int numberOfPlayersX, numberOfPlayersY;
	private int modeX, modeY;
	private int startX, startY;
	private int goalsX, goalsY;
	private int descX, descY;

	private ButtonSelected buttonSelected;
	private PlayersNumberPressed playersNumberPressed;
	private ModePressed modePressed;
	private PlayersNumberSelected playersNumberSelected;
	private ModeSelected modeSelected;

	public PlayMenu(GraphicsContext gc) {
		this.gc = gc;
		
		this.backX = 15;
		this.backY = 15;
		
		this.numberOfPlayersButton_singlePressed = new Image("file:resources\\numberOfPlayersButton_singlePressed.png");
		this.numberOfPlayersButton_multiPressed = new Image("file:resources\\numberOfPlayersButton_multiPressed.png");
		this.numberOfPlayersButton_singleSelected = new Image("file:resources\\numberOfPlayersButton_singleSelected.png");
		this.numberOfPlayersButton_singleSelected_multiPressed = new Image("file:resources\\numberOfPlayersButton_singleSelected_multiPressed.png");
		this.numberOfPlayersButton_multiSelected = new Image("file:resources\\numberOfPlayersButton_multiSelected.png");
		this.numberOfPlayersButton_multiSelected_singlePressed = new Image("file:resources\\numberOfPlayersButton_multiSelected_singlePressed.png");
		this.numberOfPlayersX = 15;
		this.numberOfPlayersY = 1080-295;
		
		this.modeButton_levelsPressed = new Image("file:resources\\modeButton_levelsPressed.png");
		this.modeButton_infinityPressed = new Image("file:resources\\modeButton_infinityPressed.png");
		this.modeButton_levelsSelected = new Image("file:resources\\modeButton_levelsSelected.png");
		this.modeButton_levelsSelected_infinityPressed = new Image("file:resources\\modeButton_levelsSelected_infinityPressed.png");
		this.modeButton_infinitySelected = new Image("file:resources\\modeButton_infinitySelected.png");
		this.modeButton_infinitySelected_levelsPressed = new Image("file:resources\\modeButton_infinitySelected_levelsPressed.png");
		this.modeX = 15;
		this.modeY = 1080-180;
		
		this.startX = 15;
		this.startY = 1080-65;
		
		this.goalsBox = new Image("file:resources\\goalsBox.png");
		this.goalsX = 450;
		this.goalsY = 1080-230;
		
		this.descriptionBox = new Image("file:resources\\descriptionBox.png");
		this.descX = 450;
		this.descY = 1080-460;		
		
		this.button = new Image("file:resources\\button.png");
		this.button_selected = new Image("file:resources\\buttonSelected.png");
		this.button_pressed = new Image("file:resources\\buttonPressed.png");
		this.buttonWidth = 200;
		this.buttonHeight = 50;
	}

	private enum ButtonSelected {
		BACK, NUMBER_OF_PLAYERS, MODE, START
	}
	
	private enum PlayersNumberPressed {
		ONE, TWO
	}
	
	private enum ModePressed {
		LEVELS, INFINITY
	}
	
	private enum PlayersNumberSelected {
		ONE, TWO
	}
	
	private enum ModeSelected {
		LEVELS, INFINITY
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
	
	public void displayMenu() { //
		gc.getCanvas().setTranslateX(1920);
		
		drawButton(button_selected, "back", backX, backY, buttonHeight, buttonWidth);
		drawButton(button, "start", startX, startY, buttonHeight, buttonWidth);

		gc.drawImage(numberOfPlayersButton_singlePressed, numberOfPlayersX, numberOfPlayersY);
		gc.drawImage(modeButton_levelsPressed, modeX, modeY);
		gc.drawImage(descriptionBox, descX, descY);
		gc.drawImage(goalsBox, goalsX, goalsY);

		this.buttonSelected = ButtonSelected.BACK;
		this.playersNumberPressed = PlayersNumberPressed.ONE;
		this.modePressed = ModePressed.LEVELS;
	}

	public void selectPreviousOption() { //
		switch (this.buttonSelected) {
		case BACK:
			drawButton(button, "back", backX, backY, buttonHeight, buttonWidth);
			drawButton(button_selected, "start", startX, startY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.START;
			break;
			
		case NUMBER_OF_PLAYERS:
			if (this.playersNumberPressed == PlayersNumberPressed.ONE) {
				gc.drawImage(numberOfPlayersButton_singlePressed, numberOfPlayersX, numberOfPlayersY);
			} else {
				gc.drawImage(numberOfPlayersButton_multiPressed, numberOfPlayersX, numberOfPlayersY);
			}
			drawButton(button_selected, "back", backX, backY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.BACK;
			break;
			
		case MODE:
			if (this.modePressed == ModePressed.LEVELS) {
				gc.drawImage(modeButton_levelsPressed, modeX, modeY);
			} else {
				gc.drawImage(modeButton_infinityPressed, modeX, modeY);
			}
			if (this.playersNumberPressed == PlayersNumberPressed.ONE) {
				gc.drawImage(numberOfPlayersButton_singleSelected, numberOfPlayersX, numberOfPlayersY);
			} else {
				gc.drawImage(numberOfPlayersButton_singleSelected_multiPressed, numberOfPlayersX, numberOfPlayersY);
			}
			this.playersNumberSelected = PlayersNumberSelected.ONE;
			this.buttonSelected = ButtonSelected.NUMBER_OF_PLAYERS;
			break;
			
		case START:
			drawButton(button, "start", startX, startY, buttonHeight, buttonWidth);
			if (this.modePressed == ModePressed.LEVELS) {
				gc.drawImage(modeButton_levelsSelected, modeX, modeY);
			} else {
				gc.drawImage(modeButton_levelsSelected_infinityPressed, modeX, modeY);
			}
			this.modeSelected = ModeSelected.LEVELS;
			this.buttonSelected = ButtonSelected.MODE;
		}
	}

	public void selectNextOption() { //
		switch (this.buttonSelected) {
		case BACK:
			drawButton(button, "back", backX, backY, buttonHeight, buttonWidth);
			if (this.playersNumberPressed == PlayersNumberPressed.ONE) {
				gc.drawImage(numberOfPlayersButton_singleSelected, numberOfPlayersX, numberOfPlayersY);
			} else {
				gc.drawImage(numberOfPlayersButton_singleSelected_multiPressed, numberOfPlayersX, numberOfPlayersY);
			}
			this.playersNumberSelected = PlayersNumberSelected.ONE;
			this.buttonSelected = ButtonSelected.NUMBER_OF_PLAYERS;
			break;
		
		case NUMBER_OF_PLAYERS:
			if (this.playersNumberPressed == PlayersNumberPressed.ONE) {
				gc.drawImage(numberOfPlayersButton_singlePressed, numberOfPlayersX, numberOfPlayersY);
			} else {
				gc.drawImage(numberOfPlayersButton_multiPressed, numberOfPlayersX, numberOfPlayersY);
			}
			if (this.modePressed == ModePressed.LEVELS) {
				gc.drawImage(modeButton_levelsSelected, modeX, modeY);
			} else {
				gc.drawImage(modeButton_levelsSelected_infinityPressed, modeX, modeY);
			}
			this.modeSelected = ModeSelected.LEVELS;
			this.buttonSelected = ButtonSelected.MODE;
			break;
			
		case MODE:
			if (this.modePressed == ModePressed.LEVELS) {
				gc.drawImage(modeButton_levelsPressed, modeX, modeY);
			} else {
				gc.drawImage(modeButton_infinityPressed, modeX, modeY);
			}
			drawButton(button_selected, "start", startX, startY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.START;
			break;
			
		case START:
			drawButton(button, "start", startX, startY, buttonHeight, buttonWidth);
			drawButton(button_selected, "back", backX, backY, buttonHeight, buttonWidth);
			this.buttonSelected = ButtonSelected.BACK;
		}
	}

	public GraphicsContext getGc() {
		return gc;
	}

	@SuppressWarnings("incomplete-switch")
	public void selectLeftOption() { //
		switch (this.buttonSelected) {
		case NUMBER_OF_PLAYERS:
			if (this.playersNumberSelected == PlayersNumberSelected.TWO) {
				if (this.playersNumberPressed == PlayersNumberPressed.ONE) {
					gc.drawImage(numberOfPlayersButton_singleSelected, numberOfPlayersX, numberOfPlayersY);
				} else {
					gc.drawImage(numberOfPlayersButton_singleSelected_multiPressed, numberOfPlayersX, numberOfPlayersY);
				}
				this.playersNumberSelected = PlayersNumberSelected.ONE;
			}
			break;
			
		case MODE:
			if (this.modeSelected == ModeSelected.INFINITY) {
				if (this.modePressed == ModePressed.LEVELS) {
					gc.drawImage(modeButton_levelsSelected, modeX, modeY);
				} else {
					gc.drawImage(modeButton_levelsSelected_infinityPressed, modeX, modeY);
				}
				this.modeSelected = ModeSelected.LEVELS;
			}
		}
	}

	@SuppressWarnings("incomplete-switch")
	public void selectRightOption() { //
		switch (this.buttonSelected) {
		case NUMBER_OF_PLAYERS:
			if (this.playersNumberSelected == PlayersNumberSelected.ONE) {
				if (this.playersNumberPressed == PlayersNumberPressed.ONE) {
					gc.drawImage(numberOfPlayersButton_multiSelected_singlePressed, numberOfPlayersX, numberOfPlayersY);
				} else {
					gc.drawImage(numberOfPlayersButton_multiSelected, numberOfPlayersX, numberOfPlayersY);
				}
				this.playersNumberSelected = PlayersNumberSelected.TWO;
			}
			break;
			
		case MODE:
			if (this.modeSelected == ModeSelected.LEVELS) {
				if (this.modePressed == ModePressed.LEVELS) {
					gc.drawImage(modeButton_infinitySelected_levelsPressed, modeX, modeY);
				} else {
					gc.drawImage(modeButton_infinitySelected, modeX, modeY);
				}
				this.modeSelected = ModeSelected.INFINITY;
			}
		}
	}

	
	public void getSelectedOption() { //
		switch(this.buttonSelected) {
		case BACK:
			drawButton(button_pressed, "back", backX, backY, buttonHeight, buttonWidth);
			Runner.submenuType = Runner.SubmenuType.MAIN;
			Runner.menuState = Runner.MenuState.PREPARESUBMENU;
			break;

		case NUMBER_OF_PLAYERS:
			if (this.playersNumberSelected == PlayersNumberSelected.ONE) {
				gc.drawImage(numberOfPlayersButton_singleSelected, numberOfPlayersX, numberOfPlayersY);
				this.playersNumberPressed = PlayersNumberPressed.ONE;
			} else {
				gc.drawImage(numberOfPlayersButton_multiSelected, numberOfPlayersX, numberOfPlayersY);
				this.playersNumberPressed = PlayersNumberPressed.TWO;
			}
			break;
			
		case MODE:
			if (this.modeSelected == ModeSelected.LEVELS) {
				gc.drawImage(modeButton_levelsSelected, modeX, modeY);
				this.modePressed = ModePressed.LEVELS;
			} else {
				gc.drawImage(modeButton_infinitySelected, modeX, modeY);
				this.modePressed = ModePressed.INFINITY;
			}
			break;
			
		case START:
			drawButton(button_pressed, "start", startX, startY, buttonHeight, buttonWidth);
			Runner.menuState = Runner.MenuState.PREPAREGAMEPLAY;
		}		
	}

	public int getNumberOfPlayers() {
		if (playersNumberPressed == PlayersNumberPressed.ONE) {
			return 1;
		} else {
			return 2;
		}
	}

	public String getMode() {
		if (modePressed == ModePressed.LEVELS) {
			return "levels";
		} else {
			return "infinity";
		}
	}

}
