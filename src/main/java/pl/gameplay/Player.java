package pl.gameplay;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import pl.gameplay.Gameplay.PlayerState;
import pl.main.values.BooleanValue;

public class Player {

	private int x, y;
	private int height, width;
	private int speed;
	private int health;

	private ImageView iv;
	private Pane pane;

	private long timer;
	BooleanValue hasCooldownEnded;

	public Player(Pane pane, int y, Image img) {
		this.pane = pane;

		this.iv = new ImageView(img);
		this.pane.getChildren().add(iv);

		this.height = (int) iv.getLayoutBounds().getHeight();
		this.width = (int) iv.getLayoutBounds().getWidth();

		this.x = 0 - width;
		this.iv.setTranslateX(this.x);
		this.y = y;
		this.iv.setTranslateY(this.y);

		this.speed = 8;
		this.health = 3;
	}

	public boolean enter() {
		if (x < 200) {
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
		if (timer % 20 == 0) {
			Bullet bullet = new Bullet(pane, 0, x + width + 10, (y + height / 2) - 10);
			Gameplay.playerBullets.add(bullet);
		}
		timer++;
	}

	public PlayerState getHit() {
		health--;
		
		hasCooldownEnded = new BooleanValue(false);
		
		if (health <= 0) {
			disappear();
			return PlayerState.DEAD;
			
		} else {
			SequentialTransition transition = transition();
			transition.play();
			
			transition.setOnFinished(e -> hasCooldownEnded.setValue(true));
			
			return PlayerState.HIT;
		}
	}

	private SequentialTransition transition() {
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.001), iv);
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		
		PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
		
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.001), iv);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		
		PauseTransition pause2 = new PauseTransition(Duration.seconds(0.3));
		
		SequentialTransition st = new SequentialTransition(fadeOut, pause, fadeIn, pause2);

		st.setCycleCount(5);
		
		return st;
	}

	public void resetTimer() {
		timer = 0;
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

	
	public boolean getHasCooldownEnded() {
		return hasCooldownEnded.getValue();
	}

	public int getHealth() {
		return health;		
	}
	
	public void disappear() {
		iv.setImage(null);
	}
}
