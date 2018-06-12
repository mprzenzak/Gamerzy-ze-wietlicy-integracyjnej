package pl.gameplayOneFile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bullet {

	private int x, y;
	private ImageView iv;
	private Pane pane;
	private int height, width;
	private int speed;
	private double angle;
	private double directionX, directionY;

	public Bullet(Pane pane, int degrees, int x, int y) {
		this.pane = pane;
		
		this.iv = new ImageView(new Image("file:resources\\bullet.png"));
		this.pane.getChildren().add(this.iv);

		this.x = x;
		this.iv.setTranslateX(this.x);
		this.y = y;
		this.iv.setTranslateY(this.y);

		this.height = (int) iv.getLayoutBounds().getHeight();
		this.width = (int) iv.getLayoutBounds().getWidth();
		
		this.speed = 7;
		this.angle = Math.toRadians(degrees);
	}

	public void move() {
		directionY = speed * Math.sin(angle);
		directionX = speed * Math.cos(angle); 
		
		x += directionX;
		y += directionY;
		iv.setTranslateX(x);
		iv.setTranslateY(y);
	}
	
	public void disappear() {
		iv.setImage(null);
	}

	public int getRightBorder() {
		return x + width;
	}

	public int getLeftBorder() {
		return x;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
}
