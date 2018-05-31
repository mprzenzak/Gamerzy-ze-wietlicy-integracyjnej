package pl.main.values;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class RootPaneAndGcSet {

	private Pane rootPane;
	private GraphicsContext bgGc;
	private GraphicsContext moneyGc;
	private GraphicsContext mainMenuGc;
	private GraphicsContext playMenuGc;
	private GraphicsContext shopMenuGc;
	private GraphicsContext achievementsMenuGc;
	private GraphicsContext highscoresMenuGc;
	private GraphicsContext optionsMenuGc;
	private GraphicsContext creditsMenuGc; 
	private GraphicsContext rootGc;
	
	public RootPaneAndGcSet(Pane rootPane, GraphicsContext bgGc, GraphicsContext moneyGc, GraphicsContext mainMenuGc, GraphicsContext playMenuGc,
			GraphicsContext shopMenuGc, GraphicsContext achievementsMenuGc, GraphicsContext highscoresMenuGc,
			GraphicsContext optionsMenuGc, GraphicsContext creditsMenuGc, GraphicsContext rootGc) {
		this.rootPane = rootPane;
		this.bgGc = bgGc;
		this.moneyGc = moneyGc;
		this.mainMenuGc = mainMenuGc;
		this.playMenuGc = playMenuGc;
		this.shopMenuGc = shopMenuGc;
		this.achievementsMenuGc = achievementsMenuGc;
		this.highscoresMenuGc = highscoresMenuGc;
		this.optionsMenuGc = optionsMenuGc;
		this.creditsMenuGc = creditsMenuGc;
		this.rootGc = rootGc;
	}

	public Pane getRootPane() {
		return rootPane;
	}

	public GraphicsContext getBgGc() {
		return bgGc;
	}
	
	public GraphicsContext getMoneyGc() {
		return moneyGc;
	}

	public GraphicsContext getMainMenuGc() {
		return mainMenuGc;
	}

	public GraphicsContext getPlayMenuGc() {
		return playMenuGc;
	}

	public GraphicsContext getShopMenuGc() {
		return shopMenuGc;
	}

	public GraphicsContext getAchievementsMenuGc() {
		return achievementsMenuGc;
	}

	public GraphicsContext getHighscoresMenuGc() {
		return highscoresMenuGc;
	}

	public GraphicsContext getOptionsMenuGc() {
		return optionsMenuGc;
	}

	public GraphicsContext getCreditsMenuGc() {
		return creditsMenuGc;
	}

	public GraphicsContext getRootGc() {
		return rootGc;
	}
	
	
}
