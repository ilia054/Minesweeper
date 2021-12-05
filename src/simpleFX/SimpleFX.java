package simpleFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleFX extends Application {
	public static void main(String[] args) {
		launch(args);

	}

	private int i = 0;

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(createRoot());
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Voting Machine");

	}

	private Parent createRoot() {
		VBox b = new VBox(15);
		GridPane gridpane = new GridPane();
		gridpane.setGridLinesVisible(false);
		Label lb = new Label("0");
		gridpane.setHgap(10);
		lb.setPrefHeight(40);
		lb.setStyle("-fx-background-color:red;");
		Button b1 = new Button("Ofra Haza");
		Button b2 = new Button("Yardena Arazi");
		class LabelIncreaser implements EventHandler<ActionEvent> {//increase one if button is clicked
			@Override
			public void handle(ActionEvent event) {
				i++;
				lb.setText("" + i);

			}

		}
		class Labeldecreaser implements EventHandler<ActionEvent> {

			@Override
			public void handle(ActionEvent event) {
				i--;
				lb.setText("" + i);

			}

		}
		b1.setOnAction(new LabelIncreaser());
		b2.setOnAction(new Labeldecreaser());
		gridpane.add(b1, 0, 0);
		gridpane.add(b2, 1, 0);
		gridpane.setVgap(10);
		lb.setMaxWidth(Double.MAX_VALUE);
		lb.setAlignment(Pos.CENTER);
		b.getChildren().addAll(gridpane,lb);
		b.setPadding(new Insets(15));

		return b;

	}

}
