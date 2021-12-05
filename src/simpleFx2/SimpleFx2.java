package simpleFx2;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleFx2 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		VBox vbox;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Screen.fxml"));
			vbox = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		
		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Voting Machine");
	}
}
