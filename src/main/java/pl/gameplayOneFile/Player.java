package pl.gameplayOneFile;

import javafx.scene.image.ImageView;

public class Player {

	private int x, y;
	private int height, width;
	private int speed;
	
	private ImageView iv;
		
	public Player(int floorBorder, ImageView iv) {
		this.iv = iv;
		
		this.height = (int) iv.getLayoutBounds().getHeight();
		this.width = (int) iv.getLayoutBounds().getWidth();

		this.x = 0 - width;
		this.iv.setTranslateX(this.x);
		this.y = floorBorder - height;
		this.iv.setTranslateY(this.y);
		
		this.speed = 8;
	}

	public boolean enter(int targetX) {
		iv.setTranslateX(x);
		x += speed;
		
		if(x >= targetX) {
			return true;
		}
		return false;
	}


	public void moveUp(int border) {
		if (y > border) {
			y -= speed;
			iv.setTranslateY(y);
			
		} else {
			y = border;
			iv.setTranslateY(y);
		}
	}

	public void moveDown(int border) {
		if (y + height < border) {
			y += speed;
			iv.setTranslateY(y);
			
		} else {
			y = border - height;
			iv.setTranslateY(y);
		}
	}

	public void moveRight(int border) {
		if (x + width < border) {
			x += speed;
			iv.setTranslateX(x);
			
		} else {
			x = border - width;
			iv.setTranslateX(x);
		}
	}

	public void moveLeft(int border) {
		if (x > border) {
			x -= speed;
			iv.setTranslateX(x);
			
		} else {
			x = border;
			iv.setTranslateX(x);
		}
	}

}
