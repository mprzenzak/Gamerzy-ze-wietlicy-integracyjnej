package pl.gameplayOneFile;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EnemiesMap {

	private ArrayList<Enemy> enemies;
		
	public EnemiesMap() {
		this.enemies = new ArrayList<>();
	}

	public void spawnWave(int level, Pane gamePane) {
		// TODO Auto-generated method stub
		switch(level) {
		case 1:
			for (int i = 0, x = 2400, y = 300; i < 5; i++, x += 100, y += 100) {
				enemies.add(new EnemyShooter(x, y, gamePane));
			}
			break;
			
		case 2:
			
			break;
			
		case 3:
	
			break;
			
		case 4:
	
			break;
			
		case 5:
			
		}
	}

	public void move() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).move();
			
			if(enemies.get(i).getRightBorder() < 0) {
				enemies.get(i).disappear();
				enemies.remove(i);
			}
		}
	}

	public boolean checkIfCleared() {
		if (enemies.isEmpty()) {
			return true;
			
		} else {
			return false;
		}
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
}
