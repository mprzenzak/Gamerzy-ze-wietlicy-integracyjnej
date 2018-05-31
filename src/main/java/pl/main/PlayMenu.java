package pl.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayMenu {

	private GraphicsContext gc;
	private Image backButton, backButtonSelected, backButtonPressed;
	private Image numberOfPlayersButton_singlePressed, numberOfPlayersButton_multiPressed,
			numberOfPlayersButton_singleSelected, numberOfPlayersButton_singleSelected_multiPressed,
			numberOfPlayersButton_multiSelected, numberOfPlayersButton_multiSelected_singlePressed;
	private Image modeButton_levelsPressed, modeButton_infinityPressed, modeButton_levelsSelected,
			modeButton_levelsSelected_infinityPressed, modeButton_infinitySelected,
			modeButton_infinitySelected_levelsPressed;
	private Image startButton, startButtonSelected, startButtonPressed;
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
		
		this.backButton = new Image("file:resources\\backButton.png");
		this.backButtonSelected = new Image("file:resources\\backButtonSelected.png");
		this.backButtonPressed = new Image("file:resources\\backButtonPressed.png");
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
		
		this.startButton = new Image("file:resources\\start_button.png");
		this.startButtonSelected = new Image("file:resources\\start_button_selected.png");
		this.startButtonPressed = new Image("file:resources\\start_button_pressed.png");
		this.startX = 15;
		this.startY = 1080-65;
		
		this.goalsBox = new Image("file:resources\\goalsBox.png");
		this.goalsX = 450;
		this.goalsY = 1080-230;
		
		this.descriptionBox = new Image("file:resources\\descriptionBox.png");
		this.descX = 450;
		this.descY = 1080-460;		
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

	public void displayPlayMenu() {
		gc.getCanvas().setTranslateX(1920);
		
		gc.drawImage(backButtonSelected, backX, backY);
		gc.drawImage(numberOfPlayersButton_singlePressed, numberOfPlayersX, numberOfPlayersY);
		gc.drawImage(modeButton_levelsPressed, modeX, modeY);
		gc.drawImage(startButton, startX, startY);
		gc.drawImage(descriptionBox, descX, descY);
		gc.drawImage(goalsBox, goalsX, goalsY);

		this.buttonSelected = ButtonSelected.BACK;
		this.playersNumberPressed = PlayersNumberPressed.ONE;
		this.modePressed = ModePressed.LEVELS;
	}

	public void selectPreviousOption() {
		switch (this.buttonSelected) {
		case BACK:
			gc.drawImage(backButton, backX, backY);
			gc.drawImage(startButtonSelected, startX, startY);
			this.buttonSelected = ButtonSelected.START;
			break;
			
		case NUMBER_OF_PLAYERS:
			if (this.playersNumberPressed == PlayersNumberPressed.ONE) {
				gc.drawImage(numberOfPlayersButton_singlePressed, numberOfPlayersX, numberOfPlayersY);
			} else {
				gc.drawImage(numberOfPlayersButton_multiPressed, numberOfPlayersX, numberOfPlayersY);
			}
			gc.drawImage(backButtonSelected, backX, backY);
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
			gc.drawImage(startButton, startX, startY);
			if (this.modePressed == ModePressed.LEVELS) {
				gc.drawImage(modeButton_levelsSelected, modeX, modeY);
			} else {
				gc.drawImage(modeButton_levelsSelected_infinityPressed, modeX, modeY);
			}
			this.modeSelected = ModeSelected.LEVELS;
			this.buttonSelected = ButtonSelected.MODE;
		}
	}

	public void selectNextOption() {
		switch (this.buttonSelected) {
		case BACK:
			gc.drawImage(backButton, backX, backY);
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
			gc.drawImage(startButtonSelected, startX, startY);
			this.buttonSelected = ButtonSelected.START;
			break;
			
		case START:
			gc.drawImage(startButton, startX, startY);
			gc.drawImage(backButtonSelected, backX, backY);
			this.buttonSelected = ButtonSelected.BACK;
		}
	}

	public GraphicsContext getGc() {
		return gc;
	}

	@SuppressWarnings("incomplete-switch")
	public void selectLeftOption() {
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
	public void selectRightOption() {
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

	
	public void getSelectedOption() {
		switch(this.buttonSelected) {
		case BACK:
			gc.drawImage(backButtonPressed, backX, backY);
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
			gc.drawImage(startButtonPressed, startX, startY);
			Runner.menuState = Runner.MenuState.PREPAREGAMEPLAY;
		}		
	}

	public PlayersNumberPressed getNumberOfPlayers() {
		return playersNumberPressed;
	}

	public ModePressed getMode() {
		return modePressed;
	}

}
