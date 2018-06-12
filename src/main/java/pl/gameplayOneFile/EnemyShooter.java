package pl.gameplayOneFile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EnemyShooter extends Enemy {

	public EnemyShooter(int x, int y, Pane pane) {
		this.pane = pane;
		
		this.iv = new ImageView(new Image("file:resources\\enemyshooter.png"));
		this.pane.getChildren().add(this.iv);

		this.x = x;
		this.iv.setTranslateX(this.x);
		this.y = y;
		this.iv.setTranslateY(this.y);

		this.height = (int) iv.getLayoutBounds().getHeight();;
		this.width = (int) iv.getLayoutBounds().getWidth();
		
		this.speed = 2;
		this.timer = 0;
	}
	
	public void shoot() {
		if (x < 1920) {
			Bullet bullet = new Bullet(pane, 180, x-10, (y+height/2)-10);
			Gameplay.bullets.add(bullet);
		}
	}
	
	@Override
	public void move() {
		if (timer % 50 == 0) {
			isMoving = !isMoving;
		}
		
		if (isMoving) {
			x -= speed;
			iv.setTranslateX(x);
			
		} else {
			if (timer % 20 == 0) {
				shoot();
			}
		}
		
		timer++;
	}
	
	public void getHit() {
		
	}
}
