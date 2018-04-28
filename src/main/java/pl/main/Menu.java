package pl.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Menu extends Application {

	private BorderPane layout;
	private Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		layout = new BorderPane();
		scene = new Scene(layout, 720, 480);

		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #F9A825;");

		Button buttonStart = new Button("START GAME");
		buttonStart.setPrefSize(100, 20);
		buttonStart.setStyle("-fx-background-color: #E65100;");

		Button buttonExit = new Button("EXIT");
		buttonExit.setPrefSize(100, 20);
		buttonExit.setStyle("-fx-background-color: #E65100;");

		buttonExit.setOnMouseClicked(event -> System.exit(0));
		hbox.getChildren().addAll(buttonStart, buttonExit);

		layout.setCenter(hbox);

		window.setScene(scene);
		window.show();
	}
}
