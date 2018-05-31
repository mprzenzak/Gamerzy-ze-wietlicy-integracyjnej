package pl.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CreditsMenu {

	private GraphicsContext gc;
	private Image backButton, backButtonSelected, backButtonPressed;

	private int backX, backY;
	
	public CreditsMenu(GraphicsContext gc) {
		this.gc = gc;

		this.backButton = new Image("file:resources\\backButton.png");
		this.backButtonSelected = new Image("file:resources\\backButtonSelected.png");
		this.backButtonPressed = new Image("file:resources\\backButtonPressed.png");
		this.backX = 15;
		this.backY = 15;
		// TODO Auto-generated constructor stub
	}

	public void displayCreditsMenu() {
		// TODO Auto-generated method stub
		
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void getSelectedOption() {
		// TODO Auto-generated method stub
		
	}

}
