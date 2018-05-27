package pl.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayMenu {

	private GraphicsContext gc;
	private Image back;
	private Image numberOfPlayersButton_singlePressed, numberOfPlayersButton_multiPressed,
			numberOfPlayersButton_singleSelected, numberOfPlayersButton_singleSelected_multiPressed,
			numberOfPlayersButton_multiSelected, numberOfPlayersButton_multiSelected_singlePressed;
	private Image modeButton_levelsPressed, modeButton_infinityPressed, modeButton_levelsSelected,
			modeButton_levelsSelected_infinityPressed, modeButton_infinitySelected,
			modeButton_infinitySelected_levelsPressed;
	private Image startButton, startButtonSelected, startButtonPressed;
	private Image descripton_items_goalsBox;
	
	private int startX, startY;

	private ButtonSelected buttonSelected;

	public PlayMenu(GraphicsContext gc) {
		this.gc = gc;
		this.startButton = new Image("file:resources\\start_button.png");
		this.startButtonSelected = new Image("file:resources\\start_button_selected.png");
		this.startButtonPressed = new Image("file:resources\\start_button_pressed.png");
		this.startX = 15;
		this.startY = 1080-65;
		// TODO Auto-generated constructor stub
	}

	private enum ButtonSelected {
		BACK, NUMBER_OF_PLAYERS, MODE, START
	}

	public void displayPlayMenu() {
		gc.getCanvas().setTranslateX(1920);
		gc.drawImage(startButtonSelected, startX, startY);

		this.buttonSelected = ButtonSelected.START;
		// TODO Auto-generated method stub

	}

	public void selectPreviousOption() {
		// TODO Auto-generated method stub

	}

	public void selectNextOption() {
		// TODO Auto-generated method stub

	}

	public GraphicsContext getGc() {
		return gc;
	}

}
