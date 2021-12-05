package mines;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class MyController {
	MinesFX m;
	GridPane currentgame = null;

	@FXML
	private Button rButton;

	@FXML
	private TextField width;

	@FXML
	private TextField height;

	@FXML
	private TextField mines;

	@FXML
	private Circle c1;

	@FXML
	private Circle c2;

	@FXML
	private Circle c3;

	@FXML
	private Button play;

	@FXML
	void newGameBoard(ActionEvent event) {//Create a new game board based on user input
		m.won = false;//reset the game terms
		m.lost = false;//reset the game terms
		if (currentgame != null)//"Deleting" old game
			m.hbox.getChildren().remove(currentgame);
		currentgame = m.createGrid(Integer.parseInt(height.getText()), Integer.parseInt(width.getText()),//Creating new grid pane game board
				Integer.parseInt(mines.getText()));
		m.hbox.getChildren().add(currentgame);//add new created game board
		currentgame.setAlignment(Pos.CENTER);//set it to be in center
		m.mStage.sizeToScene();//Resize our scene in order to fit our new game board

		

	}

	@FXML
	void play(ActionEvent event) {//Settings the animation of the circle to play
		setRotate(c1, true, 360, 10);
		setRotate(c2, true, 180, 18);
		setRotate(c3, true, 140, 24);

	}

	public void setMain(MinesFX m) {//Setting our Main pointer so we can change stuff using the controller 
		this.m = m;

	}

	private void setRotate(Circle c, boolean reverse, int angle, int duration) {//Animation for our Circle's
		RotateTransition rt = new RotateTransition(Duration.seconds(duration), c);
		rt.setAutoReverse(reverse);
		rt.setByAngle(angle);
		rt.setDelay(Duration.seconds(0));
		rt.setRate(5);
		rt.setCycleCount(18);
		rt.play();

	}

}
