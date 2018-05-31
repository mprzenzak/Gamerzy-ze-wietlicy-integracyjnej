package pl.gameplay;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import pl.main.PlayMenu;

//public class Position extends Player{
//
//
//	private final int playerX;
//	private final int playerY;
//	
//	public Position(playerX,playerY) {
//        this.playerX= playerX;
//        this.playerY = playerY;
//	}
//Position(final int playerX, final int playerY) {
//    this.playerX = playerX;
//    this.playerY = playerY;
//}
public class Position extends Player{
    private final int x;    // The X coordinate
    private final int y;    // The Y coordinate

    /**
     * The package-visible constructor. Not meant to be used outside the package.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }



//	Position(int PlayerX, int PlayerY) {
//		this.PlayerX = playerX;
//		this.PlayerY = playerY;
//	}
//	 Position(int PlayerX, int PlayerY) {
//		this.playerX = playerX;
//		this.PlayerY = playerY;
//	}	

	//	PlayerX = playerX;
//	PlayerY = playerY;
    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }
	
	public Position translate(int x,int y) {
		return new Position(playerX, playerY);
	}

	private void update() {
		if (GameLoop.keyPressed() && Player.getTranslateY() >= 0) {
			jumpPlayer();
		}
		if (playerGoDown.getY() < 10) {
			Player.playerGoDown = playerGoDown.add(0, 1);
		}
		movePlayerY((int) playerGoDown.getY());

		if (Player.getTranslateX() <= width) {
			// movePlayerX(5);
			movePlayerRight();
		}

		if (playerGoRight.getX() < 0) {
			playerGoRight = playerGoRight.add(0, 1);
		}
		movePlayerX((int) playerGoRight.getX());
	}

	private void jumpPlayer() {
		if (Player.canJump()) {
			Player.playerGoDown = playerGoDown.add(0, -10);
			//canJump = false;
		}
	}

	private void movePlayerRight() {
		playerGoRight = playerGoRight.add(10, 0);
	}
}