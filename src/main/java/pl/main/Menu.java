package pl.main;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.lang.AutoCloseable;

import javafx.application.Application;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;

public class Menu extends Application {

	private static final Node ItemExit = null;

	public Menu(MenuItem menuItem, MenuItem menuItem2, MenuItem menuItem3, MenuItem menuItem4, MenuItem menuItem5,
			MenuItem menuItem6, MenuItem menuItem7, MenuItem itemExit2) {
		// TODO Auto-generated constructor stub
	}

	private Parent createContent() {
		Pane root = new Pane();
		root.setPrefSize(720, 480);
	
				
		Title menuTitle = new Title("M A D   J E T P A C K");
		menuTitle.setTranslateX(75);
		menuTitle.setTranslateY(200);

		MenuItem itemExit = new MenuItem("EXIT");
		ItemExit.setOnMouseClicked(event -> System.exit(0));
		
		Menu  menu = new Menu(
				new MenuItem("SINGLEPLAYER"),
				new MenuItem("MULTIPLAYER"),
				new MenuItem("FIRST PLAY TOUTORIAL"),
				new MenuItem("SHOP"),
				new MenuItem("ACHIVEMENTS"),
				new MenuItem("HIGHSCORES"),
				new MenuItem("OPTIONS"),
				itemExit);
		menu.setTranslateX(100);
		menu.setTranslateY(300);
		
		root.getChildren().addAll(menuTitle, menu);
		return root;

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Game menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private static class MenuItem extends StackPane{
		public MenuItem(String name) {
			
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
