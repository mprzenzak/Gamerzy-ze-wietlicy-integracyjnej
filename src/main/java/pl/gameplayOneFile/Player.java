package pl.gameplayOneFile;

import javafx.scene.image.ImageView;

public class Player {

	private int x, y;
	private int height, width;
	private int speed;
	
	private ImageView iv;
		
	public Player(int y, ImageView iv) {
		this.iv = iv;
		
		this.height = (int) iv.getLayoutBounds().getHeight();
		this.width = (int) iv.getLayoutBounds().getWidth();

		this.x = 0 - width;
		this.iv.setTranslateX(this.x);
		this.y = y;
		this.iv.setTranslateY(this.y);
		
		this.speed = 8;
	}

	public boolean enter() {
		if(x < 200) {
			iv.setTranslateX(x);
			x += speed;
			return false;

		} else {
			return true;
		}
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

	public void shoot() {
		
	}
	
	public void getHit() {
		
	}
}
