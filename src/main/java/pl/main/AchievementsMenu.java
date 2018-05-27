package pl.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AchievementsMenu {
	
	private GraphicsContext gc;
	
	private ButtonSelected buttonSelected;

	public AchievementsMenu(GraphicsContext gc) {
		this.gc = gc;

		// TODO Auto-generated constructor stub
	}
	
	private enum ButtonSelected {
		BACK, ACHIEVEMENTS_LIST
	}

	public void displayAchievementsMenu() {
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
