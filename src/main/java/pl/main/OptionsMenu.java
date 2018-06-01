package pl.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class OptionsMenu {

	private GraphicsContext gc;

	private int backX, backY;
	
	private ButtonSelected buttonSelected;
	
	public OptionsMenu(GraphicsContext gc) {
		this.gc = gc;

		this.backX = 15;
		this.backY = 15;
		// TODO Auto-generated constructor stub
	}

	public void displayOptionsMenu() {
		// TODO Auto-generated method stub
		
	}

	public void selectPreviousOption() {
		// TODO Auto-generated method stub
		
	}

	public void selectNextOption() {
		// TODO Auto-generated method stub
		
	}

	private enum ButtonSelected {
		BACK, MUSIC, HACK, CONTROLS_LIST
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void selectLeftOption() {
		// TODO Auto-generated method stub
		
	}

	public void selectRightOption() {
		// TODO Auto-generated method stub
		
	}

	public void getSelectedOption() {
		// TODO Auto-generated method stub
		
	}
	
}
