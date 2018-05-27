package pl.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ShopMenu {

	private GraphicsContext gc;
	private Image back;
	private Image shopBox, shopBox_oneSelected, shopBox_twoSelected, shopBox_threeSelected, shopBox_fourSelected; //and so on, depends on the design
	
	private ButtonSelected buttonSelected;
	
	public ShopMenu(GraphicsContext gc) {
		this.gc = gc;

		// TODO Auto-generated constructor stub
	}
	
	private enum ButtonSelected {
		BACK, SHOP_LIST
	}

	public void displayShopMenu() {
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
