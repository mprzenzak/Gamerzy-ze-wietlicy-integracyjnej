package pl.main;

import javafx.scene.canvas.GraphicsContext;

public class OptionsMenu {

	private GraphicsContext gc;
	
	private ButtonSelected buttonSelected;
	
	public OptionsMenu(GraphicsContext gc) {
		this.gc = gc;
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
	
}
