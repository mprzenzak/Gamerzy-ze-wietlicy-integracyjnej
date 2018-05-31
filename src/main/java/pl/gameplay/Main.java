package pl.gameplay;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Pane appRoot = new Pane();

	private static final int width = 1280;
	private static final int height = 720;

	private GameLoop loop;
	private GraphicsContext context;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		Canvas canvas = new Canvas(width,height);
		context = canvas.getGraphicsContext2D();
		
			//canvas.setFocusTraversable(true);
			//canvas.setOnKeyPressed(e ->{
			//Player player = 
			Scene scene = new Scene(root);
			scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
			scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
			primaryStage.setTitle("Jetpack gameplay");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			AnimationTimer timer = new AnimationTimer() {
				@Override
				public void handle(long now) {
					update();
				}
			};
			timer.start();
		});
		
	}

}
